<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resevation.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会議予約システム</title>
</head>
<body>
<h1>予約キャンセル</h1>
<hr>
<h2>キャンセル完了</h2>
<table class="noborder">
<tr><td>予約ID</td><td>${reservation.id}</td></tr>
<tr><td>予約日</td><td>${reservation.date}</td></tr>
<tr><td>会議室</td><td>${room.name}</td></tr>
<tr><td>予約時間</td><td>${reservation.start}〜${reservation.end}</td></tr>
<tr><td>予約者</td><td>${meetingRoom.user.name}</td></tr>
</table>
<form action="menu.jsp"method="post">
<input type="submit"value="完了">
</form>

</body>
</html>