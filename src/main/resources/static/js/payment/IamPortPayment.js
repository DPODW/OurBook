document.write('<script src="/js/payment/PaymentFunction.js"></script>');

const userCode = "imp42463350";
IMP.init(userCode);
randomOrderNumber();

function requestPayKGincis() {
    const detailAddress = document.getElementById("detailAddress").value;
    const paymentInfo = {
     paymentPrice : parseInt(document.getElementById("totalPriceSpan").textContent, 10),
     bookName : document.getElementById("bookNameSpan").textContent,
     buyerName : document.getElementById("buyerName").textContent,
     receiverName : document.getElementById("receiverName").value,
     receiverPostCode : document.getElementById("receiverPostCode").value,
     receiverAddress : document.getElementById("receiverAddress").value + ' ' +detailAddress,
     receiverPhoneNumber : document.getElementById("receiverPhoneNumber").value,
     bookId : $("#bookId").val(),
     paymentBookCount : $("#quantity").val(),
     buyerEmail : $("#buyerEmail").val(),
     orderNumber : 'o' + new Date().getTime() + randomOrderNumber(),
     paymentType : 'NHPay'
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
                KGpaymentInfoSave(paymentInfo,imp_uid);
            }
        });
    });
}

