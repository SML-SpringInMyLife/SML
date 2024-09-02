<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>게시글 상세 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="community_container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community_main_content">
				<h2><c:out value='${communityDetail.commTitle}'/></h2>
				<table class="community_table">
					<tr>
						<td>작성자</td>
						<td>
							<input class="input_block" name="commWriter" readonly="readonly" value="<c:out value='${communityDetail.commWriter}'/>"/>
						</td>
					</tr>
					<tr>
						<td>등록일</td>
						<td>
							<fmt:formatDate value="${communityDetail.enrollDate}" pattern="yyyy-MM-dd" var="formattedEnrollDate"/>
							<input class="input_block" name="enrollDate" readonly="readonly" value="<c:out value='${formattedEnrollDate}'/>"/>
							<br>
							<c:if test="${not empty communityDetail.modifyDate}">
                                <fmt:formatDate value="${communityDetail.modifyDate}" pattern="yyyy-MM-dd" var="formattedModifyDate"/>
                                <small class="small-text">마지막 수정일 : <c:out value="${formattedModifyDate}"/></small>
                            </c:if>
						<%-- <td>조회수</td>
						<td>
							<input class="input_block" name="commCount" readonly="readonly" value="<c:out value='${communityDetail.commCount}'/>"/>
						</td> --%>
					</tr>
					<tr>
						<td>게시글 내용</td>
						<td>
							<input class="input_block" name="commContent" readonly="readonly" value="<c:out value='${communityDetail.commContent}'/>"/>
						</td>
					</tr>
				</table>
				
				<div class="btn_section">
        			<c:choose>
            			<c:when test="${sessionScope.member.memCode == memCode}">
                			<button id="deleteBtn" class="btn">삭제</button>
                			<button id="cancelBtn" class="btn">목록</button>
                			<button id="modifyBtn" class="btn">수정</button>
            			</c:when>
            			<c:otherwise>
                			<button id="cancelBtn" class="btn">목록</button>
            			</c:otherwise>
        			</c:choose>
    			</div>
    			
    			<div class="community_reply_content">
    				<h3>댓글</h3>
    				<c:choose>
    					<c:when test="${member != null }">
    						<div class="btn_section">
    							<button id="replyBtn">댓글 쓰기</button>
    						</div>
    					</c:when>
    					<c:otherwise>
    						로그인해 주세요.
    					</c:otherwise>
    				</c:choose>
    			</div>
         </div>      		
		<form id="moveForm" method="get">
        	<input type="hidden" name="commCode" value='<c:out value="${communityDetail.commCode }"/>'>
            <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
            <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
            <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
        </form>
        </div>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
	const form = {
			memCode : '${member.memCode}',
			commCode : '${communityDetail.commCode}'
	}
	
	let moveForm = $("#moveForm");
	
	$("#deleteBtn").on("click", function(e){
		alert("삭제 버튼 작동");
	});
	
	$("#cancelBtn").on("click", function(e){
		e.preventDefault();
		
		$("input[name=commCode]").remove();
		moveForm.attr("action", "/community/boardList");
		moveForm.submit();
	});
	
	$("#modifyBtn").on("click", function(e){
		e.preventDefault();
		
		moveForm.attr("action", "/community/modify");
		moveForm.submit();
	});
	
	$(".community_reply_button").on("click", function(e) {
		e.preventDefault();
		const memCode = '${member.memCode}';
		const commCode = '${communityDetail.commCode}';

		/*  */
		
		$.ajax({
			data : {
				commCode : commCode,
				memCode : memCode
			},
			url : '/reply/check',
			type : 'POST',
			success : function(result){
				let popUrl = "/reply/" + memCode + "?commCode=" + commCode;
				console.log(popUrl);
				let popOption = "width = 490px, height=490px, top=300px, left=300px, scrollbars=yes";
				
				window.open(popUrl,"댓글 쓰기",popOption);
			}
		})
	});
	     
		
	</script>
	
	
</body>
</html>
