<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/board.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>게시글 상세 페이지</h1>
		<div class="community-container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community-boardDetail-wrap">
				<table class="community-boardDetail">
					<tr>
						<td class="th_column_1">글 제목</td>
						<td>
							<input class="input_block" name="commTitle" readonly="readonly" value="<c:out value='${communityDetail.commTitle}'/>"/>
						</td>
						<td class="th_column_2">작성자</td>
						<td>
							<input class="input_block" name="commWriter" readonly="readonly" value="<c:out value='${communityDetail.commWriter}'/>"/>
						</td>
						<td class="th_column_3">등록일</td>
						<td>
							<fmt:formatDate value="${communityDetail.enrollDate}" pattern="yyyy-MM-dd" var="formattedEnrollDate"/>
							<input class="input_block" name="enrollDate" readonly="readonly" value="<c:out value='${formattedEnrollDate}'/>"/>
						</td>
						<td class="th_column_4">수정일</td>
						<td>
							<fmt:formatDate value="${communityDetail.modifyDate}" pattern="yyyy-MM-dd" var="formattedModifyDate"/>
							<input class="input_block" name="modifyDate" readonly="readonly" value="<c:out value='${formattedModifyDate}'/>"/>
						</td>
						<td class="th_column_4">게시글 내용</td>
						<td>
							<input class="input_block" name="commContent" readonly="readonly" value="<c:out value='${communityDetail.commContent}'/>"/>
						</td>
						<td class="th_column_5">조회수</td>
						<td>
							<input class="input_block" name="commCount" readonly="readonly" value="<c:out value='${communityDetail.commCount}'/>"/>
						</td>
					</tr>
					<tr>
						<td>댓글</td>
						<td>댓글 내용 들어갈 자리</td>
					</tr>
				</table>
				<div class="btn_section">
					<button id="deleteBtn" class="btn">삭제</button>
					<button id="BoardListBtn" class="btn">목록</button>
					<button id="modifyBtn" class="btn">수정</button>
				</div>
			</div>	
		</div>
		
		<form id="moveForm" method="get">
            <input type="hidden" name="authorId" value="<c:out value='${authorInfo.authorId}'/>">
            <input type="hidden" name="pageNum" value="<c:out value='${cri.pageNum}'/>">
            <input type="hidden" name="amount" value="<c:out value='${cri.amount}'/>">
            <input type="hidden" name="keyword" value="<c:out value='${cri.keyword}'/>">
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
		let moveForm = $("#moveForm");

		// 목록으로 이동 버튼
		$("#BoardListBtn").on("click", function(e){
			e.preventDefault();
		
			$("input[name=authorId]").remove();
			moveForm.attr("action", "/community/boardList")
			moveForm.submit();
		});
	
		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e){
			e.preventDefault();
			moveForm.attr("action", "/community/modify");
			moveForm.submit();
		});
	</script>
</body>
</html>
