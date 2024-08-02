// Enter 키 이벤트 처리
window.handleKeyPress = function(event) {
    if (event.keyCode === 13) { // Enter키 keyCode = 13
        event.preventDefault(); // 기본 동작(폼 제출 등) 방지
        console.log('Enter key pressed in search bar');
    }
}

var searchBars = document.querySelectorAll('.search-bar');

// 모든 검색창에 키다운 이벤트 리스너 추가
searchBars.forEach(function(searchBar) {
    searchBar.addEventListener('keydown', function(event) {
        handleKeyPress(event);
    });
});

// 햄버거 메뉴 버튼과 모바일 메뉴 요소를 가져옴
const hamburger = document.getElementById('hamburger');
const mobileMenu = document.getElementById('mobile-menu');

// 햄버거 메뉴 버튼 클릭 시 모바일 메뉴의 표시 여부를 토글
hamburger.addEventListener('click', function() {
    // 모바일 메뉴의 현재 표시 상태를 확인
    if (mobileMenu.style.display === 'none' || mobileMenu.style.display === '') {
        // 모바일 메뉴를 표시
        mobileMenu.style.display = 'block';
    } else {
        // 모바일 메뉴를 숨김
        mobileMenu.style.display = 'none';
    }
});

// 브라우저 창 크기 조정 시 호출되는 이벤트 리스너
window.addEventListener('resize', function() {
    // 창의 너비가 1400px 이상일 때 모바일 메뉴를 숨김
    if (window.innerWidth >= 1400) {
        mobileMenu.style.display = 'none';
    }
});

// 최대 및 최소 폰트 사이즈 (픽셀 단위)
const MAX_FONT_SIZE = 24; // 최대 폰트 사이즈, 예: 24px
const MIN_FONT_SIZE = 8;  // 최소 폰트 사이즈, 예: 8px

// 폰트 사이즈를 증가시키는 함수
function enlargeFont() {
    // 현재 폰트 사이즈 가져오기
    var currentSize = parseFloat(getComputedStyle(document.body).fontSize);
    // 폰트 사이즈를 2px 증가
    var newSize = currentSize + 2;
    // 새로운 폰트 사이즈가 최대 사이즈를 초과하지 않도록 설정
    if (newSize <= MAX_FONT_SIZE) {
        document.body.style.fontSize = newSize + 'px';
    }
}

// 폰트 사이즈를 감소시키는 함수
function reduceFont() {
    // 현재 폰트 사이즈를 가져오기
    var currentSize = parseFloat(getComputedStyle(document.body).fontSize);
    // 폰트 사이즈를 2px 감소
    var newSize = currentSize - 2;
    // 새로운 폰트 사이즈가 최소 사이즈를 밑도는지 확인
    if (newSize >= MIN_FONT_SIZE) {
        document.body.style.fontSize = newSize + 'px';
    }
}

// 채팅 상담 함수
function chatConsultation() {
    const chatContainer = document.getElementById('chat-container');
    const chatBox = document.getElementById('chat-box');
    chatBox.innerHTML = '';
    
    // 기본 메시지 추가
    const defaultMessage = document.createElement('div');
    defaultMessage.textContent = '무엇을 도와드릴까요?';
    chatBox.appendChild(defaultMessage);

    chatContainer.classList.remove('hidden');
}

function closeChat() {
    document.getElementById('close-chat-modal').classList.remove('hidden');
}

function confirmCloseChat() {
    document.getElementById('chat-container').classList.add('hidden');
    document.getElementById('close-chat-modal').classList.add('hidden');
    if (ws) {
        ws.close();
    }
}

function cancelCloseChat() {
    document.getElementById('close-chat-modal').classList.add('hidden');
}

// WebSocket 연결 설정
const ws = new WebSocket('ws://localhost:8082/chat');

// WebSocket으로부터 메시지가 도착했을 때 호출되는 함수
ws.onmessage = function(event) {
    appendMessage(event.data);
};

// 메시지 전송 함수
function sendMessage() {
    const input = document.getElementById('message-input');
    const message = input.value;
    if (message.trim() !== '') {
        ws.send(message);
        appendMessage(message);
        input.value = '';
    }
}

// 메시지를 채팅 박스에 추가하는 함수
function appendMessage(message) {
    const chatBox = document.getElementById('chat-box');
    const messageDiv = document.createElement('div');
    messageDiv.textContent = message;
    chatBox.appendChild(messageDiv);
}

// 페이지 상단으로 부드럽게 스크롤하는 함수
function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
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

    // 메시지 전송 함수
    window.sendMessage = function() {
        let messageInput = document.getElementById('message-input');
        let chatBox = document.getElementById('chat-box');
        if (messageInput.value.trim() !== '') {
            let newMessage = document.createElement('div');
            newMessage.textContent = messageInput.value;
            chatBox.appendChild(newMessage);
            messageInput.value = '';
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    };

    // 채팅창이 활성화되어 있을 때 다른 창으로 이동해도 채팅창 유지
    document.addEventListener('visibilitychange', function() {
        if (document.hidden) {
            // 페이지가 숨겨졌을 때 실행할 코드 (예: 채팅 상태 저장 등)
            console.log('채팅창이 비활성화되었습니다.');
        } else {
            // 페이지가 다시 활성화되었을 때 실행할 코드 (예: 채팅 상태 복원 등)
            console.log('채팅창이 다시 활성화되었습니다.');
        }
    });

});
