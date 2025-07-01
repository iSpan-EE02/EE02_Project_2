/**
 * API 客戶端
 * 統一管理所有API請求，包含攔截器、錯誤處理、重試機制等
 */

import { APP_CONFIG, ERROR_MESSAGES, EVENTS } from '../constants/app-config.js';
import { debounce } from '../utils/helpers.js';

// =================================
// API 客戶端類別
// =================================
class ApiClient {
  constructor() {
    this.baseURL = APP_CONFIG.api.baseURL;
    this.timeout = APP_CONFIG.api.timeout;
    this.retryCount = APP_CONFIG.api.retryCount;
    this.retryDelay = APP_CONFIG.api.retryDelay;
    
    // 請求攔截器配置
    this.requestInterceptors = [];
    this.responseInterceptors = [];
    
    // 設置預設攔截器
    this.setupDefaultInterceptors();
  }

  /**
   * 設置預設攔截器
   */
  setupDefaultInterceptors() {
    // 請求攔截器 - 添加認證資訊
    this.addRequestInterceptor((config) => {
      const token = localStorage.getItem('authToken');
      if (token) {
        config.headers = {
          ...config.headers,
          'Authorization': `Bearer ${token}`
        };
      }
      return config;
    });

    // 回應攔截器 - 處理通用錯誤
    this.addResponseInterceptor(
      (response) => response,
      (error) => {
        if (error.status === 401) {
          // 處理認證失敗
          this.handleAuthError();
        }
        return Promise.reject(error);
      }
    );
  }

  /**
   * 添加請求攔截器
   * @param {Function} fulfilled - 成功處理函數
   * @param {Function} rejected - 錯誤處理函數
   */
  addRequestInterceptor(fulfilled, rejected) {
    this.requestInterceptors.push({ fulfilled, rejected });
  }

  /**
   * 添加回應攔截器
   * @param {Function} fulfilled - 成功處理函數
   * @param {Function} rejected - 錯誤處理函數
   */
  addResponseInterceptor(fulfilled, rejected) {
    this.responseInterceptors.push({ fulfilled, rejected });
  }

  /**
   * 處理認證錯誤
   */
  handleAuthError() {
    localStorage.removeItem('authToken');
    window.dispatchEvent(new CustomEvent(EVENTS.SESSION_EXPIRED));
    window.location.href = '/login.html';
  }

  /**
   * 發送HTTP請求
   * @param {string} url - 請求URL
   * @param {Object} options - 請求選項
   * @returns {Promise}
   */
  async request(url, options = {}) {
    const config = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        ...options.headers
      },
      timeout: this.timeout,
      ...options
    };

    // 應用請求攔截器
    for (const interceptor of this.requestInterceptors) {
      if (interceptor.fulfilled) {
        try {
          await interceptor.fulfilled(config);
        } catch (error) {
          if (interceptor.rejected) {
            interceptor.rejected(error);
          }
          throw error;
        }
      }
    }

    const fullUrl = url.startsWith('http') ? url : `${this.baseURL}${url}`;
    
    return this.requestWithRetry(fullUrl, config);
  }

  /**
   * 帶重試機制的請求
   * @param {string} url - 請求URL
   * @param {Object} config - 請求配置
   * @param {number} retryCount - 重試次數
   * @returns {Promise}
   */
  async requestWithRetry(url, config, retryCount = this.retryCount) {
    try {
      const controller = new AbortController();
      const timeoutId = setTimeout(() => controller.abort(), config.timeout);

      const response = await fetch(url, {
        ...config,
        signal: controller.signal
      });

      clearTimeout(timeoutId);

      if (!response.ok) {
        throw new Error(`HTTP ${response.status}: ${response.statusText}`);
      }

      let data;
      const contentType = response.headers.get('content-type');
      
      if (contentType && contentType.includes('application/json')) {
        data = await response.json();
      } else {
        data = await response.text();
      }

      // 應用回應攔截器
      for (const interceptor of this.responseInterceptors) {
        if (interceptor.fulfilled) {
          data = await interceptor.fulfilled(data);
        }
      }

      return data;

    } catch (error) {
      if (retryCount > 0 && this.shouldRetry(error)) {
        await this.delay(this.retryDelay);
        return this.requestWithRetry(url, config, retryCount - 1);
      }

      // 應用錯誤攔截器
      for (const interceptor of this.responseInterceptors) {
        if (interceptor.rejected) {
          interceptor.rejected(error);
        }
      }

      throw this.normalizeError(error);
    }
  }

  /**
   * 判斷是否應該重試
   * @param {Error} error - 錯誤對象
   * @returns {boolean}
   */
  shouldRetry(error) {
    return (
      error.name === 'AbortError' ||
      error.message.includes('NetworkError') ||
      error.message.includes('TimeoutError') ||
      (error.status >= 500 && error.status < 600)
    );
  }

  /**
   * 延遲函數
   * @param {number} ms - 延遲毫秒數
   * @returns {Promise}
   */
  delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  /**
   * 標準化錯誤
   * @param {Error} error - 錯誤對象
   * @returns {Error}
   */
  normalizeError(error) {
    if (error.name === 'AbortError') {
      error.message = ERROR_MESSAGES.TIMEOUT_ERROR;
    } else if (error.message.includes('NetworkError')) {
      error.message = ERROR_MESSAGES.NETWORK_ERROR;
    } else if (error.status >= 500) {
      error.message = ERROR_MESSAGES.SERVER_ERROR;
    }
    
    return error;
  }

  // HTTP 方法簡化函數
  get(url, options = {}) {
    return this.request(url, { ...options, method: 'GET' });
  }

  post(url, data, options = {}) {
    return this.request(url, {
      ...options,
      method: 'POST',
      body: JSON.stringify(data)
    });
  }

  put(url, data, options = {}) {
    return this.request(url, {
      ...options,
      method: 'PUT',
      body: JSON.stringify(data)
    });
  }

  delete(url, options = {}) {
    return this.request(url, { ...options, method: 'DELETE' });
  }

  patch(url, data, options = {}) {
    return this.request(url, {
      ...options,
      method: 'PATCH',
      body: JSON.stringify(data)
    });
  }

  /**
   * 上傳檔案
   * @param {string} url - 上傳URL
   * @param {FormData} formData - 表單資料
   * @param {Function} onProgress - 進度回調
   * @returns {Promise}
   */
  upload(url, formData, onProgress) {
    return new Promise((resolve, reject) => {
      const xhr = new XMLHttpRequest();
      
      xhr.upload.addEventListener('progress', (event) => {
        if (event.lengthComputable && onProgress) {
          const percentComplete = (event.loaded / event.total) * 100;
          onProgress(percentComplete);
        }
      });

      xhr.addEventListener('load', () => {
        if (xhr.status >= 200 && xhr.status < 300) {
          try {
            const response = JSON.parse(xhr.responseText);
            resolve(response);
          } catch (error) {
            resolve(xhr.responseText);
          }
        } else {
          reject(new Error(`上傳失敗: ${xhr.status}`));
        }
      });

      xhr.addEventListener('error', () => {
        reject(new Error('上傳失敗'));
      });

      xhr.open('POST', url.startsWith('http') ? url : `${this.baseURL}${url}`);
      
      // 添加認證標頭
      const token = localStorage.getItem('authToken');
      if (token) {
        xhr.setRequestHeader('Authorization', `Bearer ${token}`);
      }

      xhr.send(formData);
    });
  }
}

// 創建全域API客戶端實例
export const apiClient = new ApiClient();

// =================================
// 商品相關API
// =================================
export const productApi = {
  // 獲取商品列表
  getProducts: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/products?${queryString}`);
  },

  // 獲取單個商品
  getProduct: (id) => {
    return apiClient.get(`/products/${id}`);
  },

  // 創建商品
  createProduct: (data) => {
    return apiClient.post('/products', data);
  },

  // 更新商品
  updateProduct: (id, data) => {
    return apiClient.put(`/products/${id}`, data);
  },

  // 刪除商品
  deleteProduct: (id) => {
    return apiClient.delete(`/products/${id}`);
  },

  // 獲取商品圖片
  getProductImages: (productId) => {
    return apiClient.get(`/products/${productId}/images`);
  },

  // 上傳商品圖片
  uploadProductImage: (productId, file, onProgress) => {
    const formData = new FormData();
    formData.append('image', file);
    return apiClient.upload(`/products/${productId}/images`, formData, onProgress);
  },

  // 刪除商品圖片
  deleteProductImage: (productId, imageId) => {
    return apiClient.delete(`/products/${productId}/images/${imageId}`);
  }
};

// =================================
// 分類相關API
// =================================
export const categoryApi = {
  // 獲取分類列表
  getCategories: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/categories?${queryString}`);
  },

  // 獲取單個分類
  getCategory: (id) => {
    return apiClient.get(`/categories/${id}`);
  },

  // 創建分類
  createCategory: (data) => {
    return apiClient.post('/categories', data);
  },

  // 更新分類
  updateCategory: (id, data) => {
    return apiClient.put(`/categories/${id}`, data);
  },

  // 刪除分類
  deleteCategory: (id) => {
    return apiClient.delete(`/categories/${id}`);
  }
};

// =================================
// 訂單相關API
// =================================
export const orderApi = {
  // 獲取訂單列表
  getOrders: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/orders?${queryString}`);
  },

  // 獲取單個訂單
  getOrder: (id) => {
    return apiClient.get(`/orders/${id}`);
  },

  // 更新訂單狀態
  updateOrderStatus: (id, status) => {
    return apiClient.patch(`/orders/${id}/status`, { status });
  },

  // 獲取訂單項目
  getOrderItems: (orderId) => {
    return apiClient.get(`/orders/${orderId}/items`);
  }
};

// =================================
// 用戶相關API
// =================================
export const userApi = {
  // 獲取用戶列表
  getUsers: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/users?${queryString}`);
  },

  // 獲取單個用戶
  getUser: (id) => {
    return apiClient.get(`/users/${id}`);
  },

  // 創建用戶
  createUser: (data) => {
    return apiClient.post('/users', data);
  },

  // 更新用戶
  updateUser: (id, data) => {
    return apiClient.put(`/users/${id}`, data);
  },

  // 刪除用戶
  deleteUser: (id) => {
    return apiClient.delete(`/users/${id}`);
  }
};

// =================================
// 儀表板相關API
// =================================
export const dashboardApi = {
  // 獲取儀表板統計
  getStats: () => {
    return apiClient.get('/dashboard/stats');
  },

  // 獲取最新活動
  getRecentActivities: (limit = 10) => {
    return apiClient.get(`/dashboard/activities?limit=${limit}`);
  },

  // 獲取銷售圖表數據
  getSalesChart: (period = '30d') => {
    return apiClient.get(`/dashboard/sales-chart?period=${period}`);
  }
};

// =================================
// 促銷相關API
// =================================
export const promotionApi = {
  // 獲取促銷列表
  getPromotions: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/promotions?${queryString}`);
  },

  // 獲取單個促銷
  getPromotion: (id) => {
    return apiClient.get(`/promotions/${id}`);
  },

  // 創建促銷
  createPromotion: (data) => {
    return apiClient.post('/promotions', data);
  },

  // 更新促銷
  updatePromotion: (id, data) => {
    return apiClient.put(`/promotions/${id}`, data);
  },

  // 刪除促銷
  deletePromotion: (id) => {
    return apiClient.delete(`/promotions/${id}`);
  }
};

// =================================
// 募資相關API
// =================================
export const crowdfundApi = {
  // 獲取募資專案列表
  getCampaigns: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/crowdfund/campaigns?${queryString}`);
  },

  // 獲取單個募資專案
  getCampaign: (id) => {
    return apiClient.get(`/crowdfund/campaigns/${id}`);
  },

  // 創建募資專案
  createCampaign: (data) => {
    return apiClient.post('/crowdfund/campaigns', data);
  },

  // 更新募資專案
  updateCampaign: (id, data) => {
    return apiClient.put(`/crowdfund/campaigns/${id}`, data);
  },

  // 刪除募資專案
  deleteCampaign: (id) => {
    return apiClient.delete(`/crowdfund/campaigns/${id}`);
  }
};

// =================================
// 物流相關API
// =================================
export const logisticsApi = {
  // 獲取物流記錄
  getLogistics: (params = {}) => {
    const queryString = new URLSearchParams(params).toString();
    return apiClient.get(`/logistics?${queryString}`);
  },

  // 獲取單個物流記錄
  getLogistic: (id) => {
    return apiClient.get(`/logistics/${id}`);
  },

  // 創建物流記錄
  createLogistic: (data) => {
    return apiClient.post('/logistics', data);
  },

  // 更新物流記錄
  updateLogistic: (id, data) => {
    return apiClient.put(`/logistics/${id}`, data);
  },

  // 追蹤物流狀態
  trackShipment: (trackingNumber) => {
    return apiClient.get(`/logistics/track/${trackingNumber}`);
  }
};

// =================================
// 檔案上傳相關API（通用）
// =================================
export const fileApi = {
  // 上傳檔案
  uploadFile: (file, path = 'general', onProgress) => {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('path', path);
    return apiClient.upload('/files/upload', formData, onProgress);
  },

  // 刪除檔案
  deleteFile: (fileId) => {
    return apiClient.delete(`/files/${fileId}`);
  }
};

// 向後兼容的匯出（保持與原始代碼的兼容性）
export const fetchCategoriesProd = categoryApi.getCategories;
export const fetchProducts = productApi.getProducts;
export const saveProduct = productApi.createProduct;
export const fetchImage = productApi.getProductImages;
export const fetchSku = (productId) => productApi.getProduct(productId);

export default apiClient; 