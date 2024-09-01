$(document).ready(function() {
    // 채팅 관련 웹소켓 변수
    var ws = new WebSocket("ws://localhost:8080/chat");

    // 웹소켓 연결이 열리면 호출됨
    ws.onopen = function() {
        console.log("웹소켓 연결 생성");
    };

    // 웹소켓으로부터 메시지를 받을 때 호출됨
    ws.onmessage = function(event) {
        var message = JSON.parse(event.data);

        // 메시지 유형에 따라 처리
        if (message.type === 'chatRoomList') {
            updateChatRoomList(message.rooms); // 채팅방 리스트 업데이트
        } else if (message.type === 'newMessage') {
            displayMessage(message.roomId, message.text); // 새 메시지 표시
        }
    };

    // 웹소켓 연결이 닫히면 호출됨
    ws.onclose = function() {
        console.log("웹소켓 연결 종료");
    };

    // 웹소켓 오류가 발생하면 호출됨
    ws.onerror = function(error) {
        console.error("WebSocket 에러:", error);
    };

    // 채팅방 리스트를 업데이트하는 함수
    function updateChatRoomList(rooms) {
        var $roomList = $('#roomList');
        $roomList.empty(); // 기존 리스트 비우기
        $.each(rooms, function(index, room) {
            var $roomItem = $('<div>').text(room.name); // 채팅방 이름 설정
            $roomItem.on('click', function() {
                joinChatRoom(room.id); // 채팅방 클릭 시 참여
            });
            $roomList.append($roomItem); // 리스트에 추가
        });
    }

    // 채팅방에 참여하는 함수
    function joinChatRoom(roomId) {
        ws.send(JSON.stringify({
            type: 'joinRoom',
            roomId: roomId
        }));
    }

    // 메시지를 보내는 함수
    $('#sendMessageButton').on('click', function() {
        var message = $('#message-input').val();
        ws.send(JSON.stringify({
            type: 'sendMessage',
            text: message
        }));
        $('#message-input').val(''); // 입력 필드 비우기
    });

    // 화면에 메시지를 표시하는 함수
    function displayMessage(roomId, text) {
        var $messageArea = $('#messageArea');
        var $messageElement = $('<div>').text(text); // 메시지 내용 설정
        $messageArea.append($messageElement); // 메시지 영역에 추가
    }

    // 채팅 요청을 서버로 보내는 함수
    $('#requestChatButton').on('click', function() {
        ws.send(JSON.stringify({
            type: 'requestChat',
            userId: $('#memId').text()
        }));
    });

    // 채팅 창 열기/닫기 함수
    $('#openChatButton').on('click', function() {
        $('#chat-container').toggleClass('hidden');
    });

    // 채팅 최소화 함수
    $('#minimizeChatButton').on('click', function() {
        $('#chat-container').addClass('hidden');
    });

    // 채팅 닫기 함수
    $('#closeChatButton').on('click', function() {
        $('#close-chat-modal').removeClass('hidden');
    });

    // 채팅 종료 확인 함수
    $('#confirmCloseChatButton').on('click', function() {
        $('#chat-container').addClass('hidden');
        $('#close-chat-modal').addClass('hidden');
    });

    // 채팅 종료 취소 함수
    $('#cancelCloseChatButton').on('click', function() {
        $('#close-chat-modal').addClass('hidden');
    });
});
