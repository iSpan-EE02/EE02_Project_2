// 當整個網頁的 DOM 結構都載入完成後，再執行我們的腳本
document.addEventListener("DOMContentLoaded", () => {
  const mainContentArea = document.getElementById("main-content-area");
  const navLinks = document.querySelectorAll(".sidebar .nav-link");

  /**
   * 非同步載入主頁面內容，並處理與頁面相關的腳本
   * @param {string} path - 要載入的頁面路徑 (不含 .html 副檔名)，例如 'dashboard' 或 'feature/product/categories'
   * @param {Event} event - 點擊事件物件
   */
  window.loadPage = async function (path, event) {
    if (event) {
      event.preventDefault(); // 阻止 <a> 標籤的預設跳轉行為
    }

    // 更新側邊欄連結的 active 狀態
    navLinks.forEach((link) => link.classList.remove("active"));
    if (event) {
      event.currentTarget.classList.add("active");
    }

    const fetchUrl = `${path}.html`;
    try {
      const response = await fetch(fetchUrl);
      if (!response.ok) {
        throw new Error(
          `無法載入頁面：${fetchUrl}，狀態碼: ${response.status}`
        );
      }
      const html = await response.text();
      mainContentArea.innerHTML = html; // 將載入的內容注入到主區域

      // 根據頁面路徑，載入對應的 JS 模組
      const scriptSrc = getScriptForPage(path);

      if (scriptSrc) {
        // *** 使用動態 import() 來載入並執行模組 ***
        const module = await import(`${scriptSrc}`);

        // 如果模組有預設匯出 (default export) 且是函式，就執行它
        if (module.default && typeof module.default === "function") {
          module.default();
        }
      }
    } catch (error) {
      mainContentArea.innerHTML = `<div class="alert alert-danger">頁面載入失敗: ${error.message}</div>`;
      console.error("載入頁面時發生錯誤:", error);
    }
  };

  /**
   * 根據頁面路徑返回對應的 JS 檔案路徑
   * @param {string} path - 頁面路徑，例如 'feature/product/categories'
   * @returns {string|null} - 對應的 JS 檔案路徑，如果沒有則返回 null
   */
  function getScriptForPage(path) {
    const pageScripts = {
      "./feature/product/categories": "../feature/product/js/categories.js",
      "./feature/product/products": "../feature/product/js/products.js",
    };
    // 使用 ./ 確保路徑是從根目錄開始的相對路徑
    return pageScripts[path] || null;
  }

  // --- 初始頁面載入 ---
  // 預設載入儀表板
  loadPage("dashboard");
  const dashboardLink = document.querySelector(
    '.sidebar .nav-link[onclick*="dashboard"]'
  );
  if (dashboardLink) {
    dashboardLink.classList.add("active");
  }
});

document.addEventListener("alpine:init", () => {
  Alpine.data("pagination", () => ({
    paginatedProducts: [],
    currentPage: 1,
    totalPages: 10, // 假設總共有10頁

    // 你也可以在這裡定義方法
    goToPage(page) {
      this.currentPage = page;
    },
    previousPage() {
      if (this.currentPage == 1) {
        this.currentPage = this.totalPages;
      } else {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage == this.totalPages) {
        this.currentPage = 1;
      } else {
        this.currentPage++;
      }
    },
  }));
});
