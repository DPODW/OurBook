/** 결제 정보 DB 저장 함수 (KG 이니시스 전용) **/
function KGpaymentInfoSave(paymentInfo,imp_uid){
    var KGorderNumber;
    paymentInfo.paymentNumber = imp_uid;
    $.ajax({
        type: 'POST',
        url: '/OurBook/payment/1',
        data: JSON.stringify(paymentInfo),
        contentType: 'application/json',
        async: false,
        success: function (response) {
            KGorderNumber = response;
            window.location.href = "/OurBook/book/info/payment/result/"+KGorderNumber
        },
        error: function (error) {
            window.location.href = "/OurBook/book/info/payment/fail"
        }
    });
}

/** 결제 정보 DB 저장 함수 (TOSS 전용) **/
function TossPaymentInfoSave(paymentInfo){
    $.ajax({
        type: 'POST',
        url: '/TossPay/payment/1',
        data: JSON.stringify(paymentInfo),
        contentType: 'application/json',
    });
}


/** 서버측 주문번호 생성 함수 **/
function randomOrderNumber() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'; // 영어 대문자 알파벳 (3문자 영어 난수)
    let randomOrderNumber = '';

    for (let i = 0; i < 3; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        randomOrderNumber += characters.charAt(randomIndex);
    }
    return randomOrderNumber;
}


/** 구매시 입력해야할 정보가 공백일시 검증하는 함수 **/
function checkInput() {
    const receiverName = document.getElementById('receiverName');
    const receiverPhoneNumber = document.getElementById('receiverPhoneNumber');
    const receiverPostCode = document.getElementById('receiverPostCode');
    const receiverAddress = document.getElementById('receiverAddress');
    const detailAddress = document.getElementById('detailAddress');

    // 숫자만 포함하는지 정규표현식으로 확인
    const phoneNumberPattern = /^\d+$/;

    if (
        receiverName.value === "" ||
        (receiverPhoneNumber.value !== null && !phoneNumberPattern.test(receiverPhoneNumber.value)) || // null 체크 추가
        receiverPostCode.value === "" ||
        receiverAddress.value === "" ||
        detailAddress.value === ""
    ) {
        alert("구매 정보를 다시 한번 확인해주세요!");
        return false;
    } else {
        return true;
    }
}
