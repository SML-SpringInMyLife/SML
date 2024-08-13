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
						<span id="warn_courseName">수업명을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>강사</label>
					</div>
					<div class="form_section_content">
						<input id="teaName_input" readonly="readonly">
						<input id="teaCode_input" name="teaCode" type="hidden"> 
						<button class="teaCode_btn">강사 선택</button>
						<span id="warn_courseTeacher">강사를 선택해주세요.</span>
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
							<select class="cate2" name="ccatCode">
								<option selected value="none">선택</option>
							</select>
						</div> 
						<span class="ck_warn ccatCode_warn">카테고리를 선택해주세요.</span>        
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
	let enrollForm = $("#enrollForm")
	
	/* 취소 버튼 */
	$("#cancelBtn").click(function(){
		location.href="/course/boardList"
	});

	/* 등록 버튼 */
	$("#enrollBtn").on("click",function(e){
		e.preventDefault();
		enrollForm.submit();
	});
	
	// 강사 선택 팝업
	$('.teaCode_btn').on("click",function(e){
		e.preventDefault();
		let popUrl = "/admin/teacher/popup"
		let popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		window.open(popUrl, "강사 찾기", popOption);
	});
	
	// 카테고리 리스트 구현
	let cateList = JSON.parse('${cateList}');
	
	let cate1Array = new Array();
	let cate2Array = new Array();

	let cate1Obj = new Object();
	let cate2Obj = new Object();
	
	let cateSelect1 = $(".cate1");		
	let cateSelect2 = $(".cate2");
	
	/* 카테고리 배열 초기화 메서드 */
	function makeCateArray(obj,array,cateList, tier){
		for(let i = 0; i < cateList.length; i++){
			if(cateList[i].tier === tier){
				obj = new Object();
				
				obj.ccatName = cateList[i].ccatName;
				obj.ccatCode = cateList[i].ccatCode;
				obj.parentCode = cateList[i].parentCode;
				
				array.push(obj);					
			}
		}
	}		

	/* 배열 초기화 */
	makeCateArray(cate1Obj,cate1Array,cateList,1);
	makeCateArray(cate2Obj,cate2Array,cateList,2);

	$(document).ready(function(){
		console.log(cate1Array);
		console.log(cate2Array);
	});
	
	// 대분류
	for(let i = 0; i<cate1Array.length; i++){
		cateSelect1.append("<option value='" +cate1Array[i].ccatCode +"'>" +cate1Array[i].ccatName +"</option>");
	}
	
	/* 중분류 <option> 태그 */
	$(cateSelect1).on("change",function(){
		let selectVal1 = $(this).find("option:selected").val();	
		cateSelect2.children().remove();
		cateSelect2.append("<option value='none'>선택</option>")
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].parentCode){
				cateSelect2.append("<option value='"+cate2Array[i].ccatCode+"'>" + cate2Array[i].ccatName + "</option>");	
			}
		}
	});
	</script>
</body>
</html>
