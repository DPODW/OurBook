$(document).ready(function() {
    $("#InquiryLoginCheck").click(function(event) {
        event.preventDefault();
        // 폼의 기본 동작인 페이지 이동을 막습니다.
        $.ajax({
            url: "/loginCheck",
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
    $(".inquiryWriterSameCheck").click(function(event) {
        event.preventDefault();
        const inquiryWriter = document.getElementById("inquiryWriter").textContent;
        const sequence = document.getElementById("sequence").value;
        $.ajax({
            url: "/WriterSameCheck/"+inquiryWriter,
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/inquiry/"+sequence;
            },
            error: function(error) {
                alert("본인이 등록한 문의 글만 접근할 수 있습니다");
                event.preventDefault();
            }
        });
    });
});