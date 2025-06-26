/**
 * @file categories.js
 * @description 商品分類頁面的主要邏輯 (ES6 模組)
 */
import { fetchCategories } from './api-client.js';

// 將扁平的分類陣列轉換為樹狀結構
function buildCategoryTree(list) {
    const map = {};
    const roots = [];
    list.forEach(item => {
        // 假設後端回傳的欄位是 cate_id, parent_cate_id, cate_name, cate_desc
        map[item.cate_id] = { ...item, children: [] };
    });
    list.forEach(item => {
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
    let html = '<ul>';
    for (const node of nodes) {
        const isParent = node.is_parent;
        // 【修改 1】: 在 li 標籤中，透過 data-is-parent 屬性儲存它是否為父分類的資訊
        html += `<li data-id="${node.cate_id}" 
                     data-name="${node.cate_name}" 
                     data-desc="${node.cate_desc}" 
                     data-parent-id="${node.parent_cate_id || 0}"
                     data-is-parent="${isParent}">
                     <a href="#">
                         <i class="bi ${isParent ? 'bi-caret-down-fill' : 'bi-dash'}"></i>
                         <b>${node.cate_name}</b> (ID: ${node.cate_id})
                     </a>`;
        if (isParent) {
            html += generateTreeHTML(node.children);
        }
        html += '</li>';
    }
    html += '</ul>';
    return html;
}

// 填充右側的編輯表單
// 函式增加 isParent 參數，並根據它來決定刪除按鈕的狀態
function populateEditForm(id, name, desc, parentId, isParent) {
    document.getElementById('edit-form-header').textContent = `編輯分類：${name}`;
    document.getElementById('edit-cate-id').value = id;
    document.getElementById('edit-cate-name').value = name;
    document.getElementById('edit-cate-desc').value = desc;
    document.getElementById('edit-parent-cate').value = parentId || 0;

    const deleteBtn = document.getElementById('delete-category-btn');
    // 注意：從 dataset 來的會是字串 "true" 或 "false"
    if (isParent === 'true') {
        deleteBtn.disabled = true;
        deleteBtn.title = "此分類底下尚有子分類，無法刪除。";
    } else {
        deleteBtn.disabled = false;
        deleteBtn.title = "刪除此分類";
    }
}

// 清空表單，用於新增分類
function clearEditForm() {
    document.getElementById('edit-form-header').textContent = '新增分類';
    document.getElementById('category-edit-form').reset();
    document.getElementById('edit-cate-id').value = '';
    document.getElementById('delete-category-btn').disabled = true;
}


// 表單提交（新增，編輯）事件
/** 
 *@param {SubmitEvent} event
*/
async function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    console.log('第 2 步：FormData 物件的內容 (需要用特殊方法讀取):');

    //什麼都沒讀出來
    for (const [key, value] of formData.entries()) {
        console.log(`  - ${key}: ${value}`);
    }
    const categoryData = Object.fromEntries(formData.entries());
    console.log("hello world");

    console.log(categoryData);


}

// 刪除按鈕事件
async function handleDelete() {

}

/**
 * 初始化頁面的主要函式
 * @description 取得資料、建立 DOM 並綁定事件
 */
export default async function init() {

    try {
        // 取得分類資料
        const categories = await fetchCategories();

        // 使用資料建立樹狀結構
        const treeData = buildCategoryTree(categories);

        // 生成樹狀結構的 HTML
        const treeHTML = generateTreeHTML(treeData);

        // 將生成的 HTML 插入到容器中
        const container = document.getElementById('category-tree-container');
        container.innerHTML = treeHTML;

        // 生成編輯表單中父分類的下拉選單
        const select = document.getElementById('edit-parent-cate');
        select.innerHTML = '<option value="0">（此為頂層分類）</option>';
        categories.forEach(cat => {
            const option = document.createElement('option');
            option.value = cat.cate_id;
            option.textContent = `${cat.cate_name} (ID: ${cat.cate_id})`;
            select.appendChild(option);
        });

        // 分類樹狀圖點擊事件
        container.addEventListener('click', function (event) {
            event.preventDefault();
            const targetLi = event.target.closest('li');
            if (targetLi) {
                // 從 li 元素中的 dataset 中讀取分類資訊
                const { id, name, desc, parentId, isParent } = targetLi.dataset;
                // 填充編輯表單
                populateEditForm(id, name, desc, parentId, isParent);
            }
        });

        // 綁定新增事件
        // 清空編輯表單
        document.getElementById('add-new-category-btn').addEventListener('click', clearEditForm);

        // 綁定編輯事件
        document.getElementById('category-edit-form').addEventListener('submit', handleFormSubmit);

        // 綁定刪除事件
        document.getElementById('delete-category-btn').addEventListener('click', handleDelete);





    } catch (error) {
        console.error('初始化分類頁面時發生錯誤:', error);
        document.getElementById('category-tree-container').innerHTML = `<div class="alert alert-danger">${error.message}</div>`;
    }
}

