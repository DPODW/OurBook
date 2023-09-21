$(document).ready(function() {
    $(".login-check").click(function(event) {
        event.preventDefault();
        const buttonId = $(this).attr("id");
        // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/loginCheck",
            type: "POST",
            async: true,
            success: function(response) {
                if(buttonId === "buyButton"){
                    $("#paymentInfo").submit();
                }else
                    window.location.href = "/OurBook/book/info/cart";
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 서비스 입니다.");
                event.preventDefault();
            }
        });
    });
});