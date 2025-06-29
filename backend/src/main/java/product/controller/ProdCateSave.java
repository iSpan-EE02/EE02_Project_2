package product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;



import product.bean.ProdCateBean;
import product.dao.ProdCateDao;
import product.util.GsonUtils;


/**
 * Servlet implementation class DaoQueryAll
 */
@WebServlet("/ProdCateSave")
public class ProdCateSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdCateSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String jsonRequest = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
		System.out.println(jsonRequest);
		ProdCateBean cate = GsonUtils.fromJson(jsonRequest, ProdCateBean.class);
		// 準備一個 Map 來存放最終要回傳的 JSON 物件的結構
		HashMap<String, Object> responseData = new HashMap<>();
		
		try {
//			測試request取回的值
//			System.out.println(cate.getCate_id());
//			System.out.println(cate.getCate_name());
//			System.out.println(cate.getCate_desc());
//			System.out.println(cate.getParent_cate_id());
			//判斷是insert還是update
			ProdCateDao prodCateDao = new ProdCateDao();
			if(cate.getCate_id() != null) {
				//修改
				System.out.println("修改");
				prodCateDao.updateProdCate(cate);
			}else {
				//新增
				System.out.println("新增");
				prodCateDao.insertProdCate(cate);
				
			}
			responseData.put("status", "success");
			responseData.put("message", "已成功儲存資料");

		} catch (Exception e) {
			 // 3. 如果在過程中發生任何錯誤 (例如資料庫連線失敗)
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 設定 HTTP 狀態碼為 500
            
            responseData.put("status", "error");
            responseData.put("message", "儲存資料時發生內部錯誤：" + e.getMessage());
            

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
