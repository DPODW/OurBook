const userCode = "imp42463350";
IMP.init(userCode);

function randomOrderNumber() {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'; // 영어 대문자 알파벳 (3문자 영어 난수)
    let randomOrderNumber = '';

    for (let i = 0; i < 3; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        randomOrderNumber += characters.charAt(randomIndex);
    }
    return randomOrderNumber;
}

        function requestPayKGincis() {
            const bookPrice = parseInt(document.getElementById("totalPriceSpan").textContent, 10);
            const bookName = document.getElementById("bookNameSpan").textContent;
            const buyerName = document.getElementById("buyerName").textContent;
            const merchant_uid = 'o'+new Date().getTime()+randomOrderNumber();
            IMP.request_pay({
                pg: "html5_inicis",
                pay_method: "card",
                merchant_uid: merchant_uid,
                name: bookName,
                amount: bookPrice ,
                buyer_name: buyerName,
                buyer_email: $("#buyerEmail").val(),
                m_redirect_url: "",
            }, function (rsp) { // callback
                $.ajax({
                    type: 'POST',
                    url: '/verifyIamport/' + rsp.imp_uid
                }).done(function(data) {
                    console.log(data);
                    if(rsp.paid_amount == data.response.amount){
                        const paymentInfoCheck = {
                            orderNumber: merchant_uid
                        }
                        $.ajax({
                            type: 'POST',
                            url: '/OurBook/payment/check',
                            data: JSON.stringify(paymentInfoCheck),
                            contentType: 'application/json',
                            success: function (response){
                                const paymentInfo = {
                                    orderNumber: merchant_uid,
                                    bookName: bookName,
                                    bookId: $("#bookId").val(),
                                    paymentBookCount: $("#quantity").val(),
                                    paymentPrice: bookPrice,
                                    buyerEmail:$("#buyerEmail").val(),
                                    buyerName: buyerName,
                                    receiverName: document.getElementById("receiverName").value,
                                    receiverAddress: document.getElementById("receiverAddress").value,
                                    receiverPhoneNumber: document.getElementById("receiverPhoneNumber").value,
                                };
                                $.ajax({
                                    type: 'POST',
                                    url: '/OurBook/payment/1',
                                    data: JSON.stringify(paymentInfo),
                                    contentType: 'application/json',
                                    success: function (response) {
                                    },
                                    error: function (error) {
                                        alert("결제 실패. 관리자에게 문의하세요(3)");
                                        console.error(error);
                                    }
                                });
                            },
                            error: function (error) {
                                alert("결제 실패. 관리자에게 문의하세요(2)");
                                console.error(error);
                            }
                        })
                    } else {
                        alert("결제 실패. 관리자에게 문의하세요(1)");
                    }
                });
            });
        }



function requestPayToss() {
    const bookPrice = parseInt(document.getElementById("totalPriceSpan").textContent, 10);
    const bookName = document.getElementById("bookNameSpan").textContent;
    const buyerName = document.getElementById("buyerName").textContent;
    const merchant_uid = 'o'+new Date().getTime()+randomOrderNumber();
    IMP.request_pay({
        pg: "tosspayments.iamporttest_3",
        pay_method: "card",
        merchant_uid: merchant_uid,
        name: bookName,
        amount: bookPrice,
        buyer_name: buyerName,
        m_redirect_url: "",
    }, function (rsp) { // callback
        $.ajax({
            type: 'POST',
            url: '/verifyIamport/' + rsp.imp_uid
        }).done(function(data) {
            console.log(data);
            if(rsp.paid_amount == data.response.amount){
                const paymentInfoCheck = {
                    orderNumber: merchant_uid
                }
                $.ajax({
                    type: 'POST',
                    url: '/OurBook/payment/check',
                    data: JSON.stringify(paymentInfoCheck),
                    contentType: 'application/json',
                    success: function (response){
                        const paymentInfo = {
                            orderNumber: merchant_uid,
                            bookName: bookName,
                            bookId: $("#bookId").val(),
                            paymentBookCount: $("#quantity").val(),
                            paymentPrice: bookPrice,
                            buyerEmail:$("#buyerEmail").val(),
                            buyerName: buyerName,
                            receiverName: document.getElementById("receiverName").value,
                            receiverAddress: document.getElementById("receiverAddress").value,
                            receiverPhoneNumber: document.getElementById("receiverPhoneNumber").value,
                        };
                        $.ajax({
                            type: 'POST',
                            url: '/OurBook/payment/1',
                            data: JSON.stringify(paymentInfo),
                            contentType: 'application/json',
                            success: function (response) {
                            },
                            error: function (error) {
                                alert("결제 실패. 관리자에게 문의하세요(3)");
                                console.error(error);
                            }
                        });
                    },
                    error: function (error) {
                        alert("결제 실패. 관리자에게 문의하세요(2)");
                        console.error(error);
                    }
                })
            } else {
                alert("결제 실패. 관리자에게 문의하세요(1)");
            }
        });
    });
}