<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>SML_Admin(채팅상담관리)</title>
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
					<select id="type" name="type">
						<option value="all">전체</option>
						<option value="date">상담일시</option>
						<option value="id">ID</option>
						<option value="name">성명</option>
						<option value="content">내용</option>
					</select>
					<div class="search-bar">
						<input type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<button onclick="search()">검색</button>
					</div>
				</div>
				<table class="chat-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="분류">분류</th>
							<th data-label="상담일시">상담일시</th>
							<th data-label="아이디">아이디</th>
							<th data-label="성명">성명</th>
							<th data-label="내용">내용</th>
						</tr>
					</thead>
					<c:if test="${chat == 'empty' || chat.size() == 0}">
						<div class="table_empty">등록된 상담이 없습니다.</div>
					</c:if>
					<c:if test="${chat != 'empty'}">
						<tbody id="chatList">
							<c:forEach items="${chat}" var="chat" varStatus="status">
								<tr>
									<td data-label="No.">${totalCount - status.index}</td>
									<td data-label="분류">${chat.categoryCode}</td>
									<td data-label="상담일시"><fmt:formatDate
											value="${chat.chatDate}" pattern="yyyy-MM-dd HH:mm" /></td>
									<td data-label="아이디">${chat.memCode}</td>
									<td data-label="성명">${chat.memCode}</td>
									<!-- 내용, 길면 축약 표시 -->
									<td data-label="내용"><span class="chat-content"
										data-chat-content="<c:out value='${chat.chatContent}' />">
											<c:choose>
												<c:when test="${fn:length(chat.chatContent) > 25}">
													<c:out value="${fn:substring(chat.chatContent, 0, 25)}" />...
											</c:when>
												<c:otherwise>
													<c:out value="${chat.chatContent}" />
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
							<li class="pageMaker_btn prev"><a
								href="${pageMaker.pageStart - 1}">이전</a></li>
						</c:if>
						<!-- Page Numbers -->
						<c:forEach begin="${pageMaker.pageStart}"
							end="${pageMaker.pageEnd}" var="num">
							<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":"" }">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>
						<!-- Next Button -->
						<c:if test="${pageMaker.next}">
							<li class="pageMaker_btn next"><a
								href="${pageMaker.pageEnd + 1 }">다음</a></li>
						</c:if>
					</ul>
				</div>
				<form id="moveForm" action="/admin/chatList" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}"> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div>
	</main>

	<!-- 채팅 상세보기 팝업 -->
	<div id="chatDetailsPopup" class="chatDetail-Popup">
		<div class="chat-popup-content">
			<span class="close" onclick="closeChatDetailsPopup()">X</span>
			<h2>채팅상담 상세보기</h2>
			<div id="chatDetailsContent"></div>
		</div>
	</div>


	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
