<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록페이지</title>

<link rel="stylesheet"
	href="${webappRoot}/resources/css/notice/main.css">
<!-- 헤더 css -->
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">

<!-- jQuery 로드 -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<main>


		<div class="notice-container">
			<div class="notice-search">
				<h1>SML 소식!</h1>
				<p>다양한 소식을 전달해 드립니다</p>
				
					
				<form id="searchForm" action="/notice/list" method="get">
    <div class="search-box">
        <select name="type">
            <option value="">전체</option>
            <option value="T">제목</option>
            <option value="C">내용</option>
            <option value="TC">제목+내용</option>
        </select>
        <input type="text" placeholder="검색어를 입력하세요" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'/>
        <input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"/>'/>
        <input type="hidden" name="amount" value='${pageMaker.cri.amount}'/>
        <button type='button' class='btn search_btn'>검색하기</button>
    </div>
</form>
			
			</div>

			<!-- 게시물 O -->
			<c:if test="${listCheck != 'empty'}">

				<div class="category">
        <select id="categoryFilter">
            <option value="">전체</option>
            <c:forEach var="category" items="${categories}">
                <option value="${category.categoryCode}">${category.categoryName}</option>
            </c:forEach>
        </select>
				</div>

				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>등록일</th>
							<th>조회</th>
							<th>좋아요</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="notice">
						<tbody>
							<tr>
								<td><c:out value="${notice.noticeCode}" /></td>

								<td><a class="move" href="<c:out value='${notice.noticeCode}'/>"> 
									<c:out value="${notice.noticeTitle}" />
								</a></td>

								<td><fmt:formatDate value="${notice.noticeEnroll}"
										pattern="yyyy-MM-dd" /></td>
								<td><c:out value="${notice.noticeCount}" /></td>
								<td><c:out value="${notice.noticeLike}" /></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</c:if>
              <!-- 게시물 x -->
              	<c:if test="${listCheck == 'empty'}">
					<div class="table_empty">
						등록된 글이 없습니다.
					</div>
                   </c:if>
                   
<%--                    디버그를 위한 로그인 상태 및 관리자 상태 출력
<p>Debug: isLoggedIn = ${isLoggedIn}, isAdmin = ${isAdmin}</p> --%>
                   
<%-- 로그인한 사용자에게만 글쓰기 버튼 표시 --%>
<c:if test="${isLoggedIn && isAdmin}">
    <div class="button-container">
        <button id="write">글쓰기</button>
    </div>
</c:if>
			  
		</div>
		
     <!-- 페이지 이동 인터페이스 영역 -->
            <div class="pageMaker_wrap" >
            	<ul class="pageMaker">
	            	<!-- 이전 버튼 -->
	                <c:if test="${pageMaker.prev}">
	                	<li class="pageMaker_btn prev">
	                    	<a href="${pageMaker.pageStart - 1}">이전</a>
	                    </li>
	                </c:if>
	                <!-- 페이지 번호 -->
	                <c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
	                	<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
	                    	<a href="${num}">${num}</a>
	                    </li>
	                </c:forEach>
					<!-- 다음 버튼 -->
	                <c:if test="${pageMaker.next}">
	                    <li class="pageMaker_btn next">
	                    	<a href="${pageMaker.pageEnd + 1 }">다음</a>
	                    </li>
	                </c:if>
				</ul>         
			</div>
			
     <form id="moveForm" action="/notice/list" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">		
			</form>
	</main>
	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<script>
		/* 글쓰기 버튼 */
		$("#write").click(function() {
			location.href = "/notice/enroll"
		});
		
		
		let searchForm = $('#searchForm');
		/* 검색버튼 */
		$(".search_btn").on("click", function(e) {
		    e.preventDefault();

		    let type = $("select[name='type']").val();
		    let keyword = $("input[name='keyword']").val();

		    /* 검색 타입 및 검색어 유효성 검사 */
		    if(!type){
		        alert("검색 종류를 선택하세요");
		        return false;
		    }

		    if(!keyword){
		        alert("키워드를 입력하세요");
		        return false;
		    }

		    searchForm.find("input[name='pageNum']").val("1");
		    searchForm.submit();
		}); 
		
		
		
		
		let moveForm = $('#moveForm');
		/* 페이지 이동 버튼 */
		$(".pageMaker_btn a").on("click", function(e){
			
			e.preventDefault();
			moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			moveForm.submit();
		});
		

		/* 알람 창 */
		let enroll = '<c:out value="${enroll_result}"/>';
		let modify = '<c:out value="${modify_result}"/>';
	
		
		checkResult(enroll);
		checkResult(modify);


		function checkResult(enroll) {
			if (enroll === '') {
				return;
			}
			alert("글'${enroll_result}' 을 등록하였습니다.");
		}
		
		function checkResult(modify) {
			if (modify === '1') {
				alert("글 수정을 완료 하였습니다");
			}else if(modify === '0')
			alert("글 수정을 실패 하였습니다");
		}
		
	
		/* 상세조회 페이지로 가기  */
		$(".move").on("click", function(e) {
			e.preventDefault();	
			moveForm.append("<input type='hidden' name='noticeCode' value='" + $(this).attr("href") + "'>");
			moveForm.attr("action", "/notice/detail");
			moveForm.submit();	
		});
	</script>
</body>
</html>