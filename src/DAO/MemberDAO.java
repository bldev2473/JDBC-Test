package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Member;

public class MemberDAO {
    public
    Connection con = null;
    PreparedStatement pstmt = null;

    public void JDBCsqlInsert(Member mem) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:59161:XE", "system", "oracle");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlUpdate(String memberId, String attr, String modification) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:59161:XE", "system", "oracle");
            String sqlUpdate = "update Member set " + attr + " = ?" + "  where member_id = ?";
            pstmt = con.prepareStatement(sqlUpdate);
            pstmt.setString(1, modification);
            pstmt.setString(2, memberId);
            int result = pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlDelete(String id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:59161:XE", "system", "oracle");
            String sqlDelete = "delete from Member where member_id = ?";
            pstmt = con.prepareStatement(sqlDelete);
            pstmt.setString(1, id);
            int result = pstmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void JDBCsqlSelect(String id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:59161:XE", "system", "oracle");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}