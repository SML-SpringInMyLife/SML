<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myCourses(내수강신청내역)</title>

</head>
<body>
<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<div class="member-container">
		<jsp:include page="/WEB-INF/views/member/mypage.jsp" />
			<div class="member-main-content">
				<h2>나의 수강신청 목록</h2>
				<div class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="courseName">수업명</option>
						<option value="enrolleeName">성명</option>						
					</select> <input type="text" id="searchQuery" class="search-bar"
						placeholder="검색어를 입력하세요.">
					<button onclick="searchCourses()">검색</button>
				</div>

				<table class="course-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="접수일">접수일</th>
							<th data-label="성명">성명</th>
							<th data-label="수업명">수업명</th>
							<th data-label="개강일">개강일</th>					
							<th data-label="신청상태">신청상태</th>
							<th data-label="비고">비고</th>
						</tr>
					</thead>
					<tbody id="courseList">
						<!-- Sample Group Row -->
						<tr class="course-group">
							<td colspan="8" data-label="수업명">나의 수강신청 목록</td>
						</tr>
						<tr>
							<td data-label="No.">1</td>
							<td data-label="접수일">2024-07-13</td>
							<td data-label="성명">홍길동</td>
							<td data-label="수업명">자바 프로그래밍</td>
							<td data-label="개강일">2024-08-01</td>	
		                	<td data-label="신청상태">완료 /
								<button class="changeStatus">취소</button>
							</td>
							<td data-label="비고">-</td>
						</tr>
						<tr>
							<td data-label="No.">2</td>
							<td data-label="접수일">2024-07-12</td>
							<td data-label="성명">홍길동</td>
							<td data-label="수업명">스프링 프로그래밍</td>
							<td data-label="개강일">2023-08-03</td>	
					     	<td data-label="신청상태">취소</td>
							<td data-label="비고">-</td>
						</tr>
						<tr>
							<td data-label="No.">3</td>
							<td data-label="접수일">2024-07-10</td>
							<td data-label="성명">홍길동</td>
							<td data-label="수업명">HTML 프로그래밍</td>
							<td data-label="개강일">2023-08-11</td>						
					        <td data-label="신청상태">완료 /
								<button class="changeStatus">취소</button>
							</td>
							<td data-label="비고">-</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%></body>
</html>