<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<h1>
로그인
</h1> </br>

<form action = "FrontController" method = "post" name = "login">
	<input type = "text" name = "id" placeholder="아이디" onfocus="this.value=''"/><br/>
	<br/>
	<input type = "password" name = "pw" placeholder="비밀번호" onfocus="this.value=''"/><br/>
	<br/>
	<a href="joinForm.jsp">회원가입</a>
	<input type = "submit" value="로그인"/>

</form>
</body>
</html>