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
                handlePaymentSuccess(paymentInfo,imp_uid);
            }
        });
    });
}





    function requestPayToss() {
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
        IMP.request_pay({
            pg: "tosspayments.iamporttest_3",
            pay_method: "card",
            merchant_uid: paymentInfo.orderNumber,
            name: paymentInfo.bookName,
            amount: paymentInfo.paymentPrice,
            buyer_name: paymentInfo.buyerName,
        }, function (rsp) {
            const imp_uid = rsp.imp_uid;
            $.ajax({
                type: 'POST',
                url: '/verifyIamport/' + imp_uid
            }).done(function (data) {
                if (rsp.paid_amount == data.response.amount) {
                    handlePaymentSuccess(paymentInfo,imp_uid);
                }
            });
        });
    }