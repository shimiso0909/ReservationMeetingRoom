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
	<h1>会議室予約</h1>
	<hr>
	<h2>メニュー</h2><br>
	<form action="reserveInput.jsp" method="post">
	<input type="submit"value="会議室予約">
	</form>
	<form action="cancelInput.jsp"method="post">
	<input type="submit"value="予約キャンセル">
	</form>
	<form action="LogoutServlet"method="post">
	<input type="submit" value="ログアウト">
	</form>
	
	


</body>
</html>