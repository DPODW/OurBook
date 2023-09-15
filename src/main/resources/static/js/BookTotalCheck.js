//책 합계 계산 기능 js
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

//수량 input 태그 강제 편집 제한 기능 JS
function restrictInput(input) {
    // 입력값이 유효한지 확인
    if (isNaN(input.value) || input.value < 1 || input.value > 50) {
        input.value = 1; // 유효하지 않은 값이면 1로 설정
    }
}


//초기 수량을 바로 계산 합계에 출력시켜주는 기능
    function updateTotalPrice() {
    var unitPrice = parseFloat($("#unitPrice").text());
    var newQuantity = parseInt($("#quantity").val());
    var newTotalPrice = unitPrice * newQuantity;
    $("#totalPriceSpan").text(newTotalPrice + '원');
}

    // 페이지 로딩 시 초기화
    $(document).ready(function() {
    updateTotalPrice(); // 초기 가격 계산
});
