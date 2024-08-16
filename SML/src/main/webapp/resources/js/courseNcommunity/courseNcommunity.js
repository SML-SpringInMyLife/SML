// DOM이 완전히 로드된 후 실행되는 코드
document.addEventListener('DOMContentLoaded', function() {
    // 현재 경로의 메뉴 항목에 'active' 클래스를 추가하는 코드
    var currentPath = window.location.pathname;
    var menuItems = document.querySelectorAll('.course-menu ul li a');
    menuItems.forEach(function(menuItem) {
        if (menuItem.getAttribute('href') === currentPath) {
            menuItem.classList.add('active');
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // 현재 경로의 메뉴 항목에 'active' 클래스를 추가하는 코드
    var currentPath = window.location.pathname;
    var menuItems = document.querySelectorAll('.community-menu ul li a');
    menuItems.forEach(function(menuItem) {
        if (menuItem.getAttribute('href') === currentPath) {
            menuItem.classList.add('active');
        }
    });
});
