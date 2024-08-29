<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>후원 문의</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .post-container {
            width: 80%;
            margin: 20px auto;
            padding: 15px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            cursor: pointer;
        }
        .post-container:hover {
            background-color: #f1f1f1;
        }
        .post-container h3 {
            margin: 0;
        }
        .post-container p {
            margin: 5px 0;
        }
        .btn-container {
            text-align: center;
            margin-top: 20px;
        }
        .btn-container a {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }
        .btn-container a:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        // 페이지가 로드될 때 메시지가 있으면 알림 창을 띄움
        window.onload = function() {
            const message = '${message}';
            if (message) {
                alert(message);
            }
        }

        function redirectToDetail(id) {
            var contextPath = '${pageContext.request.contextPath}';
            window.location.href = contextPath + '/donation/detail/' + id;
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main>
    <h2>후원 문의</h2>
    
    <c:forEach var="inquiry" items="${boards}">
        <div class="post-container" onclick="redirectToDetail(${inquiry.id})">
            <h3>${inquiry.title}</h3>
            <p><strong>작성자:</strong> 익명</p>
            <p><strong>작성일시:</strong> <fmt:formatDate value="${inquiry.createdDate}" pattern="yyyy/MM/dd" /></p>
        </div>
    </c:forEach>

    <div class="btn-container">
        <a href="${pageContext.request.contextPath}/donation/write">문의 작성하기</a>
    </div>
</main>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
