<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        textarea {
            height: 150px;
            resize: none;
        }
        .button-container {
            text-align: center;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        input[type="button"] {
            background-color: #d9534f;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-left: 10px;
        }
        input[type="button"]:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<main>
    <h2>후원 문의 ${board.id != null ? "수정" : "작성"}</h2>
    <form action="${pageContext.request.contextPath}/donation/save" method="post">
        <!-- 게시글 ID는 hidden 필드로 전달 -->
        <input type="hidden" name="id" value="${board.id}">

        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="${board.title}" required>

        <label for="content">내용</label>
        <textarea id="content" name="content" required>${board.content}</textarea>

        <div class="button-container">
            <input type="submit" value="${board.id != null ? '수정하기' : '작성하기'}">
            <input type="button" value="취소" onclick="history.back();">
        </div>
    </form>
</main>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>
