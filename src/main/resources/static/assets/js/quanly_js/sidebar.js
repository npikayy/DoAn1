function showPopup() {
    document.getElementById('overlay').style.display = 'block';
    document.getElementById('logoutPopup').style.display = 'block';
}

function hidePopup() {
    document.getElementById('overlay').style.display = 'none';
    document.getElementById('logoutPopup').style.display = 'none';
}

function toTop() {
    window.scrollTo({top: 0, behavior: 'smooth'});
}