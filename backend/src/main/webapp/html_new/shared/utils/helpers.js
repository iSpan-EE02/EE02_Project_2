/**
 * Helper Utilities
 * 工具函數庫
 * Author: 瘋購物團隊
 */

import { APP_CONFIG, ERROR_MESSAGES, VALIDATION } from '../constants/app-config.js';

// ===== DOM 操作工具 =====

/**
 * 安全的查詢選擇器
 * @param {string} selector - CSS選擇器
 * @param {Element} parent - 父元素，預設為document
 * @returns {Element|null}
 */
export function $(selector, parent = document) {
  return parent.querySelector(selector);
}

/**
 * 安全的多元素查詢選擇器
 * @param {string} selector - CSS選擇器
 * @param {Element} parent - 父元素，預設為document
 * @returns {NodeList}
 */
export function $$(selector, parent = document) {
  return parent.querySelectorAll(selector);
}

/**
 * 創建DOM元素
 * @param {string} tag - 標籤名
 * @param {Object} attributes - 屬性物件
 * @param {string|Element} content - 內容
 * @returns {Element}
 */
export function createElement(tag, attributes = {}, content = '') {
  const element = document.createElement(tag);
  
  Object.entries(attributes).forEach(([key, value]) => {
    if (key === 'className') {
      element.className = value;
    } else if (key === 'dataset') {
      Object.entries(value).forEach(([dataKey, dataValue]) => {
        element.dataset[dataKey] = dataValue;
      });
    } else {
      element.setAttribute(key, value);
    }
  });

  if (typeof content === 'string') {
    element.innerHTML = content;
  } else if (content instanceof Element) {
    element.appendChild(content);
  }

  return element;
}

/**
 * 添加事件監聽器（支援事件委託）
 * @param {Element|string} target - 目標元素或選擇器
 * @param {string} event - 事件名稱
 * @param {Function} handler - 事件處理函數
 * @param {boolean|Object} options - 選項
 */
export function addEventListeners(target, event, handler, options = false) {
  if (typeof target === 'string') {
    target = $(target);
  }
  
  if (target) {
    target.addEventListener(event, handler, options);
  }
}

// ===== 字串處理工具 =====

/**
 * 截斷文字
 * @param {string} text - 原始文字
 * @param {number} length - 最大長度
 * @param {string} suffix - 後綴
 * @returns {string}
 */
export function truncateText(text, length = 50, suffix = '...') {
  if (!text || text.length <= length) return text;
  return text.substring(0, length) + suffix;
}

/**
 * 轉換為駝峰命名
 * @param {string} str - 原始字串
 * @returns {string}
 */
export function toCamelCase(str) {
  return str.replace(/-([a-z])/g, (g) => g[1].toUpperCase());
}

/**
 * 轉換為短橫線命名
 * @param {string} str - 原始字串
 * @returns {string}
 */
export function toKebabCase(str) {
  return str.replace(/([A-Z])/g, '-$1').toLowerCase();
}

/**
 * 首字母大寫
 * @param {string} str - 原始字串
 * @returns {string}
 */
export function capitalize(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}

// ===== 數字處理工具 =====

/**
 * 格式化數字（千分位）
 * @param {number} num - 數字
 * @param {number} decimals - 小數位數
 * @returns {string}
 */
export function formatNumber(num, decimals = 0) {
  return new Intl.NumberFormat('zh-TW', {
    minimumFractionDigits: decimals,
    maximumFractionDigits: decimals
  }).format(num);
}

/**
 * 格式化貨幣
 * @param {number} amount - 金額
 * @param {string} currency - 貨幣代碼
 * @returns {string}
 */
export function formatCurrency(amount, currency = 'TWD') {
  return new Intl.NumberFormat('zh-TW', {
    style: 'currency',
    currency: currency
  }).format(amount);
}

/**
 * 產生隨機數字
 * @param {number} min - 最小值
 * @param {number} max - 最大值
 * @returns {number}
 */
export function randomNumber(min = 0, max = 100) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// ===== 日期處理工具 =====

/**
 * 格式化日期
 * @param {Date|string} date - 日期
 * @param {string} format - 格式
 * @returns {string}
 */
export function formatDate(date, format = 'YYYY-MM-DD') {
  const d = new Date(date);
  if (isNaN(d.getTime())) return '';

  const formatMap = {
    YYYY: d.getFullYear(),
    MM: String(d.getMonth() + 1).padStart(2, '0'),
    DD: String(d.getDate()).padStart(2, '0'),
    HH: String(d.getHours()).padStart(2, '0'),
    mm: String(d.getMinutes()).padStart(2, '0'),
    ss: String(d.getSeconds()).padStart(2, '0')
  };

  return format.replace(/YYYY|MM|DD|HH|mm|ss/g, match => formatMap[match]);
}

/**
 * 取得相對時間描述
 * @param {Date|string} date - 日期
 * @returns {string}
 */
export function getRelativeTime(date) {
  const now = new Date();
  const target = new Date(date);
  const diffInSeconds = Math.floor((now - target) / 1000);

  if (diffInSeconds < 60) return '剛剛';
  if (diffInSeconds < 3600) return `${Math.floor(diffInSeconds / 60)} 分鐘前`;
  if (diffInSeconds < 86400) return `${Math.floor(diffInSeconds / 3600)} 小時前`;
  if (diffInSeconds < 2592000) return `${Math.floor(diffInSeconds / 86400)} 天前`;
  
  return formatDate(target);
}

// ===== 陣列處理工具 =====

/**
 * 陣列去重
 * @param {Array} arr - 原始陣列
 * @param {string} key - 物件的key（可選）
 * @returns {Array}
 */
export function uniqueArray(arr, key = null) {
  if (!key) return [...new Set(arr)];
  
  const seen = new Set();
  return arr.filter(item => {
    const value = item[key];
    if (seen.has(value)) return false;
    seen.add(value);
    return true;
  });
}

/**
 * 陣列分組
 * @param {Array} arr - 原始陣列
 * @param {string|Function} keyFn - 分組鍵或函數
 * @returns {Object}
 */
export function groupBy(arr, keyFn) {
  const getKey = typeof keyFn === 'function' ? keyFn : item => item[keyFn];
  
  return arr.reduce((groups, item) => {
    const key = getKey(item);
    groups[key] = groups[key] || [];
    groups[key].push(item);
    return groups;
  }, {});
}

/**
 * 陣列排序
 * @param {Array} arr - 原始陣列
 * @param {string} key - 排序鍵
 * @param {string} order - 排序方向 ('asc' | 'desc')
 * @returns {Array}
 */
export function sortArray(arr, key, order = 'asc') {
  return [...arr].sort((a, b) => {
    const aVal = a[key];
    const bVal = b[key];
    
    if (aVal < bVal) return order === 'asc' ? -1 : 1;
    if (aVal > bVal) return order === 'asc' ? 1 : -1;
    return 0;
  });
}

// ===== 物件處理工具 =====

/**
 * 深拷貝物件
 * @param {any} obj - 原始物件
 * @returns {any}
 */
export function deepClone(obj) {
  if (obj === null || typeof obj !== 'object') return obj;
  if (obj instanceof Date) return new Date(obj.getTime());
  if (obj instanceof Array) return obj.map(item => deepClone(item));
  
  const clonedObj = {};
  Object.keys(obj).forEach(key => {
    clonedObj[key] = deepClone(obj[key]);
  });
  
  return clonedObj;
}

/**
 * 合併物件
 * @param {Object} target - 目標物件
 * @param {...Object} sources - 來源物件
 * @returns {Object}
 */
export function mergeObjects(target, ...sources) {
  return Object.assign({}, target, ...sources);
}

/**
 * 檢查物件是否為空
 * @param {Object} obj - 物件
 * @returns {boolean}
 */
export function isEmpty(obj) {
  if (obj == null) return true;
  if (Array.isArray(obj) || typeof obj === 'string') return obj.length === 0;
  return Object.keys(obj).length === 0;
}

// ===== 驗證工具 =====

/**
 * 驗證表單欄位
 * @param {string} value - 欄位值
 * @param {Object} rules - 驗證規則
 * @returns {Object} 驗證結果
 */
export function validateField(value, rules) {
  const errors = [];

  if (rules.required && (!value || value.trim() === '')) {
    errors.push(ERROR_MESSAGES.validation.required);
  }

  if (value && rules.minLength && value.length < rules.minLength) {
    errors.push(ERROR_MESSAGES.validation.minLength.replace('{min}', rules.minLength));
  }

  if (value && rules.maxLength && value.length > rules.maxLength) {
    errors.push(ERROR_MESSAGES.validation.maxLength.replace('{max}', rules.maxLength));
  }

  if (value && rules.pattern && !rules.pattern.test(value)) {
    errors.push(ERROR_MESSAGES.validation.pattern);
  }

  if (value && rules.min && Number(value) < rules.min) {
    errors.push(ERROR_MESSAGES.validation.min.replace('{min}', rules.min));
  }

  if (value && rules.max && Number(value) > rules.max) {
    errors.push(ERROR_MESSAGES.validation.max.replace('{max}', rules.max));
  }

  return {
    isValid: errors.length === 0,
    errors
  };
}

/**
 * 驗證表單
 * @param {Object} data - 表單資料
 * @param {Object} validationRules - 驗證規則
 * @returns {Object} 驗證結果
 */
export function validateForm(data, validationRules) {
  const errors = {};
  let isValid = true;

  Object.keys(validationRules).forEach(field => {
    const result = validateField(data[field], validationRules[field]);
    if (!result.isValid) {
      errors[field] = result.errors;
      isValid = false;
    }
  });

  return { isValid, errors };
}

// ===== 本地儲存工具 =====

/**
 * 儲存到本地儲存
 * @param {string} key - 鍵
 * @param {any} value - 值
 */
export function setStorage(key, value) {
  try {
    localStorage.setItem(key, JSON.stringify(value));
  } catch (error) {
    console.error('儲存到本地儲存失敗:', error);
  }
}

/**
 * 從本地儲存讀取
 * @param {string} key - 鍵
 * @param {any} defaultValue - 預設值
 * @returns {any}
 */
export function getStorage(key, defaultValue = null) {
  try {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : defaultValue;
  } catch (error) {
    console.error('從本地儲存讀取失敗:', error);
    return defaultValue;
  }
}

/**
 * 從本地儲存移除
 * @param {string} key - 鍵
 */
export function removeStorage(key) {
  try {
    localStorage.removeItem(key);
  } catch (error) {
    console.error('從本地儲存移除失敗:', error);
  }
}

// ===== 工具函數 =====

/**
 * 防抖函數
 * @param {Function} func - 原函數
 * @param {number} delay - 延遲時間
 * @returns {Function}
 */
export function debounce(func, delay = APP_CONFIG.ui.debounceDelay) {
  let timeoutId;
  return function (...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}

/**
 * 節流函數
 * @param {Function} func - 原函數
 * @param {number} limit - 限制時間
 * @returns {Function}
 */
export function throttle(func, limit = 100) {
  let inThrottle;
  return function (...args) {
    if (!inThrottle) {
      func.apply(this, args);
      inThrottle = true;
      setTimeout(() => inThrottle = false, limit);
    }
  };
}

/**
 * 延遲執行
 * @param {number} ms - 毫秒
 * @returns {Promise}
 */
export function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

/**
 * 重試函數
 * @param {Function} fn - 要重試的函數
 * @param {number} retries - 重試次數
 * @param {number} delay - 重試間隔
 * @returns {Promise}
 */
export async function retry(fn, retries = APP_CONFIG.api.retryAttempts, delay = 1000) {
  try {
    return await fn();
  } catch (error) {
    if (retries > 0) {
      await sleep(delay);
      return retry(fn, retries - 1, delay * 2);
    }
    throw error;
  }
}

/**
 * 產生唯一ID
 * @param {string} prefix - 前綴
 * @returns {string}
 */
export function generateId(prefix = 'id') {
  return `${prefix}_${Date.now()}_${Math.random().toString(36).substr(2, 9)}`;
}

/**
 * 檢查是否為行動裝置
 * @returns {boolean}
 */
export function isMobile() {
  return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

/**
 * 複製文字到剪貼簿
 * @param {string} text - 要複製的文字
 * @returns {Promise<boolean>}
 */
export async function copyToClipboard(text) {
  try {
    await navigator.clipboard.writeText(text);
    return true;
  } catch (error) {
    console.error('複製到剪貼簿失敗:', error);
    return false;
  }
}

/**
 * 下載檔案
 * @param {string} url - 檔案URL
 * @param {string} filename - 檔案名稱
 */
export function downloadFile(url, filename) {
  const link = createElement('a', {
    href: url,
    download: filename,
    style: 'display: none'
  });
  
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

// ===== 檔案處理工具 =====

/**
 * 讀取檔案為Base64
 * @param {File} file - 檔案物件
 * @returns {Promise<string>}
 */
export function readFileAsBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
    reader.readAsDataURL(file);
  });
}

/**
 * 壓縮圖片
 * @param {File} file - 圖片檔案
 * @param {number} quality - 壓縮品質 (0-1)
 * @param {number} maxWidth - 最大寬度
 * @param {number} maxHeight - 最大高度
 * @returns {Promise<Blob>}
 */
export function compressImage(file, quality = APP_CONFIG.upload.compressionQuality, maxWidth = APP_CONFIG.upload.maxImageWidth, maxHeight = APP_CONFIG.upload.maxImageHeight) {
  return new Promise((resolve) => {
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');
    const img = new Image();

    img.onload = () => {
      // 計算新尺寸
      let { width, height } = img;
      
      if (width > maxWidth) {
        height = (height * maxWidth) / width;
        width = maxWidth;
      }
      
      if (height > maxHeight) {
        width = (width * maxHeight) / height;
        height = maxHeight;
      }

      canvas.width = width;
      canvas.height = height;

      // 繪製並壓縮
      ctx.drawImage(img, 0, 0, width, height);
      canvas.toBlob(resolve, file.type, quality);
    };

    img.src = URL.createObjectURL(file);
  });
}

// 預設匯出工具函數
export default {
  $, $$, createElement, addEventListeners,
  truncateText, toCamelCase, toKebabCase, capitalize,
  formatNumber, formatCurrency, randomNumber,
  formatDate, getRelativeTime,
  uniqueArray, groupBy, sortArray,
  deepClone, mergeObjects, isEmpty,
  validateField, validateForm,
  setStorage, getStorage, removeStorage,
  debounce, throttle, sleep, retry,
  generateId, isMobile, copyToClipboard, downloadFile,
  readFileAsBase64, compressImage
}; 