// DOM이 완전히 로드된 후 실행되는 코드
document.addEventListener('DOMContentLoaded', () => {
    // 현재 경로의 메뉴 항목에 'active' 클래스를 추가하는 코드
    const currentPath = window.location.pathname;
    const menuItems = document.querySelectorAll('.admin-menu ul li a');
    menuItems.forEach(menuItem => {
        if (menuItem.getAttribute('href') === currentPath) {
            menuItem.classList.add('active'); // 현재 페이지 링크에 'active' 클래스 추가
        }
    });

    // 초기 데이터로 차트 설정 및 업데이트
    setupChart(); // 차트 초기화 함수 호출
});

let registrationChart; // 차트 객체를 저장할 변수

// Chart.js를 사용하여 차트를 설정하고 업데이트하는 부분
function setupChart() {
    const ctx = document.getElementById('registrationChart').getContext('2d'); // 차트 캔버스의 2D 컨텍스트 가져오기

    registrationChart = new Chart(ctx, {
        type: 'line', // 차트 타입: 선형 차트
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // x축 레이블
            datasets: [
                {
                    label: '50대 미만',
                    borderColor: 'blue',
                    backgroundColor: 'rgba(0, 0, 255, 0.1)',
                    data: [] // 데이터는 비어있음, 차트 데이터로 업데이트 필요
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
            responsive: true, // 화면 크기에 따라 차트 크기 조정
            scales: {
                x: {
                    beginAtZero: true // x축의 시작점을 0으로 설정
                },
                y: {
                    beginAtZero: true, // y축의 시작점을 0으로 설정
                    min: 0, // y축의 최소값 설정
                    max: 30, // y축의 최대값 설정
                    ticks: {
                        stepSize: 1, // y축의 단계 크기 설정
                        callback: value => value // y축 레이블 표시
                    }
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: tooltipItem => {
                            const datasetLabel = tooltipItem.dataset.label || '';
                            const value = tooltipItem.raw; // Chart.js v3 및 v4에서는 tooltipItem.raw를 사용하여 값 가져옴
                            
                            // 툴팁 레이블에 값 뒤에 "명" 추가
                            return `${datasetLabel}: ${value} 명`;
                        }
                    }
                }
            }
        }
    });

    // 초기 데이터로 차트 업데이트
    updateChart(); // 차트 데이터 업데이트 함수 호출
}

// 차트를 업데이트하는 함수
function updateChart() {
    const year = document.getElementById('yearSelect').value; // 선택된 연도 가져오기
    console.log(year);

    // 서버에서 선택된 연도의 데이터를 가져옴
    fetch(`/admin/getDataForYear?year=${year}`)
        .then(response => response.json())
        .then(data => {
            // 서버에서 받은 데이터를 차트 데이터에 적용
            registrationChart.data.datasets[0].data = data.under50;
            registrationChart.data.datasets[1].data = data.age50s;
            registrationChart.data.datasets[2].data = data.age60s;
            registrationChart.data.datasets[3].data = data.age70s;
            registrationChart.data.datasets[4].data = data.age80s;
            registrationChart.data.datasets[5].data = data.age90plus;

            registrationChart.update(); // 차트 업데이트
        })
        .catch(error => console.error('차트 데이터를 불러오는 중 오류 발생:', error)); // 오류 발생 시 콘솔에 출력
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
    const smsDetailsPopup = document.getElementById('smsDetailsPopup');
    const smsDetailsContent = document.getElementById('smsDetailsContent');
    smsDetailsContent.innerText = content; // SMS 상세 내용 설정
    smsDetailsPopup.style.display = 'block'; // SMS 상세보기 팝업 표시
}

// SMS 상세보기 팝업을 닫는 함수
function closeSmsDetailsPopup() {
    document.getElementById('smsDetailsPopup').style.display = 'none'; // SMS 상세보기 팝업 숨기기
}

// SMS 발송 함수
function sendSms() {
    const recipientNumber = document.getElementById('recipientNumber').value; // 수신인 번호 가져오기
    const senderNumber = document.getElementById('senderNumber').value; // 발신인 번호 가져오기
    const smsContent = document.getElementById('smsContent').value; // SMS 내용 가져오기

    // 실제로는 AJAX 호출을 통해 서버에 SMS 발송 요청 필요
    console.log('수신번호 : ', recipientNumber); // 수신인 번호를 콘솔에 출력
    console.log('발신번호 : ', senderNumber); // 발신인 번호를 콘솔에 출력
    console.log('내용 : ', smsContent); // SMS 내용을 콘솔에 출력

    // SMS 발송 후 팝업 닫기
    closeSmsPopup(); // 팝업 닫기
}

// 검색 결과를 처리하고 리스트를 업데이트하는 함수
function updateSearchResults(results) {
    const searchResults = document.getElementById('searchResults');
    searchResults.innerHTML = ''; // 이전 검색 결과 초기화

    results.forEach(result => {
        const listItem = document.createElement('li'); // 검색 결과 항목 생성
        listItem.textContent = `${result.memName} (${result.memPhone})`; // 이름과 전화번호 설정
        listItem.onclick = () => {
            document.getElementById('recipientNumber').value = result.memPhone; // 선택된 번호를 수신인 번호 입력 필드에 설정
        };
        searchResults.appendChild(listItem); // 검색 결과 리스트에 항목 추가
    });
}

// 팝업 내 회원 검색 함수
function popupSearchMember() {
    console.log("Is it working~~~~~~~~~~~~~~~~~~~~~~");
    // AJAX 요청을 통해 검색 결과를 받아와 처리하는 코드
    const type = document.getElementById('popupSearchType').value; // 검색 유형 가져오기
    const keyword = document.getElementById('popupSearchQuery').value; // 검색 쿼리 가져오기

    // 서버에 AJAX 요청
    $.ajax({
        url: 'searchMember.do', // 요청할 URL
        type: 'GET', // HTTP 요청 방식
        data: {
            type: type,
            keyword: keyword
        },
        success: function(data) {
            // 서버 응답 데이터의 타입을 확인
            console.log('서버 응답 데이터:', data);
            console.log('데이터 타입:', Array.isArray(data) ? '배열' : '배열 아님'); // 데이터가 배열인지 확인

            // 데이터가 배열인지 확인 후 처리
            if (Array.isArray(data)) {
                // 검색 결과를 표시하는 로직
                const searchResults = document.getElementById('searchResults');
                searchResults.innerHTML = ''; // 이전 검색 결과 초기화

                data.forEach(function(member) {
                    const listItem = document.createElement('li'); // 검색 결과 항목 생성
                    listItem.textContent = `${member.memName} (${member.memPhone})`; // 이름과 전화번호 설정
                    listItem.onclick = function() {
                        selectMember(member); // 회원 선택 함수 호출
                    };
                    searchResults.appendChild(listItem); // 검색 결과 리스트에 항목 추가
                });
            } else {
                console.error('서버 응답 데이터가 배열이 아닙니다:', data); // 데이터 형식 오류 처리
            }
        },
        error: function(err) {
            console.error('회원 검색 중 오류 발생:', err); // 오류 발생 시 콘솔에 출력
        }
    });
}

// 선택된 회원 정보를 저장할 변수
let selectedMember = {
    name: '',
    phone: '',
    memCode: '' // memCode 추가
};

// 팝업 내 회원 검색 함수
function popupSearchMember() {
    const type = document.getElementById('popupSearchType').value; // 검색 유형 가져오기
    const keyword = document.getElementById('popupSearchQuery').value; // 검색 쿼리 가져오기

    // 서버에 AJAX 요청
    $.ajax({
        url: 'searchMember.do', // 요청할 URL
        type: 'GET', // HTTP 요청 방식
        data: {
            type: type,
            keyword: keyword
        },
        dataType: 'xml', // 응답 데이터 형식으로 XML 지정
        success: function(xml) {
            const searchResults = document.getElementById('searchResults');
            searchResults.innerHTML = ''; // 이전 검색 결과 초기화

            // XML 문서에서 <item> 요소를 선택
            $(xml).find('item').each(function() {
                const memId = $(this).find('memId').text();
                const memName = $(this).find('memName').text();
                const memPhone = $(this).find('memPhone').text();
                const memCode = $(this).find('memCode').text(); // memCode 가져오기

                const listItem = document.createElement('li'); // 검색 결과 항목 생성
                listItem.textContent = `${memName}(${memId}) : ${memPhone}`; // 이름과 전화번호 설정
                listItem.onclick = function() {
                    selectMember(memName, memPhone, memCode); // 선택된 회원의 이름, 전화번호, memCode를 멤버 선택 함수에 전달
                };
                searchResults.appendChild(listItem); // 검색 결과 리스트에 항목 추가
               
            });
        },
        error: function(err) {
            console.error('회원 검색 중 오류 발생:', err); // 오류 발생 시 콘솔에 출력
        }
    });
}

// 선택된 회원의 이름, 전화번호 및 memCode를 설정하는 함수
function selectMember(name, phoneNumber, memCode) {
    selectedMember.name = name; // 선택된 회원 이름 저장
    selectedMember.phone = phoneNumber; // 선택된 회원 전화번호 저장
    selectedMember.memCode = memCode; // 선택된 회원 memCode 저장

    // 수신인 번호 입력 필드에 전화번호 설정
    document.getElementById('recipientNumber').value = selectedMember.phone;

    // 메시지 텍스트 콘텐츠 필드에 선택된 회원 이름 추가
    const smsContentField = document.getElementById('smsContent');
    smsContentField.value = `[SML] 안녕하세요, ${selectedMember.name}님! 3일 이상 미출석 확인 문자입니다.`; // 메시지 내용 설정

    // 숨겨진 필드에 memCode 설정
    document.getElementById('memCode').value = selectedMember.memCode;

    // 검색 결과를 초기화하여 클리어
    clearSearchResults();

    // 팝업을 닫지 않고 메시지 수정이 가능하도록 유지
    document.getElementById('smsPopup').style.display = 'block';
}

// 검색 결과를 초기화하는 함수
function clearSearchResults() {
    document.getElementById('searchResults').innerHTML = ''; // 검색 결과 리스트 초기화
}

// SMS 발송 팝업을 보여주는 함수
function showSmsPopup() {
	clearSearchResults();
    document.getElementById('searchResults').innerHTML = ''; // 검색 결과 초기화
    document.getElementById('smsPopup').style.display = 'block'; // SMS 발송 팝업 표시
}

// SMS 발송 팝업을 닫는 함수
function closeSmsPopup() {
    document.getElementById('smsPopup').style.display = 'none'; // SMS 발송 팝업 숨기기

    // 선택된 회원의 정보를 초기화
    selectedMember.name = '';
    selectedMember.phone = '';
    selectedMember.memCode = '';

    // 폼 필드 초기화
    document.getElementById('recipientNumber').value = '';
    document.getElementById('smsContent').value = '';
    document.getElementById('memCode').value = '';
}


// 채팅 검색 함수
function searchChats() {
    const type = document.getElementById("chatSearchType").value; // 검색 카테고리 가져오기
    const query = document.getElementById("chatSearchQuery").value; // 검색 쿼리 가져오기

    // 검색 로직의 예: 콘솔에 검색어와 카테고리 출력
    console.log(`Searching for ${query} in type ${type}`);
}

// 채팅 상세보기 팝업을 보여주는 함수
function showChatDetails(content) {
    const chatDetailsPopup = document.getElementById('chatDetailsPopup');
    const chatDetailsContent = document.getElementById('chatDetailsContent');
    chatDetailsContent.innerText = content; // 채팅 상세 내용 설정
    chatDetailsPopup.style.display = 'block'; // 채팅 상세보기 팝업 표시
}

// 채팅 상세보기 팝업을 닫는 함수
function closeChatDetailsPopup() {
    document.getElementById('chatDetailsPopup').style.display = 'none'; // 채팅 상세보기 팝업 숨기기
}
