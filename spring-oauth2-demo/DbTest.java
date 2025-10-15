import java.sql.*;

public class DbTest {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/oauth2db?useSSL=false&serverTimezone=UTC",
                "root",
                "171717"
        );
        System.out.println("Connected to DB!");
        conn.close();
    }
}
