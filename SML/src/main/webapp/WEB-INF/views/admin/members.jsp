<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>SML_Admin(회원관리)</title>
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
				<h2>회원 목록</h2>
				<div class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="id">ID</option>
						<option value="name">성명</option>
						<option value="phone">전화번호</option>
						<option value="status">회원상태</option>
					</select> <input type="text" id="searchQuery" placeholder="검색어를 입력하세요.">
					<button onclick="searchMembers()">검색</button>
				</div>

				<table class="member-table">
					<thead>
						<tr>
							<th>No.</th>
							<th>ID</th>
							<th>성명</th>
							<th>생년월일</th>
							<th>전화번호</th>
							<th>잔여포인트</th>
							<th>회원상태</th>
						</tr>
					</thead>
					<tbody id="memberList">
						<!-- 샘플 -->
						<tr>
							<td>1</td>
							<td>Hong</td>
							<td>홍길동</td>
							<td>1990-01-01</td>
							<td>010-1234-5678</td>
							<td>1500 point</td>
							<td>Y / <button class="changeStatus">휴면처리</button></td>
						</tr>
						<tr>
							<td>2</td>
							<td>Lim</td>
							<td>임꺽정</td>
							<td>1990-01-01</td>
							<td>010-1234-5678</td>
							<td>2000 point</td>
							<td>N / <button class="changeStatus">복구처리</button></td>
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
