<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>강사 목록 - 관리자 페이지</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/courseNcommunity/courseNcommunity.css">
<style>
/* 페이지네이션 전체 래퍼 */
.pageMaker_wrap {
    text-align: center;
    margin: 20px 0;
}

/* 페이지네이션 리스트 스타일 */
.pageMaker {
    list-style-type: none; /* 기본 점 스타일 제거 */
    padding: 0;
    display: inline-block;
}

/* 페이지네이션 항목 스타일 */
.pageMaker li {
    display: inline-block;
    margin: 0 5px;
}

/* 페이지네이션 링크 스타일 */
.pageMaker a {
    display: inline-block;
    text-decoration: none;
    color: black;
    padding: 8px 12px; /* 링크 패딩 */
    border-radius: 4px; /* 둥근 모서리 */
    transition: background-color 0.3s, color 0.3s; /* 부드러운 색상 전환 효과 */
}

/* 페이지네이션 링크 호버 스타일 */
.pageMaker a:hover {
    text-decoration: none;
    background-color: #e9ecef; /* 호버 시 배경색 */
    color: #0056b3; /* 호버 시 글자색 */
}

/* 현재 페이지 스타일 */
.pageMaker .active a {
    font-weight: bold;
    color: #fff; /* 현재 페이지 글자색 */
    background-color: #007bff; /* 현재 페이지 배경색 */
    border: 2px solid #007bff; /* 현재 페이지 테두리 */
}

/* 비활성화된 페이지 링크 스타일 (선택 사항) */
.pageMaker .disabled a {
    color: #6c757d; /* 비활성화된 페이지 링크 글자색 */
    cursor: not-allowed; /* 마우스 커서 */
}

/* 빈 테이블 메시지 */
.table_empty {
	text-align: center;
	font-size: 1.2em;
	color: #666;
	margin-top: 20px;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>강사 리스트</h2>
				<form id="searchForm" action="/admin/teacher/list" method="get" class="search-container">
                	<div class="search_input">
                   		<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                   		<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                   		<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                   		<button class='btn search_btn'>검색</button>
                   	</div>
                </form>
				<c:if test="${listCheck != 'empty'}">
					<table class="course-table">
						<thead>
							<tr>
								<td>No.</td>
								<td>강사명</td>
								<td>상태</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="list" varStatus="status">
						<tr>
							<td><%-- <c:out value="${list.teaCode}" /> --%>${totalCount -status.index}</td>
							<td>
								<a class="move" href='<c:out value="${list.teaCode}"/>'>
									<c:out value="${list.teaName}"></c:out>
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
			
			<form id="moveForm" action="/admin/teacher/list" method="get">
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
		let mresult = '<c:out value="${modify_result}"/>';
		
		checkResult(eResult);
		checkmResult(mresult);
		
		function checkResult(result){
			if(result === ''){
				return;
			}
			
			alert("강사 정보를 정상적으로 등록하였습니다.");	
		}
		
		function checkmResult(mresult){
			if(mresult === '1'){
				alert("강사 정보 수정을 완료하였습니다.");
			} else if(mresult === '0') {
				alert("강사 정보 수정을 하지 못하였습니다.")	
			}
		}
		
		/* 삭제 결과 경고창 */
		let delete_result = '${delete_result}';
		
		if(delete_result == 1){
			alert("삭제 완료");
		} else if(delete_result == 2){
			alert("삭제할 수 없습니다.")
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
		moveForm.append("<input type='hidden' name='teaCode' value='"+ $(this).attr("href") + "'>");
		moveForm.attr("action", "/admin/teacher/detail");
		moveForm.submit();
	});
	</script>
</body>
</html>
