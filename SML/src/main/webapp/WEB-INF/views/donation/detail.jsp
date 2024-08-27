<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
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
        .detail-container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        .detail-container p {
            font-size: 16px;
            line-height: 1.6;
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
            margin-right: 10px;
            text-decoration: none;
        }
        .btn-container a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main>
    <div class="detail-container">
        <h2>게시글 상세</h2>
        <p><strong>제목:</strong> ${board.title}</p>
        <p><strong>작성자:</strong> 익명</p>
        <p><strong>작성일시:</strong> 
            <fmt:formatDate value="${board.createdDate}" pattern="yyyy'년' MM'월' dd'일' HH:mm" />
        </p>
        <p><strong>내용:</strong></p>
        <p>${board.content}</p>
        <div class="btn-container">
            <a href="${pageContext.request.contextPath}/donation/edit/${board.id}">수정하기</a>
            <a href="${pageContext.request.contextPath}/donation/delete/${board.id}" onclick="return confirm('삭제하시겠습니까?')">삭제하기</a>
            <a href="${pageContext.request.contextPath}/donation/list">목록으로 돌아가기</a>
        </div>
    </div>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
