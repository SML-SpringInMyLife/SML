
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<title>SML_Admin(회원관리)</title>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />
			<div class="admin-main-content">
				<h2>회원 목록</h2>
				<form action="/admin/members" method="get" class="search-container">
					<select id="type" name="type">
						<option value="all">전체</option>
						<option value="id">ID</option>
						<option value="name">성명</option>
						<option value="phone">전화번호</option>
					</select>
					<div class="search-bar">
						<input type="text" id="search" class="search-bar"
							placeholder="검색어를 입력하세요." name="keyword"
							value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
						<button onclick="search()">검색</button>
					</div>
				</form>
				<table class="member-table">
					<thead>
						<tr>
							<th data-label="No.">No.</th>
							<th data-label="ID">ID</th>
							<th data-label="성명">성명</th>
							<th data-label="생년월일">생년월일</th>
							<th data-label="전화번호">전화번호</th>
							<th data-label="잔여포인트">잔여포인트</th>
							<th data-label="권한부여">권한부여</th>
							<th data-label="회원상태">회원상태</th>
						</tr>
					</thead>
					<tbody id="memberList">
						<!-- 반복출력 -->
						<c:forEach var="member" items="${members}" varStatus="status">
							<tr>
								<td data-label="No.">${totalCount - status.index}</td>
								<td data-label="ID"><c:out value="${member.memId}" /></td>
								<td data-label="성명"><c:out value="${member.memName}" /></td>
								<td data-label="생년월일"><fmt:formatDate
										value="${member.memBirth}" pattern="yyyy-MM-dd" /></td>
								<td data-label="전화번호"><c:out value="${member.memPhone}" /></td>
								<td data-label="잔여포인트"><c:out
										value="${member.memTotalPoint}" /></td>
								<td data-label="권한부여">
									<div class="admChange-container">
										<c:choose>
											<c:when test="${member.memAdminCheck == 1}">관리자 / </c:when>
										</c:choose>
										<form action="/admin/updateAdm" method="post"
											class="makeAdm-form">
											<input type="hidden" name="memCode" value="${member.memCode}" />
											<c:choose>
												<c:when test="${member.memAdminCheck == 1}">
													<input type="hidden" name="memAdminCheck" value="0" />
													<button type="submit" class="changeAdm">회수</button>
												</c:when>
												<c:otherwise>
													<input type="hidden" name="memAdminCheck" value="1" />
													<button type="submit" class="changeAdm">권한부여</button>
												</c:otherwise>
											</c:choose>
										</form>
									</div>
								</td>
								<td data-label="회원상태">
									<div class="status-container">
										<c:choose>
											<c:when test="${member.memStatus == 1}">Y</c:when>
											<c:otherwise>N</c:otherwise>
										</c:choose>
										/
										<form action="/admin/updateStatus" method="post"
											class="status-form">
											<input type="hidden" name="memCode" value="${member.memCode}" />
											<c:choose>
												<c:when test="${member.memStatus == 1}">
													<input type="hidden" name="memStatus" value="0" />
													<button type="submit" class="changeStatus">휴면처리</button>
												</c:when>
												<c:otherwise>
													<input type="hidden" name="memStatus" value="1" />
													<button type="submit" class="changeStatus">복구처리</button>
												</c:otherwise>
											</c:choose>
										</form>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이지 이동 인터페이스 영역 -->
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
						<!-- Previous Button -->
						<c:if test="${pageMaker.prev}">
							<li class="pageMaker_btn prev"><a
								href="${pageMaker.pageStart - 1}">이전</a></li>
						</c:if>
						<!-- Page Numbers -->
						<c:forEach begin="${pageMaker.pageStart}"
							end="${pageMaker.pageEnd}" var="num">
							<li
								class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active' : ''}">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>
						<!-- Next Button -->
						<c:if test="${pageMaker.next}">
							<li class="pageMaker_btn next"><a
								href="${pageMaker.pageEnd + 1}">다음</a></li>
						</c:if>
					</ul>
				</div>
				<form id="moveForm" action="/admin/members" method="get">
					<input type="hidden" name="pageNum"
						value="${pageMaker.cri.pageNum}"> <input type="hidden"
						name="amount" value="${pageMaker.cri.amount}"> <input
						type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
				</form>

			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
	document.querySelectorAll('form').forEach(form => {
	    form.addEventListener('submit', (event) => {
	        const memCode = form.querySelector('input[name="memCode"]').value;
	        const memStatus = form.querySelector('input[name="memStatus"]') ? form.querySelector('input[name="memStatus"]').value : 'N/A';
	        const memAdminCheck = form.querySelector('input[name="memAdminCheck"]') ? form.querySelector('input[name="memAdminCheck"]').value : 'N/A';

	        console.log('폼 제출 시 값:', {
	            memCode,
	            memStatus,
	            memAdminCheck
	        });
	    });
	});
	
	
</script>
</body>
</html>
