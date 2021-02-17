<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>모두의 연락처</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/custom.css" rel="stylesheet">
<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<style>
	.bg-main{
	background-color:#1abc9c;
	color:#fff;
	font-weight: bold;
	font-family: "DX경필고딕B";
	padding-top: 500px;
	padding-bottom: 500px;
	}
</style>

</head>
<body>
	<p class="h1">어장관리(魚腸管理)</p>
	${name }님의 연락처 입니다.

	<table class="table table-hover">
		<thead>
			<tr>
				<th scope='col'>이름</th>
				<th scope='col'>전화번호</th>
				<th scope='col'>주소</th>
				<th scope='col'>카테고리</th>
		</thead>
		<tbody>
			<c:forEach items="${contact }" var = "contact">
			<tr>
				<td>${contact.name }</td>
				<td>${contact.phone }</td>
				<td>${contact.address }</td>
				<td>${contact.category }</td>
				<td><a href = "ModifyServlet?phone=${contact.phone }">수정</a></td>
				<td><a href = "DeleteServlet?phone=${contact.phone }">삭제</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
			

</body>
</html>


 
 