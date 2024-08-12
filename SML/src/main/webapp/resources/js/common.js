// Enter 키 이벤트 처리
window.handleKeyPress = function(event) {
    if (event.key === 'Enter') { // Enter 키가 눌렸을 때
        event.preventDefault(); // 기본 동작(예: 폼 제출) 방지
        sendMessage(); // 메시지 전송 함수 호출
    }
};

// 메시지 입력창에 Enter 키 이벤트 리스너 추가
document.getElementById('message-input').addEventListener('keydown', handleKeyPress);

// 햄버거 메뉴 버튼과 모바일 메뉴 요소를 가져옴
const hamburger = document.getElementById('hamburger');
const mobileMenu = document.getElementById('mobile-menu');

// 햄버거 메뉴 버튼 클릭 시 모바일 메뉴의 표시 여부를 토글
hamburger.addEventListener('click', function() {
    mobileMenu.style.display = mobileMenu.style.display === 'none' || mobileMenu.style.display === '' ? 'block' : 'none';
});

// 브라우저 창 크기 조정 시 모바일 메뉴 숨김
window.addEventListener('resize', function() {
    if (window.innerWidth >= 1400) {
        mobileMenu.style.display = 'none';
    }
});

// 최대 및 최소 폰트 사이즈 (픽셀 단위)
const MAX_FONT_SIZE = 24; // 최대 폰트 사이즈
const MIN_FONT_SIZE = 8;  // 최소 폰트 사이즈

// 폰트 사이즈 증가 함수
function enlargeFont() {
    let currentSize = parseFloat(getComputedStyle(document.body).fontSize);
    let newSize = currentSize + 2;
    if (newSize <= MAX_FONT_SIZE) {
        document.body.style.fontSize = newSize + 'px';
    }
}

// 폰트 사이즈 감소 함수
function reduceFont() {
    let currentSize = parseFloat(getComputedStyle(document.body).fontSize);
    let newSize = currentSize - 2;
    if (newSize >= MIN_FONT_SIZE) {
        document.body.style.fontSize = newSize + 'px';
    }
}

// 페이지 상단으로 부드럽게 스크롤하는 함수
function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// 채팅 상담창 활성화 함수
function chatConsultation() {
	startChat();
    const chatContainer = document.getElementById('chat-container');
    const chatBox = document.getElementById('chat-box');
    chatBox.innerHTML = ''; // 이전 채팅 내용 초기화
    chatContainer.classList.remove('hidden'); // 채팅창 표시
}

// 채팅창 닫기 요청 함수
function closeChat() {
    document.getElementById('close-chat-modal').classList.remove('hidden');
}

// 채팅 종료 확인 함수
function confirmCloseChat() {
    document.getElementById('chat-container').classList.add('hidden');
    document.getElementById('close-chat-modal').classList.add('hidden');
    if (ws) {
        ws.close(); // WebSocket 연결 종료
    }
}

// 채팅 종료 취소 함수
function cancelCloseChat() {
    document.getElementById('close-chat-modal').classList.add('hidden');
}

// 채팅창 드래그 및 리사이즈 기능 추가
document.addEventListener('DOMContentLoaded', (event) => {
    let chatContainer = document.getElementById('chat-container');
    let chatHeader = document.getElementById('chat-header');
    let isDragging = false;
    let offsetX, offsetY;

    // 채팅창 헤더를 드래그할 때
    chatHeader.addEventListener('mousedown', (e) => {
        isDragging = true;
        offsetX = e.clientX - chatContainer.offsetLeft;
        offsetY = e.clientY - chatContainer.offsetTop;
        chatContainer.style.cursor = 'move';
    });

    // 드래그 중일 때
    document.addEventListener('mousemove', (e) => {
        if (isDragging) {
            chatContainer.style.left = e.clientX - offsetX + 'px';
            chatContainer.style.top = e.clientY - offsetY + 'px';
        }
    });

    // 드래그를 멈출 때
    document.addEventListener('mouseup', (e) => {
        isDragging = false;
        chatContainer.style.cursor = 'default';
    });

    // 채팅창 리사이즈 시 채팅 박스 높이 조정
    const resizeObserver = new ResizeObserver(() => {
        const chatBox = document.getElementById('chat-box');
        const chatInput = document.getElementById('chat-input');
        const headerHeight = chatHeader.offsetHeight;
        const inputHeight = chatInput.offsetHeight;
        const containerHeight = chatContainer.offsetHeight;
        chatBox.style.height = `${containerHeight - headerHeight - inputHeight}px`;
    });
    resizeObserver.observe(chatContainer);
});

// WebSocket 연결 설정
let ws;

function startChat() {
    // ws = new WebSocket('ws://192.168.1.114:8080/chat'); // WebSocket 서버 주소 설정
    ws = new WebSocket('ws://localhost:8080/chat');

    ws.onopen = function() {
        console.log('WebSocket 연결이 열렸습니다.');
    };

    ws.onmessage = function(event) {
        appendMessage(event.data);
    };

    ws.onclose = function() {
        console.log('WebSocket 연결이 닫혔습니다.');
    };

    ws.onerror = function(error) {
        console.error('WebSocket 오류:', error);
    };
}

// 메시지 전송 함수
function sendMessage() {
    const input = document.getElementById('message-input');
    const message = input.value.trim();
    if (message !== '') {
        // JSON 객체 생성
        const jsonMessage = JSON.stringify({
            type: 'chat',
            content: message
        });
        ws.send(jsonMessage); // 서버로 JSON 메시지 전송
        // appendMessage(message); // 내가 쓴 메시지 채팅창에 추가
        input.value = ''; // 입력란 초기화
    }
}

// 채팅 박스에 메시지 추가 함수
function appendMessage(message) {
    const chatBox = document.getElementById('chat-box');
    const messageDiv = document.createElement('div');

    try {
        const jsonMessage = JSON.parse(message);

        // 발신자 정보와 메시지 내용
        const userId = jsonMessage.userId;
        const content = jsonMessage.content;

        // 현재 사용자의 ID 가져오기
        const myUserId = document.getElementById('memId').textContent.trim();

        // 메시지 컨테이너 스타일 결정
        if (userId === myUserId) {
            messageDiv.className = 'message my-message';
        } else {
            messageDiv.className = 'message other-message';
        }

        // 메시지 내용 HTML 구성
        const userIdSpan = document.createElement('span');
        userIdSpan.className = 'user-id'; // 사용자 ID 스타일 적용
        userIdSpan.textContent = userId + ': ';

        const contentSpan = document.createElement('span');
        contentSpan.textContent = content;

        // 사용자 ID와 메시지 내용을 포함하는 div 생성
        const contentDiv = document.createElement('div');
        contentDiv.appendChild(userIdSpan);
        contentDiv.appendChild(contentSpan);

        // 메시지 컨테이너에 메시지 내용 추가
        messageDiv.appendChild(contentDiv);

    } catch (e) {
        // JSON 파싱 오류 발생 시 처리
        messageDiv.className = 'message other-message';
        messageDiv.textContent = message;
    }

    chatBox.appendChild(messageDiv);
    chatBox.scrollTop = chatBox.scrollHeight; // 스크롤을 가장 아래로 이동
}