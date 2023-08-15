$(document).ready(function() {
    $("#check").click(function(event) {
        event.preventDefault();

        const idData = {
            sellerId: $("#ID").val()
        };

        const EmailData = {
            sellerEmail: $("#EMAIL").val()
        };

        $.ajax({
            url: "/checkId",
            type: "POST",
            async: true,
            data: JSON.stringify(idData),
            contentType: 'application/json',
            success: function() {
                // 첫 번째 AJAX 호출이 성공적으로 처리되면 두 번째 AJAX 호출 실행
                $.ajax({
                    url: "/checkEmail",
                    type: "POST",
                    async: true,
                    data: JSON.stringify(EmailData),
                    contentType: 'application/json',
                    success: function() {
                        $("form.validation-form").attr("action", "/OurBook/2");
                        $("form.validation-form").submit();
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert("이미 존재하는 이메일 입니다.");
                        event.preventDefault();
                    }
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("이미 존재하는 아이디 입니다.");
                event.preventDefault();
            }
        });
    });
});