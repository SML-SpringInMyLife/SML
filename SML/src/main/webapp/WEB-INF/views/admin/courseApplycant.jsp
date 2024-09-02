<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>SML_Admin(수강신청관리)</title>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>수강신청 목록</h2>
				<form id="searchForm" action="/admin/courseApplycant" method="get"
					class="search-container">
					<select id="type" name="type">
						<option value="all">전체</option>
						<option value="courseName">수업명</option>
						<option value="enrolleeName">성명</option>
						<option value="phone">전화번호</option>
					</select>
					<div class="search-bar">
						<input type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<input type="hidden" name="pageNum"
							value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
						<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
						<button onclick="search()">검색</button>
					</div>
				</form>

				<table class="course-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="수업명">수업명</th>
							<th data-label="개강일">개강일</th>
							<th data-label="성명">성명</th>
							<th data-label="신청일">신청일</th>
							<!-- <th data-label="신청상태">신청상태</th>-->
							<th data-label="비고">비고</th>
						</tr>
					</thead>
					<c:if
						test="${courseApplycant == 'empty' || courseApplycant.size() == 0}">
						<div class="table_empty">등록된 신청회원이 없습니다.</div>
					</c:if>

					<c:if test="${courseApplycant != 'empty'}">
						<tbody id="courseList">

							<!-- 신청자 목록을 순회 -->
							<c:forEach items="${courseApplycant}" var="courseApplycant"
								varStatus="status">
								
								<!-- 신청자 정보 출력 -->
								<tr>
									<td data-label="No.">${totalCount - ((pageMaker.cri.pageNum - 1) * pageMaker.cri.amount + status.index)}</td>
									<!-- 순번 -->
									<td data-label="수업명">${courseApplycant.courseName}</td>
									<!-- 수업명 -->
									<td data-label="개강일">${courseApplycant.courseStartDate}</td>
									<!-- 개강일 -->
									<td data-label="성명">${courseApplycant.memName}</td>
									<!-- 성명 -->
									<td data-label="신일"><fmt:formatDate
											value="${courseApplycant.applyDate}" pattern="yyyy-MM-dd HH:mm" /></td>
									<!-- 개강일 -->
									<!-- 
									<td data-label="신청상태">
										<div class="status-container">
											<c:choose>
												<c:when test="${courseApplycant.applyStatus == 1}">신청 / </c:when>
												<c:otherwise>취소</c:otherwise>
											</c:choose>
											<form action="/admin/applyUpdateStatus" method="post"
												class="status-form">
												<input type="hidden" name="applyCode"
													value="${courseApplycant.applyCode}" />
												<c:choose>
													<c:when test="${courseApplycant.applyStatus == 1}">
														<input type="hidden" name="applyStatus" value="0" />
														<button type="submit" class="applyChangeStatus">취소</button>
													</c:when>
												</c:choose>
											</form>
										</div>
									</td> -->
									
									<td data-label="비고">-</td>
									<!-- 비고 -->
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
				<form id="moveForm" action="/admin/courseApplycant" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}"> <input
						type="hidden" name="type" value="${pageMaker.cri.type}"> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
