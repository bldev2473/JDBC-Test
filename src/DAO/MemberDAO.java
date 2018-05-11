package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Member;

public class MemberDAO {
    Connection con = null;
    PreparedStatement pstmt = null;

    public void JDBCsqlInsert(Member mem) {
        try {
            con = ConnectionUtil.getConnection();
            String sqlInsert = "insert into Member values(?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setString(1, mem.getId());
            pstmt.setString(2, mem.getPw());
            pstmt.setString(3, mem.getName());
            pstmt.setString(4, mem.getGender());
            pstmt.setString(5, mem.getBirth());
            pstmt.setString(6, mem.getAddress());
            pstmt.setString(7, mem.getPhone_number());
            int result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlUpdate(String memberId, String attr, String modification) {
        try {
            con = ConnectionUtil.getConnection();
            String sqlUpdate = "update Member set " + attr + " = ?" + " where member_id = ?";
            pstmt = con.prepareStatement(sqlUpdate);
            pstmt.setString(1, modification);
            pstmt.setString(2, memberId);
            int result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlDelete(String id) {
        try {
            con = ConnectionUtil.getConnection();
            String sqlDelete = "delete from Member where member_id = ?";
            pstmt = con.prepareStatement(sqlDelete);
            pstmt.setString(1, id);
            int result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlSelectAllWhereId(String id) {
        try {
            con = ConnectionUtil.getConnection();
            String sqlSelect = "select * from Member where member_id = ?";
            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String selectResultStr = "아이디: " + rs.getString(1) + "\n" + "이름: " + rs.getString(3) + "\n" + "성별: "
                        + rs.getString(4) + "\n" + "생년월일: " + rs.getString(5) + "\n" + "주소:" + rs.getString(6) + "\n"
                        + "연락처: " + rs.getString(7);
                System.out.println(selectResultStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String JDBCsqlSelectPwWhereId(String id) {
        String resultPw = "";
        try {
            con = ConnectionUtil.getConnection();
            String sqlSelect = "select member_pw from Member where member_id = ?";
            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultPw = rs.getString("member_pw");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultPw;
    }

    public void JDBCsqlSelectAll() {
        try {
            con = ConnectionUtil.getConnection();
            String sqlSelectAll = "select * from Member";
            pstmt = con.prepareStatement(sqlSelectAll);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String selectResultStr = "아이디: " + rs.getString(1) + "\t" + "이름: " + rs.getString(3) + "\t" + "성별: "
                        + rs.getString(4) + "\t" + "생년월일: " + rs.getString(5) + "\t" + "주소:" + rs.getString(6) + "\t"
                        + "연락처: " + rs.getString(7);
                System.out.println(selectResultStr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionUtil.closeConnection(pstmt, con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}