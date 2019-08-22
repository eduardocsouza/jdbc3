package aplicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Programa {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)");
					
			st.setString(1, "dudu");
			st.setString(2, "du@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("10/04/1988").getTime()));
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			
			//System.out.println("Pronto!, Linhas afetadas: " + rowsAffected);
			
			ResultSet rs = null;
			Statement s = null;
			
			s = conn.createStatement();
			rs = s.executeQuery("Select * from seller");
			while(rs.next()) {
				System.out.println(rs.getInt("id") + ", " + rs.getString("Name"));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally{
			DB.closeStatement(st);
			DB.closeConnetion();
		}		
		
		
	}	
}