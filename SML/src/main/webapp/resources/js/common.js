const hamburger = document.getElementById('hamburger');
const mobileMenu = document.getElementById('mobile-menu');

hamburger.addEventListener('click', function() {
    // Toggle mobile menu visibility
    if (mobileMenu.style.display === 'none' || mobileMenu.style.display === '') {
        mobileMenu.style.display = 'block';
    } else {
        mobileMenu.style.display = 'none';
    }
});

window.addEventListener('resize', function() {
    // Hide mobile menu if the window is resized above 1400px
    if (window.innerWidth >= 1400) {
        mobileMenu.style.display = 'none';
    }
});

function enlargeFont() {
    document.body.style.fontSize = (parseFloat(getComputedStyle(document.body).fontSize) + 2) + 'px';
}

function reduceFont() {
    document.body.style.fontSize = (parseFloat(getComputedStyle(document.body).fontSize) - 2) + 'px';
}

function chatConsultation() {
    alert('아직 채팅상담 연결 전입니다~!');
}

function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}