$(document).ready(function() {
    $("#PurchaseRequestLoginCheck").click(function(event) {
        event.preventDefault();
        // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/loginCheck",
            type: "POST",
            async: true,
            success: function(response) {
                $("#PurchaseRequest").submit();
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 서비스 입니다.");
                event.preventDefault();
            }
        });
    });
});