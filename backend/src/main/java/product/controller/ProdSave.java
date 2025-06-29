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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import product.bean.ProdBean;
import product.bean.ProdImagesBean;
import product.bean.ProdSkusBean;
import product.dao.ProdDao;
import product.dao.ProdImagesDao;
import product.dao.ProdSkusDao;
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
		// SKU parts
		List<Part> newSkuJsonStrings = new ArrayList<>();


		// 上傳位置

		try {

			for (Part part : request.getParts()) {
				String partName = part.getName();

				if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
					// 這是一個檔案 Part
					if (partName.equals("new_prod_img")) {
						newImageParts.add(part);
						System.out.println("找到一張");
					} 
				} else {
					// 這是一個普通的表單字段 Part
					if (partName.startsWith("sku-item-code")) {
						newSkuJsonStrings.add(part);
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
				// prodDao
				prodBean.setProd_name(prodName);
				prodBean.setProd_cate_id(Integer.parseInt(prodCateId));
				prodBean.setProd_desc(prodDesc);
				prodBean.setProd_status(Integer.parseInt(prodStatus));
				newId = prodDao.insertProd(prodBean);
				// prodImagesDao
				// prodSkusDao

			} else {
				prodBean.setProd_id(Integer.parseInt(prodId));
				prodBean.setProd_name(prodName);
				prodBean.setProd_cate_id(Integer.parseInt(prodCateId));
				prodBean.setProd_desc(prodDesc);
				prodBean.setProd_status(Integer.parseInt(prodStatus));
				prodDao.updateProd(prodBean);
				// prodImagesDao
				// prodSkusDao
			}

			if (isNewProduct) {
				// prodImagesDao

				for (int i = 0; i < newImageParts.size(); i++) {

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

					if (i == 0) {
						// 存到資料庫
						ProdImagesBean prodImagesBean = new ProdImagesBean();
						prodImagesBean.setImage_url(savePath);
						prodImagesBean.setIs_primary(1);
						prodImagesBean.setProd_id(newId);
						prodImagesBean.setSort_order(1);
						ProdImagesDao prodImagesDao = new ProdImagesDao();
						prodImagesDao.insertProdImage(prodImagesBean);
					} else {
						ProdImagesBean prodImagesBean = new ProdImagesBean();
						prodImagesBean.setImage_url(savePath);
						prodImagesBean.setIs_primary(0);
						prodImagesBean.setProd_id(newId);
						prodImagesBean.setSort_order(i + 1);
						ProdImagesDao prodImagesDao = new ProdImagesDao();
						prodImagesDao.insertProdImage(prodImagesBean);
					}
				}

			} else {
				// prodImagesDao
				String deletIdStr = request.getParameter("delete-item");
				System.out.println(deletIdStr);

				if (deletIdStr != null && !deletIdStr.isEmpty()) {
					List<Integer> idsToDelete = Arrays.stream(deletIdStr.split(",")).filter(idStr -> idStr != null && !idStr.trim().isEmpty()).map(Integer::parseInt)
							.collect(Collectors.toList());
					 for (Integer imageId : idsToDelete) {
						ProdImagesDao imgDao = new ProdImagesDao();
						imgDao.deleteProdImage(imageId);
					 }
				}
				
				ProdImagesDao imgDao = new ProdImagesDao();
				Integer isPrimary = imgDao.queryExist(Integer.parseInt(prodId));
				
				
				for (int i = 0; i < newImageParts.size(); i++) {

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

					if (i == 0) {
						// 存到資料庫
						ProdImagesBean prodImagesBean = new ProdImagesBean();
						prodImagesBean.setImage_url(savePath);
						prodImagesBean.setIs_primary(1);
						prodImagesBean.setProd_id(Integer.parseInt(prodId));
						if (isPrimary == 1) {
							prodImagesBean.setIs_primary(0);
						}else {
							prodImagesBean.setIs_primary(1);							
						}
						prodImagesBean.setSort_order(i + 1);
						ProdImagesDao prodImagesDao = new ProdImagesDao();
						prodImagesDao.insertProdImage(prodImagesBean);
						System.out.println("第一張" + firstImage.getName());
					} else {
						ProdImagesBean prodImagesBean = new ProdImagesBean();
						prodImagesBean.setImage_url(savePath);
						isPrimary = imgDao.queryExist(Integer.parseInt(prodId));
						if (isPrimary == 1) {
							prodImagesBean.setIs_primary(0);
						}else {
							prodImagesBean.setIs_primary(1);							
						}
						prodImagesBean.setProd_id(Integer.parseInt(prodId));
						prodImagesBean.setSort_order(i + 1);
						ProdImagesDao prodImagesDao = new ProdImagesDao();
						prodImagesDao.insertProdImage(prodImagesBean);
						System.out.println("第"+i+"張" + firstImage.getName());
					}
				}

				
				
				
				
				
				
				
			}

			// 處理SKU,先全部刪除,再逐筆添加

			if (isNewProduct) {
				ProdSkusDao prodSkusDao = new ProdSkusDao();
				prodSkusDao.deleteProdSku(newId);
			} else {
				ProdSkusDao prodSkusDao = new ProdSkusDao();
				prodSkusDao.deleteProdSku(Integer.parseInt(prodId));
			}

			if (isNewProduct) {
				for (int j = 0; j < newSkuJsonStrings.size(); j++) {
					ProdSkusDao prodSkusDao = new ProdSkusDao();
					String name = newSkuJsonStrings.get(j).getName();
					String count = String.valueOf(name.charAt(name.length() - 2));
					System.out.println(count);
					ProdSkusBean prodSkusBean = new ProdSkusBean();
					prodSkusBean.setProd_id(newId);
					prodSkusBean.setSku_code(request.getParameter("sku-item-code[" + count + "]"));
					prodSkusBean.setPrice(new BigDecimal(request.getParameter("sku-item-price[" + count + "]")));
					prodSkusBean
							.setStock_quantity(Integer.parseInt(request.getParameter("sku-item-stock[" + count + "]")));
					prodSkusDao.insertProdSku(prodSkusBean);
				}
			} else {
				for (int j = 0; j < newSkuJsonStrings.size(); j++) {
					ProdSkusDao prodSkusDao = new ProdSkusDao();
					String name = newSkuJsonStrings.get(j).getName();
					String count = String.valueOf(name.charAt(name.length() - 2));
					System.out.println(count);
					ProdSkusBean prodSkusBean = new ProdSkusBean();
					prodSkusBean.setProd_id(Integer.parseInt(prodId));
					prodSkusBean.setSku_code(request.getParameter("sku-item-code[" + count + "]"));
					prodSkusBean.setPrice(new BigDecimal(request.getParameter("sku-item-price[" + count + "]")));
					prodSkusBean
							.setStock_quantity(Integer.parseInt(request.getParameter("sku-item-stock[" + count + "]")));
					prodSkusDao.insertProdSku(prodSkusBean);
				}
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
