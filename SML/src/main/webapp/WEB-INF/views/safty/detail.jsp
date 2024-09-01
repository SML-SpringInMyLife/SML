<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생활안전 상세조회페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/detail.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <style type="text/css">
	#result_card img{
		max-width: 100%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
</style>
</head>
<body>
	<!-- common.css 로드 -->

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<main>
		<div class="detail-container">


			<div class="header">
				<div class="fix">
					 <fmt:formatDate
							value="${saftyDetail.saftyEnroll}" pattern="yyyy-MM-dd" /> 
					<label>작성일|</label>
					 <label>수정일 <fmt:formatDate value="${saftyDetail.saftyModify}" pattern="yyyy-MM-dd" /> |
					</label> <label >작성자: <c:out value= "${saftyDetail.memName}"/></label>
					 <label>| 조회수 <c:out value="${saftyDetail.saftyCount}" /></label> 
					 <label>| 좋아요 <span id="likeCount"> <c:out value="${saftyDetail.saftyLike}" /></span></label>
				</div>
				<button id="likeBtn" class="like ${saftyDetail.userLiked ? 'active' : ''}">♥</button>
				
			</div>

			<!-- 글제목 -->
			<div class="title">
				<c:out value="${saftyDetail.saftyTitle}" />
			</div>

			<!-- 글내용 -->
			<div class="content">
				<c:out value="${saftyDetail.saftyBody}" />
				
					<div class="form_section">
					<div class="form_section_title">
						
					</div>
					<div class="form_section_content">
						<div id="uploadResult"></div>
					</div>
				</div>

			</div>
			<div class="line"></div>
			<div class="btn-container">
				<button id="listbtn" class="list">목록</button>
				<c:if test="${isLoggedIn && isAdmin}">
				<button id="modifybtn" class="modify">수정</button>
				<button id="deletebtn" class="delete">삭제</button>
				</c:if>
			</div>

		</div>

		<form id="moveForm" method="get">
			<input type="hidden" name="saftyCode"
				value="<c:out value='${saftyDetail.saftyCode}'/>">
		</form>
        
	</main>
   
   	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
   
	<script>
	 /* 이미지 정보 호출 */
	let saftyCode = '<c:out value="${saftyDetail.saftyCode}"/>';
	let uploadResult = $("#uploadResult");			
	
	$.getJSON("/safty/getAttachList", {saftyCode : saftyCode}, function(arr){	
		
		if(arr.length === 0){			
			return;
		}
		
		let str = "";
		let obj = arr[0];	
		
		let fileCallPath = encodeURIComponent(obj.filePath + "/s_" + obj.fileUuid + "_" + obj.fileName);
		str += "<div id='result_card'";
		str += "data-path='" + obj.filePath + "' data-uuid='" + obj.fileUuid + "' data-filename='" + obj.fileName + "'";
		str += ">";
		str += "<img src='/safty/display?fileName=" + fileCallPath +"'>";
		str += "</div>";		
		
		uploadResult.html(str);						
		
	});
    
	
	$(document).ready(function() {
	    var saftyCode = '<c:out value="${saftyDetail.saftyCode}"/>';
	    
	    // 페이지 로드 시 AJAX 요청으로 조회수 증가
	    $.ajax({
	        url: '/safty/Count',
	        type: 'POST',
	        data: { saftyCode: saftyCode },
	        success: function(response) {
	            console.log("조회수 증가 성공");
	            // 조회수를 다시 가져와 화면에 표시
	            updateViewCount();
	        },
	        error: function(xhr, status, error) {
	            console.error("조회수 증가 실패", error);
	        }
	    });
	    
	    

	    let moveForm = $("#moveForm");
	    
	    /* 공지사항 조회 페이지 이동 */
	    $("#listbtn").on("click", function(e) {
	        e.preventDefault();
	        $("input[name=saftyCode]").remove();
	        moveForm.attr("action", "/safty/list");
	        moveForm.submit();
	    });

	    /* 공지사항 수정 페이지 이동 */
	    $("#modifybtn").on("click", function(e) {
	        e.preventDefault();
	        moveForm.attr("action", "/safty/modify");
	        moveForm.submit();
	    });

	    /* 공지사항 삭제 버튼 */
	    $("#deletebtn").on("click", function(e){
	        e.preventDefault();
	        if(confirm("정말로 이 공지사항을 삭제하시겠습니까?")) {
	            moveForm.find("input").remove();
	            moveForm.append('<input type="hidden" name="saftyCode" value="' + saftyCode + '">');
	            moveForm.attr("action", "/safty/delete");
	            moveForm.attr("method", "post");
	            moveForm.submit();
	        }
	    });
	});
	
	// 좋아요 버튼 클릭 이벤트
	$("#likeBtn").on("click", function() {
	    var saftyCode = '<c:out value="${saftyDetail.saftyCode}"/>';

	    $.ajax({
	        url: '/safty/like/' + saftyCode,
	        type: 'POST',
	        dataType: 'json',
	        success: function(response) {
	          
	            if(response.status === "Liked") {
	                $("#likeBtn").addClass("active");
	               
	            } else if(response.status === "Unliked") {
	                $("#likeBtn").removeClass("active");
	            
	            }
	            // 좋아요 수 실시간 업데이트
	            $("#likeCount").text(response.likeCount);
	            console.log("좋아요 수 업데이트:", response.likeCount);
	        },
	        error: function(xhr, status, error) {
	           
	            if(xhr.status === 401) {
	                alert("로그인이 필요합니다.");
	            } else {
	                console.error("좋아요 처리 실패", error);
	            }
	        }
	    });
	});

	</script>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>