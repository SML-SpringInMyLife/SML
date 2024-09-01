<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>강사 등록 - 취미교실</title>
<!-- CSS 파일 링크를 통일 -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="admin-container">
			<!-- 관리자 메뉴 포함 -->
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>강사 등록</h2>
				<div class="form_wrap">
					<form action="/admin/teacher/enroll.do" method="post"
						id="enrollForm">
						<div class="input_wrap">
							<div class="instructor-container">
								<div class="name_wrap">
									<div class="label">강사명</div>
									<input class="text_input" name="teaName" required>
								</div>
								<div class="Intro_wrap">
									<div class="label">강사 소개</div>
									<textarea class="text_input" name="teaIntro" required></textarea>
								</div>
							</div>
							<div class="button_wrap">
								<button id="cancelBtn" class="cancel_button">취소</button>
								<button id="enrollBtn" class="save_button">등록</button>
							</div>
					</form>
				</div>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
		/* 등록 버튼 클릭 이벤트 */
		$("#enrollBtn").click(function() {
			let nameCheck = false;
			let teaName = $('input[name=teaName]').val();
			let wteaName = $('#warn_teaName');

			if (teaName === '') {
				wteaName.css('display', 'block');
				nameCheck = false;
			} else {
				wteaName.css('display', 'none');
				nameCheck = true;
			}

			if (nameCheck) {
				$("#enrollForm").submit();
			} else {
				return;
			}
		});

		/* 취소 버튼 클릭 이벤트 */
		$("#cancelBtn").click(function() {
			location.href = "/admin/teacher/list";
		});

		$(document).ready(function() {
			let result = '<c:out value="${enroll_result}"/>';

			checkResult(result);

			function checkResult(result) {
				if (result === '') {
					return;
				}

				alert("강사 정보 등록 결과: " + result);
			}
		});
	</script>
</body>
</html>
