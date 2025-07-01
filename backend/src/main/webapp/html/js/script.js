document.addEventListener("DOMContentLoaded", () => {
  const mainContentArea = document.getElementById("main-content-area");
  const navLinks = document.querySelectorAll(".sidebar .nav-link");

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
      "./dashboard": "./dashboard.js",
    };
    // 使用 ./ 確保路徑是從根目錄開始的相對路徑
    return pageScripts[path] || null;
  }

  // --- 初始頁面載入 ---
  // 預設載入儀表板
  loadPage("./dashboard");
  const dashboardLink = document.querySelector(
    '.sidebar .nav-link[onclick*="dashboard"]'
  );
  if (dashboardLink) {
    dashboardLink.classList.add("active");
  }
});

//alpine.js相關
document.addEventListener("alpine:init", () => {
  Alpine.data("pagination", () => ({
    // paginatedProducts: [],
    // currentPage: 1,
    // totalPages: 10, // 假設總共有10頁

    allProducts: [], // 1. 用於儲存從 API 來的完整商品列表
    currentPage: 1,
    totalPages: 1, // 預設值改為 1
    itemsPerPage: 6, // 2. 設定每頁顯示 6 筆商品

    get paginatedProducts() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      // 使用 slice() 從完整列表中切割出當前頁的資料
      return this.allProducts.slice(start, end);
    },

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
    init() {
      // 監聽從 products.js 來的事件
      window.addEventListener("update-total-pages", (event) => {
        this.totalPages = event.detail.newTotal;
      });

      window.addEventListener("update-products", (event) => {
        // 4. 更新 allProducts，而不是 paginatedProducts
        this.allProducts = event.detail.paginatedProducts;
      });
    },
    // testMe() {
    //   const mockData = [];
    //   mockData.push({
    //     prod_id: 1,
    //     prod_name: "經典純棉素色T恤",
    //     prod_desc:
    //       "採用100%頂級純棉，觸感柔軟舒適，是衣櫃中不可或缺的百搭單品。",
    //     prod_cate_id: 5,
    //     prod_status: 0,
    //     create_at: null,
    //     image_url: null,
    //   });

    //   this.paginatedProducts = mockData;
    // },
    // testMe2() {
    //   console.log(this.paginatedProducts);
    // },
  }));
});
