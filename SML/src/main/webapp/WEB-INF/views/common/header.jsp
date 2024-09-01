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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<div id="quick-menu">
		<button id="enlarge-font-button"">
			<i class="fi fi-br-zoom-in"></i>
		</button>
		<button id="reduce-font-button">
			<i class="fi fi-br-zoom-out"></i>
		</button>
		<c:choose>
			<c:when test="${sessionScope.member.memAdminCheck == 1}">
				<a href="/admin/chat"><button>
						<i class="fi fi-br-comments-question-check"></i>
					</button> </a>
			</c:when>
			<c:otherwise>
				<button id="chat-start-button">
					<i class="fi fi-br-comments-question-check"></i>
				</button>
			</c:otherwise>
		</c:choose>
		<button id="scroll-top-button">
			<i class="fi fi-br-chevron-double-up"></i>
		</button>
	</div>

	<header>
		<div class="container">
			<div class="left-menu">
				<a href="/" class="main-link"> <img
					src="${webappRoot}/resources/images/logo.png" alt="Logo"
					class="logo">
				</a>
				<nav class="main-menu" id="main-menu">
					<ul>
						<li><a href="/notice/list">공지사항</a></li>
						<li><a href="/safty/list">생활정보</a></li>
						<li><a href="/location/map">위치찾기</a></li>
						<li><a href="/course/boardList">취미교실</a></li>
						<li><a href="/community/boardList">커뮤니티</a></li>
						<li><a href="/donation/main">후원하기</a></li>
					</ul>
				</nav>
			</div>
			<nav class="auth-menu" id="auth-menu">
				<ul>
					<c:choose>
						<c:when test="${not empty sessionScope.member}">
							<li><span class="welcome" id="memName">${sessionScope.member.memName}
									님</span><span class="memId" id="memId" style="display: none;">${sessionScope.member.memId}</span></li>
							<c:choose>
								<c:when test="${sessionScope.member.memAdminCheck == 1}">
									<li><a href="/admin/main">관리자페이지</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/member/memberCheck">마이페이지</a></li>
								</c:otherwise>
							</c:choose>
							<li><a href="/member/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/member/login">로그인</a></li>
							<li><a href="/member/join">회원가입</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<button class="hamburger" id="hamburger">&#9776;</button>
			<div class="mobile-menu" id="mobile-menu">
				<ul>
					<li><a href="/notice/list">공지사항</a></li>
					<li><a href="/safty/list">생활정보</a></li>
					<li><a href="/location/map">위치찾기</a></li>
					<li><a href="/course/boardList">취미교실</a></li>
					<li><a href="/community/boardList">커뮤니티</a></li>
					<li><a href="/donation/main">후원하기</a></li>
					<hr>
					<c:choose>
						<c:when test="${not empty sessionScope.member}">
							<c:choose>
								<c:when test="${sessionScope.member.memAdminCheck == 1}">
									<li><a href="/admin/main">관리자페이지</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/member/memberCheck">마이페이지</a></li>
								</c:otherwise>
							</c:choose>
							<li><a href="/member/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/member/login">로그인</a></li>
							<li><a href="/member/join">회원가입</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</header>

	<!-- 채팅 팝업 설정 -->
	<div id="chat-container" class="hidden">
		<%-- <c:choose>
			<c:when test="${sessionScope.member.memAdminCheck == 1}">
				<div id="chat-header">
					<span> 채팅 상담 💬 </span>
					<!-- <h3>Chat Rooms</h3>
					<div id="roomList"></div> -->
					<button class="sizeBtn" onclick="minimizeChat()">⎯</button>
					<!-- 최소화 버튼 -->
					<button class="closeBtn" onclick="closeChat()">❌</button>
				</div>
				<div id="chat-box"></div>
				<div id="chat-input">
					<input type="text" id="message-input" placeholder="메시지를 입력하세요.">
					<button class="sendBtn" onclick="sendMessage()">전송</button>
				</div>
			</c:when>
			<c:otherwise> --%>
		<div id="chat-header">
			<span> 채팅 상담 💬 </span>
			<button class="sizeBtn" onclick="minimizeChat()">⎯</button>
			<!-- 최소화 버튼 -->
			<button class="closeBtn" onclick="closeChat()">❌</button>
		</div>
		<div id="chat-box"></div>
		<div id="chat-input">
			<input type="text" id="message-input" placeholder="메시지를 입력하세요.">

			<input type="hidden" id="conversationId" value="">

			<button class="sendBtn" onclick="sendMessage()">전송</button>
		</div>
		<%-- </c:otherwise>
		</c:choose> --%>
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
