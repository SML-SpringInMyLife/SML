<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>point(포인트)</title>

</head>
<body>
<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<div class="member-container">
		<jsp:include page="/WEB-INF/views/member/mypage.jsp" />
			<div class="member-main-content">
				<h2>나의 포인트 내역</h2>
				
				<form
				action="${appServlet}/member/point"
				method="get" class="point-container">
				<select id="selectbox" name="point">
					<option value="none" ${registDate == 'none' ? 'selected' : ''}>===
						선택 ===</option>
					<option value="202401" ${registDate == '202401' ? 'selected' : ''}>2024년
						1월</option>
					<option value="202402" ${registDate == '202402' ? 'selected' : ''}>2024년
						2월</option>
					<option value="202403" ${registDate == '202403' ? 'selected' : ''}>2024년
						3월</option>
					<option value="202404" ${registDate == '202404' ? 'selected' : ''}>2024년
						4월</option>
					<option value="202405" ${registDate == '202405' ? 'selected' : ''}>2024년
						5월</option>
					<option value="202406" ${registDate == '202406' ? 'selected' : ''}>2024년
						6월</option>
					<option value="202407" ${registDate == '202407' ? 'selected' : ''}>2024년
						7월</option>
					<option value="202408" ${registDate == '202408' ? 'selected' : ''}>2024년
						8월</option>	

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
							<td colspan="8" data-label="포인트">잔여 포인트 : 11000</td>
						</tr>
						<tr>
							<th data-label="No.">1</th>
							<th data-label="날짜">2024-08-01</th>
							<th data-label="내용">회원가입 적립</th>
							<th data-label="포인트">8000</th>
							<th data-label="포인트유형">적립</th>														
							<th data-label="비고">-</th>
						</tr>
						<tr>
							<th data-label="No.">2</th>
							<th data-label="날짜">2024-08-03</th>
							<th data-label="내용">글쓰기 적립</th>
							<th data-label="포인트">5000</th>
							<th data-label="포인트유형">적립</th>								
							<th data-label="비고">-</th>
						</tr>
						<tr>
							<th data-label="No.">3</th>
							<th data-label="날짜">2024-08-05</th>
							<th data-label="내용">수강신청 사용</th>
							<th data-label="포인트">-2000</th>
							<th data-label="포인트유형">사용</th>								
							<th data-label="비고">-</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%></body>
</html>