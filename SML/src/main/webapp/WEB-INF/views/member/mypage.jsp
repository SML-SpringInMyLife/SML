<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
String requestURI = request.getRequestURI();
String basePath = "/member/";
String pagePath = requestURI.substring(requestURI.indexOf(basePath) + basePath.length());
pagePath = pagePath.replace(".jsp", "");
System.out.println(pagePath);

if (pagePath == null || pagePath.isEmpty()) {
	pagePath = "mypage";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/member/mypage.css">
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
	<nav class="member-menu">
		<a href="/member/mypage"><h3>마이 페이지</h3></a>
		<hr>
		<ul>
			<li><a href="/member/myinfo">회원정보 수정</a></li>
			<li><a href="/member/mycourses">수강신청 내역</a></li>
			<li><a href="/member/point">적립금 내역</a></li>
			<li><a href="/member/memberCheck">출석 체크</a></li>			
		</ul>
	</nav>
<script src="${webappRoot}/resources/js/member/mypage.js"></script>
</body>
</html>