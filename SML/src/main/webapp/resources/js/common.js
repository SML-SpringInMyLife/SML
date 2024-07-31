// Enter 키 이벤트 처리
window.handleKeyPress = function(event) {
	if (event.keyCode === 13) { // Enter키 keyCode = 13
		event.preventDefault(); // 기본 동작(폼 제출 등) 방지
		console.log('Enter key pressed in search bar');
	}
}

var searchBars = document.querySelectorAll('.search-bar');

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

// 창 크기 조정 시 호출되는 이벤트 리스너
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

// 채팅 상담 연결 전임을 알리는 함수
function chatConsultation() {
    alert('아직 채팅상담 연결 전입니다~!');
}

// 페이지 상단으로 부드럽게 스크롤하는 함수
function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}
