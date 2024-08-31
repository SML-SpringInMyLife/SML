<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myCourses(내수강신청내역)</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/member/mypage.css">
</head>
<body>
<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<div class="member-container">
		<jsp:include page="/WEB-INF/views/member/mypage.jsp" />
			<div class="member-main-content">
				<h2>나의 수강신청 목록</h2>				
		
				<form action="${appServlet}/member/mycourses" method="get"
					class="search-container">
					<select id="searchCategory">
						<option value="all">전체</option>
						<option value="courseName">수업명</option>						
					</select>
					<input type="text" name="keyword" class="search-bar"
						placeholder="검색어를 입력하세요." value="${criteria.keyword}">
					<button class="submit-button" type="submit">검색</button>
				</form>

				<table class="course-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="접수일">접수일</th>
							<th data-label="성명">성명</th>
							<th data-label="수업명">수업명</th>
							<th data-label="개강일">개강일</th>					
							<th data-label="신청상태">신청상태</th>
							<th data-label="비고">비고</th>
						</tr>
					</thead>
					<tbody id="courseList">						
						<tr class="course-group">
							<td colspan="8" data-label="수업명">나의 수강신청 목록</td>
						</tr>
						<c:forEach items="${ list }" var="list" varStatus="status">
						<tr>
						  <td>${(pageInfo.cri.pageNum -1) * pageInfo.cri.amount + status.index + 1 }</td>
						  <td class="hidden"><c:out value="${ list.applyCode }"/></td>
						  <td><fmt:formatDate value="${ list.applyDate }" pattern="yyyy-MM-dd"/></td>
						  <td><c:out value="${ list.memName }" /></td>
						  <td><c:out value="${ list.courseName }" /></td>
						  <td><c:out value="${ list.courseStartDate }" /></td>
						  <td>완료</td>
						  <td>상세</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이지 이동 인터페이스 영역 -->
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
						<!-- 이전 버튼 -->
						<c:if test="${pageInfo.prev}">
							<li class="pageMaker_btn prev">
								<a href="${appServlet}/member/point?pageNum=${pageInfo.pageStart - 1}&selectDate=${param.selectDate}">이전</a>
							</li>
						</c:if>
						<!-- 페이지 번호 -->
						<c:forEach begin="${pageInfo.pageStart}" end="${pageInfo.pageEnd}"
							var="num">
							<li class="pageMaker_btn ${pageInfo.cri.pageNum == num ? 'active' : ''}">
								<a href="${appServlet}/member/point?pageNum=${num}&selectDate=${param.selectDate}">${num}</a>
							</li>
						</c:forEach>
						<!-- 다음 버튼 -->
						<c:if test="${pageInfo.next}">
							<li class="pageMaker_btn next">
								<a href="${appServlet}/member/point?pageNum=${pageInfo.pageEnd + 1}&selectDate=${param.selectDate}">다음</a>
							</li>
						</c:if>
					</ul>
				</div>			
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
<script>
$(document).ready(function(){   
    let aResult = '<c:out value="${apply_result}"/>';
    checkResult(aResult);
    function checkResult(aresult){
       if(aresult === ''){
          return;
       }
       alert("수업이 정상적으로 신청되었습니다.")
    }
 });
</script>		
</body>
</html>