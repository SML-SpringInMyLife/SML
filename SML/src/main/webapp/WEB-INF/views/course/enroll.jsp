<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>수업 등록 - 수강 신청</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/course/course.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>수업 등록</h1>
		<div class="course-container">
			<form action="/course/enroll" method="post" id="enrollForm">
				<div class="form_section">
					<div class="form_section_title">
						<label>수업명</label>
					</div>
					<div class="form_section_content">
						<input name="courseName"> 
						<span id="warn_courseName">수업 명을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>카테고리</label>
					</div>
					<div class="form_section_content">
						<input name="ccatCode" placeholder="카테고리를 선택해주세요.">
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 인원</label>
					</div>
					<div class="form_section_content">
						<input name="courseLimit"> 
						<span id="warn_courseLimit">수강 최대 인원을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 기간</label>
					</div>
					<div class="form_section_content">
						<label>개강일</label>
							<input type="date" id="startDate" name="startDate"> 
						<label>종강일</label>
							<input type="date" id="endDate" name="endDate"> 
						<span id="warn_coursePeriod">수강 기간을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>강사</label>
					</div>
					<div class="form_section_content">
						<input name="teacherCode"> 
						<span id="warn_courseTeacher">강사를 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수업 시간</label>
					</div>
					<div class="form_section_content">
						<label for="start_time"></label> 
							<input type="time" id="startTime" name="startTime"> 
						<label for="end_time">~</label>
							<input type="time" id="endTime" name="endTime"> 
						<span id="warn_courseTime">수강 시간을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 요일</label>
					</div>
					<div class="form_section_content">
						<input name="courseDay"> 
						<span id="warn_courseDay">수강 요일을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수업 소개</label>
					</div>
					<div class="form_section_content">
						<input name="courseContent"> 
					</div>
				</div>
			</form>
			<div class="btn_section">
				<button id="cancelBtn" class="btn">취소</button>
				<button id="enrollBtn" class="btn enroll_btn">등록</button>
			</div>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
		// 등록 버튼
		$("#enrollBtn").click(
				function() {
					/* 검사 통과 유무 변수 - 수업명, */
					let nameCheck = false;
					let limitCheck = false;
					let periodCheck = false;
					let teacherCheck = false;
					let timeCheck = false;
					let dayCheck = false;

					/* 입력값 변수 */
					let name = $('input[name=courseName]').val();
					let limit = $('input[name=courseLimit]').val();
					let startDate = $('input[name=courseStartDate]').val();
					let endDate = $('input[name=courseEndDate]').val();
					let startTime = $('input[name=courseStartTime]').val();
					let endTime = $('input[name=courseEndTime]').val();
					let teacher = $('input[name=courseTeacher]').val();
					let day = $('input[name=courseDay]').val();

					/* 공란 경고 span태그 */
					let wCourseName = $('#warn_courseName');
					let wCourseLimit = $('#warn_courseLimit');
					let wCoursePeriod = $('#warn_coursePeriod');
					let wCourseTeacher = $('#warn_courseTeacher');
					let wCourseTime = $('#warn_courseTime');
					let wCourseDay = $('#warn_courseDay');

					/* 제목 공백 체크 */
					if (name === '') {
						wCourseName.css('display', 'block');
						nameCheck = false;
					} else {
						wCourseName.css('display', 'none');
						nameCheck = true;
					}
					
					/* 수강 인원 공백 체크 */
					if (limit === '') {
						wCourseLimit.css('display', 'block');
						limitCheck = false;
					} else {
						wCourseLimit.css('display', 'none');
						limitCheck = true;
					}

					/* 수강 기간 체크 */
					if (startDate === '' || endDate === '' || new Date(startDate) > new Date(endDate)) {
						wCoursePeriod.css('display', 'block');
						periodCheck = false;
					} else {
						wCoursePeriod.css('display', 'none');
						periodCheck = true;
					}

					/* 강사 공백 체크 */
					if (teacher === '') {
						wCourseTeacher.css('display', 'block');
						teacherCheck = false;
					} else {
						wCourseTeacher.css('display', 'none');
						teacherCheck = true;
					}

					/* 수업 시간 체크 */
					if (startTime === '' || endTime === ''
							|| startTime >= endTime) {
						wCourseTime.css('display', 'block');
						timeCheck = false;
					} else {
						wCourseTime.css('display', 'none');
						timeCheck = true;
					}

					/* 수강 요일 공백 체크 */
					if (day === '') {
						wCourseDay.css('display', 'block');
						dayCheck = false;
					} else {
						wCourseDay.css('display', 'none');
						dayCheck = true;
					}

					/* 최종 검사 */
					if (nameCheck && limitCheck && periodCheck && teacherCheck && timeCheck && dayCheck) {
						$("#enrollForm").submit();
					} else {
						return;
					}
				});

		// 취소 버튼
		$("#cancelBtn").click(function() {
			location.href = "/course/boardList"
		});
	</script>
</body>
</html>
