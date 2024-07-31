<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>
<main>
      <div class="login-container">
         <p class="login-title">Login<p>
         <form class="login-form" method="post">
            <input type="text" id="userId" name="userId"
               placeholder="아이디를 입력하세요." required> <input
               type="password" id="password" name="password"
               placeholder="비밀번호를 입력하세요." required>

            <button type="submit" class="login-button">SML 로그인</button>
            <button type="button" class="login-button kakao">Kakao 로그인</button>
            <button type="button" class="login-button naver">Naver 로그인</button>
            <button type="button" class="login-button google">Google
               로그인</button>

            <p class="join-link">
               아직 회원이 아니신가요? <a href="/join">회원가입</a>
            </p>
         </form>
      </div>
   </main>
</body>
</html>