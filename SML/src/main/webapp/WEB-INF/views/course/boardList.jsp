<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>수강신청</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="course_container">
			<jsp:include page="/WEB-INF/views/course/courseMenu.jsp" />
			<div class="course_main_content">
				<h2>수강 신청</h2>
				<!-- 게시물 O -->
				<c:if test="${listCheck != 'empty'}">
					<table class="course_table">
						<thead>
							<tr>
								<td>#</td>
								<td>수강 기간</td>
								<td>분류</td>
								<td>강좌명</td>
								<td>강사명</td>
								<td>요일 / 시간</td>
								<td>수강 인원</td>
								<td>수강 신청</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${totalCount -status.index}</td>
								<td>
									<fmt:formatDate value="${item.startDate}" pattern="yy-MM-dd" />
									~
									<fmt:formatDate value="${item.endDate}" pattern="yy-MM-dd" />
								</td>
								<td><c:out value="${item.ccatName}" /></td>
								<td>
									<a class="move" href="<c:out value='${item.courseCode}'/>"> 
										<c:out value="${item.courseName}" />
									</a>
								</td>
								<td><c:out value="${item.teaName}" /></td>
								<td>
									<c:out value="${item.courseDay}"/>
									<br>
									<c:out value="${item.startTime}"/>~<c:out value="${item.endTime}"/>
								</td>
								<td>
									<span>0</span>
									/
									<c:out value="${item.courseLimit}"/>
									명
								</td>
								<td>
									<a class="apply_btn" href="<c:out value='${item.courseCode}'/>">
        								<button>수강신청</button>
    								</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>

				<!-- 게시물 x -->
				<c:if test="${listCheck == 'empty'}">
					<div class="table_empty">개설된 수업이 없습니다.</div>
				</c:if>
				
				<br>


			<!-- 검색 영역 -->
			<div class="search_wrap">
				<form id="searchForm" action="/course/boardList" method="get">
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
			
			<form id="moveForm" action="/course/boardList" method="get">
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
			alert("수업'"+ eResult +"'을 등록하였습니다.");
		}
	});
	
	let searchForm = $('#searchForm');
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
	
	let moveForm = $('#moveForm');
	$(".pageMaker_btn a").on("click", function(e){		
		e.preventDefault();
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		moveForm.submit();
	});
	
	$(".move").on("click", function(e){
		e.preventDefault();
		
		moveForm.append("<input type='hidden' name='courseCode' value='"+$(this).attr("href") + "'>");
		moveForm.attr("action", "/course/detail");
		moveForm.submit();	
	});
	
	$('.apply_btn').on("click",function(e){	
		e.preventDefault();
		
		var courseCode = $(this).attr("href");

	    // 팝업 옵션
	    var popUrl = "/course/apply?courseCode=" + courseCode;
	    var popOption = "width=650, height=550, top=300, left=300, scrollbars=yes";
		
		window.open(popUrl,"수강신청",popOption);
	});
	</script>
</body>
</html>
