package JavaBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RoomDao {
	
	public static RoomBean[] findAll() {
	
		Connection con=null;//会議室の情報をDate型を引数にその日の会議室の情報を取り出す。
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			List <RoomBean> list=new LinkedList<RoomBean>();
			Context context=new InitialContext();
			DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/meetingroom");
			con=ds.getConnection();
			String sql="select * from room order by id";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery(sql);
			while(rs.next()) {
				String id=rs.getString("id");
				String name=rs.getString("name");
				list.add(new RoomBean(id,name));
				
				
				
				
				
				
				
			}
			return (RoomBean[])list.toArray(new RoomBean[list.size()]);
			//Listを配列に変換
			
			
			
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
