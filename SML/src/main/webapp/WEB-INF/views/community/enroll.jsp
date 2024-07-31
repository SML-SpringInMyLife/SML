<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>글 등록 - 커뮤니티</title>
<link rel="stylesheet" 	href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/enroll.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
	<h1>커뮤니티 글 작성</h1>
	<div class="community_enroll">
		<jsp:include page="/WEB-INF/views/community/communityMenu.jsp" />
		<form action="/community/enroll" method="post" id="enrollForm">
			<div class="form_section">
				<div class="form_section_title">
					<label>글 제목</label>
				</div>
				<div class="form_section_content">
					<input name="commTitle"> 
					<span id="warn_commTitle">글 제목을 입력해주세요.</span>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>글 내용</label>
				</div>
				<div class="form_section_content">
					<!-- <input name="commContent" type="text"> --> 
					<textarea name="commContent" id="commContent_textarea"></textarea>
					<span id="warn_commContent">글 내용을 입력해주세요.</span>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title secretPostLabel">
					<label>비밀 글 설정</label>
					<input type="checkbox" name="secretPost" id="secretPost">
				</div>
				<div class="form_section_content" id="passwordSection" style="display: none;">
					<label for="secretPassword">비밀 글 비밀번호 </label>
					<input type="password" name="secretPassword" id="secretPassword">
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
	
		// editor - content
		ClassicEditor
			.create(document.querySelector('#commContent_textarea'))
			.catch(error=>{
				console.error(error);
			});
		
		// 비밀글 설정 체크박스
		$("#secretPost").change(function() {
			if ($(this).is(":checked")) {
				$("#passwordSection").slideDown(); // 비밀번호 입력란 나타나기
			} else {
				$("#passwordSection").slideUp(); // 비밀번호 입력란 사라지기
			}
		});
		
		// 등록 버튼
		$("#enrollBtn").click(function() {
			/* 검사 통과 유무 변수 */
		    let titleCheck = false;            
		    let contentCheck = false; 
		    
		    /* 입력값 변수 */
		    let commTitle = $('input[name=commTitle]').val(); 
		    let commContent = $('textarea[name=commContent]').val();
		    
		    /* 공란 경고 span태그 */
		    let wCommTitle = $('#warn_commTitle');
		    let wCommContent = $('#warn_commContent');
		    
		    /* 제목 공백 체크 */
		    if(commTitle ===''){
		    	wCommTitle.css('display', 'block');
		    	titleCheck = false;
		    } else {
		    	wCommTitle.css('display', 'none');
		    	titleCheck = true;
		    }
		    
		    /* 내용 공백 체크 */
		    if(commContent ===''){
		    	wCommContent.css('display', 'block');
		    	contentCheck = false;
		    } else {
		    	wCommContent.css('display', 'none');
		    	contentCheck = true;
		    }
		    
		    /* 최종 검사 */
		    if(titleCheck && contentCheck){
		    	$("#enrollForm").submit();
		    } else {
		    	return;
		    }
		
		});
		
		// 취소 버튼
		$("#cancelBtn").click(function() {
			location.href="/community/boardList"
		});
		
	</script>
</body>
</html>
