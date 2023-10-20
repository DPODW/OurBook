$(document).ready(function() {
    $("#roleCheck").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/roleCheck",
            type: "POST",
            async: true,
            success: function(response) {
                    window.location.href = "/OurBook/market/sale";
            },
            error: function(error) {
                alert("판매자 권한의 회원만 접근할 수 있습니다.");
                event.preventDefault();
            }
        });
    });
});