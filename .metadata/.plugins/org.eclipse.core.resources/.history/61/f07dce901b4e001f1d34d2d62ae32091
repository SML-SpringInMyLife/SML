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
