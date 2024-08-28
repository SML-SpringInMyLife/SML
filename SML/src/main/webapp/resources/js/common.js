// Enter 키를 처리하는 함수
function handleEnterKey(event) {
    if (event.key === 'Enter') {
        event.preventDefault(); // 기본 동작(예: 폼 제출) 방지
        sendMessage();
    }
}

// 문서 로드 후 이벤트 리스너 추가
$(document).ready(function() {
    $('#message-input').on('keydown', handleEnterKey); // 메시지 입력 필드
    $('#search-input').on('keydown', handleEnterKey);  // 검색 입력 필드
});

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

// 폰트 사이즈 증가 함수
function enlargeFont() {
    let currentSize = parseFloat($('body').css('font-size'));
    let newSize = currentSize + 2;
    if (newSize <= MAX_FONT_SIZE) {
        $('body').css('font-size', newSize + 'px'); // 폰트 사이즈 증가
    }
}

// 폰트 사이즈 감소 함수
function reduceFont() {
    let currentSize = parseFloat($('body').css('font-size'));
    let newSize = currentSize - 2;
    if (newSize >= MIN_FONT_SIZE) {
        $('body').css('font-size', newSize + 'px'); // 폰트 사이즈 감소
    }
}

// 페이지 상단으로 부드럽게 스크롤하는 함수
function scrollToTop() {
    $('html, body').animate({ scrollTop: 0 }, 'smooth'); // 상단으로 부드럽게 스크롤
}

// 채팅 상담창 활성화 함수
function chatConsultation() {
    startChat(); // 채팅 시작 함수 호출
    let $memId = $('#memId');
    
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
        // 클로징 메시지를 서버에 전송
        const closeMessage = JSON.stringify({ action: 'closeChat' });
        ws.send(closeMessage);
        
        // WebSocket 연결 종료
        ws.close();
    }
}

// 채팅 종료 취소 함수
function cancelCloseChat() {
    $('#close-chat-modal').addClass('hidden'); // 닫기 모달 숨기기
}

// 채팅창 드래그 및 리사이즈 기능 추가
$(function() {
    const $chatContainer = $('#chat-container');
    const $chatHeader = $('#chat-header');
    let isDragging = false;
    let offsetX, offsetY;

    // 채팅창 헤더를 드래그할 때
    $chatHeader.on('mousedown', function(e) {
        isDragging = true;
        offsetX = e.clientX - $chatContainer.offset().left;
        offsetY = e.clientY - $chatContainer.offset().top;
        $chatContainer.css('cursor', 'move');
    });

    // 드래그 중일 때
    $(document).on('mousemove', function(e) {
        if (isDragging) {
            $chatContainer.css({
                left: e.clientX - offsetX + 'px',
                top: e.clientY - offsetY + 'px'
            });
        }
    });

    // 드래그를 멈출 때
    $(document).on('mouseup', function() {
        isDragging = false;
        $chatContainer.css('cursor', 'default');
    });

    // 채팅창 리사이즈 시 채팅 박스 높이 조정
    const resizeObserver = new ResizeObserver(() => {
        const $chatBox = $('#chat-box');
        const $chatInput = $('#chat-input');
        const headerHeight = $chatHeader.outerHeight();
        const inputHeight = $chatInput.outerHeight();
        const containerHeight = $chatContainer.outerHeight();
        $chatBox.css('height', containerHeight - headerHeight - inputHeight + 'px');
    });
    resizeObserver.observe($chatContainer[0]);
});

// WebSocket 연결 설정
let ws;

function startChat() {
    ws = new WebSocket('ws://localhost:8080/chat');

    ws.onopen = function() {
        console.log('WebSocket 연결이 열렸습니다.'); // WebSocket 연결 성공 로그
    };

    ws.onmessage = function(event) {
        appendMessage(event.data); // 메시지 수신 시 메시지 추가 함수 호출
        handleNewMessage(); // 새 메시지 알림 처리
    };

    ws.onclose = function() {
        console.log('WebSocket 연결이 닫혔습니다.'); // WebSocket 연결 종료 로그
    };

    ws.onerror = function(error) {
        console.error('WebSocket 오류:', error); // WebSocket 오류 로그
    };
}

// 메시지 전송 함수
function sendMessage() {
    const message = $('#message-input').val().trim();
    if (message !== '') {
        // JSON 객체 생성
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

        // 발신자 정보와 메시지 내용
        const userId = jsonMessage.userId;
        const content = jsonMessage.content;
        const timestamp = jsonMessage.timestamp;

        // 현재 사용자의 ID 가져오기
        const myUserId = $('#memId').text().trim();

        // 메시지 컨테이너 스타일 결정
        const isMyMessage = userId === myUserId;
        $messageDiv.addClass(isMyMessage ? 'my-message-container' : 'other-message-container');

        // 사용자 ID 스타일
        const $userIdDiv = $('<div></div>').addClass('user-id').text(userId);

        // 메시지 내용을 포함하는 div 생성
        const $messageBox = $('<div></div>').addClass('message-box').text(content);

        // 타임스탬프
        const $timestampDiv = $('<div></div>').addClass('timestamp').text(new Date(timestamp).toLocaleTimeString());

        // 사용자 ID와 메시지 내용을 포함하는 div 생성
        $messageDiv.append($userIdDiv, $messageBox, $timestampDiv);

    } catch (e) {
        // JSON 파싱 오류 발생 시 처리
        $messageDiv.addClass('other-message-container').text(message);
    }

    $chatBox.append($messageDiv);
    $chatBox.scrollTop($chatBox.prop('scrollHeight')); // 스크롤을 가장 아래로 이동

    // 채팅창이 최소화 상태일 때 헤더 깜빡이기
    handleNewMessage();
}

/// 새로운 메시지 알림 처리 함수
function handleNewMessage() {
    const $chatContainer = $('#chat-container');
    const $chatHeader = $('#chat-header');
    const $sizeBtn = $chatHeader.find('.sizeBtn'); // sizeBtn 선택
    const $closeBtn = $chatHeader.find('.closeBtn'); // closeBtn 선택

    if ($chatContainer.hasClass('minimized')) {
        // 헤더에 깜빡임 클래스 추가
        $chatHeader.addClass('blinking');
        $chatHeader.css('background-color', 'yellow'); // 헤더 배경색을 노란색으로 변경
        $chatHeader.css('color', 'black'); // 글자 색상을 검은색으로 변경

        // sizeBtn 및 closeBtn 배경색을 노란색으로 변경
        $sizeBtn.css('background-color', 'yellow');
        $closeBtn.css('background-color', 'yellow');

        // 알림 사운드 재생
        const audio = new Audio('/resources/new-message.mp3');
        audio.play();
    }
}

// 채팅창 최소화/최대화 시 상태 복구 처리 함수
function minimizeChat() {
    const $chatContainer = $('#chat-container');
    const $chatHeader = $('#chat-header');
    const $sizeBtn = $chatHeader.find('.sizeBtn'); // sizeBtn 선택
    const $closeBtn = $chatHeader.find('.closeBtn'); // closeBtn 선택

    if ($chatContainer.hasClass('minimized')) {
        // 현재 상태가 최소화 상태일 때 최대화
        $chatContainer.removeClass('minimized').addClass('expanded');
        $chatHeader.find('.sizeBtn').text('➖'); // 버튼 텍스트를 최소화 아이콘으로 변경

        // 헤더 원래 색상 복구 및 깜빡임 제거
        $chatHeader.removeClass('blinking');
        $chatHeader.css('background-color', ''); // 헤더 배경색 복원
        $chatHeader.css('color', ''); // 글자 색상 복원

        // sizeBtn 및 closeBtn 배경색 복원
        $sizeBtn.css('background-color', '');
        $closeBtn.css('background-color', '');

    } else {
        // 현재 상태가 최대화 상태일 때 최소화
        $chatContainer.removeClass('expanded').addClass('minimized');
        $chatHeader.find('.sizeBtn').text('⬜'); // 버튼 텍스트를 최대화 아이콘으로 변경
    }
}
