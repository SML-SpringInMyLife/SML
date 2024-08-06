<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/community.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdn.ckeditor.com/ckeditor5/41.4.2/classic/ckeditor.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>게시글 수정</h1>
		<div class="community-container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community-content-main">
				<form id="modifyForm" action="community/modify" method="post">
					<div class="form_section_title">
						<div class="form_section_title">
							<label>#</label>
						</div>
						<div class="form_section_content">
							<input name="commTitle" value="<c:out value='${communityDetail.commTitle}'/>">
							<span id="warn_commTitle">글 제목을 입력해주세요.</span>
						</div>
						<div class="form_section_title">
							<label>작성자</label>
						</div>
						<div class="form_section_content">
							<input name="commWriter" readonly="readonly" value="<c:out value='${communityDetail.commWriter}'/>"/>
						</div>
						<div class="form_section_title">
							<label>등록일</label>
						</div>
						<div class="form_section_content">
							<input class="input_block" type="text" name="enrollDate" readonly="readonly" value="<fmt:formatDate value="${communityDetail.enrollDate}" pattern="yyyy-mm-dd"/>">
						</div>
						<div class="form_section_title">
							<label>게시글 내용</label>
						</div>
						<div class="form_section_content">
							<textarea name="commContent" id="commContent_textarea"><c:out value='${communityDetail.commContent}'/></textarea>
						</div>
						<div class="form_section_title">
							<label>조회수</label>
						</div>
						<div class="form_section_content">
							<input class="input_block" name="commCount" readonly="readonly" value="<c:out value='${communityDetail.commCount}'/>"/>
						</div>
					</div>
				</form>
				<div class="btn_section">
					<button id="cancelBtn" class="btn">취소</button>
					<button id="modifyBtn" class="btn">수정</button>
				</div>
			</div>	
		</div>
		
		<form id="moveForm" method="get">
        	<input type="hidden" name="commCode" value='<c:out value="${communityDetail.commCode }"/>'>
            <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
            <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
            <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
		// editor - content
		ClassicEditor
			.create(document.querySelector('#commContent_textarea'))
			.catch(error=>{
				console.error(error);
			});
		
		let moveForm = $("#moveForm");
		// 취소 버튼
		$("#cancelBtn").on("click", function(e){
			e.preventDefault();

			moveForm.attr("action", "/community/detail")
			moveForm.submit();
		});
	
		let modifyForm = $("#modifyForm");
		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e){
			let commTitle = $(".form_section_content input[name='commTitle']").val();
			let titleCheck = false; 
			
			e.preventDefault();
			
			/* 제목 공백 체크 */
		    if(commTitle ===''){
		    	wCommTitle.css('display', 'block');
		    	titleCheck = false;
		    } else {
		    	wCommTitle.css('display', 'none');
		    	titleCheck = true;
		    }
			
		    /* 최종 검사 */
		    if(titleCheck){
		    	modifyForm.submit();
		    } else {
		    	return false;
		    } 
		});
	</script>
</body>
</html>
