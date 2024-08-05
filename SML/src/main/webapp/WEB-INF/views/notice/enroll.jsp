<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록페이지</title>

<!-- common.css 로드 -->
<link rel="stylesheet" href="${webappRoot}/resources/css/common/common.css">

<!-- enroll.css 로드 -->
<link rel="stylesheet" href="${webappRoot}/resources/css/notice/enroll.css">
</head>
<body>

<!-- 헤더 영역 포함 -->
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<main>
    <div class="enroll-container">
        <div class="category">
            <select>
                <option>☰ 게시글카테고리</option>
                <option>수강안내</option>
                <option>전체공지</option>
                <option>이벤트</option>
            </select>
        </div>
        <h1>사이트 이용안내</h1>
        <div class="content">
            사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다.<br><br>
            사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다. 사이트 이용안내에 대한 내용입니다.
       
        <div class="file-upload">
            <input id="file-upload" type="file">
        </div>
        </div>
        <div class="actions">
            <button class="update">등록</button>
            <button class="cancel">취소</button>
        </div>
    </div>
     <div class="pagination">
            <button class="page-btn active">1</button>
            <button class="page-btn">2</button>
            <button class="page-btn">3</button>
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
