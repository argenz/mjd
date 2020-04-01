package s10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactor {
    private static final String URL = "jdbc:mysql://localhost:3306/me?serverTimezone=Europe/Rome";
    private static final String USER = "me";
    private static final String PASSWORD = "password";

    public static void selectAllAndPrint(Statement stmt) throws SQLException {
        System.out.print("[");
        try (ResultSet rs = stmt.executeQuery("SELECT coder_id, first_name, last_name FROM coders")) {
            while (rs.next()) {
                System.out.print(String.format("(%d: %s %s)", //   concatenazione più leggibile, %d = numero decimale  %s = string
                        rs.getInt("coder_id"),                //  
                        rs.getString("first_name"),           //
                        rs.getString("last_name")));
            }
        } finally {
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement()) {   											 //Statement è l'oggetto che gestisce gli statement input per SQL. 
            System.out.print("By default, autocommit is " + conn.getAutoCommit());
            conn.setAutoCommit(false);
            System.out.println(". Here is set it to " + conn.getAutoCommit() + ".");

            System.out.println("Inserting new coder ...");
            stmt.executeUpdate("INSERT INTO coders VALUES(301, 'John', 'Coltrane', CURDATE(), 6000)");   //executeUpdate, executeQuery, executeEtc...
            selectAllAndPrint(stmt);
            System.out.println("Rollback");
            conn.rollback();        
            selectAllAndPrint(stmt);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}