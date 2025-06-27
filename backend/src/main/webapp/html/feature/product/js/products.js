/**
 * @file products.js
 * @description 商品頁面的主要邏輯
 */

/**
 * @description 引入根據ID產生貨號的模塊
 */
import { generateCodeFromInteger } from "./rand-str.js"; //產生貨號的前置
// console.log(`輸入 456975: ${generateCodeFromInteger(4569)}`); //應該是AGTT

/**
 * @description 引入連接後端的模塊
 */
import { fetchCategories, fetchProducts } from "./api-client.js";

// 將扁平的分類陣列轉換為樹狀結構
function buildCategoryTree(list) {
  const map = {};
  const roots = [];
  list.forEach((item) => {
    // 假設後端回傳的欄位是 cate_id, parent_cate_id, cate_name, cate_desc
    map[item.cate_id] = { ...item, children: [] };
  });
  list.forEach((item) => {
    if (item.parent_cate_id && map[item.parent_cate_id]) {
      map[item.parent_cate_id].children.push(map[item.cate_id]);
    } else {
      roots.push(map[item.cate_id]);
    }
  });
  return roots;
}

// 遞迴函式：根據樹狀結構生成 HTML
function generateTreeHTML(nodes) {
  let html = "<ul>";
  for (const node of nodes) {
    const isParent = node.is_parent;
    // 【修改 1】: 在 li 標籤中，透過 data-is-parent 屬性儲存它是否為父分類的資訊
    html += `<li data-id="${node.cate_id}" 
                     data-name="${node.cate_name}" 
                     data-desc="${node.cate_desc}" 
                     data-parent-id="${node.parent_cate_id || 0}"
                     data-is-parent="${isParent}">
                     <a href="#">
                         <i class="bi ${
                           isParent ? "bi-caret-down-fill" : "bi-dash"
                         }"></i>
                         <b>${node.cate_name}</b> (ID1: ${node.cate_id})
                     </a>`;
    if (isParent) {
      html += generateTreeHTML(node.children);
    }
    html += "</li>";
  }
  html += "</ul>";
  return html;
}

function generateProductsList(list) {
  console.log(list);
}

/**
 * 將選中的分類id和name代入至搜索的form中
 * @param {*} id
 * @param {*} name
 */
function cateEditFilter(id, name) {
  document.getElementById("cate-filter").setAttribute("data-id", id);
  document.getElementById("cate-filter").value = name;
}

/**
 * 初始化頁面
 * export default => 會在第一次載入時執行一次
 * @description 取得資料、建立 DOM 並綁定事件
 */
export default async function init() {
  try {
    /**
     * 載入分類樹狀圖
     */

    // 取得分類資料
    const categories = await fetchCategories();

    // 使用資料建立樹狀結構
    const treeData = buildCategoryTree(categories);

    // 生成樹狀結構的 HTML
    const treeHTML = generateTreeHTML(treeData);

    // 將生成的 HTML 插入到容器中
    const container = document.getElementById("product-category-tree");
    container.innerHTML = treeHTML;

    // 分類樹狀圖點擊事件
    container.addEventListener("click", function (event) {
      event.preventDefault();
      const targetLi = event.target.closest("li");
      if (targetLi) {
        // 從 li 元素中的 dataset 中讀取分類資訊
        const { id, name } = targetLi.dataset;
        // 填充右側查詢表單
        cateEditFilter(id, name);
      }
    });

    /**
     * 載入搜尋商品列表
     */

    // 取得產品資料
    const products = await fetchProducts();

    // 生成包含product物件的Array
    const productsArray = generateProductsList(products);

    // currentPage = 1;

    /**
     * 廣播按鈕點擊按鍵
     */

    // 假設你透過 API 取得了新的總頁數
    const newTotalPagesFromAPI = Math.floor(products.length / 6) + 1;

    // 建立一個自訂事件
    const event = new CustomEvent("update-total-pages", {
      detail: {
        newTotal: newTotalPagesFromAPI, // 將新總頁數放在 detail 物件中
      },
    });

    // 在 window 上廣播這個事件
    window.dispatchEvent(event);
  } catch (error) {
    //初始化頁面錯誤處理
    console.error("初始化商品頁面時發生錯誤:", error);
    document.getElementById(
      "product-main-section"
    ).innerHTML = `<div class="alert alert-danger">${error.message}</div>`;
  }
}
