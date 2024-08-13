<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>수업 수정 - 취미 교실</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/course/course.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>수업 관리 - 수정</h1>
		<div class="course-container">
			<form action="/course/modify" method="post" id="modifyForm">
				<div class="form_section">
					<div class="form_section_title">
						<label>수업명</label>
					</div>
					<div class="form_section_content">
						<input name="courseName" value="<c:out value='${detail.courseName}'/>"> 
						<span id="warn_courseName">수업명을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>강사</label>
					</div>
					<div class="form_section_content">
						<input id="teaName_input" readonly="readonly" value="<c:out value='${detail.teaName}'/>">
						<input id="teaCode_input" name="teaCode" type="hidden" value="<c:out value='${detail.teaCode}'/>"> 
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
							<label>대분류</label>
							<select class="cate1">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<label>중분류</label>
							<select class="cate2" name="ccatCode">
								<option selected value="none">선택</option>
							</select>
						</div> 
						<span class="warn_ccatCode">카테고리를 선택해주세요.</span>        
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 인원</label>
					</div>
					<div class="form_section_content">
						<input name="courseLimit" value="<c:out value='${detail.courseLimit}'/>"> 
						<span id="warn_courseLimit">수강 최대 인원을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 기간</label>
					</div>
					<div class="form_section_content">
						<label>개강일</label>
							<input type="date" id="startDate" name="startDate" autocomplete="off"> 
						<label>종강일</label>
							<input type="date" id="endDate" name="endDate" autocomplete="off"> 
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
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수강 요일</label>
					</div>
					<div class="form_section_content">
						<input name="courseDay" value="<c:out value='${detail.courseDay}'/>""> 
						<span id="warn_courseDay">수강 요일을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>수업 소개</label>
					</div>
					<div class="form_section_content">
						<input name="courseContent" value="<c:out value='${detail.courseContent}'/>"> 
					</div>
				</div>
				<input type="hidden" name="courseCode" value="${detail.courseCode}">
			</form>
			<div class="btn_section">
				<button id="cancelBtn" class="btn">취소</button>
				<button id="modifyBtn" class="btn modify_btn">수정</button>
			</div>
		</div>
		
		<form id="moveForm" action="course/boardList" method="get">
        	 <input type="hidden" name="courseCode" value='<c:out value="${detail.courseCode}"/>'>
             <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
             <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>' >
             <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	let modifyForm = $("#modifyForm")

	/* 등록 버튼 */
	$("#modifyBtn").on("click",function(e){
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

	let targetCate2 = '';
	let targetCate3 = '${detail.ccatCode}';
	
	// 대분류
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].ccatCode+"'>" + cate1Array[i].ccatName + "</option>");
	}	
	$(".cate1 option").each(function(i,obj){
		if(targetCate2.parentCode === obj.value){
			$(obj).attr("selected", "selected");
		}
	});
	
	// 중분류
	for(let i = 0; i < cate2Array.length; i++){
		if(targetCate3.parentCode === cate2Array[i].ccatCode){
			targetCate2 = cate2Array[i];	
		}
	}		
	for(let i = 0; i < cate2Array.length; i++){
		if(targetCate2.parentCode === cate2Array[i].parentCode){
			cateSelect2.append("<option value='"+cate2Array[i].ccatCode+"'>" + cate2Array[i].ccatName + "</option>");
		}
	}		
	$(".cate2 option").each(function(i,obj){
		if(targetCate2.ccatCode === obj.value){
		$(obj).attr("selected", "selected");
		}
	});
	
	/* 목록 이동 버튼 */
	$("#listBtn").on("click", function(e){
		e.preventDefault();
		$("#moveForm").submit();	
	});	
	
	</script>
</body>
</html>
