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
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/2.5.1/uicons-bold-rounded/css/uicons-bold-rounded.css'>
</head>
<body>
	<div id="quick-menu">
		<button onclick="enlargeFont()">
			<i class="fi fi-br-zoom-in"></i>
		</button>
		<button onclick="reduceFont()">
			<i class="fi fi-br-zoom-out"></i>
		</button>
		<button onclick="chatConsultation()">
			<i class="fi fi-br-comments-question-check"></i>
		</button>
		<button onclick="scrollToTop()">
			<i class="fi fi-br-chevron-double-up"></i>
		</button>
	</div>


	<header>
		<div class="container">
			<a href="/" class="main-link"> <img
				src="${webappRoot}/resources/images/logo.jpg" alt="Logo"
				class="logo">
			</a>
			<nav class="main-menu" id="main-menu">
				<ul>
					<li><a href="/notice/list">공지사항</a></li>
					<li><a href="/life/list">생활정보</a></li>
					<li><a href="/location/map">위치찾기</a></li>
					<li><a href="/course/boardList">취미교실</a></li>
					<li><a href="/community/boardList">커뮤니티</a></li>
				</ul>
			</nav>
			<nav class="auth-menu" id="auth-menu">
				<ul>
					<li><a href="/member/login">로그인</a></li>
					<li><a href="/member/join">회원가입</a></li>
				</ul>
			</nav>
			<button class="hamburger" id="hamburger">&#9776;</button>
			<div class="mobile-menu" id="mobile-menu">
				<ul>
					<li><a href="/notice/list">공지사항</a></li>
					<li><a href="/life/list">생활정보</a></li>
					<li><a href="/location/map">위치찾기</a></li>
					<li><a href="/course/boardList">취미교실</a></li>
					<li><a href="/community/boardList">커뮤니티</a></li>
					<p>----------------</p>
					<li><a href="/member/login">로그인</a></li>
					<li><a href="/member/join">회원가입</a></li>
				</ul>
			</div>
		</div>
	</header>

	<div id="chat-container" class="hidden">
		<div id="chat-header">
			<span><< 채팅 상담 >></span>
			<button onclick="closeChat()">X</button>
		</div>
		<div id="chat-box"></div>
		<div id="chat-input">
			<input type="text" id="message-input" placeholder="메시지를 입력하세요.">
			<button onclick="sendMessage()">전송</button>
		</div>
	</div>

	<div id="close-chat-modal" class="modal hidden">
		<div class="modal-content">
			<p>채팅(상담)을 종료하시겠습니까?</p>
			<button onclick="confirmCloseChat()">종료</button>
			<button onclick="cancelCloseChat()">취소</button>
		</div>
	</div>

	<script src="${webappRoot}/resources/js/common.js"></script>
</body>
</html>