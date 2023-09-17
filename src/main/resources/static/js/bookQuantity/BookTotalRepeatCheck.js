document.addEventListener("DOMContentLoaded", function () {
    const quantityInputs = document.querySelectorAll(".quantity-input");

    quantityInputs.forEach(function (input) {
        const index = input.getAttribute("data-index");
        const bookPriceElement = document.getElementById("book-price-" + index);
        const totalPriceElement = document.getElementById("total-price-" + index);

        // 초기 가격 설정
        let bookPrice = parseInt(bookPriceElement.textContent);
        let quantity = parseInt(input.value);
        let totalPrice = bookPrice * quantity;
        totalPriceElement.textContent = totalPrice + "원";

        // 수량 변경 이벤트 핸들러
        input.addEventListener("input", function () {
            quantity = parseInt(input.value);
            totalPrice = bookPrice * quantity;
            totalPriceElement.textContent = totalPrice + "원";
        });
    });
});