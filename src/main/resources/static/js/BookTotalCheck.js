$(document).ready(function() {
    // 초기 값 설정
    var unitPrice = parseFloat($("#unitPrice").text());

    // 초기 값으로 설정
    $("#totalPriceSpan").text(unitPrice + '원');

    // 구매 수량 변경 이벤트 핸들러
    $("#quantity").on("input", function() {
        // 새로운 수량 가져오기
        var newQuantity = parseInt($(this).val());

        // 새로운 합계 계산
        var newTotalPrice = unitPrice * newQuantity;

        // 화면에 업데이트
        $("#totalPriceSpan").text(newTotalPrice + '원');
    });
});

