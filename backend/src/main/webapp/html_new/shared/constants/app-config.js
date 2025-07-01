/**
 * Application Configuration
 * 應用程式配置檔案
 * Author: 瘋購物團隊
 */

/**
 * 應用程式配置檔案
 * 統一管理所有配置選項、路由設定和常數定義
 */

// =================================
// 應用程式基本配置
// =================================
export const APP_CONFIG = {
  name: '瘋購物後台管理系統',
  version: '2.0.0',
  description: '現代化的電商後台管理系統',
  
  // API 配置
  api: {
    baseURL: '/api/v1',
    timeout: 10000,
    retryCount: 3,
    retryDelay: 1000
  },
  
  // UI 配置
  ui: {
    sidebarWidth: 280,
    headerHeight: 70,
    animationDuration: 200,
    notificationDuration: 5000
  },
  
  // 分頁配置
  pagination: {
    defaultPageSize: 10,
    pageSizeOptions: [5, 10, 20, 50, 100],
    maxVisiblePages: 5
  },
  
  // 上傳配置
  upload: {
    maxFileSize: 5 * 1024 * 1024, // 5MB
    allowedImageTypes: ['image/jpeg', 'image/png', 'image/gif', 'image/webp'],
    maxImagesPerProduct: 5
  },

  // 本地儲存鍵值
  storage: {
    userPreferences: 'crazy_shop_user_prefs',
    authToken: 'crazy_shop_auth_token',
    cartData: 'crazy_shop_cart',
    recentSearches: 'crazy_shop_recent_searches',
    draftData: 'crazy_shop_drafts'
  },

  // 功能開關
  features: {
    darkMode: true,
    notifications: true,
    autoSave: true,
    keyboardShortcuts: true,
    analytics: false,
    exportData: true,
    bulkOperations: true
  }
};

// =================================
// 路由配置
// =================================
export const ROUTES = {
  // 主要頁面
  dashboard: {
    path: 'modules/dashboard/dashboard.html',
    title: '儀表板',
    icon: 'bi-speedometer2',
    module: 'modules/dashboard/dashboard.js'
  },
  
  // 商品管理
  products: {
    path: 'modules/products/products.html',
    title: '商品管理',
    icon: 'bi-box-seam',
    module: 'modules/products/products.js'
  },
  
  categories: {
    path: 'modules/products/categories.html',
    title: '商品分類管理',
    icon: 'bi-diagram-3',
    module: 'modules/products/categories.js'
  },
  
  // 訂單管理
  orders: {
    path: 'modules/orders/orders.html',
    title: '訂單管理',
    icon: 'bi-clipboard',
    module: 'modules/orders/orders.js'
  },
  
  // 用戶管理
  users: {
    path: 'modules/users/users.html',
    title: '帳號管理',
    icon: 'bi-people',
    module: 'modules/users/users.js'
  },
  
  // 促銷管理
  promotions: {
    path: 'modules/promotions/promotions.html',
    title: '活動管理',
    icon: 'bi-calendar-day',
    module: 'modules/promotions/promotions.js'
  },
  
  // 募資管理
  crowdfund: {
    path: 'modules/crowdfund/crowdfund.html',
    title: '募資管理',
    icon: 'bi-currency-dollar',
    module: 'modules/crowdfund/crowdfund.js'
  },
  
  // 物流管理
  logistics: {
    path: 'modules/logistics/logistics.html',
    title: '物流管理',
    icon: 'bi-truck-front',
    module: 'modules/logistics/logistics.js'
  }
};

// =================================
// 應用程式狀態常數
// =================================
export const APP_STATES = {
  LOADING: 'loading',
  READY: 'ready',
  ERROR: 'error'
};

// =================================
// 事件名稱常數
// =================================
export const EVENTS = {
  // 路由事件
  ROUTE_CHANGE: 'route:change',
  ROUTE_BEFORE_CHANGE: 'route:beforeChange',
  ROUTE_AFTER_CHANGE: 'route:afterChange',
  
  // 數據事件
  DATA_LOADED: 'data:loaded',
  DATA_ERROR: 'data:error',
  DATA_UPDATED: 'data:updated',
  
  // UI 事件
  SIDEBAR_TOGGLE: 'ui:sidebarToggle',
  NOTIFICATION_SHOW: 'ui:notificationShow',
  MODAL_OPEN: 'ui:modalOpen',
  MODAL_CLOSE: 'ui:modalClose',
  
  // 商品事件
  PRODUCT_UPDATED: 'product:updated',
  PRODUCT_DELETED: 'product:deleted',
  CATEGORY_SELECTED: 'category:selected',
  
  // 分頁事件
  PAGE_CHANGED: 'pagination:pageChanged',
  PAGE_SIZE_CHANGED: 'pagination:pageSizeChanged'
};

// =================================
// 商品狀態常數
// =================================
export const PRODUCT_STATUS = {
  DRAFT: { value: 0, label: '未上架', class: 'status-inactive', icon: 'bi-circle' },
  ACTIVE: { value: 1, label: '上架中', class: 'status-active', icon: 'bi-check-circle' },
  DISCONTINUED: { value: 2, label: '已下架', class: 'status-discontinued', icon: 'bi-x-circle' }
};

// =================================
// 訂單狀態常數
// =================================
export const ORDER_STATUS = {
  PENDING: { value: 'pending', label: '待處理', class: 'order-status pending' },
  PROCESSING: { value: 'processing', label: '處理中', class: 'order-status processing' },
  SHIPPED: { value: 'shipped', label: '已出貨', class: 'order-status shipped' },
  DELIVERED: { value: 'delivered', label: '已送達', class: 'order-status delivered' },
  CANCELLED: { value: 'cancelled', label: '已取消', class: 'order-status cancelled' }
};

// =================================
// 表單驗證規則
// =================================
export const VALIDATION_RULES = {
  // 商品驗證
  product: {
    name: {
      required: true,
      minLength: 2,
      maxLength: 100,
      message: '商品名稱長度必須在2-100字之間'
    },
    description: {
      required: true,
      minLength: 10,
      maxLength: 1000,
      message: '商品描述長度必須在10-1000字之間'
    },
    price: {
      required: true,
      min: 0,
      type: 'number',
      message: '價格必須大於等於0'
    }
  },
  
  // 分類驗證
  category: {
    name: {
      required: true,
      minLength: 2,
      maxLength: 50,
      message: '分類名稱長度必須在2-50字之間'
    }
  },
  
  // 用戶驗證
  user: {
    email: {
      required: true,
      pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
      message: '請輸入有效的電子郵件地址'
    },
    password: {
      required: true,
      minLength: 8,
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/,
      message: '密碼必須至少8位，包含大小寫字母和數字'
    }
  }
};

// =================================
// 錯誤訊息
// =================================
export const ERROR_MESSAGES = {
  // 網路錯誤
  NETWORK_ERROR: '網路連線錯誤，請檢查您的網路設定',
  TIMEOUT_ERROR: '請求超時，請稍後再試',
  SERVER_ERROR: '伺服器錯誤，請聯繫系統管理員',
  
  // 認證錯誤
  UNAUTHORIZED: '您沒有權限執行此操作',
  SESSION_EXPIRED: '登入已過期，請重新登入',
  
  // 數據錯誤
  DATA_NOT_FOUND: '找不到相關資料',
  INVALID_DATA: '資料格式不正確',
  
  // 操作錯誤
  OPERATION_FAILED: '操作失敗，請稍後再試',
  FILE_TOO_LARGE: '檔案大小超過限制',
  INVALID_FILE_TYPE: '不支援的檔案類型',
  
  // 表單錯誤
  REQUIRED_FIELD: '此欄位為必填',
  INVALID_FORMAT: '格式不正確',
  VALUE_TOO_SHORT: '輸入值太短',
  VALUE_TOO_LONG: '輸入值太長'
};

// =================================
// 成功訊息
// =================================
export const SUCCESS_MESSAGES = {
  SAVE_SUCCESS: '儲存成功',
  UPDATE_SUCCESS: '更新成功',
  DELETE_SUCCESS: '刪除成功',
  UPLOAD_SUCCESS: '上傳成功',
  OPERATION_SUCCESS: '操作成功'
};

// =================================
// 本地存儲鍵名
// =================================
export const STORAGE_KEYS = {
  USER_PREFERENCES: 'app_user_preferences',
  SIDEBAR_STATE: 'app_sidebar_state',
  CURRENT_ROUTE: 'app_current_route',
  PAGE_SIZE: 'app_page_size',
  THEME: 'app_theme'
};

// =================================
// 輔助函數
// =================================
export const getRouteByPath = (path) => {
  return Object.values(ROUTES).find(route => route.path.includes(path));
};

export const getProductStatusInfo = (statusValue) => {
  return Object.values(PRODUCT_STATUS).find(status => status.value === statusValue) || PRODUCT_STATUS.DRAFT;
};

export const getOrderStatusInfo = (statusValue) => {
  return Object.values(ORDER_STATUS).find(status => status.value === statusValue) || ORDER_STATUS.PENDING;
};

export const validateField = (value, rules) => {
  const errors = [];
  
  if (rules.required && (!value || value.toString().trim() === '')) {
    errors.push(ERROR_MESSAGES.REQUIRED_FIELD);
    return errors;
  }
  
  if (value && rules.minLength && value.toString().length < rules.minLength) {
    errors.push(ERROR_MESSAGES.VALUE_TOO_SHORT);
  }
  
  if (value && rules.maxLength && value.toString().length > rules.maxLength) {
    errors.push(ERROR_MESSAGES.VALUE_TOO_LONG);
  }
  
  if (value && rules.pattern && !rules.pattern.test(value.toString())) {
    errors.push(rules.message || ERROR_MESSAGES.INVALID_FORMAT);
  }
  
  if (rules.type === 'number') {
    const numValue = parseFloat(value);
    if (isNaN(numValue)) {
      errors.push('必須為數字');
    } else {
      if (rules.min !== undefined && numValue < rules.min) {
        errors.push(`數值不能小於 ${rules.min}`);
      }
      if (rules.max !== undefined && numValue > rules.max) {
        errors.push(`數值不能大於 ${rules.max}`);
      }
    }
  }
  
  return errors;
};

// ===== 主題配置 =====
export const THEMES = {
  light: {
    name: '明亮主題',
    cssClass: 'theme-light'
  },
  dark: {
    name: '深色主題',
    cssClass: 'theme-dark'
  },
  auto: {
    name: '自動切換',
    cssClass: 'theme-auto'
  }
};

// 預設匯出配置
export default APP_CONFIG; 