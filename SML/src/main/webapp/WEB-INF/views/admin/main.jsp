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
							<td data-label="인 원">${memberCnt}<c:if
									test="${not empty cntCheck}">
									<p>회원 수가 없습니다.</p>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>50대 미만</td>
							<td>${ageGroupCnt['UNDER50']}</td>
						</tr>
						<tr>
							<td>50대</td>
							<td>${ageGroupCnt['AGE50S']}</td>
						</tr>
						<tr>
							<td>60대</td>
							<td>${ageGroupCnt['AGE60S']}</td>
						</tr>
						<tr>
							<td>70대</td>
							<td>${ageGroupCnt['AGE70S']}</td>
						</tr>
						<tr>
							<td>80대</td>
							<td>${ageGroupCnt['AGE80S']}</td>
						</tr>
						<tr>
							<td>90대 이상</td>
							<td>${ageGroupCnt['AGE90PLUS']}</td>
						</tr>
					</tbody>
				</table>

				<!-- 년도 Selection -->
				<div class="chart-section">
					<h2>가입 현황</h2>
					<div class="select-wrapper">
						<select id="yearSelect" onchange="updateChart()">
							<!-- 옵션은 JavaScript에서 동적으로 추가 -->
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

	<script>
		// 현재 연도를 가져옴
		var currentYear = new Date().getFullYear();
		var selectElement = document.getElementById('yearSelect');

		// 현재 연도부터 과거 10년까지의 옵션을 생성
		for (var i = 0; i < 10; i++) {
			var year = currentYear - i;
			var option = document.createElement('option');
			option.value = year;
			option.text = year;
			selectElement.appendChild(option);
		}
	</script>

	<script src="${webappRoot}/resources/js/admin/admin.js"></script>
</body>
</html>
