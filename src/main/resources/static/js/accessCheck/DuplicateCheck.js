$(document).ready(function() {
    $("#check").click(function(event) {
        event.preventDefault();

        const idData = {
            memberId: $("#ID").val()
        };

        const EmailData = {
            memberEmail: $("#EMAIL").val()
        };
        console.log(EmailData)
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
                        $("form.validation-form").attr("action", "/OurBook/join");
                        $("form.validation-form").submit();
                    },
                    error: function() {
                        alert("이미 존재하는 이메일 입니다.");
                        event.preventDefault();
                    }
                });
            },
            error: function() {
                alert("이미 존재하는 아이디 입니다.");
                event.preventDefault();
            }
        });
    });
});




$(document).ready(function() {
    $("#checkEditEmail").click(function(event) {
        event.preventDefault();
        const EmailData = {
            memberEditId: $("#ID").val(),
            memberEditEmail: $("#EMAIL").val()
        };
        console.log(EmailData)
        $.ajax({
            url: "/checkEditEmail",
            type: "POST",
            async: true,
            data: JSON.stringify(EmailData),
            contentType: 'application/json',
            success: function(response) {
                console.log("dd")
                $("#updateEmailCheck").submit();

            },
            error: function(error) {
                alert("이미 존재하는 이메일 입니다.");
                event.preventDefault();
            }
        });
    });
});