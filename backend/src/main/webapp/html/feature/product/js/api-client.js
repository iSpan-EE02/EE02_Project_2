
/**
 * @file api-client.js
 * @description 可複用的 API 請求模組
 */

// 您的 Servlet 相對路徑
const PROD_CATE_UPSERT = '/Project2/ProdCateQueryAll';
const PROD_CATE_DELETE = '/Project2/ProdCateQueryAll';
const PROD_CATE_QUERY = '/Project2/ProdCateQueryAll';


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
        if (result && result.status === 'success' && Array.isArray(result.data)) {
            console.log('成功取得並解析資料！');
            return result.data; // 只回傳最重要的 data 陣列
        } else {
            // 如果 JSON 格式不對或 status 不是 success，拋出一個帶有後端訊息的錯誤
            throw new Error(result.message || '從伺服器回傳的資料格式不正確');
        }

    } catch (error) {
        // 捕獲網路連線錯誤 (如無法連線到伺服器) 或上面拋出的所有錯誤
        console.error('取得分類資料時發生錯誤:', error);
        // 將錯誤再次拋出，這樣呼叫此函式的程式碼 (categories.js) 才能捕獲到它並在介面上顯示錯誤訊息
        throw error;
    }
}
