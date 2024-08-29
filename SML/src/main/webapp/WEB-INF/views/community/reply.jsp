<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">
<style>
.login-required-message {
    font-weight: bold; /* 볼드체로 설정 */
    color: #FF0000; /* 필요에 따라 색상 설정 (여기서는 빨간색으로 설정) */
    margin-top: 10px;
    font-size: 16px; /* 필요에 따라 폰트 크기 조정 */
}
</style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<main>
	<h1>${commCode}</h1>
	<h1>${memCode}</h1>
		<div class="community_container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp"/>
			<div class="community_main_content">
				<h2>댓글...</h2>
				<div class="community_reply">
					<c:if test="${listCheck != 'empty'}">
						<c:forEach items="${list}" var="list">
							<table class="community_table">
								<tr>
									<td>
										<c:out value="${list.repWriter}"/>
										<br>
										<c:out value="${list.repContent}"/>
										<br>
										<small>
											<c:choose>
												<c:when test="${not empty list.rmodifyDate}">
													<fmt:formatDate value="${list.rmodifyDate}" pattern="yyyy-MM-dd"/>
													<small><c:out value="${list.rmodifyDate}"/>(수정됨)</small>
												</c:when>
												<c:otherwise>
													<fmt:formatDate value="${list.renrollDate}" pattern="yyyy-MM-dd"/>
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
				<div class="community_reply_enroll">
					<c:choose>
						<c:when test="${not empty sessionScope.member}">
							<div>현재 작성자 : ${sessionScope.member.memName}님</div>
							<form id="replyEnrollForm" method="post" action="/community/reply/enroll">
								<textarea id="replyContent" name="replyContent" rows="4" cols="50" placeholder="댓글을 입력하세요.."></textarea>
								<button id="replyeEnrollBtn" type="submit" class="btn">댓글 등록</button>
							</form>	
						</c:when>
						<c:otherwise>
							<div class="login-required-message">로그인 후 댓글을 작성해 주세요.</div>
						</c:otherwise>
					</c:choose>								
				</div>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>