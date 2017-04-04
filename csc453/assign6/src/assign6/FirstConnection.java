package assign6;

import java.sql.*;
import javax.swing.JOptionPane;

public class FirstConnection 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		int sectionNumber;
		int ACount;
		int BCount;
		int CCount;
		int DCount;
		int FCount;
		int totalCount;
		System.out.println("Creating connection to database...");
		// Load the driver
		Class.forName("oracle.jdbc.OracleDriver");
		// Make the connection
		// (You would of course use your own cdmoracle username and password)
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:@140.192.30.237:1521:def", "rharvey4", "cdm1188701");
		// Close the connection
		Statement s = c.createStatement();
		
		sectionNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter a 5 digit SectionID"));
		
		System.out.println("Section " + sectionNumber + ":");
		System.out.println();
		System.out.println("--------------");
		ResultSet r;
		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
		"WHERE SECTIONID = " + sectionNumber + "AND GRADE = 'A'");
		while (r.next())
		{
			ACount = r.getInt(1);
			System.out.println("A's: " + ACount);
		}

		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
				"WHERE SECTIONID = " + sectionNumber + "AND GRADE = 'B'");
		while (r.next())
		{
			BCount = r.getInt(1);
			System.out.println("B's: " + BCount);
		}
		
		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
				"WHERE SECTIONID = " + sectionNumber + "AND GRADE = 'C'");
		while (r.next())
		{
			CCount = r.getInt(1);
			System.out.println("C's: " + CCount);
		}
		
		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
				"WHERE SECTIONID = " + sectionNumber + "AND GRADE = 'D'");
		while (r.next())
		{
			DCount = r.getInt(1);
			System.out.println("D's: " + DCount);
		}
		
		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
				"WHERE SECTIONID = " + sectionNumber + "AND GRADE = 'F'");
		while (r.next())
		{
			FCount = r.getInt(1);
			System.out.println("F's: " + FCount);
		}
		
		r = s.executeQuery("SELECT Count(GRADE) FROM GRADING " +
				"WHERE SECTIONID = " + sectionNumber);
		while (r.next())
		{
			totalCount = r.getInt(1);
			System.out.println("Total grades: " + totalCount);
		}
		c.close();
		
		}
	}