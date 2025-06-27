package crowdfund.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;



import crowdfund.bean.CampaignBean;
import crowdfund.util.JDBCUtil;

public class FundraisingDao {
	
//	新增
	public void addCam(CampaignBean cam) {
		String SQL = "INSERT INTO campaigns(campaign_name, campaign_category, goal_amount, current_amount,"
				+ " start_date, end_date, cover_image, status,creator_id,description) values(?,?,?,?,?,?,?,?,?,?)";
		try (
				Connection conn = JDBCUtil.getConnection();
				
	             PreparedStatement stmt = conn.prepareStatement(SQL)) {

	            stmt.setString(1, cam.getCampaignName());
	            stmt.setString(2, cam.getCampaignCategory());
	            stmt.setDouble(3, cam.getGoalAmount());
	            stmt.setDouble(4, cam.getCurrentAmount());
	            stmt.setDate(5, cam.getStartDate());
	            stmt.setDate(6, cam.getEndDate());
	            stmt.setString(7, cam.getCoverImage());
	            stmt.setString(8, cam.getStatus());
	            stmt.setInt(9, cam.getCreatorID());
	            stmt.setString(10, cam.getDescription());

	            stmt.executeUpdate();
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
//	查詢全部
	public List<CampaignBean> getAllCams() {
		String SQL = "SELECT* FROM campaigns";
		List<CampaignBean> cams =new ArrayList<>();
		try(
				Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				ResultSet rs = stmt.executeQuery();) {
			CampaignBean cam = null;
			while(rs.next()) {
				cam = new CampaignBean();
				cam.setCampaignID(rs.getInt("campaign_id"));
				cam.setCampaignName(rs.getString("campaign_name"));
				cam.setCampaignCategory(rs.getString("campaign_category"));
				cam.setGoalAmount(rs.getDouble("goal_amount"));
				cam.setCurrentAmount(rs.getDouble("current_amount"));
				cam.setStartDate(rs.getDate("start_date"));
				cam.setEndDate(rs.getDate("end_date"));
				cam.setCoverImage(rs.getString("cover_image"));
				cam.setStatus(rs.getString("status"));
				cam.setCreatorID(rs.getInt("creator_id"));
				cam.setDescription(rs.getString("description"));
				cams.add(cam);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cams;
		
	}
//	查詢單項
	public CampaignBean getCamById(Integer campaign_id) {
		String SQL = "SELECT* FROM campaigns where campaign_id = ?";
		CampaignBean cam = new CampaignBean();
		try(	Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL);){
			stmt.setInt(1, campaign_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				cam.setCampaignID(rs.getInt("campaign_id"));
				cam.setCampaignName(rs.getString("campaign_name"));
				cam.setCampaignCategory(rs.getString("campaign_category"));
				cam.setGoalAmount(rs.getDouble("goal_amount"));
				cam.setCurrentAmount(rs.getDouble("current_amount"));
				cam.setStartDate(rs.getDate("start_date"));
				cam.setEndDate(rs.getDate("end_date"));
				cam.setCoverImage(rs.getString("cover_image"));
				cam.setStatus(rs.getString("status"));
				cam.setCreatorID(rs.getInt("creator_id"));
				cam.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return cam;
		
	}
//	依據名稱查詢
	public List<CampaignBean> SearchByName(String campaignName){
		String SQL ="SELECT * FROM CAMPAIGNS WHERE CAMPAIGN_NAME LIKE ?";
		List<CampaignBean> cams = new ArrayList<>();
		try(	Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt= conn.prepareStatement(SQL);
				){
			stmt.setString(1, "%"+campaignName+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CampaignBean cam = new CampaignBean();
				cam.setCampaignID(rs.getInt("campaign_id"));
				cam.setCampaignName(rs.getString("campaign_name"));
				cam.setCampaignCategory(rs.getString("campaign_category"));
				cam.setGoalAmount(rs.getDouble("goal_amount"));
				cam.setCurrentAmount(rs.getDouble("current_amount"));
				cam.setStartDate(rs.getDate("start_date"));
				cam.setEndDate(rs.getDate("end_date"));
				cam.setCoverImage(rs.getString("cover_image"));
				cam.setStatus(rs.getString("status"));
				cam.setCreatorID(rs.getInt("creator_id"));
				cam.setDescription(rs.getString("description"));
				cams.add(cam);
		} }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cams;
		
	}
//	依據活動類別查詢
	public List<CampaignBean> SearchByCategory(String campaignCategory){
		String SQL ="SELECT * FROM CAMPAIGNS WHERE CAMPAIGN_Category LIKE ?";
		List<CampaignBean> cams = new ArrayList<>();
		try(	Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, "%"+campaignCategory+"%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CampaignBean cam = new CampaignBean();
				cam.setCampaignID(rs.getInt("campaign_id"));
				cam.setCampaignName(rs.getString("campaign_name"));
				cam.setCampaignCategory(rs.getString("campaign_category"));
				cam.setGoalAmount(rs.getDouble("goal_amount"));
				cam.setCurrentAmount(rs.getDouble("current_amount"));
				cam.setStartDate(rs.getDate("start_date"));
				cam.setEndDate(rs.getDate("end_date"));
				cam.setCoverImage(rs.getString("cover_image"));
				cam.setStatus(rs.getString("status"));
				cam.setCreatorID(rs.getInt("creator_id"));
				cam.setDescription(rs.getString("description"));
				cams.add(cam);
		} }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cams;
	}
//	依據活動狀態查詢
	public List<CampaignBean> SearchByStatus(String status){
		String SQL ="SELECT * FROM CAMPAIGNS WHERE status LIKE ?";
		List<CampaignBean> cams = new ArrayList<>();
		try(	Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				){
			stmt.setString(1, "%"+status+"%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CampaignBean cam = new CampaignBean();
				cam.setCampaignID(rs.getInt("campaign_id"));
				cam.setCampaignName(rs.getString("campaign_name"));
				cam.setCampaignCategory(rs.getString("campaign_category"));
				cam.setGoalAmount(rs.getDouble("goal_amount"));
				cam.setCurrentAmount(rs.getDouble("current_amount"));
				cam.setStartDate(rs.getDate("start_date"));
				cam.setEndDate(rs.getDate("end_date"));
				cam.setCoverImage(rs.getString("cover_image"));
				cam.setStatus(rs.getString("status"));
				cam.setCreatorID(rs.getInt("creator_id"));
				cam.setDescription(rs.getString("description"));
				cams.add(cam);
			} }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return cams;
	}
//	依據日期查詢
	public List<CampaignBean> SearchByStartDate(Date from, Date to){
		String SQL = "SELECT * FROM campaigns WHERE start_date BETWEEN ? AND ?";
		List<CampaignBean> cams = new ArrayList<>();
	    try (Connection conn = JDBCUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(SQL)) {
	        
	        stmt.setDate(1, from);
	        stmt.setDate(2, to);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            CampaignBean cam = new CampaignBean();
	            cam.setCampaignID(rs.getInt("campaign_id"));
	            cam.setCampaignName(rs.getString("campaign_name"));
	            cam.setStartDate(rs.getDate("start_date"));
	            cam.setEndDate(rs.getDate("end_date"));
	            cam.setCoverImage(rs.getString("cover_image"));
	            cam.setGoalAmount(rs.getDouble("goal_amount"));
	            cam.setCurrentAmount(rs.getDouble("current_amount"));
	            cam.setStatus(rs.getString("status"));
	            cam.setCampaignCategory(rs.getString("campaign_category"));
	            cam.setCreatorID(rs.getInt("creator_id"));
	            cam.setDescription(rs.getString("description"));
	            cams.add(cam);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return cams;
		
	}
//	刪除
	public void deleteCamById(Integer campaign_ID) {
		String SQL = "DELETE FROM CAMPAIGNS WHERE CAMPAIGN_ID=?";
		try (
				Connection conn = JDBCUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL);
				
				){
			stmt.setInt(1, campaign_ID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public CampaignBean updateCam(CampaignBean cam) {
		String SQL = "UPDATE campaigns SET campaign_name=?, campaign_category=?, goal_amount=?, current_amount=?, "
	               + "start_date=?, end_date=?, cover_image=?,status=?, creator_id=?, description=? WHERE campaign_id=?";
	    try (Connection conn = JDBCUtil.getConnection();
	    		PreparedStatement stmt = conn.prepareStatement(SQL);){
	    	 stmt.setString(1, cam.getCampaignName());
	         stmt.setString(2, cam.getCampaignCategory());
	         stmt.setDouble(3, cam.getGoalAmount());
	         stmt.setDouble(4, cam.getCurrentAmount());
	         stmt.setDate(5, cam.getStartDate());
	         stmt.setDate(6, cam.getEndDate());
	         stmt.setString(7, cam.getCoverImage());
	         stmt.setString(8, cam.getStatus());
	         stmt.setInt(9, cam.getCreatorID());
	         stmt.setString(10, cam.getDescription());
	         stmt.setInt(11, cam.getCampaignID());

	         stmt.executeUpdate();
	    	
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cam;
	}

}
