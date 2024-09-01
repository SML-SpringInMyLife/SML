$(document).ready(function () {
    // 현재 경로에 해당하는 메뉴 항목에 'active' 클래스 추가
    const currentPath = window.location.pathname;
    $('.admin-menu ul li a').each(function () {
        if ($(this).attr('href') === currentPath) {
            $(this).addClass('active'); // 현재 페이지 링크에 'active' 클래스 추가
        }
    });

    $(".pageMaker_btn a").on("click", function(e) {
				e.preventDefault();
				const page = $(this).data("page");
				$("#moveForm").find("input[name='pageNum']").val(page);
				$("#moveForm").submit();
	});

    // 각 폼의 제출 시 콘솔에 값 출력 및 폼 제출
    $('form.makeAdm-form, form.status-form').on('submit', function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지
        let $form = $(this);
        let memCode = $form.find('input[name="memCode"]').val();
        let memStatus = $form.find('input[name="memStatus"]').val() || 'N/A';
        let memAdminCheck = $form.find('input[name="memAdminCheck"]').val() || 'N/A';

        console.log('폼 제출 시 값:', {
            memCode,
            memStatus,
            memAdminCheck
        });

        $form.off('submit').submit(); // 기본 폼 제출 동작 후 이벤트 핸들러 제거
    });

    // 차트 초기화
    setupChart();
});

let registrationChart; // 차트 객체를 저장할 변수

// 차트 설정 및 초기 데이터로 업데이트
function setupChart() {
    const ctx = $('#registrationChart')[0].getContext('2d'); // jQuery로 차트 캔버스의 2D 컨텍스트 가져오기

    registrationChart = new Chart(ctx, {
        type: 'line', // 차트 유형: 선형 차트
        data: {
            labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'], // x축 레이블을 월 숫자로 설정
            datasets: [
                { label: '50대미만', borderColor: 'blue', backgroundColor: 'rgba(0, 0, 255, 0.1)', data: [] },
                { label: '50대', borderColor: 'red', backgroundColor: 'rgba(255, 0, 0, 0.1)', data: [] },
                { label: '60대', borderColor: 'green', backgroundColor: 'rgba(0, 255, 0, 0.1)', data: [] },
                { label: '70대', borderColor: 'purple', backgroundColor: 'rgba(128, 0, 128, 0.1)', data: [] },
                { label: '80대', borderColor: 'orange', backgroundColor: 'rgba(255, 165, 0, 0.1)', data: [] },
                { label: '90대이상', borderColor: 'brown', backgroundColor: 'rgba(165, 42, 42, 0.1)', data: [] }
            ]
        },
        options: {
            responsive: true, // 화면 크기에 따라 차트 크기 조정
            scales: {
                x: { beginAtZero: true }, // x축의 시작점을 0으로 설정
                y: {
                    beginAtZero: true, // y축의 시작점을 0으로 설정
                    min: 0, // y축의 최소값 설정
                    max: 10, // y축의 최대값 설정
                    ticks: { stepSize: 1, callback: value => value } // y축 눈금 간격 및 레이블 표시
                }
            },
            plugins: {
                tooltip: {
                    callbacks: {
                        label: tooltipItem => {
                            const datasetLabel = tooltipItem.dataset.label || '';
                            const value = tooltipItem.raw; // Chart.js v3 및 v4에서 값을 가져오는 방법
                            return `${datasetLabel}: ${value} 명`; // 툴팁 레이블에 "명" 추가
                        }
                    }
                }
            }
        }
    });

    // 차트를 초기 데이터로 업데이트
    updateChart(); // 차트 데이터 업데이트 함수 호출
}


// 차트를 업데이트하는 함수
function updateChart() {
    const year = $('#yearSelect').val(); // 선택된 연도 가져오기
    console.log(year);

    // 서버에서 선택된 연도의 데이터를 가져옴
    $.getJSON(`/admin/getDataForYear?year=${year}`)
        .done(data => {
            // 서버에서 받은 데이터를 차트 데이터에 적용
            registrationChart.data.datasets[0].data = data.under50;
            registrationChart.data.datasets[1].data = data.age50s;
            registrationChart.data.datasets[2].data = data.age60s;
            registrationChart.data.datasets[3].data = data.age70s;
            registrationChart.data.datasets[4].data = data.age80s;
            registrationChart.data.datasets[5].data = data.age90plus;

            registrationChart.update(); // 차트 업데이트
        })
        .fail(error => console.error('차트 데이터를 불러오는 중 오류 발생:', error)); // 오류 발생 시 콘솔에 출력
}

// 개별 폼 제출 처리
$(document).ready(function () {
    $('form.makeAdm-form, form.status-form').on('submit', function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지
        const $form = $(this);
        const memCode = $form.find('input[name="memCode"]').val();
        const memStatus = $form.find('input[name="memStatus"]').val() || 'N/A';
        const memAdminCheck = $form.find('input[name="memAdminCheck"]').val() || 'N/A';

        console.log('폼 제출 시 값:', {
            memCode,
            memStatus,
            memAdminCheck
        });

        // 폼을 실제로 제출
        $form.off('submit').submit();
    });
});

// 선택된 회원 정보 저장 변수
let selectedMember = {
    name: '',
    phone: '',
    memCode: '' // 회원 코드 추가
};

// SMS 발송 팝업 표시 함수
function showSmsPopup() {
    clearSearchResults(); // 검색 결과 초기화
    $('#searchResults').empty(); // 검색 결과 목록 초기화
    $('#popupSearchQuery').val(''); // 검색어 입력 필드 초기화
    $('#smsPopup').show(); // SMS 발송 팝업 표시
}

// SMS 발송 팝업 닫기 함수
function closeSmsPopup() {
    $('#smsPopup').hide(); // SMS 발송 팝업 숨기기

    // 선택된 회원 정보 초기화
    selectedMember.name = '';
    selectedMember.phone = '';
    selectedMember.memCode = '';

    // 폼 필드 초기화
    $('#recipientNumber').val('');
    $('#smsContent').val('');
    $('#memCode').val('');
}

// SMS 상세보기 팝업 표시 함수
function showSmsDetails(content) {
    $('#smsDetailsContent').text(content); // SMS 내용 설정
    $('#smsDetailsPopup').show(); // SMS 상세보기 팝업 표시
}

// SMS 상세보기 팝업 닫기 함수
function closeSmsDetailsPopup() {
    $('#smsDetailsPopup').hide(); // SMS 상세보기 팝업 숨기기
}

// SMS 발송 함수
function sendSms() {
    const recipientNumber = $('#recipientNumber').val(); // 수신인 번호 가져오기
    const senderNumber = $('#senderNumber').val(); // 발신인 번호 가져오기
    const smsContent = $('#smsContent').val(); // SMS 내용 가져오기

    // 실제로는 AJAX를 사용해 서버에 SMS 발송 요청을 해야 합니다.
    console.log('수신인 번호: ', recipientNumber); // 수신인 번호 출력
    console.log('발신인 번호: ', senderNumber); // 발신인 번호 출력
    console.log('내용: ', smsContent); // SMS 내용 출력

    // SMS 발송 후 팝업 닫기
    closeSmsPopup(); // 팝업 닫기
}

// 검색 결과를 처리하고 목록을 업데이트하는 함수
function updateSearchResults(results) {
    $('#searchResults').empty(); // 기존 검색 결과를 리셋
    
    if (results.length === 0) { // 결과가 없는 경우
        alert("회원이 없습니다."); // 알림창 표시
        return; // 함수를 조기에 종료
    }

    results.forEach(result => {
        // 검색 결과 항목 생성
        const $listItem = $('<li></li>').text(`${result.memName} (${result.memPhone})`);
        $listItem.on('click', () => {
            $('#recipientNumber').val(result.memPhone); // 선택한 번호를 수신자 번호 입력란에 설정
        });
        $('#searchResults').append($listItem); // 결과 목록에 추가
    });
}

// 팝업에서 회원 검색 함수
function popupSearchMember(event) {
    const type = $('#popupSearchType').val(); // 검색 타입 가져오기
    const keyword = $('#popupSearchQuery').val(); // 검색어 가져오기

    // 서버에 AJAX 요청
    $.ajax({
        url: 'searchMember.do', // 요청 URL
        type: 'GET', // HTTP 요청 방식
        data: {
            type: type,
            keyword: keyword
        },
        dataType: 'xml', // 응답 데이터 타입
        success: function (xml) {
            const $searchResults = $('#searchResults');
            $searchResults.empty(); // 기존 검색 결과 초기화

            // 검색 결과 항목 추가
            $(xml).find('item').each(function () {
                const memId = $(this).find('memId').text();
                const memName = $(this).find('memName').text();
                const memPhone = $(this).find('memPhone').text();
                const memCode = $(this).find('memCode').text(); // 회원 코드 가져오기

                // 검색 결과 항목 생성
                const $listItem = $('<li></li>').text(`${memName} (${memId}) : ${memPhone}`);
                $listItem.on('click', () => {
                    selectMember(memName, memPhone, memCode); // 선택된 회원 정보 전달
                });
                $searchResults.append($listItem); // 결과 목록에 추가
            });
        },
        error: function (err) {
            console.error('회원 검색 중 오류 발생:', err); // 오류 발생 시 콘솔 출력
        }
    });
}

// 선택된 회원 정보 설정 함수
function selectMember(name, phoneNumber, memCode) {
    selectedMember.name = name; // 회원 이름 저장
    selectedMember.phone = phoneNumber; // 회원 전화번호 저장
    selectedMember.memCode = memCode; // 회원 코드 저장

    // 수신인 번호 입력 필드에 전화번호 설정
    $('#recipientNumber').val(selectedMember.phone);

    // 메시지 내용 필드에 회원 이름을 포함한 메시지 자동 입력
    $('#smsContent').val(`[SML] 안녕하세요, ${selectedMember.name}님! `);

    // 숨겨진 필드에 회원 코드 설정
    $('#memCode').val(selectedMember.memCode);

    // 검색 결과 초기화
    clearSearchResults();

    // 팝업을 닫지 않고 메시지 입력 필드를 유지
    $('#smsPopup').show(); // 팝업 표시
}

// 검색 결과 초기화 함수
function clearSearchResults() {
    $('#searchResults').empty(); // 검색 결과 목록 초기화
}

// 채팅 상세보기 팝업 표시 함수
function showChatDetails(content) {
    $('#chatDetailsContent').html(formatContent(content)); // 포맷팅된 채팅 내용 설정
    $('#chatDetailsPopup').show(); // 채팅 상세보기 팝업 표시
}

// 채팅 내용 포맷팅 함수
function formatContent(content) {
    return content
        .split('//') // '//'를 기준으로 문자열 분리
        .map(line => line.trim()) // 공백 제거
        .filter(line => line) // 빈 줄 제거
        .join('<br><br>'); // <br>로 줄 바꿈 연결
}

// 문서 전체에서 클릭 이벤트를 감지하고 '.chat-content' 요소 클릭 시 처리
document.addEventListener('click', function(event) {
    if (event.target.matches('.chat-content')) { // '.chat-content' 요소 클릭 여부 확인
        const content = event.target.getAttribute('data-chat-content'); // data 속성에서 값 가져오기
        showChatDetails(content); // 팝업에 채팅 내용 표시
    }
});

// 채팅 상세보기 팝업 닫기 함수
function closeChatDetailsPopup() {
    $('#chatDetailsPopup').hide(); // 채팅 상세보기 팝업 숨기기
}

// 채팅 상세보기 팝업 닫기 버튼 이벤트 핸들러
$('#closeChatDetailsButton').on('click', function() {
    closeChatDetailsPopup(); // 팝업 닫기
});

