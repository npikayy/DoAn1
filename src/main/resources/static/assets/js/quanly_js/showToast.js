function showToast(message, type) {
    const toast = document.createElement('div');
    toast.id = 'toast';

    let toastType = '';
    let icon = '';

    if (type === 'success') {
        toastType = 'toast__success';
        icon = '<i class="fa-solid fa-circle-check"></i>';
    } else if (type === 'error') {
        toastType = 'toast__error';
        icon = '<i class="fa-solid fa-circle-xmark"></i>';
    }

    toast.innerHTML = `
        <div class="toast ${toastType}">
            <div class="toast__icon">
                ${icon}
            </div>
            <div class="toast__body">
<!--                <h3 class="toast__title">${type.charAt(0).toUpperCase() + type.slice(1)}</h3>-->
                <p class="toast__msg">
                   ${message}
                </p>
            </div>
            <div class="toast__close" >
                <i class="fa-solid fa-xmark" onclick="toastClose()"></i>
            </div>
        </div>
    `;

    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 5000);
}

function toastClose(){
    toast.remove();
}