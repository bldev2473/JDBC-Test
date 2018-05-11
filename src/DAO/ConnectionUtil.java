package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // 초기화
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 객체 생성 없이 메소드 사용 가능 (클래스 메소드)
    // getConnection() 메소드를 호출하는 쪽에서 SQLException 처리를 해주어야함
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:59162:XE", "system", "oracle");
    }

    public static void closeConnection(PreparedStatement pstmt, Connection con) throws SQLException {
        if (pstmt != null)
            pstmt.close();
        if (con != null)
            con.close();
    }
}
