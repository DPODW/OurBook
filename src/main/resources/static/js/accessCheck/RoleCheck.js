$(document).ready(function() {
    $("#EditMember").click(function(event) {
        event.preventDefault(); // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/checkNaver",
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/myInfo/Member";
            },
            error: function(error) {
                alert("네이버 회원은 가입 정보 수정이 불가능 합니다. 일반 회원으로 가입 부탁 드립니다.");
                event.preventDefault();
            }
        });
    });
});


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