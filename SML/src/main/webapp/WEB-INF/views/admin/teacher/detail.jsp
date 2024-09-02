<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>게시글 상세 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="${webappRoot}/resources/css/admin/admin.css">
<style>
/* 기본 입력 필드 스타일링 */
input {
    border: 1px solid #ccc;
    border-radius: 4px;
    padding: 8px;
    width: 100%; /* 기본적으로 100% 너비 */
    box-sizing: border-box;
}

input[readonly] {
    background-color: #e9ecef;
    cursor: not-allowed;
}

/* 버튼 섹션 스타일링 */
.btn_section {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end; /* 버튼을 오른쪽으로 정렬합니다 */
    gap: 10px;
}

.btn {
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 4px;
    padding: 10px 15px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    width: 150px; /* 기본 버튼 너비 */
}

.btn.long {
    width: 200px; /* 긴 버튼의 너비 */
}

.btn.short {
    width: 100px; /* 짧은 버튼의 너비 */
}

.btn:hover {
    background-color: #0056b3;
}

.modify_btn {
    background-color: #28a745;
}

.modify_btn:hover {
    background-color: #218838;
}

.small-text {
    color: #6c757d;
    font-size: 0.875em;
}

.input_block {
    width: auto;
}

#moveForm {
    display: none; /* form을 숨깁니다 */
}

</style>

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
                    </tr>
                    <tr>
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
