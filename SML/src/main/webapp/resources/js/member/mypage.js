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