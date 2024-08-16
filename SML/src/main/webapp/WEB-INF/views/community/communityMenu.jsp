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
<link rel="stylesheet" href="${webappRoot}/resources/css/community/community.css">
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
</head>
<body>

	<nav class="community-menu">
		<a href="/community/boardList"><h3>커뮤니티</h3></a>
		<hr>
		<ul>
			<li><a href="/community/boardList">게시글 보기</a></li>
			<li><a href="/community/enroll">글쓰기</a></li>
		</ul>
	</nav>
	<script src="${webappRoot}/resources/js/courseNcommunity/courseNcommunity.js"></script>
</body>
</html>