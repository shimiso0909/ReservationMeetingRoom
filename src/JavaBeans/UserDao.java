package JavaBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDao {
	
	public static UserBean certificate(String id,String password) {
		String name;
		String address;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/meetingroom");
			con=ds.getConnection();
			String sql="select name,address from user where id=? and password=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,password);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				name=rs.getString("name");
				address=rs.getString("address");
				return new UserBean(id,password,name,address);
				
			}
			return null;
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}catch(NamingException e) {
			System.err.println(e.getMessage());
			return null;
		}finally {
			try {
				if(rs!=null) {
					rs.close();
				}
				if(ps!=null) {
					ps.close();
				}
				if(con!=null) {
					con.close();
				}
			}catch(SQLException e) {
					System.err.println(e.getMessage());
					return null;
				}
			}
		
		
	


	}
	

}
