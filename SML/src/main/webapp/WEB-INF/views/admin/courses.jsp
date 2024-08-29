<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<div class="search-container">
					<select id="type" name="type">
						<option value="all">전체</option>
						<option value="courseName">수업명</option>
						<option value="enrolleeName">성명</option>
						<option value="phone">전화번호</option>
					</select>
					<div class="search-bar">
						<input
							type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<input type="hidden" name="pageNum"
							value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
						<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
						<button onclick="search()">검색</button>
					</div>
				</div>

				<table class="course-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="수업명">수업명</th>
							<th data-label="개강일">개강일</th>
							<th data-label="신청자No.">신청자No.</th>
							<th data-label="성명">성명</th>
							<th data-label="전화번호">전화번호</th>
							<th data-label="신청상태">신청상태</th>
							<th data-label="비고">비고</th>
						</tr>
					</thead>
					<tbody id="courseList">
						<!-- Sample Group Row -->
						<tr class="course-group">
							<td colspan="8" data-label="수업명">2024 자바 프로그래밍</td>
						</tr>
						<tr>
							<td data-label="No.">1</td>
							<td data-label="수업명">자바 프로그래밍</td>
							<td data-label="개강일">2023-08-01</td>
							<td data-label="신청자No.">1</td>
							<td data-label="성명">홍길동</td>
							<td data-label="전화번호">010-1234-5678</td>
							<td data-label="신청상태">완료 /
								<button class="changeStatus">취소</button>
							</td>
							<td data-label="비고">-</td>
						</tr>
						<tr>
							<td data-label="No.">2</td>
							<td data-label="수업명">자바 프로그래밍</td>
							<td data-label="개강일">2023-08-01</td>
							<td data-label="신청자No.">2</td>
							<td data-label="성명">임꺽정</td>
							<td data-label="전화번호">010-1234-5678</td>
							<td data-label="신청상태">취소</td>
							<td data-label="비고">-</td>
						</tr>
						<tr>
							<td data-label="No.">3</td>
							<td data-label="수업명">자바 프로그래밍</td>
							<td data-label="개강일">2023-08-01</td>
							<td data-label="신청자No.">3</td>
							<td data-label="성명">유관순</td>
							<td data-label="전화번호">010-1234-5678</td>
							<td data-label="신청상태">완료 /
								<button class="changeStatus">취소</button>
							</td>
							<td data-label="비고">-</td>
						</tr>
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
				<form id="moveForm" action="/admin/courses" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}"> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
