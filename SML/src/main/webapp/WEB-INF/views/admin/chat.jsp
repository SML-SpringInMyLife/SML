<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chat Consultation List</title>
<link rel="stylesheet"
	href="${webappRoot}/resources/css/common/common.css">
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/2.5.1/uicons-bold-rounded/css/uicons-bold-rounded.css'>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<!-- 헤더 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />

	<!-- 채팅 상담 목록 메인 내용을 여기에 작성 -->
	<main>
		<div class="admin-container">
			<jsp:include page="/WEB-INF/views/admin/adminMenu.jsp" />

			<div class="admin-main-content" id="chatList">
				<h2>채팅 상담 목록</h2>
				<table class="stats-table">
					<thead>
						<tr>
							<th>NO.</th>
							<th>채팅방 ID</th>
							<th>회원명</th>
						</tr>
					</thead>
					<tbody id="chat-room-list">
						<!-- 여기에 동적으로 채팅방 목록이 추가됨 -->
					</tbody>
				</table>
			</div>
		</div>
	</main>

	<!-- 푸터 영역 포함 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script>
		// 채팅방 목록을 갱신하는 함수
		function updateChatRooms() {
			$.ajax({
			    url: '/admin/chatRooms',
			    method: 'GET',
			    dataType: 'json', // JSON 형식으로 응답을 받도록 명시
			    success: function(response) {
			        let chatRooms = response.chatRooms; // 이미 JSON으로 파싱된 응답을 사용
			        let chatRoomList = $('#chat-room-list');
			        chatRoomList.empty(); // 기존 목록 제거

			        chatRooms.forEach(function(room, index) {
			            var status = index + 1;
			            chatRoomList.append('<tr data-id="' + room.id + '">'
			                                + '<td>' + status + '</td>'  // 자동 번호
			                                + '<td>' + room.id + '</td>' 
			                                + '<td>' + room.name + '</td>' // 한글 이름
			                                + '</tr>');
			        });
					// 각 채팅방 클릭 이벤트 설정
					$('#chat-room-list tr').on('click', function() {
						const conversationId = $(this).data('id');
						console.log("채팅방 선택됨 ===> " + conversationId);

						const message = JSON.stringify({
							action : 'joinChatRoom',
							conversationId : conversationId
						});

						ws.send(message); // WebSocket을 통해 메시지 전송
						$('#chat-container').removeClass('hidden');
						$('#conversationId').val(conversationId); // 선택된 대화 ID 설정
					});
				},
				error : function(xhr, status, error) {
					console.error('채팅방 목록 가져오기 실패:', status, error);
				}
			});
		}

		// 페이지가 로드될 때 채팅방 목록을 갱신
		$(document).ready(function() {
			startChat(); // 채팅 연결 함수 호출
			updateChatRooms();
			// 10초마다 채팅방 목록을 갱신
			setInterval(updateChatRooms, 10000);
		});
	</script>
</body>
</html>
