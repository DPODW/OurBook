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
    const detailAddress = document.getElementById("detailAddress");

    if (receiverName.value === "" || receiverPhoneNumber.value === "" ||
        receiverPostCode.value === "" || receiverAddress.value === "" ||
        detailAddress.value === "") {
        alert("구매 정보를 전부 입력해주세요!");
        return false;
    } else {
        return true;
    }
}


/** 결제 완료 시간 생성 함수 **/
function paymentSuccessTime(){
    const currentDate = new Date();

    // 날짜 및 시간 정보 얻기
    const year = currentDate.getFullYear(); // 년도
    const month = currentDate.getMonth() + 1; // 월 (0부터 시작하므로 1을 더함)
    const day = currentDate.getDate(); // 일
    const hours = currentDate.getHours(); // 시간
    const minutes = currentDate.getMinutes(); // 분
    const seconds = currentDate.getSeconds(); // 초

    // 날짜와 시간을 YYYY-MM-DD HH:mm:ss 형식으로 포맷팅
    const formattedDate = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;

    return formattedDate;
}

