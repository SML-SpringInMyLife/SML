<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<title>수강신청</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
<link rel="stylesheet" href="../resources/css/course/course.css">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<h1>수강 신청</h1>
		<div class="course_container">
			<jsp:include page="/WEB-INF/views/course/courseMenu.jsp" />
			<div class="course_boardList_wrap">
				<!-- 게시물 O -->
				<c:if test="${listCheck != 'empty'}">
					<table class="course_boardList">
						<thead>
							<tr>
								<td>#</td>
								<td>수강 기간</td>
								<td>카테고리</td>
								<td>강좌명</td>
								<td>강사명</td>
								<td>수업 시간/요일</td>
								<td>수강 인원</td>
							</tr>
						</thead>
						<c:forEach items="${list}" var="item">
							<tr>
								<td><c:out value="${item.courseCode}" /></td>
								<td>
									<fmt:formatDate value='<c:out value="${item.startDate}"/>' pattern="yyyy=MM=dd"/>
									~
									<fmt:formatDate value='<c:out value="${item.endDate}"/>' pattern="yyyy-MM-dd"/>
								</td>
								<td><c:out value="${item.ccatCode}" /></td>
								<td>
									<a class="move" href="<c:out value='${item.courseCode}'/>"> 
										<c:out value="${item.courseName}" />
									</a>
								</td>
								<td><c:out value="${item.TeacherCode}" /></td>
								<td><fmt:formatDate value="${item.TeacherCode}"
										pattern="yyyy-MM-dd" /></td>
								<td>
									<c:out value="${item.courseDay}"/>
									<br>
									<c:out value="${item.startTime}"/>~<c:out value="${item.endTime}"/>
								</td>
								<td><c:out value="${item.courseLimit}" /></td>
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
</body>
</html>
