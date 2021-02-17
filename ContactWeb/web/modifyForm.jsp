<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 수정</title>
</head>
<body>

<form action = "ModifyServlet" method = "post" name = "modify">
	이름<br/>
	<input type = "text" name = "name" placeholder="${contact.name }" onfocus="this.value=''"/><br/>
	<br/>
	전화번호<br/>
	<input type = "text" name = "phone" placeholder="${contact.phone }" onfocus="this.value=''"/><br/>
	<br/>
	주소<br/>
	<input type = "text" name = "address" placeholder="${contact.address }" onfocus="this.value=''"/><br/>
	카테고리<br/>
	<select name = "category">
		<option value="가족">가족</option>
		<option value="친구">친구</option>
		<option value="기타">기타</option>
	</select>
	<br/>
	<input type = "submit" value="수정 완료"/>

</form>

</body>
</html>