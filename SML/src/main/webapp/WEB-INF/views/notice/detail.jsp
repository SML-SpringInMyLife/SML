<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세조회페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/detail.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
							value="${noticeDetail.noticeEnroll}" pattern="yyyy-MM-dd" /> 
					
					<label>작성일|
					</label> <label>수정일 <fmt:formatDate
							value="${noticeDetail.noticeModify}" pattern="yyyy-MM-dd" /> |
					</label> <label >작성자: ${noticeDetail.memCode}</label>
					 <label>| 조회수 <c:out value="${noticeDetail.noticeCount}" /></label> 
					 <label>| 좋아요 <c:out value="${noticeDetail.noticeLike}" /></label>
				</div>
				<button id="like" class="like">♥</button>
				<script>
					document.querySelector('.like').addEventListener('click',
							function() {
								this.classList.toggle('active');
							});
				</script>
			</div>

			<!-- 글제목 -->
			<div class="title">
				<c:out value="${noticeDetail.noticeTitle}" />
			</div>

			<!-- 글내용 -->
			<div class="content">
				<c:out value="${noticeDetail.noticeBody}" />

			</div>
			<div class="line"></div>
			<div class="btn-container">
				<button id="listbtn" class="list">목록</button>
				<button id="modifybtn" class="modify">수정</button>
				<button id="deletebtn" class="delete">삭제</button>
			</div>

		</div>
		<div class="pagination">
			<button class="page-btn active">1</button>
			<button class="page-btn">2</button>
			<button class="page-btn">3</button>
		</div>

		<form id="moveForm" method="get">
			<input type="hidden" name="noticeCode"
				value="<c:out value='${noticeDetail.noticeCode}'/>">
		</form>
        
        <form id="moveForm" method="get">
            <input type="hidden" name="noticeCode" value="<c:out value='${noticeDetail.noticeCode}'/>">
        </form>
	</main>
   
	<script>
	let moveForm = $("#moveForm");
	
		/* 공지사항 조회 페이지 이동 */
		$("#listbtn").on("click", function(e) {
			e.preventDefault();
			$("input[name=noticeCode]").remove();
			moveForm.attr("action", "/notice/list")
			moveForm.submit();
		});

		/* 공지사항 수정 페이지 이동 */
		$("#modifybtn").on("click", function(e) {
			e.preventDefault();
			moveForm.attr("action", "/notice/modify");
			moveForm.submit();
		});
	</script>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>
</html>