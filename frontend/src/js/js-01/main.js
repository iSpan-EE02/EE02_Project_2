// src/js/main.js
import 'bootstrap';
// 或者按需匯入特定元件（推薦）
import { Modal, Toast, Dropdown } from 'bootstrap';
// 在這裡初始化 Bootstrap 元件
document.querySelectorAll('[data-bs-toggle="dropdown"]').forEach(el => new Dropdown(el));