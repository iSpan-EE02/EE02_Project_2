package account.dao;

import java.sql.*;
import java.util.*;
import account.bean.MemberRank;
import product.util.JDBCutil;


public class MemberRankDAO {

    public List<MemberRank> getAll() {
        List<MemberRank> list = new ArrayList<>();
        String sql = "SELECT * FROM MEM_RANK";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MemberRank rank = new MemberRank();
                rank.setRankId(rs.getInt("RANKID"));
                rank.setRankName(rs.getString("RANKNAME"));
                list.add(rank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public MemberRank getById(int id) {
        String sql = "SELECT * FROM MEM_RANK WHERE RANKID = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                MemberRank rank = new MemberRank();
                rank.setRankId(rs.getInt("RANKID"));
                rank.setRankName(rs.getString("RANKNAME"));
                return rank;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void insert(MemberRank rank) {
        String sql = "INSERT INTO MEM_RANK (RANKID, RANKNAME) VALUES (?, ?)";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rank.getRankId());
            ps.setString(2, rank.getRankName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(MemberRank rank) {
        String sql = "UPDATE MEM_RANK SET RANKNAME = ? WHERE RANKID = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, rank.getRankName());
            ps.setInt(2, rank.getRankId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM MEM_RANK WHERE RANKID = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
