<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>point(포인트)</title>
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
				<h2>나의 포인트</h2>
				
				<form
				action="${appServlet}/member/point" method="get" class="point-container">
				
				<!-- 현재시스템 년/월로 조회 --> 
				<c:set var="now" value="<%= new java.util.Date() %>" />
				<fmt:formatDate var="currentYear" value="${now}" pattern="yyyy" />
				<fmt:formatDate var="currentMonth" value="${now}" pattern="MM" />
				
				<select id="selectbox" name="selectDate">
		        <option value="none" selected>=== 선택 ===</option>
		        <c:forEach begin="${currentYear - 0}" end="${currentYear}" var="year">
            	<c:forEach begin="1" end="12" var="month">
                <c:if test="${year < currentYear || (year == currentYear && month <= currentMonth)}">
                    <c:set var="formattedMonth" value="${month < 10 ? '0' : ''}${month}" />
                    <c:set var="optionValue" value="${year}${formattedMonth}" />
                    <option value="${optionValue}">${year}년 ${month}월
                    <!--  ${param.point == optionValue || (empty param.point && year == currentYear 
                    && formattedMonth == currentMonth) ? 'selected' : ''}>-->                        
                    </option>
                </c:if>
               </c:forEach>
               </c:forEach>
               </select>
				<button class="submit-button" type="submit">조회</button>
			</form>			

				<table class="course-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="날짜">날짜</th>
							<th data-label="내용">내용</th>
							<th data-label="포인트">포인트</th>
							<th data-label="포인트유형">포인트유형</th>		
							<th data-label="비고">비고</th>
						</tr>
					</thead>
					<tbody id="courseList">
						<!-- Sample Group Row -->
						<tr class="course-group">
							<td colspan="8" data-label="포인트">현재 포인트 : <c:out value="${ TotalPoint }"/> 점 입니다.</td>
						</tr>
					    <c:forEach items="${list}" var="list" varStatus="status">
					    <tr>
					      <td>${(pageInfo.cri.pageNum - 1) * pageInfo.cri.amount + status.index + 1}</td>					      
						  <td class="hidden"><c:out value="${ list.pointCode }"/></td>
						  <td><fmt:formatDate value="${list.pointDate}" pattern="yyyy-MM-dd HH:mm"/></td>
						  <td><c:out value="${ list.pointComment }"/></td>
						  <td><fmt:formatNumber value="${ list.pointPrice}" pattern="#,###" />P</td>	
						  <td>
						  	<c:choose>
	            				<c:when test="${list.status == 1}">적립</c:when>
	           				    <c:otherwise>사용</c:otherwise>	           				    
	        			 	</c:choose>	
	        			 </td>		
						 <td>-</td>
					    </tr>					
					    </c:forEach>		
					</tbody>
				</table>
				
				
				
			<!-- 페이지 이동 인터페이스 영역 -->
            <div class="pageMaker_wrap" >
               <ul class="pageMaker">
                  <!-- 이전 버튼 -->                
                   <c:if test="${pageInfo.prev}">
                      <li class="pageMaker_btn prev">
                          <a href="${appServlet}/member/point?pageNum=${pageInfo.pageStart - 1}&selectDate=${param.selectDate}">이전</a>
                       </li>
                   </c:if>
                   <!-- 페이지 번호 -->
                   <c:forEach begin="${pageInfo.pageStart}" end="${pageInfo.pageEnd}" var="num">
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
				
				<!-- Pagination -->
				<!--  
            	<div class="pagination">
               	 <c:if test="${pageInfo.prev}">
                    <a href="${appServlet}/member/point?pageNum=${pageInfo.pageStart - 1}&selectDate=${param.selectDate}">&laquo; 이전</a>
                 </c:if>
                
                 <c:forEach var="num" begin="${pageInfo.pageStart}" end="${pageInfo.pageEnd}">
                    <a href="${appServlet}/member/point?pageNum=${param.memCode}&selectDate=${param.selectDate}" 
                       class="${pageInfo.cri.pageNum == num ? 'active' : ''}">${param.memCode}</a>
                 </c:forEach>
                
                 <c:if test="${pageInfo.next}">
                    <a href="${appServlet}/member/point?pageNum=${pageInfo.pageEnd + 1}&selectDate=${param.selectDate}">다음 &raquo;</a>
                 </c:if>
               </div>
               -->
			</div>
		</div>
		
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%></body>
</html>