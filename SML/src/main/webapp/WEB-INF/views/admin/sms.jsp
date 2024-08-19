<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
					</select>
					<div class="search-bar">
						<input type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<button onclick="search()">검색</button>
					</div>
				</div>

				<table class="sms-table">
					<thead>
						<tr>
							<th>No.</th>
							<th>발송일시</th>
							<th>수신인</th>
							<th>내용</th>
							<!-- <th>발신누적횟수</th> -->
						</tr>
					</thead>
					<tbody id="smsList">
						<c:forEach items="${sms}" var="sms" varStatus="status">
							<tr>
								<td data-label="No.">${totalCount - status.index}</td>
								<td data-label="발송일시"><fmt:formatDate
										value="${sms.sendDate}" pattern="yyyy-MM-dd" /></td>
								<td data-label="수신인"><c:out value="${sms.memCode}" /></td>
								<td data-label="내용"><span class="sms-content"
									onclick="showSmsDetails(${sms.smsContent})"><c:out value="${sms.smsContent}" /></span></td>
								<!-- <td data-label="발신누적횟수">5</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이지 이동 인터페이스 영역 -->
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
						<!-- Previous Button -->
						<c:if test="${pageMaker.prev}">
							<li class="pageMaker_btn prev"><a
								href="${pageMaker.pageStart - 1}">이전</a></li>
						</c:if>
						<!-- Page Numbers -->
						<c:forEach begin="${pageMaker.pageStart}"
							end="${pageMaker.pageEnd}" var="num">
							<li
								class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active' : ''}">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>
						<!-- Next Button -->
						<c:if test="${pageMaker.next}">
							<li class="pageMaker_btn next"><a
								href="${pageMaker.pageEnd + 1}">다음</a></li>
						</c:if>
					</ul>
				</div>


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
			<form id="searchForm" onsubmit="return false;"
				class="search-container">
				<select id="popupSearchCategory" name="category">
					<option value="name">성명</option>
					<option value="id">ID</option>
					<option value="phone">전화번호</option>
				</select> <input type="text" class="search-bar" id="popupSearchQuery"
					placeholder="검색어를 입력하세요." name="keyword">
				<button type="button" onclick="popupSearchMember()">검색</button>
			</form>
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
				<button type="submit" class="send-button">보내기</button>
			</form>
		</div>
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
