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
        html += `<li data-id="${node.cate_id}" data-name="${node.cate_name}" data-desc="${node.cate_desc}" data-parent-id="${node.parent_cate_id || 0}">
                     <a href="#">
                        <i class="bi ${node.children.length > 0 ? 'bi-caret-down-fill' : 'bi-dash'}"></i>
                        <b>${node.cate_name}</b> (ID: ${node.cate_id})
                     </a>`;
        if (node.children.length > 0) {
            html += generateTreeHTML(node.children);
        }
        html += '</li>';
    }
    html += '</ul>';
    return html;
}

// 填充右側的編輯表單
function populateEditForm(id, name, desc, parentId) {
    document.getElementById('edit-form-header').textContent = `編輯分類：${name}`;
    document.getElementById('edit-cate-id').value = id;
    document.getElementById('edit-cate-name').value = name;
    document.getElementById('edit-cate-desc').value = desc;
    document.getElementById('edit-parent-cate').value = parentId || 0;
    document.getElementById('delete-category-btn').disabled = false;
}

// 清空表單，用於新增分類
function clearEditForm() {
    document.getElementById('edit-form-header').textContent = '新增分類';
    document.getElementById('category-edit-form').reset();
    document.getElementById('edit-cate-id').value = '';
    document.getElementById('delete-category-btn').disabled = true;
}

/**
 * 初始化頁面的主要函式
 * @description 取得資料、建立 DOM 並綁定事件
 */
export default async function init() {
    try {
        const categories = await fetchCategories();
        const treeData = buildCategoryTree(categories);
        const treeHTML = generateTreeHTML(treeData);

        const container = document.getElementById('category-tree-container');
        container.innerHTML = treeHTML;

        // 將所有分類填入父分類的下拉選單
        const select = document.getElementById('edit-parent-cate');
        select.innerHTML = '<option value="0">（此為頂層分類）</option>';
        categories.forEach(cat => {
            const option = document.createElement('option');
            option.value = cat.cate_id;
            option.textContent = `${cat.cate_name} (ID: ${cat.cate_id})`;
            select.appendChild(option);
        });

        // 綁定事件
        container.addEventListener('click', function (event) {
            event.preventDefault();
            const targetLi = event.target.closest('li');
            if (targetLi) {
                const { id, name, desc, parentId } = targetLi.dataset;
                populateEditForm(id, name, desc, parentId);
            }
        });

        document.getElementById('add-new-category-btn').addEventListener('click', clearEditForm);

    } catch (error) {
        console.error('初始化分類頁面時發生錯誤:', error);
        document.getElementById('category-tree-container').innerHTML = `<div class="alert alert-danger">${error.message}</div>`;
    }
}

console.log("hello");

