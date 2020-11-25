import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.io.PrintWriter;
import java.lang.*;

public class DB {
	public static Hashtable<Integer, String> menu_set =new Hashtable<Integer, String>();
	
	boolean DB(int a_time, int m_time, int rest) {
		int result;
	    int cnt=0;
	    int food_time;
	     
		Connection con = null;
		try
		{
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    String url="jdbc:mysql://localhost:3306/abob?serverTimezone=UTC&useSSL=false";
		    String user = "root", passwd = "1234";
		    con = DriverManager.getConnection(url, user, passwd);
		   // System.out.println(con);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet prs = null;		
	//	n.put(name,out); // ������ ������ n�̶�� �ؽ����̺� client ������ �־���.
	//	n.remove(name,out); //n hashTable���� �ش� name ����
		try {
			//stmt = con.createStatement();
			String psql = "select str_name, loc, name, cost,cook_time,menu_id from store natural join menu where ?-(cook_time+?)>900 and str_id=?";
			
			pstmt= con.prepareStatement(psql);
			pstmt.setInt(1, a_time*60); //query���� ���.
			pstmt.setInt(2, m_time*60); //query���� ���.
			pstmt.setInt(3, rest); //query���� ���.
			rs = pstmt.executeQuery();
			//System.out.println(pstmt);
	        //System.out.println();	        		
			while(rs.next()) {
				cnt++;
				String str_name = rs.getString(1);
				if(rs.wasNull()) str_name = "null";
				String str_loc = rs.getString(2);
				if(rs.wasNull()) str_loc = "null";
				String name = rs.getString(3);
				if(rs.wasNull()) name = "null";
				String cost  = rs.getString(4);
				if(rs.wasNull()) cost = "null";
				String cook_time = rs.getString(5);
				if(rs.wasNull()) cook_time = "null";
				String menu_id = rs.getString(6);
				if(rs.wasNull()) cook_time = "null";
				food_time = a_time - (m_time + Integer.parseInt(cook_time)/60);
				String ab = "["+name + "] // " + str_name +
						 " // " + cost + "�� // "+food_time+"��\n";
				//System.out.println(ab);			
				DB.menu_set.put(Integer.parseInt(menu_id), ab);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// --------------------------------- //

		try {
			if(con != null && !con.isClosed()) con.close();
			if(rs != null && !rs.isClosed()) rs.close();			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		if(cnt>=1)return true;
		else return false;
	}
}