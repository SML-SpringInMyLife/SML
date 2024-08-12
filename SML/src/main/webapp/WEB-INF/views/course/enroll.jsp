<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>수업 등록 - 수강 신청</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
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
						<div class="cate_wrap">
							<span>대분류</span>
							<select class="cate1">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<span>중분류</span>
							<select class="cate2">
								<option selected value="none">선택</option>
							</select>
						</div>
						<span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span>        
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
	
	let enrollForm = = $("#enrollForm")
	
	// 취소 버튼
	$("#cancelBtn").click(function() {
		location.href = "/course/boardList"
	});
		
	// 등록 버튼
	$("#enrollBtn").on("click", function(e){
		
		e.preventDefault();

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
	}
		
	// 카테고리
	let cateList = JSON.parse('${cateList}');
		
	let cate1Array = new Array();
	let cate2Array = new Array();
	let cate3Array = new Array();
	let cate4Array = new Array();
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	let cate3Obj = new Object();
	let cate4Obj = new Object();
		
	let cateSelect1 = $(".cate1");
	let cateSelect2 = $(".cate2");
	let cateSelect3 = $(".cate3");
	let cateSelect4 = $(".cate4");
		
	/* 카테고리 배열 초기화 메서드 */
	function makeCateArray(obj,array,cateList, tier){
		for(let i = 0; i < cateList.length; i++){
			if(cateList[i].tier === tier){
				obj = new Object();
				
				obj.cateName = cateList[i].cateName;
				obj.cateCode = cateList[i].cateCode;
					
				array.push(obj);				
			}
		}
	}
		
	/* 배열 초기화 */
	makeCateArray(cate1Obj,cate1Array,cateList,1);
	makeCateArray(cate2Obj,cate2Array,cateList,2);
	makeCateArray(cate3Obj,cate2Array,cateList,3);
	makeCateArray(cate4Obj,cate2Array,cateList,4);
		
	$(document).ready(function(){
		console.log(cate1Array);
		console.log(cate2Array);
		console.log(cate3Array);
		console.log(cate4Array);
	});
	
	/* 대분류 <option> 태그 */
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
	}
	
	
	/* 중분류 <option> 태그 */
	$(cateSelect1).on("change",function(){
		
		let selectVal1 = $(this).find("option:selected").val();	
		
		cateSelect2.children().remove();
		cateSelect3.children().remove();
		
		cateSelect2.append("<option value='none'>선택</option>");
		cateSelect3.append("<option value='none'>선택</option>");
		
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].cateParent){
				cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
			}
		}
		
	});
	</script>
</body>
</html>
