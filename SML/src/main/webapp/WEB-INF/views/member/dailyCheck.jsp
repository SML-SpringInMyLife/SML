<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>회원 출석체크</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/member/dailyCheck.css">
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main>
	<div class="dailyCheck-container">
  	<form>
        
     </form>
  </div>
  </div>
  
    <div id='calendar'></div>
    
    <div id="modal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>출석 정보 등록/수정</h2>
        <form id="attendanceForm">
            <input type="hidden" id="modal-dog-code">
            <label for="modal-date">날짜:</label>
            <input type="text" id="modal-date" readonly><br><br>
            <label for="modal-status">출석 여부:</label>
            <select id="modal-status">
                <option value="Y">출석</option>
                <option value="N">결석</option>
            </select><br><br>
            <button type="button" id="modal-save">저장</button>
            <button type="button" id="modal-reset">초기화</button>
        </form>
    </div>



	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>
