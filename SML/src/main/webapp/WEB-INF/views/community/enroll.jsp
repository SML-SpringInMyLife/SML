<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>글 등록 - 커뮤니티</title>
<link rel="stylesheet" 	href="${webappRoot}/resources/css/common/common.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<h1>커뮤니티 글 작성</h1>
	<div class="community_enroll">
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
				<div class="form_section_title">
					<label>비밀글 설정</label>
				</div>
				<div class="form_section_content">
					<!-- checkbox -->
				</div>
			</div>
		</form>
		<div class="btn_section">
			<button id="cancelBtn" class="btn">취소</button>
			<button id="submitBtn" class="btn">등록</button>
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
		
		// 등록 버튼
		$("#enrollBtn").click(function() {
			
			let titleCheck = false;
			let contentCheck = false;
			
			let commTitle = $('input[name=commTitle]').val();
			let commContent = $('input[name=commContent]').val();
			
			let wCommTitle = $('#warn_commTitle');
			let wCommContent = $('#warn_commContent');
			
			// 공란 체크
			if (commTitle === '') {
				wCommTitle.css('display', 'block');
				titleCheck = false;
			} else {
				wCommTitle.css('display', 'none');
				titleCheck = true;
			}
			
			if (commContent === '') {
				wCommContent.css('display', 'block');
				contentCheck = false;
			} else {
				wCommContent.css('display', 'none');
				contentCheck = true;
			}
			
			if (titleCheck && contentCheck) {
				$("#enrollForm").submit();
			} else {
				return;
			}
			
		}
		
		// 취소 버튼
		$("#cancelBtn").click(function() {
			location.href="/community/boardList"
		});
		
	</script>
</body>
</html>
