<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>생활안전 조회 게시판</title>
<link rel="stylesheet" href="${webappRoot}/resources/css/notice/safty.css"> 
<!-- 헤더 css -->
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<main>
	    <div class="safty-containery">
        <div class="filter-section">
            <select class="category">
                <option>카테고리</option>
                <option>수강안내</option>
                <option>전체공지</option>
                <option>이벤트</option>
            </select>
            <div class="searchBar">
                <select class="bar">
                    <option value="all">전체</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                    <option value="title-content">제목+내용</option>
                </select>
                <input type="text" placeholder="검색어">
                <button>조회하기</button>
            </div>
        </div>

        <div class="grid-container">

            <div class="grid-item">

                <p class="date">2024-00-00</p>

                <img src="https://via.placeholder.com/150" alt="SML Image">
                <label>사고 예방법</label>
                <div class="info">
                    <p>조회수 35</p>
                    <p>좋아요 1</p>
                </div>
            </div>
            <div class="grid-item">
                <p class="date">2024-00-00</p>
                <img src="https://via.placeholder.com/150" alt="SML Image">
                <label>일자리</label>
                <div class="info">
                    <p>조회수</p>
                    <p>좋아요 1</p>
                </div>
            </div>
            <div class="grid-item">
                <p class="date">2024-00-00</p>
                <img src="https://via.placeholder.com/150" alt="SML Image">
                <label>키오스크 사용법</label>
                <div class="info">

                    <p>조회수</p>
                    <p>좋아요 1</p>
                </div>
            </div>
            <div class="grid-item">
                <p class="date">2024-00-00</p>
                <img src="https://via.placeholder.com/150" alt="SML Image">
                <label >건강관리</label>
                <div class="info">

                    <p>조회수</p>
                    <p>좋아요 1</p>
                </div>
            </div>
        </div>

        <div class="pagination">
            <button class="page-btn active">1</button>
            <button class="page-btn">2</button>
            <button class="page-btn">3</button>
        </div>
    </div>
    <script>
        document.querySelectorAll('.page-btn').forEach(button => {
            button.addEventListener('click', function () {
                document.querySelectorAll('.page-btn').forEach(btn => btn.classList.remove('active'));
                this.classList.add('active');
            });
        });
    </script>
	</main>
		<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>