<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
	<header>
		<div class="container">
			<a href="/" class="main-link"> <img
				src="${webappRoot}/resources/images/logo.jpg" alt="Logo"
				class="logo">
			</a>
			<nav class="main-menu" id="main-menu">
				<ul>
					<li><a href="/notice">공지사항</a></li>
					<li><a href="/life">생활정보</a></li>
					<li><a href="/location">위치찾기</a></li>
					<li><a href="/class">취미교실</a></li>
					<li><a href="/community">커뮤니티</a></li>
				</ul>
			</nav>
			<nav class="auth-menu" id="auth-menu">
				<ul>
					<li><a href="/login">로그인</a></li>
					<li><a href="/register">회원가입</a></li>
				</ul>
			</nav>
			<button class="hamburger" id="hamburger">&#9776;</button>
			<div class="mobile-menu" id="mobile-menu">
				<ul>
					<li><a href="/notice">공지사항</a></li>
					<li><a href="/life">생활정보</a></li>
					<li><a href="/location">위치찾기</a></li>
					<li><a href="/class">취미교실</a></li>
					<li><a href="/community">커뮤니티</a></li>
					<p>----------------</p>
					<li><a href="/login">로그인</a></li>
					<li><a href="/register">회원가입</a></li>
				</ul>
			</div>
		</div>
	</header>
	
	<script src="${webappRoot}/resources/js/common.js"></script>
</body>
</html>