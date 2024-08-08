<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String requestURI = request.getRequestURI();
String basePath = "/admin/";
String pagePath = requestURI.substring(requestURI.indexOf(basePath) + basePath.length());
pagePath = pagePath.replace(".jsp", "");
// System.out.println(pagePath);

if (pagePath == null || pagePath.isEmpty()) {
	pagePath = "main";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/admin/admin.css">
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>

	<nav class="admin-menu">
		<a href="/admin/main"><h3>관리자 페이지</h3></a>
		<hr>
		<ul>
			<li><a href="/admin/members">회원 관리</a></li>
			<li><a href="/admin/courses">수강신청 관리</a></li>
			<li><a href="/admin/sms">SMS 관리</a></li>
			<li><a href="/admin/chat">채팅상담 관리</a></li>
			<li><a href="/admin/adminInfo">내 정보수정</a></li>
		</ul>
	</nav>

	<script src="${webappRoot}/resources/js/admin/admin.js"></script>
</body>
</html>