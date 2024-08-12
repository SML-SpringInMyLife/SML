<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>강사 등록 - 취미교실</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<style>
/* 입력란 공란 경고 태그 */
.form_section_content span{    
    display: none;
    padding-top: 10px;
    text-align: center;
    color: #e05757;
    font-weight: 300;    
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
	<div class="teacher_content_main">
		<form action="/admin/teacher/enroll.do" method="post" id="enrollForm">
			<div class="form_section">
				<div class="form_section_title">
					<label>강사명</label>
				</div>
				<div class="form_section_content">
					<input name="teaName">
					<span id="warn_teaName">강사 이름을 입력해주세요.</span>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>강사 소개</label>
				</div>
				<div class="form_section_content">
					<input name="teaIntro" type="text" placeholder="강사 소개">
				</div>
			</div>
		</form>
		<div class="btn_section">
			<button id="cancelBtn" class="btn">취소</button>
			<button id="enrollBtn" class="btn enroll_btn">등록</button>
		</div>
	</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	/* 등록 버튼 */
	$("#enrollBtn").click(function(){  
		let nameCheck = false;
		let teaName = $('input[name=teaName]').val(); 
		let wteaName = $('#warn_teaName');  
		
		if(teaName ===''){
			wteaName.css('display', 'block');
	        nameCheck = false;
	    } else{
	    	wteaName.css('display', 'none');
	        nameCheck = true;
	    }

		if(nameCheck){
	        $("#enrollForm").submit();    
	    } else{
	        return;
	    }	
	});
	 
	/* 취소 버튼 */
	$("#cancelBtn").click(function(){
	    location.href="/admin/teacher/list"
	});
	
	$(document).ready(function(){
	    
		let result = '<c:out value="${enroll_result}"/>';
	    
	    checkResult(result);
	    
	    function checkResult(result){
	        
	        if(result === ''){
	            return;
	        }
	        
	        alert("강사'${enroll_result}' 을 등록하였습니다.");
	        
	    }
	 
	});
	</script>
</body>
</html>
