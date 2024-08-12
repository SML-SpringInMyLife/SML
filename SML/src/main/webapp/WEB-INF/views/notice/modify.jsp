<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<!-- enroll.css 로드 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/enroll.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<form action="/notice/modify" method="post" id="modifyForm">
<input type="hidden" name="noticeCode"
						value="<c:out value='${noticeDetail.noticeCode}'/>">
<input type="hidden" name="memCode" value="${noticeDetail.memCode}" />						
						
	
						
			<div class="enroll-container">
				<div class="category">
					<select>
						<option>☰ 게시글카테고리</option>
						<option>수강안내</option>
						<option>전체공지</option>
						<option>이벤트</option>
					</select>
				</div>
				<div class="title">
					<span id="alram_title">제목을 입력해 주세요</span> <input name="noticeTitle"
						type="text" size="30" class="titletext"
						value=<c:out value="${noticeDetail.noticeTitle}" />>
				</div>

				<div class="content">
					<span id="alram_body">내용을 입력해 주세요</span>
					<textarea name="noticeBody" class="bodytext">
          <c:out value="${noticeDetail.noticeBody}" />
          </textarea>

					<div class="file-upload">
						<input id="file-upload" type="file">
					</div>

					<input type="hidden" name="noticeCode"
						value="<c:out value='${noticeDetail.noticeCode}'/>">
				</div>
				<div class="actions">
					<button id="modifybtn" class="update">수정</button>
					<button id="cancelbtn" class="cancel">취소</button>
				</div>
			</div>
		</form>

		<!--  <form id="moveForm" method="get">
        </form> -->
	</main>
	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		/* let moveForm = $("#moveForm"); */
		let moveForm = $("#modifyForm");

		/* 글 수정버튼, 유효성 검사 */
		$("#modifybtn").on("click", function(e) {

			let noticeTitle = $(".title input[name='noticeTitle']").val();
			let noticeBody = $(".content textarea[name='noticeBody']").val();

			let TitleCk = false;
			let BodyCk = false;

			e.preventDefault();

			if (!noticeTitle) {
				$("#alram_title").css("display", "block");
			} else {
				$("#alram_title").css("display", "none");
				TitleCk = true;
			}
			if (!noticeBody) {
				$("#alram_body").css("display", "block");
			} else {
				$("#alram_body").css("display", "none");
				BodyCk = true;
			}

			if (TitleCk && BodyCk) {
				modifyForm.submit();
			} else {
				return false;
			}

		});

		/* 공지사항 취소버튼 페이지 이동 */
		$("#cancelbtn").on("click", function(e) {

			e.preventDefault();

			moveForm.attr("action", "/notice/detail")
			moveForm.submit();
		});
	</script>


</body>
</html>
