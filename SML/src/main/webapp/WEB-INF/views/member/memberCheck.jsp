<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 출석체크</title>
<link rel="stylesheet"
	href="/resources/css/member/mypage.css">
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.js'></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 해당 페이지의 메인내용을 여기에 작성하세요. -->
	<main> 
	<div class="member-container">
	  <jsp:include page="/WEB-INF/views/member/mypage.jsp" />
	  			<div class="member-main-content">
				<h2>매일매일 출석체크</h2>
							<form
				action="${appServlet}/member/point"
				method="get" class="point-container">
				<select id="selectbox" name="point">
					<option value="none" ${registDate == 'none' ? 'selected' : ''}>===
						선택 ===</option>
					<option value="202401" ${registDate == '202401' ? 'selected' : ''}>2024년
						1월</option>
					<option value="202402" ${registDate == '202402' ? 'selected' : ''}>2024년
						2월</option>
					<option value="202403" ${registDate == '202403' ? 'selected' : ''}>2024년
						3월</option>
					<option value="202404" ${registDate == '202404' ? 'selected' : ''}>2024년
						4월</option>
					<option value="202405" ${registDate == '202405' ? 'selected' : ''}>2024년
						5월</option>
					<option value="202406" ${registDate == '202406' ? 'selected' : ''}>2024년
						6월</option>
					<option value="202407" ${registDate == '202407' ? 'selected' : ''}>2024년
						7월</option>
					<option value="202408" ${registDate == '202408' ? 'selected' : ''}>2024년
						8월</option>	

				</select>
				<button class="submit-button" type="submit">출석하기</button>
			</form>	

				<!--  달력 보이기 -->
				<div id='calendar'></div>
				</div>
		</div>
				
	 
  	
	</main>

	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>

<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
    	locale: 'ko', // 한국어 설정
    	headerToolbar: {
            left: 'prev,next',
            center: 'title',
            right: 'today'
        },
        initialView: 'dayGridMonth',
        events: [], // 초기 빈 이벤트 배열
        
        dateClick: function(info) {
            var clickedDate = info.dateStr;
            var today = new Date().toISOString().split('T')[0];
            
            if (clickedDate === today) {
                // 오늘 날짜를 클릭했을 때만 출석 처리
                markAttendance(clickedDate);
            } else if (clickedDate > today) {
                alert("미래 날짜는 출석할 수 없습니다.");
            } else {
                alert("지난 날짜는 출석할 수 없습니다.");
            }
        }
    });

    calendar.render();

    function markAttendance(date) {
        $.ajax({
            url: '/member/memberCheck',
            type: 'POST',
            data: { attendanceDate: date },
            success: function(response) {
                alert("출석이 완료되었습니다.");
                // 캘린더에 출석 표시 추가
                calendar.addEvent({
                    title: '출석',
                    start: date,
                    allDay: true,
                    color: 'green'
                });
            },
            error: function(error) {
                console.error('Error marking attendance:', error);
                alert("출석 처리 중 오류가 발생했습니다.");
            }
        });
    }
  
    // 페이지 로드 시 출석 기록 불러오기
    loadAttendanceRecords();
    
    
}); 
</script>
</html>
