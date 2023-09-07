$(document).ready(function() {
    $("#loginCheck").click(function(event) {
        event.preventDefault(); // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/checkLogin",
            type: "POST",
            async: true,
            success: function(response) {
                // Ajax 요청이 성공하면 form을 서브밋합니다.
                $("#addToCartForm").submit();
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 페이지 입니다.");
                event.preventDefault();
            }
        });
    });
});