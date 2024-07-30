<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>SML_Admin(수강신청관리)</title>
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
				<h2>수강신청 목록</h2>
				<div class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="courseName">수업명</option>
						<option value="enrolleeName">성명</option>
						<option value="phone">전화번호</option>
					</select> <input type="text" id="searchQuery" placeholder="검색어를 입력하세요.">
					<button onclick="searchCourses()">검색</button>
				</div>

				<table class="course-table">
					<thead>
						<tr>
							<th>No.</th>
							<th>수업명</th>
							<th>개강일</th>
							<th>신청자No</th>
							<th>성명</th>
							<th>전화번호</th>
							<th>신청상태</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="courseList">
						<!-- Sample Group Row -->
						<tr class="course-group">
							<td colspan="8">2024 자바 프로그래밍</td>
						</tr>
						<tr>
							<td>001</td>
							<td>자바 프로그래밍</td>
							<td>2023-08-01</td>
							<td>1</td>
							<td>홍길동</td>
							<td>010-1234-5678</td>
							<td>완료 / <button class="changeStatus">취소</button></td>
							<td>-</td>
						</tr>
						<tr>
							<td>002</td>
							<td>자바 프로그래밍</td>
							<td>2023-08-01</td>
							<td>2</td>
							<td>임꺽정</td>
							<td>010-1234-5678</td>
							<td>취소</button></td>
							<td>-</td>
						</tr>
						<tr>
							<td>003</td>
							<td>자바 프로그래밍</td>
							<td>2023-08-01</td>
							<td>3</td>
							<td>유관순</td>
							<td>010-1234-5678</td>
							<td>완료 / <button class="changeStatus">취소</button></td>
							<td>-</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>




	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
