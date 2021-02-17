<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 추</title>
</head>
<body>

<form action = "InsertServlet" method = "post" name = "insert">
	이름<br/>
	<input type = "text" name = "name"><br/>
	전화번호<br/>
	<input type = "text" name = "phone"><br/>
	주소 <br/>
	<input type = "text" name = "address"><br/>
	카테고리<br/>
	<select name = "category">
		<option value ="가족">가족</option>
		<option value ="친구">친구</option>
		<option value ="기타">기타</option>
	</select>
	<br/>
	<input type = "submit" value = "연락처 추가">

</form>


</body>
</html>