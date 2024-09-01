<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script> 
</head>
<body>
</body>
<script>
  var naver_id_login = new naver_id_login("z3VX36utH7jAe5G7GvzU", "http://localhost:8080/member/naverLogin");
   // 접근 토큰 값 출력
  alert(naver_id_login.oauthParams.access_token);
  // 네이버 사용자 프로필 조회
  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
  function naverSignInCallback() {
    alert(naver_id_login.getProfileData('email'));    
  }
    /*
    var naverLogin = new naver.LoginWithNaverId({
        clientId: "z3VX36utH7jAe5G7GvzU", // 본인걸로 수정, 띄어쓰기 금지.
        callbackUrl: "http://localhost:8080/naverLogin", // 아무거나 설정
        isPopup: false,
        callbackHandle: true
    });
    naverLogin.init();
 
    window.addEventListener('load', function () {
    naverLogin.getLoginStatus(function (status) {
 
    if (status) {
       console.log(naverLogin.user); 
       var id = naverLogin.user.getId();
   	   var name = naverLogin.user.getName();
       var email = naverLogin.user.getEmail();
       var phone = naverLogin.user.getPhone();         
        
        
        $.ajax({
            type: 'post',
            url: 'naverSave',
            data: {
            	'n_id': id,
                'n_name': name,
                'n_email': email,
                'n_phone': phone
            },
            dataType: 'text',
            success: function(result) {
                if(result=='ok') {
                    console.log('네이버 로그인 성공')
                    location.replace("http://localhost:8080/") 
                } else if(result=='no') {
                    console.log('네이버 로그인 실패')
                    location.replace("http://localhost:8080/member/login")
                }
            },
            error: function(result) {
                console.log('오류 발생')
            }
        })
 
    } else {
        console.log("네이버 callback 처리에 실패하였습니다.");
    }
    });
});
    */
</script>
</html>