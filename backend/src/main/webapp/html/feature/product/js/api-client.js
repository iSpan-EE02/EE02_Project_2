/**
 * @file api-client.js
 * @description 可複用的 API 請求模組
 */

// 您的 Servlet 相對路徑
const PROD_CATE_UPSERT = "/Project2/ProdCateSave";
const PROD_CATE_DELETE = "/Project2/ProdCateDelete";
const PROD_CATE_QUERY = "/Project2/ProdCateQueryAll";
const PROD_CATE_QUERY_PROD = "/Project2/ProdCateQueryAllProd";
const PROD_QUERY = "/Project2/ProdQueryAll";
const PROD_UPSERT = "/Project2/ProdSave";
const IMAGE_QUERY = "/Project2/ProdImageQuery";
const SKU_QUERY = "/Project2/ProdSkusQuery";

/**
 * 從後端非同步取得所有分類資料
 * @returns {Promise<Array>} 回傳一個包含所有分類物件的 Promise
 */

export async function fetchCategories() {
  console.log(`正在從 ${PROD_CATE_QUERY} 取得分類資料...`);

  try {
    const response = await fetch(PROD_CATE_QUERY);

    // 檢查 HTTP 回應狀態碼是否成功 (在 200-299 範圍內)
    if (!response.ok) {
      // 如果伺服器回應錯誤 (如 404 Not Found, 500 Internal Server Error), 拋出錯誤
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    // 解析 JSON 格式的回應主體
    const result = await response.json();

    // 根據您後端回傳的 JSON 結構，檢查業務邏輯是否真的成功
    if (result && result.status === "success" && Array.isArray(result.data)) {
      console.log("成功取得並解析資料！");
      return result.data; // 只回傳最重要的 data 陣列
    } else {
      // 如果 JSON 格式不對或 status 不是 success，拋出一個帶有後端訊息的錯誤
      throw new Error(result.message || "從伺服器回傳的資料格式不正確");
    }
  } catch (error) {
    // 捕獲網路連線錯誤 (如無法連線到伺服器) 或上面拋出的所有錯誤
    console.error("取得分類資料時發生錯誤:", error);
    // 將錯誤再次拋出，這樣呼叫此函式的程式碼 (categories.js) 才能捕獲到它並在介面上顯示錯誤訊息
    throw error;
  }
}

export async function fetchCategoriesProd() {
  console.log(`正在從 ${PROD_CATE_QUERY_PROD} 取得分類資料...`);

  try {
    const response = await fetch(PROD_CATE_QUERY_PROD);

    // 檢查 HTTP 回應狀態碼是否成功 (在 200-299 範圍內)
    if (!response.ok) {
      // 如果伺服器回應錯誤 (如 404 Not Found, 500 Internal Server Error), 拋出錯誤
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    // 解析 JSON 格式的回應主體
    const result = await response.json();

    // 根據您後端回傳的 JSON 結構，檢查業務邏輯是否真的成功
    if (result && result.status === "success" && Array.isArray(result.data)) {
      console.log("成功取得並解析資料！");
      return result.data; // 只回傳最重要的 data 陣列
    } else {
      // 如果 JSON 格式不對或 status 不是 success，拋出一個帶有後端訊息的錯誤
      throw new Error(result.message || "從伺服器回傳的資料格式不正確");
    }
  } catch (error) {
    // 捕獲網路連線錯誤 (如無法連線到伺服器) 或上面拋出的所有錯誤
    console.error("取得分類資料時發生錯誤:", error);
    // 將錯誤再次拋出，這樣呼叫此函式的程式碼 (categories.js) 才能捕獲到它並在介面上顯示錯誤訊息
    throw error;
  }
}

/**
 * 新增或修改一個分類資料
 * @param {object} categoryData - 要儲存的分類物件，例如 { cate_id: '1', cate_name: '新名稱', ... }
 * @returns {Promise<object>} 回傳一個包含後端回應的 Promise
 */
export async function saveCategory(categoryData) {
  console.log(`正在將資料儲存至 ${PROD_CATE_UPSERT}...`, categoryData);

  try {
    const response = await fetch(PROD_CATE_UPSERT, {
      method: "POST", // 使用 POST 方法來傳送資料
      headers: {
        // 必須設定這個標頭，告訴後端我們傳送的是 JSON 格式的資料
        "Content-Type": "application/json",
      },
      // 將 JavaScript 物件轉換為 JSON 字串後，放在請求的主體 (body) 中
      body: JSON.stringify(categoryData),
    });

    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    const result = await response.json();

    // 檢查後端回傳的業務邏輯是否成功
    if (result && result.status === "success") {
      console.log("資料儲存成功！");
      return result; // 將後端的成功回應傳回
    } else {
      throw new Error(result.message || "儲存失敗，但伺服器未提供錯誤訊息");
    }
  } catch (error) {
    console.error("儲存分類資料時發生錯誤:", error);
    throw error;
  }
}

/**
 * 刪除一個分類
 * @param {number | string} categoryId - 要刪除的分類 ID
 * @returns {Promise<object>} 回傳一個包含後端回應的 Promise
 */
export async function deleteCategory(categoryId) {
  console.log(`正在從 ${PROD_CATE_DELETE} 刪除 ID 為 ${categoryId} 的資料...`);

  try {
    const response = await fetch(`${PROD_CATE_DELETE}?cate_id=${categoryId}`, {
      method: "GET", // 使用 GET 方法
    });

    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    const result = await response.json();

    if (result && result.status === "success") {
      console.log("資料刪除成功！");
      return result;
    } else {
      throw new Error(result.message || "刪除失敗，但伺服器未提供錯誤訊息");
    }
  } catch (error) {
    console.error("刪除分類資料時發生錯誤:", error);
    throw error;
  }
}

/**
 * 從後端非同步取得所有產品資料
 * @param {object} product
 * @returns {Promise<Array>} 回傳一個包含所有產品物件的 Promise
 */

export async function fetchProducts(productData = {}) {
  console.log(`正在從 ${PROD_QUERY} 取得分類資料...`);

  try {
    const response = await fetch(PROD_QUERY, {
      method: "POST", // 使用 POST 方法來傳送資料
      headers: {
        // 必須設定這個標頭，告訴後端我們傳送的是 JSON 格式的資料
        "Content-Type": "application/json",
      },
      // 將 JavaScript 物件轉換為 JSON 字串後，放在請求的主體 (body) 中
      body: JSON.stringify(productData),
    });

    // 檢查 HTTP 回應狀態碼是否成功 (在 200-299 範圍內)
    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }
    const result = await response.json();
    if (result && result.status === "success" && Array.isArray(result.data)) {
      console.log("成功取得產品資料！");
      return result.data; // 只回傳最重要的 data 陣列
    } else {
      throw new Error(result.message || "從伺服器回傳的資料格式不正確");
    }
  } catch (error) {
    console.error("取得產品資料時發生錯誤:", error);
    throw error;
  }
}

export async function saveProduct(ProductFormData) {
  console.log(`正在將資料儲存至 ${PROD_UPSERT}...`);

  try {
    const response = await fetch(PROD_UPSERT, {
      method: "POST", // 使用 POST 方法來傳送資料

      // 傳入FormData
      body: ProductFormData,
    });

    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    const result = await response.json();

    // 檢查後端回傳的業務邏輯是否成功
    if (result && result.status === "success") {
      console.log("商品資料儲存成功！");
      return result; // 將後端的成功回應傳回
    } else {
      throw new Error(result.message || "商品儲存失敗，但伺服器未提供錯誤訊息");
    }
  } catch (error) {
    console.error("儲存商品資料時發生錯誤:", error);
    throw error;
  }
}

/**
 * 查詢圖片 by prodId
 * @param {number | string} prodId - 要查詢圖片的產品 ID
 * @returns {Promise<object>} 回傳一個包含後端回應的 Promise
 */
export async function fetchImage(prodId) {
  console.log(`正在從 ${IMAGE_QUERY} 查詢 ID 為 ${prodId} 的資料...`);

  try {
    const response = await fetch(`${IMAGE_QUERY}?prod_id=${prodId}`, {
      method: "GET", // 使用 GET 方法
    });

    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    const result = await response.json();

    if (result && result.status === "success") {
      console.log("圖片查詢成功！");
      return result.data;
    } else {
      throw new Error(result.message || "圖片查詢失敗，但伺服器未提供錯誤訊息");
    }
  } catch (error) {
    console.error("查詢圖片時發生錯誤:", error);
    throw error;
  }
}

/**
 * 查詢Sku by prodId
 * @param {number | string} prodId - 要查詢Sku的產品 ID
 * @returns {Promise<object>} 回傳一個包含後端回應的 Promise
 */
export async function fetchSku(prodId) {
  console.log(`正在從 ${SKU_QUERY} 查詢 ID 為 ${prodId} 的資料...`);

  try {
    const response = await fetch(`${SKU_QUERY}?prod_id=${prodId}`, {
      method: "GET", // 使用 GET 方法
    });

    if (!response.ok) {
      throw new Error(`伺服器錯誤！狀態碼: ${response.status}`);
    }

    const result = await response.json();

    if (result && result.status === "success") {
      console.log("Sku查詢成功！");
      return result.data;
    } else {
      throw new Error(result.message || "Sku查詢失敗，但伺服器未提供錯誤訊息");
    }
  } catch (error) {
    console.error("查詢Sku時發生錯誤:", error);
    throw error;
  }
}
