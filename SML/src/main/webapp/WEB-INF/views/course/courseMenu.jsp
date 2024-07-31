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
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
</head>
<body>

	<nav class="course-menu">
		<a href="/course/boardList"><h3>취미 교실</h3></a>
		<hr>
		<ul>
			<li><a href="/course/boardList">수강 신청</a></li>
			<li><a href="/course/enroll">글 등록 - admin only</a></li>
			<li><a href="/admin/courses">수강한 강의 보러가기</a></li>
		</ul>
	</nav>

	<script src="${webappRoot}/resources/js/admin/admin.js"></script>
</body>
</html>