import {
  fetchCategoriesProd,
  fetchProducts,
  fetchImage,
  fetchSku,
} from "../feature/product/js/api-client.js";

export default async function init() {
  try {
    const newProducts = await fetchProducts();
    const prodCount = newProducts.length;
    document.getElementById("dashboard-prod-count").innerText = prodCount;
    console.log("總商品數量" + prodCount);
  } catch (error) {
    console.log(error);
  }
}
