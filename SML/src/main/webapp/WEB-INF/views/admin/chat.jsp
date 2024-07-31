<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>SML_Admin(채팅상담관리)</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>채팅상담 관리</h2>
				<div class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="date">상담일시</option>
						<option value="id">ID</option>
						<option value="name">성명</option>
						<option value="content">내용</option>
					</select> <input type="text" id="searchQuery" placeholder="검색어를 입력하세요.">
					<button onclick="searchChats()">검색</button>
				</div>

				<table class="chat-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="상담일시">상담일시</th>
							<th data-label="아이디">아이디</th>
							<th data-label="성명">성명</th>
							<th data-label="내용">내용</th>
						</tr>
					</thead>
					<tbody id="chatList">
						<!-- 샘플 -->
						<tr>
							<td data-label="No.">1</td>
							<td data-label="상담일시">2024-07-31 10:00</td>
							<td data-label="아이디">Hong</td>
							<td data-label="성명">홍길동</td>
							<td data-label="내용"><span class="chat-content"
								onclick="showChatDetails('안녕하세요, 홍길동님!')">안녕하세요, 홍길동님!</span></td>
						</tr>
						<tr>
							<td data-label="No.">2</td>
							<td data-label="상담일시">2024-07-31 11:00</td>
							<td data-label="아이디">Lim</td>
							<td data-label="성명">임꺽정</td>
							<td data-label="내용"><span class="chat-content"
								onclick="showChatDetails('안녕하세요, 임꺽정님! 상담이 완료되었습니다.')">안녕하세요,
									임꺽정님! 상담이 완료되었습니다.</span></td>
						</tr>
					</tbody>
				</table>

				<!-- 채팅 상세보기 팝업 -->
				<div id="chatDetailsPopup" class="chatDetail-Popup">
					<div class="chat-popup-content">
						<span class="close" onclick="closeChatDetailsPopup()">X</span>
						<h2>채팅상담 상세보기</h2>
						<div id="chatDetailsContent"></div>
					</div>
				</div>
			</div>
		</div>

	ㄴ</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
