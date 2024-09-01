<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
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

	<!-- 해당 페이지의 메인 내용을 여기에 작성하세요. -->
	<main>
		<div id="chatList">
			<h3>Chat Rooms</h3>
			<div id="roomList">
				<ol id="chat-room-list">
					<!-- 여기에 동적으로 채팅방 목록이 추가됨. 10초마다 갱신됨 -->
				</ol>
			</div>
		</div>
	</main>
	<!-- 푸터 영역 포함 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
		function updateChatRooms() {
			$.ajax({
				url : '/admin/chatRooms',
				method : 'GET',
				success : function(response) {
					let chatRooms = JSON.parse(response).chatRooms;
					let chatRoomList = $('#chat-room-list');
					chatRoomList.empty(); // 기존 목록 제거

					chatRooms.forEach(function(room) {
						chatRoomList
								.append('<li data-id="' + room.id + '">ID: '
										+ room.id + ', Name: ' + room.name
										+ '</li>');
					});

					/// 채팅방 클릭 이벤트 추가
					$('#chat-room-list li').on('click', function() {
						const conversationId = $(this).data('id');
						console.log("chat.jsp 클릭이벤트 ====> "+conversationId);
						const message = JSON.stringify({
					        action: 'joinChatRoom',
					        conversationId: conversationId
					    });
					    ws.send(message);
					    $('#chat-container').removeClass('hidden');
					    $('#conversationId').val(conversationId);
					});
				},
				error : function(xhr, status, error) {
					console.error('채팅방 목록 가져오기 실패:', status, error);
				}
			});
		}

		
		// 페이지 로드 시 채팅방 목록 갱신
		$(document).ready(function() {
			startChat();
			updateChatRooms();
			// 10초마다 채팅방 목록 갱신
			setInterval(updateChatRooms, 10000);
		});
	</script>
</body>
</html>
