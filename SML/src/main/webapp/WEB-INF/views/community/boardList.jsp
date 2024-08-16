<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>커뮤니티 게시판</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">
 <style>
        .community_container {
            padding: 20px;
        }
        .community_boardList {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .community_boardList th, .community_boardList td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        .community_boardList th {
            background-color: #f2f2f2;
        }
        .community_boardList tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .community_boardList tr:hover {
            background-color: #ddd;
        }
        .community_boardList a.move {
            color: #007bff;
            text-decoration: none;
        }
        .community_boardList a.move:hover {
            text-decoration: underline;
        }
        .table_empty {
            text-align: center;
            margin: 20px 0;
            font-size: 18px;
            color: #999;
        }
        .search_wrap {
            text-align: center;
            margin-bottom: 20px;
        }
        .search_wrap .search_input {
            display: inline-block;
        }
        .search_wrap input[type="text"] {
            padding: 5px 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .search_wrap button.search_btn {
            padding: 5px 10px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search_wrap button.search_btn:hover {
            background-color: #0056b3;
        }
        .pageMaker_wrap {
            text-align: center;
            margin: 20px 0;
        }
        .pageMaker {
            list-style-type: none;
            padding: 0;
        }
        .pageMaker li {
            display: inline;
            margin: 0 5px;
        }
        .pageMaker a {
            text-decoration: none;
            color: #007bff;
        }
        .pageMaker a:hover {
            text-decoration: underline;
        }
        .pageMaker .active a {
            font-weight: bold;
            color: #333;
        }
    </style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="community_container">
			<jsp:include page="/WEB-INF/views/community/communityMenu.jsp" />
			<div class="community_main_content">
				<h2>커뮤니티</h2>	
				<c:if test="${listCheck != 'empty'}">
					<table class="community_table">
						<thead>
							<tr>
								<td class="th_column_1">#</td>
								<td class="th_column_2">제목</td>
								<td class="th_column_3">작성자</td>
								<td class="th_column_4">등록일</td>
								<td class="th_column_5">조회수</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="list" varStatus="status">
							<tr>
								<td>${totalCount -status.index}</td>
								<td>
									<a class="move" href='<c:out value="${list.commCode}"/>'>
										<c:out value="${list.commTitle}"></c:out>
									</a>
								</td>
								<td><c:out value="${list.commWriter}" /></td>
								<td><fmt:formatDate value="${list.enrollDate}" pattern="yyyy-MM-dd" /></td>
								<td><c:out value="${list.commCount}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				
				<c:if test="${listCheck == 'empty'}">
					<div class="table_empty">
						등록된 글이 없습니다.
					</div>
				</c:if>
			
			<!-- 검색 영역 -->
            <div class="search_wrap">
            	<form id="searchForm" action="/community/boardList" method="get">
                	<div class="search_input">
                    	<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    	<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                    	<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                    	<button class='btn search_btn'>검색</button>
                    </div>
                </form>
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
			
			<form id="moveForm" action="/community/boardList" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
			</form>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>

	$(document).ready(function(){
		
		let eResult = '<c:out value="${enroll_result}"/>';
		
		checkResult(eResult);
		
		function checkResult(result){
			
			if(result === ''){
				return;
			}
			
			alert("수업을 정상적으로 등록하였습니다.");
			
		}

	});
		
		let moveForm = $('#moveForm');
		/* 페이지 이동 버튼 */
		$(".pageMaker_btn a").on("click", function(e){
			e.preventDefault();
			 moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			 moveForm.submit();
		});
		
		let searchForm = $('#searchForm');
		/* 검색 버튼 동작 */
		$("#searchForm button").on("click", function(e){	
			alert('검색 버튼');
			e.preventDefault();
			
			/* 검색 키워드 유효성 검사 */
			if(!searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하십시오");
				return false;
			}
			
			searchForm.find("input[name='pageNum']").val("1");
			searchForm.submit();	
		});	 
		
		/* 상세 페이지 이동 */
		$(".move").on("click", function(e){
			e.preventDefault();
			moveForm.append("<input type='hidden' name='commCode' value='"+ $(this).attr("href") + "'>");
			moveForm.attr("action", "/community/detail");
			moveForm.submit();
		});

	</script>

</body>
</html>
