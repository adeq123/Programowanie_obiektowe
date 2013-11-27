package Lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Lab1.JIn;

public class DB {
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

	public void searchByAuthor() {
		try {
			connect();
			stmt = conn.createStatement();

			System.out.print("Podaj klucz (autora):");
			String author = JIn.getString();
			rs = stmt.executeQuery("SELECT * FROM books WHERE author ='"
					+ author + "'");

			while (rs.next()) {
				String record = rs.getString(1) + " |  " + rs.getString(2)
						+ " |  " + rs.getString(3) + " |  " + rs.getString(4);
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

	public void searchByISBN() {
		try {
			connect();
			stmt = conn.createStatement();
			System.out.print("Podaj klucz (ISBN):");
			String isbn = JIn.getString();

			rs = stmt.executeQuery("SELECT * FROM books WHERE isbn ='" + isbn + "'");
			while (rs.next()) {
				String record = rs.getString(1) + " | " + rs.getString(2)
						+ " | " + rs.getString(3) + " | " + rs.getString(4);
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

	public void deleteByAuthor() {
		try {
			stmt = conn.createStatement();
			System.out.print("Podaj klucz (autora):");
			String author = JIn.getString();

			stmt.executeUpdate("DELETE FROM books WHERE author = '" + author
					+ "'");
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

	public void deleteByISBN() {
		try {
			stmt = conn.createStatement();
			System.out.print("Podaj klucz (ISBN):");
			String isbn = JIn.getString();

			stmt.executeUpdate("DELETE FROM books WHERE isbn = '" + isbn + "'");
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
			rs = stmt.executeQuery("SELECT * FROM books");
			while (rs.next()) {
				String record = rs.getString(1) + " | " + rs.getString(2)
						+ " | " + rs.getString(3) + " | " + rs.getString(4);
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
		  DB database = new DB(); 
		  database.printDB();
		  System.out.println();
		  database.searchByAuthor();
		  System.out.println();
		  database.printDB();
		  System.out.println();
		  database.deleteByISBN(); 
		  database.printDB(); 
	}
}
