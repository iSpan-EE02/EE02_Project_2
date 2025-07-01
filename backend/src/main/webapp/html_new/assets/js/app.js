/**
 * Main Application
 * 主要應用程式控制器
 * Author: 瘋購物團隊
 */

import { APP_CONFIG, ROUTES, EVENTS, KEYBOARD_SHORTCUTS } from '../shared/constants/app-config.js';
import { $, $$, addEventListeners, debounce, getStorage, setStorage } from '../shared/utils/helpers.js';
import { apiClient } from '../shared/api/api-client.js';

/**
 * 應用程式主類別
 */
class CrazyShopApp {
  constructor() {
    this.currentRoute = null;
    this.loadedModules = new Map();
    this.eventListeners = new Map();
    this.isInitialized = false;
    
    // 綁定方法的 this 上下文
    this.handleRouteChange = this.handleRouteChange.bind(this);
    this.handleKeyboardShortcut = this.handleKeyboardShortcut.bind(this);
    this.handleNotification = this.handleNotification.bind(this);
    
    this.init();
  }

  /**
   * 初始化應用程式
   */
  async init() {
    console.log('🚀 初始化瘋購物後台管理系統...');
    
    try {
      // 顯示載入動畫
      this.showLoading();
      
      // 初始化各個組件
      await this.initializeComponents();
      
      // 載入應用程式配置
      await this.loadConfiguration();
      
      // 設置事件監聽器
      this.setupEventListeners();
      
      // 初始化路由系統
      this.initializeRouter();
      
      // 載入預設頁面
      await this.loadDefaultRoute();
      
      // 隱藏載入動畫
      this.hideLoading();
      
      this.isInitialized = true;
      console.log('✅ 應用程式初始化完成');
      
    } catch (error) {
      console.error('❌ 應用程式初始化失敗:', error);
      this.showErrorMessage('應用程式初始化失敗，請重新整理頁面');
    }
  }

  /**
   * 初始化各個組件
   */
  async initializeComponents() {
    // 載入側邊欄
    await this.loadSidebar();
    
    // 載入標頭
    await this.loadHeader();
    
    // 載入底部
    await this.loadFooter();
    
    // 初始化通知系統
    this.initializeNotificationSystem();
    
    // 初始化模態框系統
    this.initializeModalSystem();
  }

  /**
   * 載入側邊欄
   */
  async loadSidebar() {
    const sidebarContainer = $('#app-sidebar');
    if (!sidebarContainer) return;

    const sidebarHTML = `
      <div class="sidebar-header">
        <h4>瘋購物</h4>
        <button class="btn btn-sm btn-outline-light d-lg-none" id="sidebar-toggle">
          <i class="bi bi-x"></i>
        </button>
      </div>
      
      <nav class="sidebar-nav">
        <ul class="nav flex-column">
          ${Object.entries(ROUTES).map(([key, route]) => `
            <li class="nav-item">
              <a class="nav-link" href="#" data-route="${key}">
                <i class="${route.icon}"></i>
                <span class="nav-text">${route.title}</span>
              </a>
            </li>
          `).join('')}
        </ul>
      </nav>
      
      <div class="sidebar-footer">
        <button class="btn btn-outline-light btn-sm" id="logout-btn">
          <i class="bi bi-box-arrow-right"></i>
          <span class="nav-text">登出</span>
        </button>
      </div>
    `;

    sidebarContainer.innerHTML = sidebarHTML;
    
    // 設置側邊欄事件
    this.setupSidebarEvents();
  }

  /**
   * 載入標頭
   */
  async loadHeader() {
    const headerContainer = $('#app-header');
    if (!headerContainer) return;

    const headerHTML = `
      <div class="d-flex justify-content-between align-items-center">
        <div class="d-flex align-items-center">
          <button class="btn btn-outline-secondary d-lg-none me-3" id="mobile-sidebar-toggle">
            <i class="bi bi-list"></i>
          </button>
          <h5 class="mb-0" id="page-title">儀表板</h5>
        </div>
        
        <div class="d-flex align-items-center gap-3">
          <div class="search-modern d-none d-md-flex">
            <i class="bi bi-search search-icon"></i>
            <input type="text" class="search-input" placeholder="搜尋..." id="global-search">
          </div>
          
          <div class="dropdown">
            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
              <i class="bi bi-person-circle"></i>
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
              <li><a class="dropdown-item" href="#"><i class="bi bi-person"></i> 個人資料</a></li>
              <li><a class="dropdown-item" href="#"><i class="bi bi-gear"></i> 設定</a></li>
              <li><hr class="dropdown-divider"></li>
              <li><a class="dropdown-item" href="#" id="header-logout"><i class="bi bi-box-arrow-right"></i> 登出</a></li>
            </ul>
          </div>
        </div>
      </div>
    `;

    headerContainer.innerHTML = headerHTML;
    
    // 設置標頭事件
    this.setupHeaderEvents();
  }

  /**
   * 載入底部
   */
  async loadFooter() {
    const footerContainer = $('#app-footer');
    if (!footerContainer) return;

    const footerHTML = `
      <div class="d-flex justify-content-between align-items-center">
        <div>
          <span>&copy; 2024 瘋購物後台管理系統 v${APP_CONFIG.version}</span>
        </div>
        <div>
          <small class="text-muted">最後更新時間: <span id="last-update-time">${new Date().toLocaleString('zh-TW')}</span></small>
        </div>
      </div>
    `;

    footerContainer.innerHTML = footerHTML;
  }

  /**
   * 載入應用程式配置
   */
  async loadConfiguration() {
    // 從本地儲存載入用戶偏好設定
    const userPrefs = getStorage(APP_CONFIG.storage.userPreferences, {
      theme: 'light',
      language: 'zh-TW',
      sidebarCollapsed: false
    });

    // 應用用戶設定
    this.applyUserPreferences(userPrefs);
  }

  /**
   * 應用用戶偏好設定
   * @param {Object} preferences - 用戶偏好設定
   */
  applyUserPreferences(preferences) {
    // 應用主題
    if (preferences.theme) {
      document.body.className = `admin-dashboard theme-${preferences.theme}`;
    }

    // 應用側邊欄狀態
    if (preferences.sidebarCollapsed) {
      const sidebar = $('#app-sidebar');
      if (sidebar) {
        sidebar.classList.add('collapsed');
      }
    }
  }

  /**
   * 設置事件監聽器
   */
  setupEventListeners() {
    // 鍵盤快捷鍵
    if (APP_CONFIG.features.keyboardShortcuts) {
      document.addEventListener('keydown', this.handleKeyboardShortcut);
    }

    // 自訂事件監聽器
    window.addEventListener('show-notification', this.handleNotification);
    window.addEventListener('route-change', this.handleRouteChange);

    // 視窗大小改變
    window.addEventListener('resize', debounce(() => {
      this.handleWindowResize();
    }, 250));

    // 頁面可見性改變
    document.addEventListener('visibilitychange', () => {
      if (!document.hidden) {
        this.handlePageVisible();
      }
    });
  }

  /**
   * 設置側邊欄事件
   */
  setupSidebarEvents() {
    // 路由導航
    $$('[data-route]').forEach(link => {
      addEventListeners(link, 'click', (e) => {
        e.preventDefault();
        const route = e.currentTarget.dataset.route;
        this.navigateTo(route);
      });
    });

    // 登出按鈕
    addEventListeners('#logout-btn', 'click', () => {
      this.handleLogout();
    });

    // 側邊欄切換（桌面版）
    addEventListeners('#sidebar-toggle', 'click', () => {
      this.toggleSidebar();
    });

    // 行動版側邊欄切換
    addEventListeners('#mobile-sidebar-toggle', 'click', () => {
      this.toggleMobileSidebar();
    });
  }

  /**
   * 設置標頭事件
   */
  setupHeaderEvents() {
    // 全域搜尋
    const globalSearch = $('#global-search');
    if (globalSearch) {
      addEventListeners(globalSearch, 'input', debounce((e) => {
        this.handleGlobalSearch(e.target.value);
      }, APP_CONFIG.ui.debounceDelay));
    }

    // 標頭登出按鈕
    addEventListeners('#header-logout', 'click', (e) => {
      e.preventDefault();
      this.handleLogout();
    });
  }

  /**
   * 初始化路由系統
   */
  initializeRouter() {
    // 監聽瀏覽器返回/前進按鈕
    window.addEventListener('popstate', (e) => {
      if (e.state && e.state.route) {
        this.navigateTo(e.state.route, false);
      }
    });
  }

  /**
   * 載入預設路由
   */
  async loadDefaultRoute() {
    // 檢查 URL hash 或使用預設路由
    const hash = window.location.hash.slice(1);
    const route = hash && ROUTES[hash] ? hash : 'dashboard';
    
    await this.navigateTo(route, false);
  }

  /**
   * 導航到指定路由
   * @param {string} routeName - 路由名稱
   * @param {boolean} pushState - 是否推送到瀏覽器歷史
   */
  async navigateTo(routeName, pushState = true) {
    if (!ROUTES[routeName]) {
      console.error(`路由不存在: ${routeName}`);
      return;
    }

    try {
      this.showLoading();
      
      const route = ROUTES[routeName];
      
      // 更新瀏覽器歷史
      if (pushState) {
        history.pushState({ route: routeName }, '', `#${routeName}`);
      }

      // 更新頁面標題
      this.updatePageTitle(route.title);

      // 更新側邊欄活動狀態
      this.updateSidebarActiveState(routeName);

      // 載入路由模組
      await this.loadRouteModule(route);

      this.currentRoute = routeName;
      
      // 觸發路由變更事件
      window.dispatchEvent(new CustomEvent(EVENTS.routeChange, {
        detail: { route: routeName, config: route }
      }));

      this.hideLoading();

    } catch (error) {
      console.error(`載入路由失敗 (${routeName}):`, error);
      this.showErrorMessage(`頁面載入失敗: ${error.message}`);
      this.hideLoading();
    }
  }

  /**
   * 載入路由模組
   * @param {Object} route - 路由配置
   */
  async loadRouteModule(route) {
    const mainContent = $('#main-content-area');
    if (!mainContent) return;

    // 載入 HTML 內容
    const response = await fetch(route.path);
    if (!response.ok) {
      throw new Error(`無法載入頁面: ${response.status}`);
    }
    
    const html = await response.text();
    mainContent.innerHTML = html;

    // 載入並執行 JavaScript 模組
    if (route.module) {
      if (!this.loadedModules.has(route.module)) {
        try {
          const module = await import(route.module);
          this.loadedModules.set(route.module, module);
          
          // 執行模組初始化
          if (module.default && typeof module.default === 'function') {
            module.default();
          } else if (module.init && typeof module.init === 'function') {
            module.init();
          }
        } catch (error) {
          console.error(`載入模組失敗 (${route.module}):`, error);
        }
      } else {
        // 重新初始化已載入的模組
        const module = this.loadedModules.get(route.module);
        if (module.reinit && typeof module.reinit === 'function') {
          module.reinit();
        }
      }
    }
  }

  /**
   * 更新頁面標題
   * @param {string} title - 標題
   */
  updatePageTitle(title) {
    const pageTitle = $('#page-title');
    if (pageTitle) {
      pageTitle.textContent = title;
    }
    
    document.title = `${title} - ${APP_CONFIG.name}`;
  }

  /**
   * 更新側邊欄活動狀態
   * @param {string} routeName - 路由名稱
   */
  updateSidebarActiveState(routeName) {
    // 移除所有活動狀態
    $$('.sidebar-nav .nav-link').forEach(link => {
      link.classList.remove('active');
    });

    // 添加新的活動狀態
    const activeLink = $(`.sidebar-nav [data-route="${routeName}"]`);
    if (activeLink) {
      activeLink.classList.add('active');
    }
  }

  /**
   * 初始化通知系統
   */
  initializeNotificationSystem() {
    const container = $('#notification-container');
    if (!container) return;

    // 確保容器是空的
    container.innerHTML = '';
  }

  /**
   * 初始化模態框系統
   */
  initializeModalSystem() {
    const container = $('#modal-container');
    if (!container) return;

    // 確保容器是空的
    container.innerHTML = '';
  }

  /**
   * 處理通知事件
   * @param {CustomEvent} event - 通知事件
   */
  handleNotification(event) {
    const { message, type = 'info', duration = APP_CONFIG.ui.notificationDuration } = event.detail;
    this.showNotification(message, type, duration);
  }

  /**
   * 顯示通知
   * @param {string} message - 訊息
   * @param {string} type - 類型 (success, error, warning, info)
   * @param {number} duration - 顯示時間
   */
  showNotification(message, type = 'info', duration = APP_CONFIG.ui.notificationDuration) {
    const container = $('#notification-container');
    if (!container) return;

    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.innerHTML = `
      <div class="notification-header">
        <h6 class="notification-title">
          <i class="bi ${this.getNotificationIcon(type)}"></i>
          ${this.getNotificationTitle(type)}
        </h6>
        <button class="notification-close" type="button">
          <i class="bi bi-x"></i>
        </button>
      </div>
      <div class="notification-body">
        ${message}
      </div>
    `;

    // 添加關閉事件
    const closeBtn = notification.querySelector('.notification-close');
    addEventListeners(closeBtn, 'click', () => {
      this.hideNotification(notification);
    });

    container.appendChild(notification);

    // 自動隱藏
    if (duration > 0) {
      setTimeout(() => {
        this.hideNotification(notification);
      }, duration);
    }
  }

  /**
   * 隱藏通知
   * @param {Element} notification - 通知元素
   */
  hideNotification(notification) {
    if (notification && notification.parentNode) {
      notification.style.opacity = '0';
      notification.style.transform = 'translateX(100%)';
      
      setTimeout(() => {
        if (notification.parentNode) {
          notification.parentNode.removeChild(notification);
        }
      }, 300);
    }
  }

  /**
   * 取得通知圖示
   * @param {string} type - 通知類型
   * @returns {string} 圖示類別
   */
  getNotificationIcon(type) {
    const icons = {
      success: 'bi-check-circle',
      error: 'bi-x-circle',
      warning: 'bi-exclamation-triangle',
      info: 'bi-info-circle'
    };
    return icons[type] || icons.info;
  }

  /**
   * 取得通知標題
   * @param {string} type - 通知類型
   * @returns {string} 標題
   */
  getNotificationTitle(type) {
    const titles = {
      success: '成功',
      error: '錯誤',
      warning: '警告',
      info: '資訊'
    };
    return titles[type] || titles.info;
  }

  /**
   * 顯示錯誤訊息
   * @param {string} message - 錯誤訊息
   */
  showErrorMessage(message) {
    this.showNotification(message, 'error');
  }

  /**
   * 顯示載入動畫
   */
  showLoading() {
    const loading = $('#loading-spinner');
    if (loading) {
      loading.classList.remove('hidden');
    }
  }

  /**
   * 隱藏載入動畫
   */
  hideLoading() {
    const loading = $('#loading-spinner');
    if (loading) {
      loading.classList.add('hidden');
    }
  }

  /**
   * 處理鍵盤快捷鍵
   * @param {KeyboardEvent} event - 鍵盤事件
   */
  handleKeyboardShortcut(event) {
    const key = `${event.ctrlKey ? 'Ctrl+' : ''}${event.shiftKey ? 'Shift+' : ''}${event.altKey ? 'Alt+' : ''}${event.key}`;
    
    const action = KEYBOARD_SHORTCUTS[key];
    if (action) {
      event.preventDefault();
      this.executeShortcutAction(action);
    }
  }

  /**
   * 執行快捷鍵動作
   * @param {string} action - 動作名稱
   */
  executeShortcutAction(action) {
    switch (action) {
      case 'save':
        this.triggerSave();
        break;
      case 'search':
        this.focusGlobalSearch();
        break;
      case 'cancel':
        this.triggerCancel();
        break;
      default:
        console.log(`執行快捷鍵動作: ${action}`);
    }
  }

  /**
   * 觸發儲存動作
   */
  triggerSave() {
    window.dispatchEvent(new CustomEvent('keyboard-save'));
  }

  /**
   * 聚焦全域搜尋
   */
  focusGlobalSearch() {
    const searchInput = $('#global-search');
    if (searchInput) {
      searchInput.focus();
    }
  }

  /**
   * 觸發取消動作
   */
  triggerCancel() {
    window.dispatchEvent(new CustomEvent('keyboard-cancel'));
  }

  /**
   * 處理全域搜尋
   * @param {string} query - 搜尋關鍵字
   */
  handleGlobalSearch(query) {
    if (query.trim().length < 2) return;
    
    console.log('全域搜尋:', query);
    // 實作全域搜尋邏輯
  }

  /**
   * 處理登出
   */
  async handleLogout() {
    if (confirm('確定要登出嗎？')) {
      try {
        // 清除本地儲存
        localStorage.removeItem(APP_CONFIG.storage.authToken);
        localStorage.removeItem(APP_CONFIG.storage.userPreferences);
        
        // 重定向到登入頁面
        window.location.href = '../html/login.html';
      } catch (error) {
        console.error('登出失敗:', error);
        this.showErrorMessage('登出失敗，請稍後再試');
      }
    }
  }

  /**
   * 切換側邊欄
   */
  toggleSidebar() {
    const sidebar = $('#app-sidebar');
    if (sidebar) {
      sidebar.classList.toggle('collapsed');
      
      // 儲存用戶偏好
      const userPrefs = getStorage(APP_CONFIG.storage.userPreferences, {});
      userPrefs.sidebarCollapsed = sidebar.classList.contains('collapsed');
      setStorage(APP_CONFIG.storage.userPreferences, userPrefs);
    }
  }

  /**
   * 切換行動版側邊欄
   */
  toggleMobileSidebar() {
    const sidebar = $('#app-sidebar');
    if (sidebar) {
      sidebar.classList.toggle('show');
      
      // 切換背景遮罩
      this.toggleSidebarBackdrop();
    }
  }

  /**
   * 切換側邊欄背景遮罩
   */
  toggleSidebarBackdrop() {
    let backdrop = $('.sidebar-backdrop');
    
    if (!backdrop) {
      backdrop = document.createElement('div');
      backdrop.className = 'sidebar-backdrop';
      addEventListeners(backdrop, 'click', () => {
        this.toggleMobileSidebar();
      });
      document.body.appendChild(backdrop);
    }
    
    backdrop.classList.toggle('show');
  }

  /**
   * 處理視窗大小改變
   */
  handleWindowResize() {
    // 在大螢幕時自動隱藏行動版側邊欄
    if (window.innerWidth >= 992) {
      const sidebar = $('#app-sidebar');
      if (sidebar) {
        sidebar.classList.remove('show');
      }
      
      const backdrop = $('.sidebar-backdrop');
      if (backdrop) {
        backdrop.classList.remove('show');
      }
    }
  }

  /**
   * 處理頁面變為可見
   */
  handlePageVisible() {
    // 更新最後更新時間
    const lastUpdateElement = $('#last-update-time');
    if (lastUpdateElement) {
      lastUpdateElement.textContent = new Date().toLocaleString('zh-TW');
    }
  }

  /**
   * 處理路由變更事件
   * @param {CustomEvent} event - 路由變更事件
   */
  handleRouteChange(event) {
    console.log('路由已變更:', event.detail);
  }
}

// ===== 初始化應用程式 =====

// 等待 DOM 載入完成
if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', () => {
    window.crazyShopApp = new CrazyShopApp();
  });
} else {
  window.crazyShopApp = new CrazyShopApp();
}

// 匯出應用程式類別
export default CrazyShopApp; 