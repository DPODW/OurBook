/** 동일한 주문번호가 DB에 존재하는지 검증 하는 함수 **/
function handlePaymentSuccess(paymentInfo,imp_uid) {
    const paymentInfoCheck = {
        orderNumber: paymentInfo.merchant_uid
    };

    $.ajax({
        type: 'POST',
        url: '/OurBook/payment/check',
        data: JSON.stringify(paymentInfoCheck),
        contentType: 'application/json',
        success: function (response) {
            paymentInfoSave(paymentInfo,imp_uid);
        },
        error: function (error) {
            paymentCancel(paymentInfo,imp_uid);
            alert("결제 실패. 관리자에게 문의하세요(2)");
        }
    });
}

/** 아임포트와 인증된 통신을 위한 인증 토큰 발급 함수 **/
function getAccessTokens(callback) {
    $.ajax({
        type: "GET",
        url: "/iamports/accessToken",
        success: function (accessToken) {
            callback(accessToken);
        },
        error: function (error) {
            console.log("토큰 발급 실패")
        }
    });
}


/** 결제 취소 함수 **/
function paymentCancel(paymentInfo,imp_uid) {
    getAccessTokens(function(accessToken) {
        const cancelData = {
            accessToken: accessToken,
            imp_uid: imp_uid,
            reason: ':결제 오류',
            paymentPrice: paymentInfo.paymentPrice
        };
        $.ajax({
            type: "POST",
            url: '/iamports/cancel',
            data: JSON.stringify(cancelData),
            contentType: 'application/json',
            success: function (res) {
            },
            error: function (res) {
                alert("결제 취소 실패. 관리자에게 문의하세요(5)");
            }
        });
    });
}



/** 결제 정보 DB 저장 함수 **/
function paymentInfoSave(paymentInfo,imp_uid){
    paymentInfo.paymentNumber = imp_uid;
    $.ajax({
        type: 'POST',
        url: '/OurBook/payment/1',
        data: JSON.stringify(paymentInfo),
        contentType: 'application/json',
        success: function (response) {
            const orderNumber = paymentInfo.orderNumber;
            paymentResult(paymentInfo,imp_uid,orderNumber);
        },
        error: function (error) {
            paymentCancel(paymentInfo,imp_uid);
            alert("결제 실패. 관리자에게 문의하세요(3)");
        }
    });
}

/** 최종 주문 내역 반환 함수 (영수증 출력 개념과 동일) **/
function paymentResult(paymentInfo,imp_uid,orderNumber){
    $.ajax({
        type: 'POST',
        url: '/OurBook/book/info/payment/result/' + orderNumber,
        data: JSON.stringify(paymentInfo),
        contentType: 'application/json',
        success: function (response) {
            window.location.href = this.url;
        },
        error: function (error) {
            paymentCancel(paymentInfo,imp_uid);
            alert("결제 실패. 관리자에게 문의하세요(4)");
        }
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

