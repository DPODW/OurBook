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
        buyer_email: "",
        m_redirect_url: "",
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
    });

}
