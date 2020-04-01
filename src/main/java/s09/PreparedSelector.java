package s09;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import s08.Coder;

public class PreparedSelector {
    private static final String URL = "jdbc:mysql://localhost:3306/me?serverTimezone=Europe/Rome";
    private static final String USER = "me";
    private static final String PASSWORD = "password";

    private static final String CODERS_BY_SALARY = "SELECT first_name, last_name, salary FROM coders "
            + "WHERE salary >= ? ORDER BY 3 DESC";
    

    public List<Coder> getCodersBySalary(double lower) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement prepStmt = conn.prepareStatement(CODERS_BY_SALARY)) {
            prepStmt.setDouble(1, lower);                		 //bundary on the statement per evitare SQL injection, 
            											  		 //va a vedere dov'è il punto di domanda e sostituisce il valore così. 

            System.out.println("I'm about to execute " + prepStmt);       
            List<Coder> results = new ArrayList<>();

            try (ResultSet rs = prepStmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Coder(rs.getString(1), rs.getString(2), rs.getInt(3)));
                }
            }

            return results;
        }
    }
    
    private static final String CODERS_BY_DATE = "SELECT first_name, last_name, hire_date FROM coders WHERE hire_date < ? ORDER BY 3";
    
    
    public List<Coder> getCodersByHireDate(LocalDate limit) throws SQLException{
		
		 try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	                PreparedStatement prepStmt = conn.prepareStatement(CODERS_BY_DATE)) {
	            prepStmt.setDate(1, Date.valueOf(limit));           

	            System.out.println("I'm about to execute " + prepStmt);       
	            List<Coder> results = new ArrayList<>();

	            try (ResultSet rs = prepStmt.executeQuery()) {
	                while (rs.next()) {
	                    results.add(new Coder(rs.getString(1), rs.getString(2), rs.getDate(3)));
	                }
	            }

	            return results;
	        }
	}

    public static void main(String[] args) {
        try {
            PreparedSelector sample = new PreparedSelector();
            System.out.println("Rich coders are: " + sample.getCodersBySalary(6000));
            System.out.println("Mature coders are: " + sample.getCodersByHireDate(LocalDate.of(2007, Month.JANUARY, 1)));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
}