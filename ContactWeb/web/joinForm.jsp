<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="JoinServlet" method="post" name = "join">
	이름<br/>
	<input type="text" name = "name" size="10"/><br/>
	아이디<br/>
	<input type="text" name = "id" size="10"/><br/>
	비밀번호<br/>
	<input type="password" name = "pw" size="10"/><br/>
	전화번호<br/>
	<input type="number" name = "phonenum" size="10"><br/>
			
	성별<br/>
		<input type="radio" name = "gender" value="man"/> 남
		<input type="radio" name = "gender" value="woman"/>여
	<br/>
	<input type="submit" value="회원 가입">	
</form>
</body>
</html>