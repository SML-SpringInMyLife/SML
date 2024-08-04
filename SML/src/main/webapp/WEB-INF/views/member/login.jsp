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

	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
	<main>
      <div class="login-container">
         <p class="login-title">Login</p>
         <form class="login-form" class="login-form" method="post">
            <input type="text" id="memId" name="memId"
               placeholder="아이디를 입력하세요." required> 
             <input type="password" id="memPw" name="memPw"
               placeholder="비밀번호를 입력하세요." required>
            
             <c:if test = "${result == 0}">   
             <div class ="login_warn">사용자 ID 또는 비밀번호를 잘못 입력하였습니다.</div>  
             </c:if>

            <button type="submit" class="login-button">SML 로그인</button>
            <button type="button" class="login-button kakao">Kakao 로그인</button>
            <button type="button" class="login-button naver">Naver 로그인</button>
            <button type="button" class="login-button google">Google 로그인</button>

            <p class="join-link">
               아직 회원이 아니신가요? <a href="/join">회원가입</a>
            </p>
         </form>
      </div>
   </main>
   
   <!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
<script>
 
    /* 로그인 버튼 클릭 메서드 */
    $(".login-button").click(function(){
        
        //alert("로그인 버튼 작동");
        
    	/* 로그인 메서드 서버 요청 */
        $("#login-form").attr("action", "/member/login");
        $("#login-form").submit();
        
    });
 
</script>
</html>