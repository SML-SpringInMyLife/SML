<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@page import="java.util.List"%>
<%@page import="com.sml.model.MemberCheckVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 출석체크</title>
<link rel="stylesheet" href="/resources/css/member/mypage.css">
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.10.2/main.min.js'></script>
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
				<h2>나의 출석기록 확인</h2>
				

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
    	
    	
        initialView: 'dayGridMonth',
        locale: 'ko', // 한국어 설정
        headerToolbar: {
        	left: 'prev,next',
            center: 'title',
            right: 'today'
        },
        selectable: true,
        droppable: true,
        editable: true,
        
        
        dateClick: function(info) {
        	/*
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
            */
        },
        
        
        events: [ 
            <%
            List<MemberCheckVO> attendanceList = (List<MemberCheckVO>) request.getAttribute("checkList");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (attendanceList != null) {
                for (MemberCheckVO record : attendanceList) {
                	Date attendanceDate = record.getCheckDate();
                    String formattedDate = sdf.format(attendanceDate);
                    
                    if (record.getStatus() == 1) { // 출석한 경우만 표시
            %>
                {
                    title: '출석',
                    start: '<%=formattedDate%>',
                    color: 'green' 
                },
            <%
                    }
                }
            }
            %>
        ]
    });
    calendar.render();
    
    function markAttendance(date) {
        $.ajax({
            url: '/member/memberCheck',
            type: 'POST',
            data: { selectDate: date },
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
    };  
    
});
	   

/*
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar;
    
 // 출석 기록 불러오기
    $.ajax({
        url: '/member/memberCheck',
        type: 'GET',
        success: function(res) {
            var list = res;
            console.log(list);

            var events = list.map(function(item) {
                return {
                    title: item.reservationTitle,
                    start: item.reservationDate + "T" + item.reservationTime
                };
            });
            
         // 캘린더 초기화
            calendar = new FullCalendar.Calendar(calendarEl, {
                locale: 'ko', // 한국어 설정
                headerToolbar: {
                    left: 'prev,next',
                    center: 'title',
                    right: 'today'
                },
                initialView: 'dayGridMonth',
                events: events,
                eventTimeFormat: {
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                },
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
    
    },
    error: function(error) {
        console.error('Error marking attendance:', error);
        alert("출석 기록을 불러오는 중 오류가 발생했습니다.");
    }
});	
 */   

  

    
  
    
    
 
</script>
</html>
