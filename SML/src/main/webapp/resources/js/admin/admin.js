// DOM이 완전히 로드된 후 실행되는 코드
document.addEventListener('DOMContentLoaded', function() {
    // 현재 URL의 경로를 가져옴
    var currentPath = window.location.pathname;

    // 모든 메뉴 항목을 선택
    var menuItems = document.querySelectorAll('.admin-menu ul li a');

    // 각 메뉴 항목을 순회
    menuItems.forEach(function(menuItem) {
        // 메뉴 항목의 href 속성이 현재 경로와 일치하면 'active' 클래스를 추가
        if (menuItem.getAttribute('href') === currentPath) {
            menuItem.classList.add('active');
        }
    });
});

// Chart.js를 사용하여 차트를 설정하고 업데이트하는 부분
var registrationChart;

document.addEventListener('DOMContentLoaded', function () {
    // 차트를 그릴 canvas 요소의 컨텍스트를 가져옴
    var ctx = document.getElementById('registrationChart').getContext('2d');

    // Chart.js 차트 객체를 생성
    registrationChart = new Chart(ctx, {
        type: 'line', // 차트 유형
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // x축 레이블
            datasets: [
                {
                    label: '50대 미만', // 데이터셋 레이블
                    borderColor: 'blue', // 선 색상
                    backgroundColor: 'rgba(0, 0, 255, 0.1)', // 배경 색상
                    data: [] // 데이터 배열
                },
                {
                    label: '50대',
                    borderColor: 'red',
                    backgroundColor: 'rgba(255, 0, 0, 0.1)',
                    data: []
                },
                {
                    label: '60대',
                    borderColor: 'green',
                    backgroundColor: 'rgba(0, 255, 0, 0.1)',
                    data: []
                },
                {
                    label: '70대',
                    borderColor: 'purple',
                    backgroundColor: 'rgba(128, 0, 128, 0.1)',
                    data: []
                },
                {
                    label: '80대',
                    borderColor: 'orange',
                    backgroundColor: 'rgba(255, 165, 0, 0.1)',
                    data: []
                },
                {
                    label: '90대 이상',
                    borderColor: 'brown',
                    backgroundColor: 'rgba(165, 42, 42, 0.1)',
                    data: []
                }
            ]
        },
        options: {
            responsive: true, // 반응형 차트
            scales: {
                x: {
                    beginAtZero: true // x축의 시작점을 0으로 설정
                },
                y: {
                    beginAtZero: true // y축의 시작점을 0으로 설정
                }
            }
        }
    });

    // 차트를 초기 데이터로 업데이트
    updateChart();
});

// 특정 연도의 데이터를 가져오는 함수
function getDataForYear(year) {
    var data = {
        "2023": {
            under50: [12, 19, 3, 5, 2, 3, 8, 12, 6, 30, 5, 7],
            fifties: [15, 29, 5, 10, 5, 6, 12, 18, 9, 5, 6, 8],
            sixties: [10, 24, 5, 8, 6, 5, 10, 15, 8, 6, 5, 7],
            seventies: [8, 20, 4, 7, 3, 5, 9, 13, 6, 4, 5, 6],
            eighties: [5, 15, 3, 6, 2, 4, 7, 10, 5, 3, 4, 5],
            ninetiesAndAbove: [2, 5, 1, 2, 1, 2, 3, 4, 2, 1, 2, 3]
        },
        "2022": {
            under50: [22, 29, 5, 7, 4, 5, 10, 15, 9, 6, 7, 9],
            fifties: [18, 25, 7, 12, 6, 8, 14, 20, 11, 7, 8, 10],
            sixties: [15, 20, 6, 10, 5, 7, 12, 18, 10, 7, 6, 8],
            seventies: [10, 18, 5, 8, 4, 6, 11, 15, 8, 5, 6, 7],
            eighties: [8, 12, 4, 6, 3, 5, 8, 10, 6, 4, 5, 6],
            ninetiesAndAbove: [3, 7, 2, 3, 1, 3, 5, 7, 3, 2, 3, 4]
        }
    };

    // 특정 연도의 데이터를 반환
    return data[year];
}

// 차트를 업데이트하는 함수
function updateChart() {
    // 선택된 연도를 가져옴
    var year = document.getElementById('yearSelect').value;

    // 선택된 연도의 데이터를 가져옴
    var data = getDataForYear(year);

    // 차트 데이터 업데이트
    registrationChart.data.datasets[0].data = data.under50;
    registrationChart.data.datasets[1].data = data.fifties;
    registrationChart.data.datasets[2].data = data.sixties;
    registrationChart.data.datasets[3].data = data.seventies;
    registrationChart.data.datasets[4].data = data.eighties;
    registrationChart.data.datasets[5].data = data.ninetiesAndAbove;

    // 차트 갱신
    registrationChart.update();
}

// 회원 검색 함수
function searchMembers() {
    var category = document.getElementById("searchCategory").value; // 검색 카테고리 가져오기
    var query = document.getElementById("searchQuery").value; // 검색 쿼리 가져오기

    // 검색 로직의 예: 콘솔에 검색어와 카테고리 출력
    console.log("Searching for " + query + " in category " + category);
}

// 수강신청 검색 함수
function searchCourses() {
    var category = document.getElementById("searchCategory").value; // 검색 카테고리 가져오기
    var query = document.getElementById("searchQuery").value; // 검색 쿼리 가져오기

    // 검색 로직의 예: 콘솔에 검색어와 카테고리 출력
    console.log("Searching for " + query + " in category " + category);
}

// SMS 발송 팝업을 보여주는 함수
function showSmsPopup() {
    document.getElementById('smsPopup').style.display = 'block'; // SMS 발송 팝업 표시
    document.getElementById('searchResults').innerHTML = ''; // 검색 결과 초기화
    document.getElementById('popupSearchQuery').value = ''; // 검색 쿼리 입력 필드 초기화
}

// SMS 발송 팝업을 닫는 함수
function closeSmsPopup() {
    document.getElementById('smsPopup').style.display = 'none'; // SMS 발송 팝업 숨기기
}

// SMS 상세보기 팝업을 보여주는 함수
function showSmsDetails(content) {
    var smsDetailsPopup = document.getElementById('smsDetailsPopup');
    var smsDetailsContent = document.getElementById('smsDetailsContent');
    smsDetailsContent.innerText = content; // SMS 상세 내용 설정
    smsDetailsPopup.style.display = 'block'; // SMS 상세보기 팝업 표시
}

// SMS 상세보기 팝업을 닫는 함수
function closeSmsDetailsPopup() {
    var smsDetailsPopup = document.getElementById('smsDetailsPopup');
    smsDetailsPopup.style.display = 'none'; // SMS 상세보기 팝업 숨기기
}

// SMS 발송 함수
function sendSms() {
    var recipientNumber = document.getElementById('recipientNumber').value; // 수신인 번호 가져오기
    var senderNumber = document.getElementById('senderNumber').value; // 발신인 번호 가져오기
    var smsContent = document.getElementById('smsContent').value; // SMS 내용 가져오기

    // 실제로는 AJAX 호출을 통해 서버에 SMS 발송 요청 필요
    console.log('수신번호 : ', recipientNumber); // 수신인 번호를 콘솔에 출력
    console.log('발신번호 : ', senderNumber); // 발신인 번호를 콘솔에 출력
    console.log('내용 : ', smsContent); // SMS 내용을 콘솔에 출력

    // SMS 발송 후 팝업 닫기
    closeSmsPopup();
}

// 검색 결과를 처리하고 리스트를 업데이트하는 함수
function updateSearchResults(results) {
    var searchResults = document.getElementById('searchResults');
    searchResults.innerHTML = ''; // 이전 검색 결과 초기화

    results.forEach(function(result) {
        var listItem = document.createElement('li'); // 검색 결과 항목 생성
        listItem.textContent = result.name + '(' + result.phone + ')'; // 이름과 전화번호 설정
        listItem.onclick = function() {
            document.getElementById('recipientNumber').value = result.phone; // 선택된 번호를 수신인 번호 입력 필드에 설정
            // 팝업을 유지하려면 아래 코드를 주석 처리하거나 제거
            // closeSmsPopup();
        };
        searchResults.appendChild(listItem); // 검색 결과 리스트에 항목 추가
    });
}

// 팝업 내 회원 검색 함수
function popupSearchMember() {
    var query = document.getElementById('popupSearchQuery').value; // 검색 쿼리 가져오기

    // 샘플 데이터: 실제로는 AJAX 호출을 통해 서버에서 검색 결과를 받아옴
    var sampleResults = [
        { id: '1', name: '홍길동', phone: '010-9193-3200' },
        { id: '2', name: '임꺽정', phone: '010-9876-5432' }
    ];

    // 쿼리로 필터링된 결과
    var filteredResults = sampleResults.filter(function(result) {
        return result.name.includes(query) || result.phone.includes(query); // 이름이나 전화번호가 쿼리를 포함하는지 확인
    });

    updateSearchResults(filteredResults); // 검색 결과 업데이트
}

// 페이지 로드 시 SMS 내용이 20자 이상인 경우 '...'으로 자르는 CSS 적용
document.addEventListener('DOMContentLoaded', function() {
    var smsContents = document.querySelectorAll('.sms-content');
    smsContents.forEach(function(content) {
        var text = content.innerText;
        if (text.length > 20) {
            content.innerText = text.slice(0, 20) + '...'; // 20자를 초과하면 '...'으로 자르기
        }
    });
});

// 채팅 검색 함수
function searchChats() {
    var category = document.getElementById("chatSearchCategory").value; // 검색 카테고리 가져오기
    var query = document.getElementById("chatSearchQuery").value; // 검색 쿼리 가져오기

    // 검색 로직의 예: 콘솔에 검색어와 카테고리 출력
    console.log("Searching for " + query + " in category " + category);
}

// 채팅 발송 팝업을 보여주는 함수
function showChatPopup() {
    document.getElementById('chatPopup').style.display = 'block'; // 채팅 발송 팝업 표시
    document.getElementById('chatRecipientId').value = ''; // 수신인 ID 초기화
    document.getElementById('chatContent').value = ''; // 채팅 내용 초기화
}

// 채팅 발송 팝업을 닫는 함수
function closeChatPopup() {
    document.getElementById('chatPopup').style.display = 'none'; // 채팅 발송 팝업 숨기기
}

// 채팅 상세보기 팝업을 보여주는 함수
function showChatDetails(content) {
    var chatDetailsPopup = document.getElementById('chatDetailsPopup');
    var chatDetailsContent = document.getElementById('chatDetailsContent');
    chatDetailsContent.innerText = content; // 채팅 상세 내용 설정
    chatDetailsPopup.style.display = 'block'; // 채팅 상세보기 팝업 표시
}

// 채팅 상세보기 팝업을 닫는 함수
function closeChatDetailsPopup() {
    var chatDetailsPopup = document.getElementById('chatDetailsPopup');
    chatDetailsPopup.style.display = 'none'; // 채팅 상세보기 팝업 숨기기
}

// 채팅 발송 함수
function sendChat() {
    var recipientId = document.getElementById('chatRecipientId').value; // 수신인 ID 가져오기
    var chatContent = document.getElementById('chatContent').value; // 채팅 내용 가져오기

    // 실제로는 AJAX 호출을 통해 서버에 채팅 발송 요청 필요
    console.log('Recipient ID:', recipientId); // 수신인 ID를 콘솔에 출력
    console.log('Chat Content:', chatContent); // 채팅 내용을 콘솔에 출력

    // 채팅 발송 후 팝업 닫기
    closeChatPopup();
}

// 페이지 로드 시 채팅 내용이 20자 이상인 경우 '...'으로 자르는 CSS 적용
document.addEventListener('DOMContentLoaded', function() {
    var chatContents = document.querySelectorAll('.chat-content');
    chatContents.forEach(function(content) {
        var text = content.innerText;
        if (text.length > 20) {
            content.innerText = text.slice(0, 20) + '...'; // 20자를 초과하면 '...'으로 자르기
        }
    });
});

// Admin 채팅 멀티창 구현 예정
var stompClient = null;
var chatBoxes = {};

// WebSocket 연결을 설정하는 함수
function connect() {
    var socket = new SockJS('/chat'); // WebSocket 소켓 생성
    stompClient = Stomp.over(socket); // STOMP 클라이언트 생성

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame); // 연결 성공 시 콘솔에 출력
        stompClient.subscribe('/topic/public', function (message) {
            var chatMessage = JSON.parse(message.body); // 메시지를 JSON으로 파싱
            showMessage(chatMessage); // 메시지 표시 함수 호출
        });
    });
}

// 메시지를 화면에 표시하는 함수
function showMessage(message) {
    var chatBox = chatBoxes[message.sender];
    if (!chatBox) {
        chatBox = createChatBox(message.sender); // 채팅 박스가 없으면 생성
        chatBoxes[message.sender] = chatBox;
    }
    var messageElement = document.createElement('div'); // 메시지 요소 생성
    messageElement.className = 'message';
    messageElement.innerHTML = `<b>${message.sender}:</b> ${message.content} <i>${message.time}</i>`; // 메시지 내용 설정
    chatBox.appendChild(messageElement); // 채팅 박스에 메시지 추가
}

// 새로운 채팅 박스를 생성하는 함수
function createChatBox(sender) {
    var chatContainer = document.getElementById('chat-container'); // 채팅 컨테이너 요소 가져오기
    var chatBox = document.createElement('div'); // 채팅 박스 요소 생성
    chatBox.className = 'chat-box';
    chatBox.id = 'chat-' + sender;

    var header = document.createElement('h3'); // 채팅 박스 헤더 생성
    header.innerHTML = 'Chat with ' + sender;
    chatBox.appendChild(header);

    var messageInput = document.createElement('input'); // 메시지 입력 필드 생성
    messageInput.type = 'text';
    messageInput.id = 'input-' + sender;

    var sendButton = document.createElement('button'); // 메시지 전송 버튼 생성
    sendButton.innerHTML = 'Send';
    sendButton.onclick = function() {
        sendMessage(sender); // 메시지 전송 함수 호출
    };

    chatBox.appendChild(messageInput); // 입력 필드 추가
    chatBox.appendChild(sendButton); // 버튼 추가
    chatContainer.appendChild(chatBox); // 채팅 컨테이너에 채팅 박스 추가

    return chatBox;
}

// 메시지를 전송하는 함수
function sendMessage(receiver) {
    var inputElement = document.getElementById('input-' + receiver); // 입력 필드 가져오기
    var messageContent = inputElement.value; // 메시지 내용 가져오기
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: '관리자',
            content: messageContent,
            type: 'CHAT',
            time: new Date().toLocaleTimeString() // 현재 시간 설정
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage)); // 메시지 전송
        inputElement.value = ''; // 입력 필드 초기화
        showMessage(chatMessage); // 관리자 자신의 메시지도 바로 채팅창에 표시
    }
}

// WebSocket 연결 설정
connect();
