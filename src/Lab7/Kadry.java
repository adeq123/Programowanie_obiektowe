package Lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kadry {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://mysql.agh.edu.pl/kspyt", "kspyt", "zPxvWx9f");

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeWynagrodzenieBrutto(String pesel,
			double newWynagrodzenieBrutto) {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE prac SET wynagrodzenieBrutto ='"
					+ newWynagrodzenieBrutto + "' WHERE pesel = '" + pesel
					+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}

	}

	public void createTablePracownik() {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS prac ("
					+ "pesel CHAR(11)," + "wynagrodzenieBrutto DOUBLE,"
					+ "PRIMARY KEY (pesel));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}

	public void addPracownik(Pracownik pracownik) {
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO prac VALUES (" + pracownik.pesel
					+ ", " + pracownik.wynagrodzenieBrutto + ")");
		} catch (SQLException e) {
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}

	public void searchPracownik(String pesel) {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM prac WHERE pesel ='" + pesel
					+ "'");
			rs.next();
			String record = rs.getString(1) + " | "
					+ String.valueOf(rs.getDouble(2));
			System.out.println(record);
		} catch (SQLException ex) {
			// handle any errors
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}

	public void printDB() {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM prac");
			while (rs.next()) {
				String record = rs.getString(1) + " | " + rs.getString(2);
				System.out.println(record);
			}
		} catch (SQLException ex) {
			// handle any errors
		} finally {
			// zwalniamy zasoby, które nie będą potrzebne
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
	}

	public static void main(String[] argv) {
		Kadry pracownicyDatabase = new Kadry();
		pracownicyDatabase.createTablePracownik();
		pracownicyDatabase.addPracownik(new Student(1000.00, "12345678912"));
		pracownicyDatabase.addPracownik(new PracownikEtatowy(234.4567,
				"23456789123"));
		pracownicyDatabase.addPracownik(new Student(1.24, "34567891234"));
		pracownicyDatabase.printDB();
		System.out.println();
		pracownicyDatabase.searchPracownik("23456789123");
		System.out.println();
		pracownicyDatabase
				.changeWynagrodzenieBrutto("23456789123", 9999999.999);
		pracownicyDatabase.printDB();
	}
}