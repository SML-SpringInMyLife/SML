<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록페이지</title>

<link rel="stylesheet" href="${webappRoot}/resources/css/notice/main.css"> 
<!-- 헤더 css -->
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">

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
				<div class="search-box">
					<select>
						<option value="전체">전체</option>
						<option value="제목">제목</option>
						<option value="내용">내용</option>
						<option value="제목+내용">제목+내용</option>
					</select> <input type="text" placeholder="검색어를 입력하세요">
					<button>검색하기</button>
				</div>
			</div>
			
			<!-- 게시물 O -->
				<c:if test="${listCheck != 'empty'}">
				
			<div class="category">
			<select>
			  <option>카테고리</option> <!-- 카테고리를 클릭 했을 때, 원래 목록페이지로 돌아오기 -->
			  <option>수강안내</option>
			  <option>전체공지</option>
			  <option>이벤트</option>			
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
						<td><c:out value="${notice.noticeTitle}" /></td>
						<td><fmt:formatDate value="${notice.noticeEnroll}" pattern="yyyy-MM-dd"/></td>
						<td><c:out value="${notice.noticeCount}" /></td>
						<td><c:out value="${notice.noticeLike}" /></td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
			
			</c:if>
			
			<div class="button-container">
				<button id="write">글쓰기</button>
			</div>
			</div>
			<div class="pagination">
            <button class="page-btn active">1</button>
            <button class="page-btn">2</button>
            <button class="page-btn">3</button>
        </div>
      
       
	</main>
	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


     <script>
     /* 글쓰기 버튼 */
     $("#write").click(function(){
     location.href="/notice/enroll"
     });
      
        let result = '<c:out value="${enroll_result}"/>';
        checkResult(result);
        
        function checkResult(result){
            if(result === ''){
                return;
            }		        
            alert("글'${enroll_result}' 을 등록하였습니다.");        
        }
    </script>
</body>
</html>