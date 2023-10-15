document.write('<script src="/js/payment/PaymentFunction.js"></script>');

randomOrderNumber();

function requestPayToss() {
    const clientKey = 'test_ck_DpexMgkW36mbZ2Y5wD48GbR5ozO0'
    const tossPayments = TossPayments(clientKey)
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
        paymentTime : paymentSuccessTime()
    }
    TossPaymentInfoSave(paymentInfo);
    tossPayments.requestPayment('카드', {
        amount: paymentInfo.paymentPrice,
        orderId: paymentInfo.orderNumber,
        orderName:paymentInfo.bookName,
        customerName: 'ourBook(아워북)',
        successUrl: 'http://localhost:8080/TossPay/validate',
        failUrl: 'http://localhost:8080/OurBook/book/info/payment/fail'
    });
}
