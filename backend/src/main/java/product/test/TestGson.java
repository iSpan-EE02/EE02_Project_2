package product.test;

import com.google.gson.Gson;

import product.bean.ProdCateBean;

public class TestGson {
	public static void main(String[] args) {
		 // 建立 Gson 實例
        Gson gson = new Gson();
        
        // 物件 → JSON 序列化
        ProdCateBean prodBean = new ProdCateBean();
        prodBean.setCate_name("電腦");
        prodBean.setCate_desc("電子計算機");
        String json = gson.toJson(prodBean);
        System.out.println("轉換成 JSON: " + json); 
        // 輸出：{"name":"John","age":30}
        
        // JSON → 物件反序列化
        ProdCateBean parsedUser = gson.fromJson(json, ProdCateBean.class);
        System.out.println("反序列化的物件: " + parsedUser);
	}
}
