package s03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/me?serverTimezone=Europe/Rome";
    private static final String USER = "me";
    private static final String PASSWORD = "password";

    /**
     * The following static initializer should not be required anymore
     */
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException cnfe) {
//            cnfe.printStackTrace();
//            throw new IllegalStateException("Can't load JDBC driver " + cnfe.getMessage());
//        }
//    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String user = conn.getCatalog();
            if (user == null) {
                user = conn.getSchema();  // check both methods getCatalog and getSchema in caso uno non funzioni. 
            }

            System.out.println("Connected as " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
