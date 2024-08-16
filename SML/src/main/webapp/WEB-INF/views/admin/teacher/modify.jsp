<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>게시글 상세 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>강사 수정</h2>
				<form id="modifyForm" action="/admin/teacher/modify" method="post">
				<table class="course-table">
					<tr>
						<td>강사 번호</td>
						<td>
							<input name="teaCode" readonly="readonly" value="<c:out value='${detail.teaCode}'/>"/>						
					</tr>
					<tr>
						<td>강사명</td>
						<td>
							<input name="teaName" value="<c:out value='${detail.teaName}'/>"/>
							<span id="warn_teaName">강사 이름을 입력해주세요.</span>
						</td>
					</tr>
					<tr>
						<td>강사 소개</td>
						<td>
							<input name="teaIntro" type="text" value="<c:out value='${detail.teaIntro}'/>"/>
						</td>
					</tr>
				</table>
				<div class="btn_section">
					<button id="deleteBtn" class="btn delete_btn">삭제</button>
        			<button id="cancelBtn" class="btn">취소</button>
        			<button id="modifyBtn" class="btn modify_btn">수정</button>
    			</div>
    			</form>
			</div>
		</div>
		<form id="moveForm" method="get">
        	 <input type="hidden" name="teaCode" value='<c:out value="${detail.teaCode}"/>'>
             <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
             <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>' >
             <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		let moveForm = $("#moveForm");
		let modifyForm = $("#modifyForm");
		
		/* 삭제 버튼 */
		$("#deleteBtn").on("click", function(e){
			e.preventDefault();
			moveForm.find("input").remove();
			moveForm.append('<input type="hidden" name="teaCode" value="${detail.teaCode}">');
			moveForm.attr("action", "/admin/teacher/delete");
			moveForm.attr("method", "post");
			moveForm.submit();
		});

		// 취소 버튼
		$("#cancelBtn").on("click", function(e) {
			e.preventDefault();
			
			moveForm.attr("action", "/admin/teacher/detail")
			moveForm.submit();
		});

		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e) {
			let teaName = $(".form_section_content input[name='teaName']").val();
			let teaIntro = $(".form_section_content textarea").val();	
			
			let nameCheck = false;
			
			let wteaName = $('#warn_teaName'); 
			
			e.preventDefault();
			
			if(!teaName ===''){
				wteaName.css('display', 'block');
		        nameCheck = false;
		    } else{
		    	wteaName.css('display', 'none');
		        nameCheck = true;
		    }

			if(nameCheck){
		        modifyForm.submit(); 
		    } else{
		        return false;
		    }	
		});
	</script>
</body>
</html>
