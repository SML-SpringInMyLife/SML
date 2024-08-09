<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guide</title>
<style>


#backToLocationButton {
    position: center;
    margin: 10px;
    }
#backToLocationButton button {
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

#backToLocationButton button:hover {
    background-color: #45a049; /* 버튼 호버 시 배경색 변경 */
}

</style>
</head>
<body>
<center>
<h1> 위치 찾기 기능 사용법 안내</h1>

<h3>위치 찾기 첫 화면</h3>




<img src="/resources/images/location/guide1.png" alt="위치 찾기 첫 화면" />



<h3>검색 창 사용</h3>
<h4>원하는 장소를 입력하여 직접 검색합니다.</h4>
<img src="/resources/images/location/guide2.png" alt="검색 버튼 사용" />

<br>
<br>
<h4>검색 후 지도위에 표시 된 위치 정보를 클릭하면 카카오맵에서 제공하는 상세 페이지로 이동됩니다.</h4>


<img src="/resources/images/location/guide3.png" alt="지도 위 위치 정보 클릭" />

<br>
<br>
<h4>검색 기록을 조회할 수 있습니다</h4>


<img src="/resources/images/location/guide4.png" alt="검색 기록 조회" />

<br>
<br>
<h3>카테고리 버튼 사용</h3>
<h4>원하는 항목을 누르면 내 위치 중심으로 가까운 장소가 검색됩니다.</h4>


<img src="/resources/images/location/guide5.png" alt="카테고리 버튼 사용" />

  <div id="backToLocationButton">
		    <button onclick="backToLocation()">돌아가기</button>
		</div>

</center>
</body>
<script>
    function backToLocation() {
        window.location.href = '/location/map'; 
    }
    </script>
</html>
