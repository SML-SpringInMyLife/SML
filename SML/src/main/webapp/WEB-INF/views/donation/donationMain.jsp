<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>donation</title>
<style>

#donationButton {
    position: center;
    margin: 10px;
    }
#donationButton button {
    background-color: #4CAF50;
    border: none; /* 테두리 없음 */
    color: white; /* 텍스트 색상 */
    padding: 10px 20px; /* 패딩 설정 */
    text-align: center; /* 텍스트 정렬 */
    text-decoration: none; /* 텍스트 데코레이션 없음 */
    display: inline-block; /* 인라인 블록 속성 */
    font-size: 16px; /* 폰트 크기 */
    border-radius: 5px; /* 모서리 둥글게 */
    cursor: pointer; /* 커서 모양 */
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2); /* 그림자 효과 */
    transition: background-color 0.3s ease; /* 배경색 전환 효과 */
}

#donationButton button:hover {
    background-color: #45a049; /* 버튼 호버 시 배경색 변경 */
}
#donationImage {
    cursor: pointer;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<main>


<h3>당신의 후원을 기다립니다.</h3>

<div id="donationButton">
		    <button onclick="donationGo()">후원하기</button>
		          
		</div>

<img id="donationImage" src="/resources/images/location/donation.png" onclick="donationGo()" alt="후원하기"/>
</main>
<script>

function donationGo() {
    window.location.href = '/donation/payment'; 
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>