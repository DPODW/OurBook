$(document).ready(function() {
    $("#InquiryLoginCheck").click(function(event) {
        event.preventDefault();
        // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/checkLogin",
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/inquiry/form";
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 서비스 입니다.");
                event.preventDefault();
            }
        });
    });
});

$(document).ready(function() {
    $("#InquiryListLoginCheck").click(function(event) {
        event.preventDefault();
        // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/checkLogin",
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/inquiry/history";
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 서비스 입니다.");
                event.preventDefault();
            }
        });
    });
});



$(document).ready(function() {
    $(".allowAuthorizedUsersOnly").click(function(event) {
        event.preventDefault();
        const sequence = $(this).closest("tr").find("#sequence").text();
        const inquiryWriter = $(this).closest("tr").find("#inquiryWriter").text();

        $.ajax({
            url: "/checkAuthorizedUser/"+inquiryWriter,
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/inquiry/"+sequence;
            },
            error: function(error) {
                alert("권한이 없습니다.");
                event.preventDefault();
            }
        });
    });
});