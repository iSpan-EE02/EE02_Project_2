package product.test;

import com.google.gson.Gson;

import product.bean.ProdCateBean;

public class TestGson {
	public static void main(String[] args) {
		 // 建立 Gson 實例
        Gson gson = new Gson();
        
        // 物件 → JSON 序列化
        ProdCateBean empBean = new ProdCateBean();
        empBean.setDeptno("1001");
        empBean.setDname("admin");
        String json = gson.toJson(empBean);
        System.out.println("轉換成 JSON: " + json); 
        // 輸出：{"name":"John","age":30}
        
        // JSON → 物件反序列化
        ProdCateBean parsedUser = gson.fromJson(json, ProdCateBean.class);
        System.out.println("反序列化的物件: " + parsedUser);
	}
}
