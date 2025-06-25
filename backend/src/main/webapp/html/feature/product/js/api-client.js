// /**
//  * @file api-client.js
//  * @description 可複用的 API 請求模組
//  */

// // 模擬從 Servlet 取得的 JSON 資料
// const mockResponse = {
//     "data": [{ "cate_id": 1, "cate_name": "服裝", "cate_desc": "所有穿戴在身上的衣物及配件" }, { "cate_id": 2, "cate_name": "男裝", "parent_cate_id": 1, "cate_desc": "所有男性專屬的時尚服飾" }, { "cate_id": 3, "cate_name": "女裝", "parent_cate_id": 1, "cate_desc": "所有女性專屬的時尚服飾" }, { "cate_id": 4, "cate_name": "配件", "parent_cate_id": 1, "cate_desc": "點綴整體造型的時尚單品，如帽子、圍巾等" }, { "cate_id": 5, "cate_name": "男性上衣", "parent_cate_id": 2, "cate_desc": "包含T恤、Polo衫、襯衫等" }, { "cate_id": 6, "cate_name": "男性褲款", "parent_cate_id": 2, "cate_desc": "包含休閒褲、牛仔褲等" }, { "cate_id": 7, "cate_name": "女性上衣", "parent_cate_id": 3, "cate_desc": "包含T恤、雪紡衫、襯衫等" }, { "cate_id": 8, "cate_name": "女性裙裝", "parent_cate_id": 3, "cate_desc": "包含短裙、長裙、連身裙等" }, { "cate_id": 9, "cate_name": "帽子", "parent_cate_id": 4, "cate_desc": "包含棒球帽、漁夫帽等時尚帽款" }],
//     "message": "成功查詢到 9 筆資料。",
//     "status": "success",
//     "itemCount": 9
// };

// /**
//  * 模擬從後端非同步取得所有分類資料
//  * @returns {Promise<Array>} 回傳一個包含所有分類物件的 Promise
//  */
// export async function fetchCategories() {
//     console.log('正在模擬從 API 取得分類資料...');
//     // 模擬網路延遲
//     await new Promise(resolve => setTimeout(resolve, 300));

//     if (mockResponse.status === 'success') {
//         console.log('成功取得資料！');
//         return mockResponse.data;
//     } else {
//         throw new Error('無法從伺服器取得分類資料');
//     }
// }
/**
 * @file api-client.js
 * @description 可複用的 API 請求模組
 */

// 您的 Servlet 相對路徑
const API_ENDPOINT = '/Project2/ProdCateQueryAll';

/**
 * 從後端非同步取得所有分類資料
 * @returns {Promise<Array>} 回傳一個包含所有分類物件的 Promise
 */
export async function fetchCategories() {
    console.log(`正在從 ${API_ENDPOINT} 取得分類資料...`);

    try {
        const response = await fetch(API_ENDPOINT);

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
