package product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import product.bean.ProdCateBean;
import product.dao.ProdCateDao;
import product.util.GsonUtils;


/**
 * Servlet implementation class DaoQueryAll
 */
@WebServlet("/ProdCateQueryAllProd")
public class ProdCateQueryAllProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdCateQueryAllProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// 準備一個 Map 來存放最終要回傳的 JSON 物件的結構
		HashMap<String, Object> responseData = new HashMap<>();
		List<ProdCateBean> prodCateList = null;
		ProdCateDao prodCateDao = new ProdCateDao();		
		try {
			// 1. 取得列表
			prodCateList =  prodCateDao.queryAllProd();
			
			// 2. 準備成功時的回應資料
			responseData.put("status", "success");
			responseData.put("message", String.format("成功查詢到 %d 筆資料。", prodCateList.size()));
			responseData.put("itemCount", prodCateList.size());
			responseData.put("data", prodCateList);
		} catch (Exception e) {
			 // 3. 如果在過程中發生任何錯誤 (例如資料庫連線失敗)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 設定 HTTP 狀態碼為 500
            
            responseData.put("status", "error");
            responseData.put("message", "查詢資料時發生內部錯誤：" + e.getMessage());
            responseData.put("data", new ArrayList<>()); // 返回一個空陣列

            // 在伺服器控制台印出錯誤堆疊
            e.printStackTrace();
		}
		// 4. 設定回應的內容類型 (Content-Type) 和編碼
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
     // 5. 使用 Gson 將 Map 物件序列化為 JSON 字串並回傳
        PrintWriter out = response.getWriter();
        out.print(GsonUtils.toJson(responseData));
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
