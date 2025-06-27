package promotion.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import promotion.util.JDBCUtil;
import promotion.bean.promotionBean;

public class promotionDao  {
	
		public promotionDao() {
        try{
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");        	
        }catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
    }
		public promotionBean insertPromotion(promotionBean insertprom) {
			
			try(Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO Promotion(title,description,type,validityType,validityFrom,validityTo,validityDays,active,note,image) VALUES (?,?,?,?,?,?,?,?,?,?)")){
				
				stmt.setString(1, insertprom.getTitle());
				stmt.setString(2, insertprom.getDescription());
				stmt.setString(3, insertprom.getType());
				stmt.setString(4, insertprom.getValidityType());
				stmt.setString(5, insertprom.getValidityFrom());
				stmt.setString(6, insertprom.getValidityTo());
				stmt.setString(7, insertprom.getValidityDays());
				stmt.setString(8, insertprom.getActive());
				stmt.setString(9, insertprom.getNote());
				stmt.setString(10, insertprom.getImage());
				
				int rs = stmt.executeUpdate();
				if (rs > 0) {
					return insertprom;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			return null;
					
		}
		
		public List<promotionBean> getAllProms() {
			List <promotionBean> proms = new ArrayList<>();
			try(Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Promotion")){
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					promotionBean prom = new promotionBean();
					prom.setPromotionId(rs.getString("promotionId"));
					prom.setTitle(rs.getString("title"));
					prom.setDescription(rs.getString("description"));
					prom.setType(rs.getString("type"));
					prom.setValidityType(rs.getString("validityType"));
					prom.setValidityFrom(rs.getString("validityFrom"));
					prom.setValidityTo(rs.getString("validityTo"));
					prom.setValidityDays(rs.getString("validityDays"));
					prom.setActive(rs.getString("active"));
					prom.setCreatedAt(rs.getString("createdAt"));
					prom.setUpdatedAt(rs.getString("updatedAt"));
					prom.setNote(rs.getString("note"));
					prom.setImage(rs.getString("image"));
					proms.add(prom);
				}
			}catch (Exception e){
				e.printStackTrace();
			}
			return proms;
					
		}
		
		public boolean DeletepromById(String promotionId) {
			
			try(Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement("DELETE FROM Promotion WHERE promotionId=?")){
				
				stmt.setString(1, promotionId);			
				
				int rs = stmt.executeUpdate();
				return rs > 0;
				
			}catch (Exception e){
				e.printStackTrace();
			}
			return false;
					
		}

		public promotionBean getpromById(String promotionId) {
			promotionBean prom = null;

	    	try (Connection conn = JDBCUtil.getConnection();
	    		 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Promotion WHERE promotionId = ?")) {

	    		stmt.setString(1, promotionId);
	    		ResultSet rs = stmt.executeQuery();

	    		if (rs.next()) {
	    			prom = new promotionBean();
					prom.setPromotionId(rs.getString("promotionId"));
					prom.setTitle(rs.getString("title"));
					prom.setDescription(rs.getString("description"));
					prom.setType(rs.getString("type"));
					prom.setValidityType(rs.getString("validityType"));
					prom.setValidityFrom(rs.getString("validityFrom"));
					prom.setValidityTo(rs.getString("validityTo"));
					prom.setValidityDays(rs.getString("validityDays"));
					prom.setActive(rs.getString("active"));
					prom.setCreatedAt(rs.getString("createdAt"));
					prom.setUpdatedAt(rs.getString("updatedAt"));
					prom.setNote(rs.getString("note"));
					prom.setImage(rs.getString("image"));
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return prom;
	    }
		
		
		public List<promotionBean> searchByTitle(String keyword){
			List<promotionBean> list = new ArrayList<>();
			
			try(Connection conn = JDBCUtil.getConnection();
					PreparedStatement stmt = conn.prepareStatement
							("SELECT promotionId,title,description,type,validityType,validityFrom,validityTo,"
									+ "validityDays,active,createdAt,updatedAt,note,image FROM Promotion WHERE title LIKE ?")){
					
					stmt.setString(1, "%" +keyword + "%");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						promotionBean prom = new promotionBean();
						prom.setPromotionId(rs.getString("promotionId"));
						prom.setTitle(rs.getString("title"));
						prom.setDescription(rs.getString("description"));
						prom.setType(rs.getString("type"));
						prom.setValidityType(rs.getString("validityType"));
						prom.setValidityFrom(rs.getString("validityFrom"));
						prom.setValidityTo(rs.getString("validityTo"));
						prom.setValidityDays(rs.getString("validityDays"));
						prom.setActive(rs.getString("active"));
						prom.setCreatedAt(rs.getString("createdAt"));
						prom.setUpdatedAt(rs.getString("updatedAt"));
						prom.setNote(rs.getString("note"));
						prom.setImage(rs.getString("image"));
						list.add(prom);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				return list;
			
		}
		
		public List<promotionBean> searchByType(String type){
			List<promotionBean> list = new ArrayList<>();
			
			try(Connection conn = JDBCUtil.getConnection();
					PreparedStatement stmt = conn.prepareStatement
							("SELECT * FROM Promotion WHERE type LIKE ?")){
					
					stmt.setString(1, "%" +type + "%");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						promotionBean prom = new promotionBean();
						prom.setPromotionId(rs.getString("promotionId"));
						prom.setTitle(rs.getString("title"));
						prom.setDescription(rs.getString("description"));
						prom.setType(rs.getString("type"));
						prom.setValidityType(rs.getString("validityType"));
						prom.setValidityFrom(rs.getString("validityFrom"));
						prom.setValidityTo(rs.getString("validityTo"));
						prom.setValidityDays(rs.getString("validityDays"));
						prom.setActive(rs.getString("active"));
						prom.setCreatedAt(rs.getString("createdAt"));
						prom.setUpdatedAt(rs.getString("updatedAt"));
						prom.setNote(rs.getString("note"));
						prom.setImage(rs.getString("image"));
						list.add(prom);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				return list;
			
		}
		
		public List<promotionBean> searchByTitleAndType(String keyword,String type){
			List<promotionBean> list = new ArrayList<>();
			
			try(Connection conn = JDBCUtil.getConnection();
					PreparedStatement stmt = conn.prepareStatement
							("SELECT * FROM Promotion WHERE title LIKE? AND type LIKE ?")){
					
					stmt.setString(1, "%" +keyword + "%");	
					stmt.setString(2, "%" +type + "%");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						promotionBean prom = new promotionBean();
						prom.setPromotionId(rs.getString("promotionId"));
						prom.setTitle(rs.getString("title"));
						prom.setDescription(rs.getString("description"));
						prom.setType(rs.getString("type"));
						prom.setValidityType(rs.getString("validityType"));
						prom.setValidityFrom(rs.getString("validityFrom"));
						prom.setValidityTo(rs.getString("validityTo"));
						prom.setValidityDays(rs.getString("validityDays"));
						prom.setActive(rs.getString("active"));
						prom.setCreatedAt(rs.getString("createdAt"));
						prom.setUpdatedAt(rs.getString("updatedAt"));
						prom.setNote(rs.getString("note"));
						prom.setImage(rs.getString("image"));
						list.add(prom);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				return list;
			
		}
		
		public boolean UpdatepromById(promotionBean prom) {
			
			
			try(Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement("UPDATE Promotion SET title=?,description=?,type=?,validityType=?,validityFrom=?,validityTo=?,validityDays=?,active=?,note=?,image=? WHERE promotionId=?")){
				
				stmt.setString(11, prom.getPromotionId());
				stmt.setString(1, prom.getTitle());
				stmt.setString(2, prom.getDescription());
				stmt.setString(3, prom.getType());
				stmt.setString(4, prom.getValidityType());
				stmt.setString(5, prom.getValidityFrom());
				stmt.setString(6, prom.getValidityTo());
				stmt.setString(7, prom.getValidityDays());
				stmt.setString(8, prom.getActive());
				stmt.setString(9, prom.getNote());
				stmt.setString(10, prom.getImage());
				
				int rs = stmt.executeUpdate();
				return rs>0;
			
			}catch (Exception e){
				e.printStackTrace();
			}return false;
				
			}
					
		}	
		


