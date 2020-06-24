<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="JavaBeans.*" %>
    
    <%
    MeetingRoom meetingRoom=(MeetingRoom)session.getAttribute("meetingRoom");
    RoomBean[]rooms=meetingRoom.getRooms();
    ReservationBean[][]reservations=meetingRoom.getReservations();
    String[]period=MeetingRoom.getPeriod();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" href="resevation.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会議室予約システム</title>
</head>
<body>
<h1>予約キャンセル</h1>
<hr>
<h2>予約日</h2>
<form action="ChangeDateServlet" method="post">
<input type="date"name="date" value="${meetingRoom.date}">
<input type="hidden" name="page" value="cancelInput.jsp">
<input type="submit" value="日付変更">

</form>
<h2>キャンセル可能時間帯(${meetingRoom.user.name})</h2>
<table>
<tr>
<th>会議室＼時間帯</th>
<%
for(int i=0;i<period.length;i++){
	out.println("<th>"+period[i]+"</th>");
}
out.println("</tr>");
for(int r=0;r<rooms.length;r++){
	out.println("<tr>");
	out.println("<td>"+rooms[r].getName()+"</td>");
	for(int i=0;i<reservations[r].length;i++){
		out.println("<td>");
		out.println("<form action=\"CancelCreateServlet\" method=\"post\">");
		out.println("<input type=\"hidden\" name=\"roomId\"value=\""
				+rooms[r].getId()+"\">");
		if(reservations[r][i]!=null){
			out.println(
					"<input type=\"submit\" name=\"time\" value=\""
					+period[i]+"\">");
			
		}else{
			out.println(
					"<input type=\"submit\" name=\"time\" value=\""
					+period[i]+"\" disabled>");
		}
		out.println("</form>");
		out.println("</td>");
	}
	out.println("</tr>");
	
}
%>

</table>
<hr>
<form action="menu.jsp" method="post">
<input type="submit" value="戻る">
</form>
</body>
</html>