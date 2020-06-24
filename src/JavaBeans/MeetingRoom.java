package JavaBeans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class MeetingRoom implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private final int INTERVAL=60;
	
	private static final String[] PERIOD= {
			"09:00","10:00","11:00","12:00",
			"13:00","14:00","15:00","16:00"
			
	};
	//利用者
	private UserBean user;
	//会議室
	private RoomBean []rooms;
	//利用日
	private String date;
	
	//会議室予約システムを初期化する。会議室の一覧を読み込み利用日を本日の日付で初期化する
	
	public MeetingRoom() {
		rooms=RoomDao.findAll();
		date=String.format("%tF",System.currentTimeMillis());//現在時刻

	}
	
	//roomIdの会議室が配列に格納されている添字を返す
	
	private int roomIndex(String roomId) throws IndexOutOfBoundsException {
		for(int index=0;index<rooms.length;index++) {
			if(roomId.equals(rooms[index].getId())) {
				return index;
			}
		}
		throw new IndexOutOfBoundsException();
		
	}
	
	//利用開始時刻に対応する時間帯番号を計算します
	private int startPeriod(String start)throws IndexOutOfBoundsException{
		for(int i=0;i<PERIOD.length;i++) {
			if(PERIOD[i].equals(start)) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	//時間帯の配列を返します
	public static String[] getPeriod() {
		return PERIOD;
	}
	
	//会議予約システムで利用できる全ての会議室を返す。
	
	public RoomBean[] getRooms() {
		return rooms;
	}
	
	public RoomBean getRooms(String roomId) {
		for(RoomBean room:rooms) {
			if(room.getId().equals(roomId)) {
				return room;
			}
		}
		return null;
	}
	//会議室予約システムにログインしているユーザーを返します
	public UserBean getUser() {
		return user;
	}
	
	//予約システムの利用日を返します
	public String getDate() {
		return date;
	}
	//会議室予約システムの利用日を設定します
	public void setDate(String date) {
		this.date=date;
	}
	
	//会議室予約システムにログインする
	
	public boolean login(String id,String password) {
		user=UserDao.certificate(id,password);
		return user!=null;
	}
	
	//会議室予約システムの利用日における予約状況を返します
	public ReservationBean [][]getReservations(){
		//利用日の予約を検索する
		List<ReservationBean> rList=ReservationDao.findByDate(date);
		ReservationBean[][] reservations=new ReservationBean[rooms.length][PERIOD.length];
		for(ReservationBean r:rList) {
			try {
				int i=roomIndex(r.getRoomId());
				int start=startPeriod(r.getStart());
				reservations[i][start]=r;
			}catch(IndexOutOfBoundsException e) {
				System.err.println(e);
			}
		}
		return reservations;
	}
	//予約日で会議室と時間帯を指定した会議室予約情報を精製します
	
	public ReservationBean createReservation(String roomId,String start) {
		int hour=Integer.parseInt(start.substring(0,2));
		int minute=Integer.parseInt(start.substring(3,5));
		int t=hour*60+minute+INTERVAL;
		hour=t/60;
		minute=t%60;
		String end=String.format("%02d:%02d",hour,minute);
		return new ReservationBean(roomId,date,start,end,user.getId());
	}
	
	//会議室予約情報で会議室を予約します
	public void reserve(ReservationBean reservation)throws Exception{
		long t=System.currentTimeMillis();
		String date=String.format("%tF",t);
		String time=String.format("%tR",t);
		
		if(date.compareTo(reservation.getDate())<0
				||date.compareTo(reservation.getDate())==0
				&&time.compareTo(reservation.getStart())<0) {
			if(!ReservationDao.insert(reservation)) {
				throw new Exception("既に予約されています");
			}
			
		}
		else {
			throw new Exception("時刻が過ぎているので予約できません");
		}
	}
	
	//会議室予約情報で会議室をキャンセルします
	
	public void cancel(ReservationBean reservation)throws Exception{
		long t=System.currentTimeMillis();
		String date=String.format("%tF",t);
		String time=String.format("%tR",t);
		
		if(date.compareTo(reservation.getDate())<0
				||date.compareTo(reservation.getDate())==0
				&&time.compareTo(reservation.getStart())<0) {
			if(!ReservationDao.delete(reservation)) {
				throw new Exception("すでにキャンセルされています");
			}
			
		}else {
			throw new Exception("時刻が過ぎているのでキャンセルできません");
		}
	}
	
	//会議室予約システムの文字列表現
	public String toString() {
		return "MeetingRoom[user="+user+",rooms="+Arrays.toString(rooms)+",date="+date+"]";
	}
	
	
	
	

	
		
}


