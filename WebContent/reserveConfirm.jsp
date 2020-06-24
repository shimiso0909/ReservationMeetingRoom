<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会議予約システム</title>
</head>
<link rel="stylesheet"href="resevation.css">
<body>
<h1>会議室予約</h1>
<hr>
<h2>予約確認</h2>
<table class="noborder">
<tr><td>予約日</td> <td>${reservation.date}</td></tr>
<tr><td>会議日</td> <td>${room.name}</td></tr>
<tr><td>予約時間</td> <td>${reservation.start}〜${reservation.end}</td></tr>
<tr><td>予約者</td> <td>${meetingRoom.user.name}</td></tr>



</table>
<hr>
<form class="inline" action="reserveInput.jsp" method="post">
<input type="submit"value="戻る">
</form>
<form class="inline" action="ReserveServlet" method="post">
<input type="submit"value="決定">

</form>

</body>
</html>