package s08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Selector {
	private static final String URL = "jdbc:mysql://localhost:3306/me?serverTimezone=Europe/Rome";
	private static final String USER = "me";
	private static final String PASSWORD = "password";

	public List<String> getCoderNames() throws SQLException {
		final String query = "SELECT first_name FROM coders ORDER BY 1"; // queries in formato String, non mettere il
																			// punto e virgola!!

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); // create connection
				Statement stmt = conn.createStatement(); // create statement
				ResultSet rs = stmt.executeQuery(query)) { // execute statement and obtain result set (assomiglia al
															// cursor, non molto piacevole da usare)
			List<String> results = new ArrayList<>(); // si crea una lista vuora per trasformare il resultset in lista
			while (rs.next()) { // while rs.next() == true
								// rs.next() naviga riga per riga.
				results.add(rs.getString(1)); // 1 si riferisce alla prima colonna, usa convenzione sql
			}

			return results;
		}
	}

	public List<Coder> getCoders() throws SQLException {
		final String query = "SELECT first_name, last_name, salary FROM coders ORDER BY 1";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			List<Coder> results = new ArrayList<>(); // Si crea una list di oggetti Coders
			while (rs.next()) {
				Coder coder = new Coder(rs.getString(1), rs.getString(2), rs.getInt(3)); // si creano gli oggetti Coder
																							// con gli attributi delle
																							// colonne della table sql
				results.add(coder);
			}

			return results;
		}
	}

	// Attenzione agli attacchi hacker!! SQL injection.
	// Se uno aggunge << "9000; drop table coders; select * from coders;" >> <-
	// perfettamente eseguibile dal punto di vista sql. Quindi nel frattempo ho
	// perso il table.

	public List<Coder> getCodersBySalary(double lower) throws SQLException { // il metodo per leggerli è sempre uguale,
																				// il result set che si ottiene dipende
																				// unicamente dalla query che si ottiene
		String query = "SELECT first_name, last_name, salary FROM coders WHERE salary >= " + lower + " ORDER BY 3 DESC";

		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement(); // <-- Non protegge da SQL injection.
				ResultSet rs = stmt.executeQuery(query)) {
			List<Coder> results = new ArrayList<>();
			while (rs.next()) {
				results.add(new Coder(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}

			return results;
		}
	}

	public static List<Coder> getCodersByHireDate(LocalDate limit) throws SQLException{
		String query = "SELECT first_name, last_name, hire_date FROM coders WHERE hire_date < '" + limit + "' ORDER BY 3";
		//System.out.println(query);
		
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			List<Coder> results = new ArrayList<>();
			while (rs.next()) {
				results.add(new Coder(rs.getString(1), rs.getString(2), rs.getDate(3)));
			}

			return results;
		}
	}
	
	

	public static void main(String[] args) {
		try {
			Selector sample = new Selector();

			System.out.println("Coder names are: " + sample.getCoderNames());
			System.out.println("Coders are: " + sample.getCoders());
			System.out.println("Rich coders are: " + sample.getCodersBySalary(6000));

			LocalDate limitDate = LocalDate.of(2007, Month.JANUARY, 1);
			List<Coder> codersByHireDate = getCodersByHireDate(limitDate);
			System.out.println("Coders hired before " + limitDate + " are:");
			System.out.println(codersByHireDate);

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}