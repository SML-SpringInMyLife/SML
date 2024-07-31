<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>수업 등록 - 수강 신청</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<h1>수업 등록</h1>
	<div class="course_enroll">
		<form action="/course/enroll" method="post" id="enrollForm">
			<div class="form_section">
				<div class="form_section_title">
					<label>수업명</label>
				</div>
				<div class="form_section_content">
					<input name="courseName">
					<span id="warn_courseName">글 제목을 입력해주세요.</span>
				</div>
			</div>
			<!-- insert 해야할 것
				수업명(class_name)
				수업 소개(class_content)
				수강 인원(class limit)
				수업 시작 날짜(class_start_date)
				수업 종료 날짜(class_end_date)
				강사
				수업 시작 시간(class_start_time)
				수업 종료 시간(class_end_time)
				수업 요일 (class day) -->
		</form>	
	</div>
	
	
		
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
