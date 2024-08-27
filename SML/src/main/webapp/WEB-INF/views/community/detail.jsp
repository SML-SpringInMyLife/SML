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
    				<div class="reply_list">
    					<c:if test="${listCheck != 'empty'}">
    						<c:forEach items="${reply}" var="reply">
    							<table class="community_reply_list">
    								<tr>
    									<td>
    										<c:out value="${reply.repWriter}"/>
    										<br>
    										<c:out value="${reply.repContent}"/>
    										<br>
    										<small>
    											<c:choose>
                                               		<c:when test="${not empty reply.rmodifyDate}">
                                                   		<fmt:formatDate value="${reply.rmodifyDate}" pattern="yyyy-MM-dd"/>
                                                   			<small><c:out value="${reply.rmodifyDate}"/>(수정됨)</small>
                                               		</c:when>
                                               		<c:otherwise>
                                                   		<fmt:formatDate value="${reply.renrollDate}" pattern="yyyy-MM-dd"/>
                                               		</c:otherwise>
                                           		</c:choose>
    										</small>
    										<c:if test="${sessionScope.member != null && sessionScope.member.memName eq list.repWriter}">
                                         		<div class="reply_detail_btn_section">
                                             		<button id="detailDeleteBtn" class="btn">삭제</button>
                                             		<button id="detailModifyBtn" class="btn">수정</button>
                                         		</div>
                                     		</c:if>
    									</td>
    								</tr>
    							</table>
    						</c:forEach>
    					</c:if>
    					<c:if test="${listCheck == 'empty'}">
                     		<div class="table_empty">
                        		등록된 댓글이 없습니다.
                     		</div>
               			</c:if>			
    				</div>
    				<div class="reply_enroll">
    					<c:choose>
    						<c:when test="${not empty sessionScope.member}">
    							현재 작성자 : ${sessionScope.member.memName}님
    						</c:when>
    					</c:choose>
    					<form action="/community/reply/enroll.do" method="post" id="replyEnrollForm">
    						<input type="hidden" name="commCode" value="${reply.repCode}">
    						<input type="hidden" name="targetCode" value="${reply.memCode}">
    						<input type="hidden" name="commCode" value="${reply.commCode}"/>
    						<textarea rows="repContent">
    							<c:if test="${empty memCode}">로그인이 필요합니다.</c:if>
    						</textarea>
    						<button type="submit">등록</button>
    					</form>
    				</div>
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
	
	$("#replyBtn").on("click", function(e){
		e.preventDefault();
		
		const memCode = '${member.memCode}';
		const commCode = '${communityDetail.commCode}';
		
		moveForm.attr("action", "/community/reply")
		moveForm.submit();
	});
		
	</script>
	
	
</body>
</html>
