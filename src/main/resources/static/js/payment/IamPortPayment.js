document.write('<script src="/js/payment/IamPortFunction.js"></script>');

const userCode = "imp42463350";
IMP.init(userCode);
randomOrderNumber();

function requestPayKGincis() {
    const paymentInfo = {
     paymentPrice : parseInt(document.getElementById("totalPriceSpan").textContent, 10),
     bookName : document.getElementById("bookNameSpan").textContent,
     buyerName : document.getElementById("buyerName").textContent,
     receiverName : document.getElementById("receiverName").value,
     receiverAddress : document.getElementById("receiverAddress").value,
     receiverPhoneNumber : document.getElementById("receiverPhoneNumber").value,
     bookId : $("#bookId").val(),
     paymentBookCount : $("#quantity").val(),
     buyerEmail : $("#buyerEmail").val(),
     orderNumber : 'o' + new Date().getTime() + randomOrderNumber(),
     paymentTime : paymentSuccessTime(),
    }
    IMP.request_pay({
        pg: "html5_inicis",
        pay_method: "card",
        merchant_uid: paymentInfo.orderNumber,
        name: paymentInfo.bookName,
        amount: paymentInfo.paymentPrice,
        buyer_name: paymentInfo.buyerName,
        buyer_email: paymentInfo.buyerEmail
    }, function (rsp) {
        const imp_uid = rsp.imp_uid;
        $.ajax({
            type: 'POST',
            url: '/verifyIamport/' + imp_uid
        }).done(function (data) {
            if (rsp.paid_amount == data.response.amount) {
                paymentInfoSave(paymentInfo,imp_uid);
            }
        });
    });
}


    function requestPayToss() {
        const clientKey = 'test_ck_DpexMgkW36mbZ2Y5wD48GbR5ozO0'
        const tossPayments = TossPayments(clientKey)
        const paymentInfo = {
            paymentPrice : parseInt(document.getElementById("totalPriceSpan").textContent, 10),
            bookName : document.getElementById("bookNameSpan").textContent,
            buyerName : document.getElementById("buyerName").textContent,
            receiverName : document.getElementById("receiverName").value,
            receiverAddress : document.getElementById("receiverAddress").value,
            receiverPhoneNumber : document.getElementById("receiverPhoneNumber").value,
            bookId : $("#bookId").val(),
            paymentBookCount : $("#quantity").val(),
            buyerEmail : $("#buyerEmail").val(),
            orderNumber : 'o' + new Date().getTime() + randomOrderNumber(),
            paymentTime : paymentSuccessTime()
        }
        $.ajax({
            type: 'POST',
            url: '/TossPay/payment/1',
            data: JSON.stringify(paymentInfo),
            contentType: 'application/json',
            success: function (response) {
                console.log('Ajax request successful.');
            },
            error: function (error) {
                console.error('Ajax request failed:', error);
            }
        });
        tossPayments.requestPayment('카드', {
            amount: paymentInfo.paymentPrice,
            orderId: paymentInfo.orderNumber,
            orderName:paymentInfo.bookName,
            customerName: 'ourBook(아워북)',
            successUrl: 'http://localhost:8080/TossPay/validate',
            failUrl: 'http://localhost:8080/OurBook/1'
        });
    }
