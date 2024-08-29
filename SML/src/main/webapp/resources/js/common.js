// WebSocket 연결 설정
let ws;

// WebSocket 연결 및 초기화 함수
function startChat() {
    ws = new WebSocket('ws://localhost:8080/chat');

    ws.onopen = function() {
        console.log("WebSocket 연결 완료");
        requestChatRooms(); // 연결이 되면 채팅방 목록 요청
    };

    ws.onmessage = function(event) {
        appendMessage(event.data); // 서버로부터 메시지 수신
    };

    ws.onclose = function() {
        console.log("WebSocket 연결 종료");
    };

    ws.onerror = function(error) {
        console.error("WebSocket error: ", error);
    };
}



// 채팅방 목록 요청 함수
function requestChatRooms() {
    if (ws) {
        ws.send(JSON.stringify({ action: 'getChatRooms' }));
	}
}


// 메시지 전송 함수
function sendMessage() {
    const message = $('#message-input').val().trim();
    if (message !== '') {
        const jsonMessage = JSON.stringify({
            type: 'chat',
            content: message
        });
        ws.send(jsonMessage); // 서버로 JSON 메시지 전송
        $('#message-input').val(''); // 입력란 초기화
    }
}

// 채팅 박스에 메시지 추가 함수
function appendMessage(message) {
    const $chatBox = $('#chat-box');
    const $messageDiv = $('<div></div>').addClass('message-container');

    try {
        const jsonMessage = JSON.parse(message);
        const userId = jsonMessage.userId;
        const content = jsonMessage.content;
        const timestamp = jsonMessage.timestamp;

        const myUserId = $('#memId').text().trim();
        const isMyMessage = userId === myUserId;
        $messageDiv.addClass(isMyMessage ? 'my-message-container' : 'other-message-container');

        const $userIdDiv = $('<div></div>').addClass('user-id').text(userId);
        const $messageBox = $('<div></div>').addClass('message-box').text(content);
        const $timestampDiv = $('<div></div>').addClass('timestamp').text(new Date(timestamp).toLocaleTimeString());

        $messageDiv.append($userIdDiv, $messageBox, $timestampDiv);

    } catch (e) {
        $messageDiv.addClass('other-message-container').text(message);
    }

    $chatBox.append($messageDiv);
    $chatBox.scrollTop($chatBox.prop('scrollHeight')); // 스크롤을 가장 아래로 이동
    handleNewMessage(); // 새 메시지 알림 처리
}

// 새로운 메시지 알림 처리 함수
function handleNewMessage() {
    const $chatContainer = $('#chat-container');
    const $chatHeader = $('#chat-header');

    if ($chatContainer.hasClass('minimized')) {
        $chatHeader.addClass('blinking');
        $chatHeader.css('background-color', 'yellow');
        $chatHeader.css('color', 'black');

        const audio = new Audio('/resources/new-message.mp3');
        audio.play();
    }
}

// 채팅창 활성화 함수
function chatConsultation() {
    startChat(); // 채팅 시작 함수 호출
    const $memId = $('#memId');

    if (!$memId.length || $memId.text().trim() === '') {
        alert("로그인 후 이용할 수 있습니다."); // 로그인 필요 알림
        return;
    } else {
        const $chatContainer = $('#chat-container');
        const $chatBox = $('#chat-box');
        $chatBox.empty(); // 이전 채팅 내용 초기화
        $chatContainer.removeClass('hidden'); // 채팅창 표시
    }
}

// 채팅창 닫기 요청 함수
function closeChat() {
    $('#close-chat-modal').removeClass('hidden'); // 닫기 모달 표시
}

// 채팅 종료 확인 함수
function confirmCloseChat() {
    $('#chat-container').addClass('hidden');
    $('#close-chat-modal').addClass('hidden');

    if (ws) {
        const closeMessage = JSON.stringify({ action: 'closeChat' });
        ws.send(closeMessage);
        ws.close();
    }
}

// 채팅 종료 취소 함수
function cancelCloseChat() {
    $('#close-chat-modal').addClass('hidden'); // 닫기 모달 숨기기
}

// 햄버거 메뉴 버튼과 모바일 메뉴 요소를 가져옴
const $hamburger = $('#hamburger');
const $mobileMenu = $('#mobile-menu');

// 햄버거 메뉴 버튼 클릭 시 모바일 메뉴 표시/숨기기 토글
$hamburger.on('click', function() {
    $mobileMenu.toggle(); // 모바일 메뉴의 표시 여부 토글
});

// 브라우저 창 크기 조정 시 모바일 메뉴 숨김
$(window).on('resize', function() {
    if ($(window).width() >= 1400) {
        $mobileMenu.hide(); // 창 크기가 1400px 이상이면 모바일 메뉴 숨기기
    }
});

// 최대 및 최소 폰트 사이즈 (픽셀 단위)
const MAX_FONT_SIZE = 24; // 최대 폰트 사이즈
const MIN_FONT_SIZE = 8;  // 최소 폰트 사이즈

// 페이지 상단으로 부드럽게 스크롤하는 함수
function scrollToTop() {
    $('html, body').animate({ scrollTop: 0 }, 'smooth'); // 상단으로 부드럽게 스크롤
}

// 폰트 사이즈 증가 함수
function enlargeFont() {
    let currentSize = parseFloat($('body').css('font-size'));
    let newSize = currentSize + 2;
    if (newSize <= 24) {
        $('body').css('font-size', newSize + 'px'); // 폰트 사이즈 증가
    }
}

// 폰트 사이즈 감소 함수
function reduceFont() {
    let currentSize = parseFloat($('body').css('font-size'));
    let newSize = currentSize - 2;
    if (newSize >= 8) {
        $('body').css('font-size', newSize + 'px'); // 폰트 사이즈 감소
    }
}

// 채팅창 드래그 및 리사이즈 기능 추가
$(function() {
    const $chatContainer = $('#chat-container');
    const $chatHeader = $('#chat-header');
    const $resizeHandle = $('<div></div>').addClass('resize-handle');
    let isDragging = false;
    let offsetX, offsetY;
    let isResizing = false;
    let originalWidth, originalHeight, originalLeft, originalTop;

    $chatContainer.append($resizeHandle);

    $chatHeader.on('mousedown', function(e) {
        if (!isResizing && !$chatContainer.hasClass('minimized')) {
            isDragging = true;
            offsetX = e.clientX - $chatContainer.offset().left;
            offsetY = e.clientY - $chatContainer.offset().top;
            $chatContainer.css('cursor', 'move');
        }
    });

    $(document).on('mousemove', function(e) {
        if (isDragging) {
            $chatContainer.css({
                left: e.clientX - offsetX + 'px',
                top: e.clientY - offsetY + 'px'
            });
        }
    });

    $(document).on('mouseup', function() {
        isDragging = false;
        $chatContainer.css('cursor', 'default');
    });

    $resizeHandle.on('mousedown', function(e) {
        if ($chatContainer.hasClass('minimized')) return;

        isResizing = true;
        originalWidth = $chatContainer.width();
        originalHeight = $chatContainer.height();
        const startX = e.clientX;
        const startY = e.clientY;

        $(document).on('mousemove', function(e) {
            if (isResizing) {
                const newWidth = originalWidth + (e.clientX - startX);
                const newHeight = originalHeight + (e.clientY - startY);

                if (newWidth > 200) {
                    $chatContainer.css('width', newWidth + 'px');
                }
                if (newHeight > 150) {
                    $chatContainer.css('height', newHeight + 'px');
                }
            }
        });

        $(document).on('mouseup', function() {
            isResizing = false;
            $(document).off('mousemove');
            $(document).off('mouseup');
        });
    });

    function minimizeChat() {
        const $sizeBtn = $chatHeader.find('.sizeBtn');

        if ($chatContainer.hasClass('minimized')) {
            $chatContainer.removeClass('minimized').addClass('expanded');
            $chatContainer.css({
                width: originalWidth + 'px',
                height: originalHeight + 'px',
                left: originalLeft + 'px',
                top: originalTop + 'px'
            });
            $sizeBtn.text('➖');
        } else {
            originalWidth = $chatContainer.width();
            originalHeight = $chatContainer.height();
            originalLeft = $chatContainer.offset().left;
            originalTop = $chatContainer.offset().top;

            $chatContainer.removeClass('expanded').addClass('minimized');
            $chatContainer.css({
                width: '300px',
                height: '40px',
                left: '10px',
                top: '10px'
            });
            $sizeBtn.text('⬜');
        }
    }

    const resizeObserver = new ResizeObserver(() => {
        const $chatBox = $('#chat-box');
        const $chatInput = $('#chat-input');
        const headerHeight = $chatHeader.outerHeight();
        const inputHeight = $chatInput.outerHeight();
        const containerHeight = $chatContainer.outerHeight();

        if (!$chatContainer.hasClass('minimized')) {
            $chatBox.css('height', containerHeight - headerHeight - inputHeight + 'px');
        }
    });

    resizeObserver.observe($chatContainer[0]);
});

// Enter 키를 처리하는 함수
function handleEnterKey(event) {
    if (event.key === 'Enter' && !event.shiftKey) {
        event.preventDefault();
        sendMessage(); // Enter 키로 메시지 전송
    }
}

// 페이지 로드 시 WebSocket 시작
$(document).ready(function() {
    // 이벤트 리스너 추가
    $('#send-button').on('click', sendMessage);
    $('#message-input').on('keypress', handleEnterKey);
    $('#close-chat-button').on('click', closeChat);
    $('#confirm-close-button').on('click', confirmCloseChat);
    $('#cancel-close-button').on('click', cancelCloseChat);
    $('#scroll-top-button').on('click', scrollToTop);
    $('#enlarge-font-button').on('click', enlargeFont);
    $('#reduce-font-button').on('click', reduceFont);
    $('#chat-start-button').on('click', chatConsultation);
});