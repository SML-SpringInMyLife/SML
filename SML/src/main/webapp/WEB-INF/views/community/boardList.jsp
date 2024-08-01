<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/community/boardList.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="community_board_list">
			<div class="community_boardList_wrap">
				<!-- 게시물 O -->
				<c:if test="${listCheck != 'empty'}">
					<table class="community_list_table">
						<thead>
							<tr>
								<td class="th_column_1">#</td>
								<td class="th_column_2">제목</td>
								<td class="th_column_3">작성자</td>
								<td class="th_column_4">등록일</td>
								<td class="th_column_5">조회수</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="item">
							<tr>
								<td><c:out value="${item.commCode}" /></td>
								<td><a class="move"
									href="<c:out value='${item.authorId}'/>"> <c:out
											value="${item.commTitle}" />
								</a></td>
								<td><c:out value="${item.commWriter}" /></td>
								<td><fmt:formatDate value="${item.enrollDate}"
										pattern="yyyy-MM-dd" /></td>
								<td><c:out value="${item.commCount}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<!-- 게시물 x -->
				<c:if test="${listCheck == 'empty'}">
					<div class="table_empty">작성된 글이 없습니다.</div>
				</c:if>
			</div>

			<!-- 검색 영역 -->
			<div class="search_wrap">
				<form id="searchForm" action="/community/boardList" method="get">
					<div class="search_input">
						<input type="text" name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<input type="hidden" name="pageNum"
							value='<c:out value="${pageMaker.cri.pageNum}"></c:out>'>
						<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
						<button class='btn search_btn'>검색</button>
					</div>
				</form>
			</div>

			<!-- 페이지 이동 인터페이스 영역 -->
			<div class="pageMaker_wrap">
				<ul class="pageMaker">
					<!-- 이전 버튼 -->
					<c:if test="${pageMaker.prev}">
						<li class="pageMaker_btn prev"><a
							href="${pageMaker.pageStart - 1}">이전</a></li>
					</c:if>
					<!-- 페이지 번호 -->
					<c:forEach begin="${pageMaker.pageStart}"
						end="${pageMaker.pageEnd}" var="num">
						<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
							<a href="${num}">${num}</a>
						</li>
					</c:forEach>
					<!-- 다음 버튼 -->
					<c:if test="${pageMaker.next}">
						<li class="pageMaker_btn next"><a
							href="${pageMaker.pageEnd + 1 }">다음</a></li>
					</c:if>
				</ul>
			</div>
			<form id="moveForm" action="/admin/authorManage" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
			</form>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
		$(document).ready(function() {
			let result = '<c:out value="${enroll_result}"/>';
		    checkResult(result);
		    function checkResult(result){
		        if(result === ''){
		            return;
		        }		        
		        alert("글'${enroll_result}' 을 등록하였습니다.");        
		    }
		});
		
			/* 삭제 결과 경고창 */
			let delete_result = '${delete_result}';

			if (delete_result == 1) {
				alert("삭제 완료");
			} else if (delete_result == 2) {
				alert("해당 글 데이터를 사용하고 있는 데이터가 있어서 삭제할 수 없습니다.")
			}

		});
		let moveForm = $('#moveForm');
		let searchForm = $('#searchForm');

		/* 페이지 이동 버튼 */
		$(".pageMaker_btn a").on("click", function(e) {
			e.preventDefault();
			moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			moveForm.submit();
		});

		/* 작가 검색 버튼 동작 */
		$("#searchForm button").on("click", function(e) {
			e.preventDefault();
			/* 검색 키워드 유효성 검사 */
			if (!searchForm.find("input[name='keyword']").val()) {
				alert("키워드를 입력하세요.");
				return false;
			}
			searchForm.find("input[name='pageNum']").val("1");
			searchForm.submit();
		});

		// 상세 페이지 이동
		$(".move")
				.on(
						"click",
						function(e) {
							e.preventDefault();
							moveForm
									.append("<input type='hidden' name='authorId' value='"
											+ $(this).attr("href") + "'>");
							moveForm.submit();
						});
	</script>
</body>
</html>
