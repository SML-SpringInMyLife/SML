<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>SML_Admin(SMS관리)</title>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>SMS 관리</h2>
				<div class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="date">발송일시</option>
						<option value="receiver">수신인</option>
						<option value="content">내용</option>
					</select> <input type="text" id="searchQuery" placeholder="검색어를 입력하세요.">
					<button onclick="searchSMS()">검색</button>
				</div>

				<table class="sms-table">
					<thead>
						<tr>
							<th>No.</th>
							<th>발송일시</th>
							<th>수신인</th>
							<th>내용</th>
							<th>발신누적횟수</th>
						</tr>
					</thead>
					<tbody id="smsList">
						<!-- 샘플 데이터 -->
						<tr>
							<td data-label="No.">1</td>
							<td data-label="발송일시">2024-07-31 10:00</td>
							<td data-label="수신인">홍길동</td>
							<td data-label="내용"><span class="sms-content"
								onclick="showSmsDetails('안녕하세요, 홍길동님!')">안녕하세요, 홍길동님!</span></td>
							<td data-label="발신누적횟수">5</td>
						</tr>
						<tr>
							<td data-label="No.">2</td>
							<td data-label="발송일시">2024-07-31 11:00</td>
							<td data-label="수신인">임꺽정</td>
							<td data-label="내용"><span class="sms-content"
								onclick="showSmsDetails('[안부문자] 안녕하세요, 임꺽정님! 1주일간 출석이 없으셔서 문자 보냅니다.')">[안부문자]
									안녕하세요, 임꺽정님! 1주일간 출석이 없으셔서 문자 보냅니다.</span></td>
							<td data-label="발신누적횟수">3</td>
						</tr>
					</tbody>
				</table>

				<div class="sms-button-container">
					<button class="send-sms" onclick="showSmsPopup()">SMS 발송</button>
				</div>
			</div>
		</div>
	</main>

	<!-- SMS 발송 팝업 -->
	<div id="smsPopup" class="sms-popup">
		<div class="sms-popup-content">
			<span class="close" onclick="closeSmsPopup()">&times;</span>
			<h2>SMS 발송</h2>
			<div class="search-container">
				<select id="popupSearchCategory">
					<option value="name">성명</option>
					<option value="id">ID</option>
					<option value="phone">전화번호</option>
				</select> <input type="text" class="search-bar" id="popupSearchQuery"
					placeholder="검색어를 입력하세요.">
				<button onclick="popupSearchMember()">검색</button>
			</div>
			<div>
				<ul id="searchResults"></ul>
			</div>
			<form id="sendSms" method="post" action="sendSms.do">
				<label for="recipientNumber" class="sms-label">수신인 번호:</label> <input
					type="text" id="recipientNumber" name="recipientNumber" readonly>
				<div>
					<label for="senderNumber" class="sms-label">발신인 번호:</label> <input
						type="text" id="senderNumber" name="senderNumber"
						value="01091933200">
				</div>
				<div>
					<label for="smsContent" class="sms-label">내용:</label>
					<textarea id="smsContent" name="smsContent" rows="4" cols="50"></textarea>
				</div>
				<button type="submit" class="send-button" onclick="sendSms()">보내기</button>
		</div>
		</form>
	</div>

	<!-- SMS 상세보기 팝업 -->
	<div id="smsDetailsPopup" class="smsDetail-popup">
		<div class="sms-popup-content">
			<span class="close" onclick="closeSmsDetailsPopup()">&times;</span>
			<h2>SMS 상세보기</h2>
			<div id="smsDetailsContent"></div>
		</div>
	</div>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
