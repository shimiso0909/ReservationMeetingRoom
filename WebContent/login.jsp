<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resevation.css">

<title>会議室予約システム</title>
</head>
<body>
<h1>会議室予約</h1>
<hr>
<h2>ログイン</h2>
<form action ="LoginServlet" method="post">
利用者ID:
<input type="text" name="userId"><br>
パスワード:
<input type="password" name="userPw"><br>
<input type="submit"value="ログイン">
</form>


</body>
</html>