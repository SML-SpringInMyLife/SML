<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<!-- 관리자 메뉴 포함 -->
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>SMS 관리</h2>
				<form id="searchForm" action="/admin/sms" method="get"
					class="search-container">
					<select id="type" name="type">
						<option value="all">전체</option>
						<option value="date"
							${pageMaker.cri.type == 'date' ? 'selected' : ''}>발송일</option>
						<option value="receiver"
							${pageMaker.cri.type == 'receiver' ? 'selected' : ''}>수신인</option>
						<option value="content"
							${pageMaker.cri.type == 'content' ? 'selected' : ''}>내용</option>
					</select>
					<div class="search-bar">
						<input type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<input type="hidden" name="pageNum"
							value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
						<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
						<button class='btn search_btn'>검색</button>
					</div>
				</form>
				<!-- SMS 리스트 테이블 -->
				<table class="sms-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="발송일">발송일</th>
							<th data-label="수신인">수신인</th>
							<th data-label="내용">내용</th>
						</tr>
					</thead>
					<c:if test="${sms == 'empty' || sms.size() == 0}">
						<div class="table_empty">등록된 문자가 없습니다.</div>
					</c:if>
					<c:if test="${sms != 'empty'}">
						<tbody id="smsList">
							<c:forEach items="${sms}" var="sms" varStatus="status">
								<tr>
									<td data-label="No.">${totalCount - ((pageMaker.cri.pageNum - 1) * pageMaker.cri.amount + status.index)}</td>
									<td data-label="발송일시"><fmt:formatDate
											value="${sms.sendDate}" pattern="yyyy-MM-dd HH:mm" /></td>
									<td data-label="수신인"><c:out
											value="${sms.memName}(${sms.memId})" /></td>
									<!-- 내용, 길면 축약 표시 -->
									<td data-label="내용"><span class="sms-content"
										onclick="showSmsDetails('<c:out value="${sms.smsContent}"/>')">
											<c:choose>
												<c:when test="${fn:length(sms.smsContent) > 25}">
													<c:out value="${fn:substring(sms.smsContent, 0, 25)}" />...
											</c:when>
												<c:otherwise>
													<c:out value="${sms.smsContent}" />
												</c:otherwise>
											</c:choose>
									</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</c:if>
				</table>

				<!-- 페이지 이동 인터페이스 영역 -->
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
						<!-- Previous Button -->
						<c:if test="${pageMaker.prev}">
							<li class="pageMaker_btn prev"><a href="#"
								data-page="${pageMaker.pageStart - 1}">이전</a></li>
						</c:if>
						<!-- Page Numbers -->
						<c:forEach begin="${pageMaker.pageStart}"
							end="${pageMaker.pageEnd}" var="num">
							<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":"" }">
								<a href="#" data-page="${num}">${num}</a>
							</li>
						</c:forEach>
						<!-- Next Button -->
						<c:if test="${pageMaker.next}">
							<li class="pageMaker_btn next"><a href="#"
								data-page="${pageMaker.pageEnd + 1}">다음</a></li>
						</c:if>
					</ul>
				</div>
				<form id="moveForm" action="/admin/sms" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}"> <input
						type="hidden" name="type" value="${pageMaker.cri.type}"> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>
				<!-- SMS 발송 버튼 -->
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
			<!-- 회원 검색 폼 -->
			<form id="memberSearchForm" onsubmit="return false;"
				class="search-container" action="searchMember.do">
				<select id="popupSearchType" name="type">
					<option value="all">전체</option>
					<option value="name">성명</option>
					<option value="id">ID</option>
					<option value="phone">전화번호</option>
				</select> <input type="text" class="search-bar" id="popupSearchQuery"
					placeholder="검색어를 입력하세요." name="keyword">
				<!-- 검색 버튼 -->
				<button type="button" onclick="popupSearchMember()">검색</button>
			</form>
			<div>
				<ol id="searchResults"></ol>
			</div>
			<!-- SMS 발송 폼 -->
			<form id="sendSms" method="post" action="sendSms.do">
				<!-- 숨겨진 회원 코드 -->
				<input type="hidden" id="memCode" name="memCode"> <label
					for="recipientNumber" class="sms-label">수신인 번호:</label> <input
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
				<!-- 보내기 버튼 -->
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
