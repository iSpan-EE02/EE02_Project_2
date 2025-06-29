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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import product.bean.ProdBean;
import product.bean.ProdCateBean;
import product.bean.ProdImagesBean;
import product.dao.ProdCateDao;
import product.dao.ProdDao;
import product.dao.ProdImagesDao;
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
        // 準備一個 Map 來存放最終要回傳的 JSON 物件的結構
        HashMap<String, Object> responseData = new HashMap<>();
        
        // text parts
        Map<String, String> fields = new HashMap<>();
        // image parts
        List<Part> newImageParts = new ArrayList<>();
        Map<String, Part> existingImageParts = new HashMap<>();
        //SKU parts
        List<String> newSkuJsonStrings = new ArrayList<>();
        Map<String, String> existingSkuJsonStrings = new HashMap<>();
        
        //上傳位置

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
            String prodCateId = fields.get("prod-cate-select");
            String prodDesc = fields.get("prod-desc");
            String prodStatus = fields.get("prodStatus");
            Integer newId = 0;
//            String deletedImages = fields.get("deleted_images");
            
            boolean isNewProduct = (prodId == null || prodId.isEmpty());
			
            ProdBean prodBean = new ProdBean();
            ProdDao prodDao = new ProdDao();
            
            
            if (isNewProduct) {
				//prodDao
            	prodBean.setProd_name(prodName);
				prodBean.setProd_cate_id(Integer.parseInt(prodCateId));
				prodBean.setProd_desc(prodDesc);
				prodBean.setProd_status(Integer.parseInt(prodStatus));
				newId =  prodDao.insertProd(prodBean);
				//prodImagesDao
				//prodSkusDao
				
			}else {
				prodBean.setProd_id(Integer.parseInt(prodId));
				prodBean.setProd_name(prodName);
				prodBean.setProd_cate_id(Integer.parseInt(prodCateId));
				prodBean.setProd_desc(prodDesc);
				prodBean.setProd_status(Integer.parseInt(prodStatus));
				prodDao.updateProd(prodBean);
				//prodImagesDao
				//prodSkusDao
			}
            
            if (isNewProduct) {
				//prodImagesDao
				
				for (int i = 0; i < newImageParts.size(); i++) {
					if (i==0) {
						String webappRootPath = getServletContext().getRealPath("/");
						String uploadDir = "html/feature/product/img";
						Path uploadPath = Paths.get(webappRootPath, uploadDir);
						Part firstImage = newImageParts.get(i);
						String originalFileName = firstImage.getSubmittedFileName();
						String fileExtension = "";
						int f = originalFileName.lastIndexOf('.');
						fileExtension = originalFileName.substring(f);
						String newFileName = UUID.randomUUID().toString() + fileExtension;
						Path destinationPath = uploadPath.resolve(newFileName); 
						String savePath = "/Project2/" + uploadDir + "/" + newFileName;
						InputStream inputStream = firstImage.getInputStream();
						Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);
						
						//存到資料庫
						ProdImagesBean prodImagesBean = new ProdImagesBean();
						prodImagesBean.setImage_url(savePath);
						prodImagesBean.setIs_primary(1);
						prodImagesBean.setProd_id(newId);
						prodImagesBean.setSort_order(1);
						ProdImagesDao prodImagesDao = new ProdImagesDao();
						prodImagesDao.insertProdImage(prodImagesBean);
						System.out.println("檔案成功儲存");
					}
				}


			}else {
				//prodImagesDao
				
			}
            
            if (isNewProduct) {
				//prodSkusDao
				
			}else {

				//prodSkusDao
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
