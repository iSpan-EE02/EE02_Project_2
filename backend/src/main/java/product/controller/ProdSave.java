package product.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import product.bean.ProdCateBean;
import product.dao.ProdCateDao;
import product.util.GsonUtils;

/**
 * Servlet implementation class DaoQueryAll
 */
@WebServlet("/ProdSave")
@MultipartConfig
public class ProdSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdSave() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HashMap<String, Object> responseData = new HashMap<>();
        
        // text parts
        Map<String, String> fields = new HashMap<>();
        // image parts
        List<Part> newImageParts = new ArrayList<>();
        Map<String, Part> existingImageParts = new HashMap<>();
        //SKU parts
        List<String> newSkuJsonStrings = new ArrayList<>();
        Map<String, String> existingSkuJsonStrings = new HashMap<>();
        
		
		// 準備一個 Map 來存放最終要回傳的 JSON 物件的結構

		try {
			
			for (Part part : request.getParts()) {
                String partName = part.getName();

                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                    // 這是一個檔案 Part
                    if (partName.equals("new_prod_img")) {
                        newImageParts.add(part);
                    } else if (partName.startsWith("existing_prod_img_")) {
                        String imageId = partName.substring("existing_prod_img_".length());
                        existingImageParts.put(imageId, part);
                    }
                } else {
                    // 這是一個普通的表單字段 Part
                	if (partName.equals("new_sku")) {
                        newSkuJsonStrings.add(getValue(part));
                    } else if (partName.startsWith("existing_sku_")) {
                        String skuId = partName.substring("existing_sku_".length());
                        existingSkuJsonStrings.put(skuId, getValue(part));
                    } else {
                        fields.put(partName, getValue(part));
                    }
                }
            }
			
			String prodId = fields.get("prod-id");
            String prodName = fields.get("prod-name");
            String deletedImages = fields.get("deleted_images");
            
            boolean isNewProduct = (prodId == null || prodId.isEmpty());
			
		   
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getValue(Part part) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
		StringBuilder value = new StringBuilder();
		char[] buffer = new char[1024];
		for (int length = 0; (length = reader.read(buffer)) > 0;) {
			value.append(buffer, 0, length);
		}
		return value.toString();
	}

}
