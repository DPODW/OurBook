document.write('<script src="/js/payment/IamPortPayment.js"></script>');
document.write('<script src="/js/payment/TossPayment.js"></script>');


function PayKGincis() {
    const checkResult = checkInput();
    if (checkResult) {
        requestPayKGincis();
    }
}

function PayToss() {
    const checkResult = checkInput();
    if (checkResult) {
        requestPayToss();
    }
}