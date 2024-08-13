<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/course/course.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>수업 상세 페이지</h1>
		<div class="course-container">
			<jsp:include page="/WEB-INF/views/course/courseMenu.jsp"/>
			<div class="course-board-detail">
				<table class="course-board-detail">
					<tr>
						<td>분류</td>
						<td>
							<input name="ccatName" readonly="readonly" value="<c:out value='${detail.ccatName}'/>">
						</td>
					</tr>
					<tr>
						<td>강좌명</td>
						<td>
                    		<input name="courseName" readonly="readonly" value="<c:out value='${detail.courseName}'/>">
                		</td>
                	</tr>
                	<tr>
                		<td>강사명</td>
                		<td>
                			<input name="teaName" readonly="readonly" value="<c:out value='${detail.teaName}'/>">
						</td>
					</tr>
					<tr>
						<td>수강 기간</td>
						<td>
							<input type="date" readonly="readonly" value="<c:out value='${detail.startDate}'/>">
							~
							<input type="date" readonly="readonly" value="<c:out value='${detail.endDate}'/>">
						</td>
					</tr>
					<tr>
						<td>수업 시간/요일</td>
						<td>
							<input type="time" readonly="readonly" value="<c:out value='${detail.startTime}'/>">
							~
							<input type="time" readonly="readonly" value="<c:out value='${detail.endTime}'/>">
							<br>
							<input type="text" readonly="readonly" value="<c:out value='${detail.courseDay}'/>">
						</td>
					</tr>	
					<tr>
						<td>수강 인원</td>
						<td>
							<span>수강 신청 인원</span>
							/
							<input name="courseLimit" readonly="readonly" value="<c:out value='${detail.courseLimit}'/>">
						</td>
					</tr>
					<tr>
						<td>차감 포인트</td>
						<td>
							<input name="course" readonly="readonly" value="<c:out value='${detail.coursePoint}'/>">
						</td>
					</tr>
					<tr>
						<td>수업 설명</td>
						<td>
							<input class="input_block" name="courseContent" readonly="readonly" value="<c:out value='${detail.courseContent}'/>"/>
						</td>
					</tr>
				</table>
				<div class="btn_section">
					<button id="applyBtn" class="btn">수강 신청</button>
				</div>
			</div>
		</div>
	
		
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
