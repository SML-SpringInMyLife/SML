<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin(메인)</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />

			<div class="admin-main-content">
				<h2>회원 통계</h2>
				<table class="stats-table">
					<thead>
						<tr>
							<th>구 분</th>
							<th>인 원</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td data-label="구 분">총 회원수</td>
							<td data-label="인 원">1234</td>
						</tr>
						<tr>
							<td data-label="구 분">50대 미만</td>
							<td data-label="인 원">200</td>
						</tr>
						<tr>
							<td data-label="구 분">50대</td>
							<td data-label="인 원">300</td>
						</tr>
						<tr>
							<td data-label="구 분">60대</td>
							<td data-label="인 원">250</td>
						</tr>
						<tr>
							<td data-label="구 분">70대</td>
							<td data-label="인 원">150</td>
						</tr>
						<tr>
							<td data-label="구 분">80대</td>
							<td data-label="인 원">100</td>
						</tr>
						<tr>
							<td data-label="구 분">90대 이상</td>
							<td data-label="인 원">34</td>
						</tr>
					</tbody>
				</table>

				<!-- 년도 Selection -->
				<div class="chart-section">
					<h2>가입 현황</h2>
					<div class="select-wrapper">
						<select id="yearSelect" onchange="updateChart()">
							<option value="2023">2023</option>
							<option value="2022">2022</option>
							<option value="2021">2021</option>
						</select>
					</div>
				</div>

				<!-- Bar Chart -->
				<canvas id="registrationChart"></canvas>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script src="${webappRoot}/resources/js/admin/admin.js"></script>
</body>
</html>
