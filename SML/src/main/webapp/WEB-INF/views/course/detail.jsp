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
						<td>강좌명</td>
						<td>
                    		<input name="courseName" readonly="readonly" value="<c:out value='${list.courseName}'/>">
                		</td>
                	</tr>
                	<tr>
                		<td>강사명</td>
                		<td>
                			<input name="teacherName" readonly="readonly" value="<c:out value='${list.teacherName}'/>">
						</td>
					</tr>
					<tr>
						<td>수강 기간</td>
						<td>
							<input type="date" readonly="readonly" value="<c:out value='${list.startDate}'/>">
							~
							<input type="date" readonly="readonly" value="<c:out value='${list.endDate}'/>">
						</td>
					</tr>
					<tr>
						<td>수업 시간/요일</td>
						<td>
							<input type="time" readonly="readonly" value="<c:out value='${list.startTime}'/>">
							~
							<input type="time" readonly="readonly" value="<c:out value='${list.endTime}'/>">
							<br>
							<input type="text" readonly="readonly" value="<c:out value='${list.courseDay}'/>">
						</td>
					</tr>	
					<tr>
						<td>수강 인원</td>
						<td>
							<input name="courseLimit" readonly="readonly" value="<c:out value='${list.courseLimit}'/>">
						</td>
					</tr>
					<tr>
						<td>차감 포인트</td>
						<td>
							<input name="course" readonly="readonly" value="<c:out value='${list.coursePoint}'/>">
						</td>
					</tr>
				</table>
			</div>
		</div>
	
		
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
