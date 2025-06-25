// 當整個網頁的 DOM 結構都載入完成後，再執行我們的腳本
document.addEventListener('DOMContentLoaded', () => {

    const mainContentArea = document.getElementById('main-content-area');
    const navLinks = document.querySelectorAll('.sidebar .nav-link');

    /**
     * 非同步載入主頁面內容 (dashboard, products, categories)
     * @param {string} pageName - 要載入的 HTML 檔案名稱 (不含 .html)
     * @param {Event} event - 點擊事件物件
     */
    window.loadPage = async function (pageName, event) {
        if (event) {
            event.preventDefault(); // 阻止 <a> 標籤的預設跳轉行為
        }

        // 更新側邊欄連結的 active 狀態
        navLinks.forEach(link => link.classList.remove('active'));
        if (event) {
            event.currentTarget.classList.add('active');
        }

        const fetchUrl = `${pageName}.html`;
        try {
            const response = await fetch(fetchUrl);
            if (!response.ok) {
                throw new Error(`無法載入頁面，狀態碼: ${response.status}`);
            }
            const html = await response.text();
            mainContentArea.innerHTML = html; // 將載入的內容注入到主區域
        } catch (error) {
            mainContentArea.innerHTML = `<div class="alert alert-danger">頁面載入失敗: ${error.message}</div>`;
            console.error('載入頁面時發生錯誤:', error);
        }
    };

    /**
     * 在已載入的內容中，切換子頁面 (主要用於商品管理內部的導覽)
     * @param {string} subPageId - 要顯示的子頁面區塊的 ID
     */
    window.showSubPage = function (subPageId) {
        // 如果當前不在 products 頁，先載入 products.html
        if (!document.getElementById('product-list')) {
            loadPage('products').then(() => {
                // 載入完成後，再執行切換
                toggleProductSubPage(subPageId);
            });
        } else {
            toggleProductSubPage(subPageId);
        }
    }

    // 輔助函數: 處理商品管理頁面內部的顯示/隱藏
    function toggleProductSubPage(subPageId) {
        const productPages = document.querySelectorAll('.product-page');
        productPages.forEach(p => p.style.display = 'none');

        const editPage = document.getElementById('product-edit');

        if (subPageId === 'product-add') {
            editPage.querySelector('h1.h2').textContent = '新增商品';
            // 清空表單欄位 (此處僅為範例)
            editPage.querySelectorAll('input[type="text"]').forEach(input => input.value = '');
            editPage.style.display = 'block';
        } else {
            // 還原編輯頁的標題
            editPage.querySelector('h1.h2').textContent = '編輯商品：經典純棉素色T恤';
            const pageToShow = document.getElementById(subPageId);
            if (pageToShow) {
                pageToShow.style.display = 'block';
            }
        }
    }

    // --- 初始頁面載入 ---
    loadPage('dashboard');
    document.querySelector('.sidebar .nav-link[onclick*="dashboard"]').classList.add('active');
});
