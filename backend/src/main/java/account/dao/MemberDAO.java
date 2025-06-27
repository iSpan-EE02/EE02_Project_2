package account.dao;

import java.sql.*;
import java.util.*;

import account.bean.*;
import product.util.JDBCutil;


public class MemberDAO {

    public Member getById(int id) {
        String sql = "SELECT m.*, r.RANKNAME FROM MEM m " +
                     "LEFT JOIN MEM_RANK r ON m.RANKID = r.RANKID " +
                     "WHERE m.MEMid = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapMember(rs, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> getAll() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT m.*, r.RANKNAME FROM MEM m " +
                     "LEFT JOIN MEM_RANK r ON m.RANKID = r.RANKID";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapMember(rs, false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Member member) {
        String sql = "INSERT INTO MEM (MEMNAME, MEMGD, MEMPN, MEMIN, MEMADR, MEMMAIL, MEMBD, MEMPW, MEMDCT, RANKID, MEMUPDATEBY, MEMUPDATEAT, GIFTTOUSERID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, member);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Member member) {
        String sql = "UPDATE MEM SET MEMNAME = ?, MEMGD = ?, MEMPN = ?, MEMIN = ?, MEMADR = ?, MEMMAIL = ?, MEMBD = ?, MEMPW = ?, MEMDCT = ?, " +
                     "RANKID = ?, MEMUPDATEBY = ?, MEMUPDATEAT = ?, GIFTTOUSERID = ? WHERE MEMid = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            setParams(ps, member);
            ps.setInt(14, member.getMemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM MEM WHERE MEMid = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    private void setParams(PreparedStatement ps, Member m) throws SQLException {
        ps.setString(1, m.getMemName());
        ps.setString(2, m.getMemGd());
        ps.setString(3, m.getMemPn());
        ps.setString(4, m.getMemIn());
        ps.setString(5, m.getMemAdr());
        ps.setString(6, m.getMemMail());
        ps.setDate(7, m.getMemBd());
        ps.setString(8, m.getMemPw());
        ps.setTimestamp(9, m.getMemDct());
        ps.setInt(10, m.getRank() != null ? m.getRank().getRankId() : 0);
        ps.setInt(11, m.getMemUpdateBy() != null ? m.getMemUpdateBy().getEmpId() : 0);
        ps.setTimestamp(12, m.getMemUpdateAt());
        ps.setObject(13, m.getGiftToUser() != null ? m.getGiftToUser().getMemId() : null);
    }

    private Member mapMember(ResultSet rs, boolean deepGift) throws SQLException {
        Member m = new Member();
        m.setMemId(rs.getInt("MEMid"));
        m.setMemName(rs.getString("MEMNAME"));
        m.setMemGd(rs.getString("MEMGD"));
        m.setMemPn(rs.getString("MEMPN"));
        m.setMemIn(rs.getString("MEMIN"));
        m.setMemAdr(rs.getString("MEMADR"));
        m.setMemMail(rs.getString("MEMMAIL"));
        m.setMemBd(rs.getDate("MEMBD"));
        m.setMemPw(rs.getString("MEMPW"));
        m.setMemDct(rs.getTimestamp("MEMDCT"));

        // 會員等級
        MemberRank rank = new MemberRank();
        rank.setRankId(rs.getInt("RANKID"));
        rank.setRankName(rs.getString("RANKNAME"));
        m.setRank(rank);

        // 更新人員
        int updaterId = rs.getInt("MEMUPDATEBY");
        if (!rs.wasNull()) {
            EmployeeDAO empDao = new EmployeeDAO();
            Employee updater = empDao.getById(updaterId);
            m.setMemUpdateBy(updater);
        }

        m.setMemUpdateAt(rs.getTimestamp("MEMUPDATEAT"));

        // 贈送會員
        int giftId = rs.getInt("GIFTTOUSERID");
        if (!rs.wasNull() && deepGift) {
            m.setGiftToUser(getById(giftId)); 
        }

        return m;
    }
    public Member getByEmail(String email) {
        String sql = "SELECT m.*, r.RANKNAME FROM MEM m LEFT JOIN MEM_RANK r ON m.RANKID = r.RANKID WHERE MEMMAIL = ?";
        try (Connection conn = JDBCutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapMember(rs, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
