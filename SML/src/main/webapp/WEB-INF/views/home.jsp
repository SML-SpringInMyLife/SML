<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>SML Home</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>  
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<div class="main-container">
		<main>
			<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
			<div class="row align-items-center">
				<div class="main-text">
					<h1>Spring in My Life</h1>
				</div>
			</div>
		</main>
	</div>
	
<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>	
	
<script>

$(document).ready(function(){   
    let aResult = '<c:out value="${usePoint}"/>';
    checkResult(aResult);
    function checkResult(aresult){
       if(aresult !== ''){
    	   alert("출석 " + aResult +" 포인트가 적립되었습니다") 
       }
       return;
    }
 });
</script>
	
</body>

</html>
