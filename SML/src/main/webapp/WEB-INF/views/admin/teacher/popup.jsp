<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>강사 선택 팝업</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<style>
/* 페이지 버튼 인터페이스 */
.pageMaker_wrap{
	text-align: center;
    margin-top: 30px;
    margin-bottom: 40px;
}
.pageMaker_wrap a{
	color : black;
}
.pageMaker{
    list-style: none;
    display: inline-block;
}	
.pageMaker_btn {
    float: left;
    width: 40px;
    height: 40px;
    line-height: 40px;
    margin-left: 20px;
}
.next, .prev {
    border: 1px solid #ccc;
    padding: 0 10px;
}
.next a, .prev a {
    color: #ccc;
}
/* 현재 페이지 버튼 */
.active{							
	border : 2px solid black;
	font-weight:400;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<h1>강사 목록</h1>
		<div class="course_teacher_container">
			<div class="course_teacher_list">
				<c:if test="${listCheck != 'empty'}">
					<table class="course_teacher_list">
						<thead>
							<tr>
								<td>#</td>
								<td>강사명</td>
								<td>상태</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="list">
						<tr>
							<td><c:out value="${list.teaCode}" /></td>
							<td>
    							<a class="move" href='<c:out value="${list.teaCode}"/>' data-name='<c:out value="${list.teaName}"/>'>
        							<c:out value="${list.teaName}"/>
    							</a>
							</td>
							<td>
								<c:choose>
        							<c:when test="${list.teaStatus eq 'N'}">정상</c:when>
        							<c:otherwise>(삭제됨)</c:otherwise>
    							</c:choose>
							</td>
						</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${listCheck == 'empty'}">
					<div class="table_empty">
						등록된 강사가 없습니다.
					</div>
				</c:if>
			</div>
			
			<div class="search_wrap">
				<form id="searchForm" action="/admin/teacher/popup" method="get">
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
			
			<form id="moveForm" action="/admin/teacher/popup" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
				<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
			</form>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
	let searchForm = $('#searchForm');
	let moveForm = $('#moveForm');
	
	/* 강사 검색 버튼 동작 */
	$("#searchForm button").on("click", function(e){
		e.preventDefault();
		/* 검색 키워드 유효성 검사 */
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하십시오");
			return false;
		}
		searchForm.find("input[name='pageNum']").val("1");
		searchForm.submit();
	});
	
	/* 페이지 이동 버튼 */
	$(".pageMaker_btn a").on("click", function(e){
		e.preventDefault();
		console.log($(this).attr("href"));
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		moveForm.submit();
	});	
	
	// 선택 및 팝업창 닫기
	$(".move").on("click", function(e){	
		e.preventDefault();
			
		let teaCode = $(this).attr("href");
		let teaName= $(this).data("name");
		$(opener.document).find("#teaCode_input").val(teaCode);
		$(opener.document).find("#teaName_input").val(teaName);
			
		window.close();
	});
	</script>
</body>
</html>
