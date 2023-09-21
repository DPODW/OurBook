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
            console.log("토큰 발급 성공")
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
                console.log("결제 취소 완료");
            },
            error: function (res) {
                console.log("결제 취소 실패");
            }
        });
    });
}



/** 결제 정보 DB 저장 함수 **/
function paymentInfoSave(paymentInfo,imp_uid){

    $.ajax({
        type: 'POST',
        url: '/OurBook/payment/1',
        data: JSON.stringify(paymentInfo),
        contentType: 'application/json',
        success: function (response) {
            console.log("결제 정보 저장 완료")
        },
        error: function (error) {
            paymentCancel(paymentInfo,imp_uid);
            alert("결제 실패. 관리자에게 문의하세요(3)");
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