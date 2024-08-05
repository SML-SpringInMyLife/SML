<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>SML_Admin(회원관리)</title>
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
					</select> <input type="text" id="searchQuery" class="search-bar"
						placeholder="검색어를 입력하세요.">
					<button onclick="searchMembers()">검색</button>
				</div>

				<table class="member-table">
					<!-- Caption added for better accessibility -->
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="ID">ID</th>
							<th data-label="성명">성명</th>
							<th data-label="생년월일">생년월일</th>
							<th data-label="전화번호">전화번호</th>
							<th data-label="잔여포인트">잔여포인트</th>
							<th data-label="회원상태">회원상태</th>
						</tr>
					</thead>
					<tbody id="memberList">
						<!-- 샘플 -->
						<c:forEach var="member" items="${members}">
							<tr>
								<td data-label="No."><c:out value="${member.memCode}" /></td>
								<td data-label="ID"><c:out value="${member.memId}" /></td>
								<td data-label="성명"><c:out value="${member.memName}" /></td>
								<td data-label="생년월일"><c:out value="${member.memPhone}" /></td>
								<td data-label="전화번호"><c:out value="${member.memCode}" /></td>
								<td data-label="잔여포인트"><c:out
										value="${member.memTotalPoint}" /></td>
								<td data-label="회원상태"><c:out value="${member.memStatus}" />
									/
									<button class="changeStatus">휴면처리</button></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
