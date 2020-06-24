package JavaBeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReservationDao {
	
	//利用日の予約を検索する
	public static List<ReservationBean> findByDate(String date){
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			List<ReservationBean> list=new LinkedList<ReservationBean>();
			Context context =new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/meetingroom");
			con=ds.getConnection();
			String sql="select id,roomid,start,end,userid from reservation where date=? order by roomid,start";
			ps=con.prepareStatement(sql);
			ps.setDate(1,Date.valueOf(date));
			rs=ps.executeQuery();
			while(rs.next()) {
				int id=rs.getInt("id");
				String roomId=rs.getString("roomId");
				Time start =rs.getTime("start");
				Time end=rs.getTime("end");
				String userId=rs.getString("userid");
				String st=start.toString().substring(0,5);
				String ed=end.toString().substring(0,5);
				list.add(new ReservationBean(id,roomId,date,st,ed,userId));//利用日の予約を追加していく。
				
			}
			return list;
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}catch(NamingException e) {
			System.err.println(e.getMessage());
			return null;
		}
		finally {
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
	public static boolean insert(ReservationBean reservation) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;//予約追加、
		
		try {
			Context context=new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/meetingroom");
			con=ds.getConnection();
			//重複予約を確認
			String sql="select * from reservation where roomid=?"
					+ " and date=? and start=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,reservation.getRoomId());
			ps.setDate(2,Date.valueOf(reservation.getDate()));//Date型に変換
			ps.setTime(3,Time.valueOf(reservation.getStart()+":00"));
			rs=ps.executeQuery();
			if(rs.next()) {
				return false;//重複する予約がある
				
				
			}
			rs.close();
			ps.close();
			//予約の追加
			sql="insert into reservation value(null,?,?,?,?,?)";
			ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,reservation.getRoomId());
			ps.setDate(2,Date.valueOf(reservation.getDate()));
			ps.setTime(3,Time.valueOf(reservation.getStart()+":00"));
			ps.setTime(4,Time.valueOf(reservation.getEnd()+":00"));
			ps.setString(5,reservation.getUserId());
			int cnt=ps.executeUpdate();
			if(cnt==1) {
				rs=ps.getGeneratedKeys();
				if(rs.next()) {
					reservation.setId(rs.getInt(1));
					return true;
				}
			}
			return false;
		}catch(SQLException e) {
			System.err.println(e.getMessage());
			return false;
			
		}catch(NamingException e) {
			return false;
		}
		finally {
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
				return false;
			}
			
		}
		
	}
		public static boolean delete(ReservationBean reservation) {
			Connection con=null;//予約キャンセルで使うのかな？
			PreparedStatement ps=null;
			try {
				Context context=new InitialContext();
				DataSource ds=(DataSource)context.lookup("java:comp/env/jdbc/meetingroom");
				con=ds.getConnection();
				String sql="delete from reservation"
						+ " where roomid=?"
						+ " and date=?"
						+ " and start=?"
						+ " and userid=?";
				ps=con.prepareStatement(sql);
				ps.setString(1,reservation.getRoomId());
				ps.setDate(2,Date.valueOf(reservation.getDate()));
				ps.setTime(3,Time.valueOf(reservation.getStart()+":00"));
				ps.setString(4,reservation.getUserId());
				
				int cnt=ps.executeUpdate();
				if(cnt>=1) {
					return true;
				}
				return false;
						
				
			}
			catch(SQLException e) {
				System.err.println(e.getMessage());
				return false;
			}
			catch(NamingException e) {
				System.err.println(e.getMessage());
				return false;
			}
			finally {
				try {
					if(ps!=null) {
						ps.close();
					}
					if(con!=null) {
						con.close();
					}
				}catch(SQLException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
		}
		
		
		
		
		
	

}
