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
				<h2>강사 상세 페이지</h2>
				<table>
					<tr>
						<td>강사 번호</td>
						<td>
							<input name="teaCode" readonly="readonly" value="<c:out value='${detail.teaCode}'/>"/>						
					</tr>
					<tr>
						<td>강사명</td>
						<td>
							<input name="teaName" readonly="readonly" value="<c:out value='${detail.teaName}'/>"/>
						</td>
					</tr>
					<tr>
						<td>등록일</td>
						<td>
							<fmt:formatDate value="${detail.teaEnrollDate}" pattern="yyyy-MM-dd" var="formattedEnrollDate"/>
							<input class="input_block" name="teaEnrollDate" readonly="readonly" value="<c:out value='${formattedEnrollDate}'/>"/>
							<br>
							<c:if test="${not empty detail.teaModifyDate}">
                            	<fmt:formatDate value="${detail.teaModifyDate}" pattern="yyyy-MM-dd" var="formattedModifyDate"/>
                            	<small class="small-text">마지막 수정일 : <c:out value="${formattedModifyDate}"/></small>
                            </c:if>
                        </td>
                        <td>상태</td>
                        <td>
                        	<input name="teaStatus" readonly="readonly" value="<c:out value='${detail.teaStatus}'/>"/>					
						</td>
					</tr>
					<tr>
						<td>강사 소개</td>
						<td>
							<input name="teaIntro" readonly="readonly" value="<c:out value='${detail.teaIntro}'/>"/>
						</td>
					</tr>
				</table>
				<div class="btn_section">
        			<button id="cancelBtn" class="btn">목록</button>
        			<button id="modifyBtn" class="btn modify_btn">수정</button>
    			</div>
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

		// 목록으로 이동 버튼
		$("#cancelBtn").on("click", function(e) {
			e.preventDefault();

			$("input[name=teaCode]").remove();
			moveForm.attr("action", "/admin/teacher/list")
			moveForm.submit();
		});

		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e) {
			e.preventDefault();
			moveForm.attr("action", "/admin/teacher/modify");
			moveForm.submit();
		});
		
	</script>
</body>
</html>
