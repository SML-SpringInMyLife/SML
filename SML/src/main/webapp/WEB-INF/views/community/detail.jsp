<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>게시글 상세 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/community.css">
<link rel="stylesheet" href="../resources/css/community/detail.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<h1>게시글 상세 페이지</h1>
		<div class="community-container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community-boardDetail-wrap">
				<table class="community_boardDetail">
					<tr>
						<td>글 제목</td>
						<td>
							<input class="input_block" name="commTitle" readonly="readonly" value="<c:out value='${communityDetail.commTitle}'/>"/>
						</td>
					</tr>
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
						</td>
						<td>조회수</td>
						<td>
							<input class="input_block" name="commCount" readonly="readonly" value="<c:out value='${communityDetail.commCount}'/>"/>
						</td>
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
            			<c:when test="${sessionScope.member.memCode == commWriter}">
                			<button id="deleteBtn" class="btn">삭제</button>
                			<button id="BoardListBtn" class="btn">목록</button>
                			<button id="modifyBtn" class="btn">수정</button>
            			</c:when>
            			<c:otherwise>
                			<button id="BoardListBtn" class="btn">목록</button>
            			</c:otherwise>
        			</c:choose>
    			</div>
			</div>
			<div class="community-reply">
             <h3>댓글</h3>
             <div id="replyListSection">
                 <c:if test="${listCheck != 'empty'}">
                    <c:forEach items="${list}" var="list">
                     <table class="community_reply">
                        <tr>
                           <td>
                              <c:out value="${list.repWriter}" />
                              <br>
                              <c:out value="${list.repContent}" />
                              <br>
                              <small>
                                  <c:choose>
                                      <c:when test="${not empty list.rmodifyDate}">
                                          <fmt:formatDate value="${list.rmodifyDate}" pattern="yyyy-MM-dd"/>
                                          <small><c:out value="${list.rmodifyDate}"/>수정됨</small>
                                      </c:when>
                                      <c:otherwise>
                                             <fmt:formatDate value="${list.renrollDate}" pattern="yyyy-MM-dd"/>
                                      </c:otherwise>
                                  </c:choose>
                              </small>
                                   </td>
                        </tr>
                     </table>
                  </c:forEach>
               </c:if>
            <c:if test="${listCheck == 'empty'}">
               <div class="table_empty">
                  등록된 글이 없습니다.
               </div>
            </c:if>
             </div>
             <div id="replyEnrollSection">
                  <c:choose>
                     <c:when test="${not empty sessionScope.member}">
                         <div>
                             현재 작성자 : ${sessionScope.member.memName} 님
                         </div>
                         <form id="replyEnrollForm" method="post" action="/community/reply/enroll">
                             <textarea id="replyContentTextarea" name="replyContent" rows="4" cols="50" placeholder="댓글을 입력하세요..."></textarea>
                             <div class="reply_btn_section">
                                 <button id="replyCancelBtn" type="button" class="btn">취소</button>
                                 <button id="replyEnrollBtn" type="submit" class="btn">댓글 등록</button>
                             </div>
                         </form>
                     </c:when>
                     <c:otherwise>
                         <div class="login-required-message">
                             로그인 후 댓글을 작성해 주세요.
                         </div>
                     </c:otherwise>
                 </c:choose>
             </div>
         </div>
		</div>
			
		<form id="moveForm" method="get">
        	<input type="hidden" name="commCode" value='<c:out value="${communityDetail.commCode }"/>'>
            <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum }"/>'>
            <input type="hidden" name="amount" value='<c:out value="${cri.amount }"/>' >
            <input type="hidden" name="keyword" value='<c:out value="${cri.keyword }"/>'>
        </form>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
		let moveForm = $("#moveForm");
		
		// delete 버튼
		$("#deleteBtn").on("click", function(e){
			alert("삭제 버튼");
			e.preventDefault();
			moveForm.find("input").remove();
			moveForm.append('<input type="hidden" name="commCode" value="${communityDetail.commCode}">');
			moveForm.attr("action", "/community/delete");
			moveForm.attr("method", "post");
			moveForm.submit();
		});

		// 목록으로 이동 버튼
		$("#BoardListBtn").on("click", function(e){
			e.preventDefault();
		
			$("input[name=commCode]").remove();
			moveForm.attr("action", "/community/boardList")
			moveForm.submit();
		});
	
		// 수정 페이지 이동 버튼 
		$("#modifyBtn").on("click", function(e){
			e.preventDefault();
			moveForm.attr("action", "/community/modify");
			moveForm.submit();
		});
		
		// 댓글
		$(document).ready(function() {
        
			// 댓글 등록
			$(".replyEnrollBtn").on("click", function(e){
				const commCode = '${commCode}';
				const memCode = '${memCode}';
				const repContent = '${replyEnroll.repContent}';
				
				const data = {
						commCode : commCode,
						memCode : memCode,
						repContent : repContent
				}
				
				$.ajax({
					data : data,
					type : 'POST',
					url : '/reply/enroll',
					success : function(result){
						window.close();
					}
				});
			});

        // 댓글 목록을 로드하는 함수
        function loadReplies() {
            $.ajax({
                url: '/reply/list',
                type: 'GET',
                data: { commCode: $('input[name="commCode"]').val() },
                success: function(response) {
                    let replyList = $("#replyList");
                    replyList.empty();
                    
                    // 댓글 목록을 동적으로 생성
                    $.each(response.replies, function(index, reply) {
                        replyList.append(`
                            <div class="reply-item">
                                <p>${reply.content}</p>
                                <small>작성일: ${reply.date}</small>
                            </div>
                        `);
                    });
                },
                error: function(xhr, status, error) {
                    alert("댓글 목록 로드 실패: " + error);
                }
            });
        }

        // 페이지 로드 시 댓글 목록을 로드
        loadReplies();
    });
	</script>
</body>
</html>
