<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>
<main>
      <div class="login-container">
         <p class="login-title">Login<p>
         <form class="login-form" method="post">
            <input type="text" id="memId" name="memId"
               placeholder="아이디를 입력하세요." required> 
             <input type="password" id="memPw" name="memPw"
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
<script>
 
    /* 로그인 버튼 클릭 메서드 */
    $(".login_button").click(function(){
        
        //alert("로그인 버튼 작동");
        
    	/* 로그인 메서드 서버 요청 */
        $("#login-form").attr("action", "/member/login");
        $("#login-form").submit();
        
    });
 
</script>
</html>