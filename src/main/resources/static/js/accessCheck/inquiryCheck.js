$(document).ready(function() {
    $("#InquiryLoginCheck").click(function(event) {
        event.preventDefault();
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
    $("#AlreadyAnswerCheck").click(function(event) {
        event.preventDefault();
        const writer = $("#inquiryEmail").val();
        const inquiryNumber = $("#inquiryNumber").val();
        console.log(writer);
        $.ajax({
            url: "/checkAlreadyAnswer/"+inquiryNumber,
            type: "POST",
            async: true,
            success: function(response) {
               InquiryMeCheck(writer,inquiryNumber);
            },
            error: function(error) {
                alert("답변이 등록된 문의는 수정할 수 없습니다.");
                event.preventDefault();
            }
        });
    });
});

function InquiryMeCheck(writer,inquiryNumber){
    $.ajax({
        url: "/checkMe/"+writer,
        type: "POST",
        async: true,
        success: function(response) {
            window.location.href = "/OurBook/inquiry/edit/"+inquiryNumber;
        },
        error: function(error) {
            alert("권한이 없습니다.");
            event.preventDefault();
        }
    });
}


$(document).ready(function() {
    $(".allowAuthorizedUsersOnly").click(function(event) {
        event.preventDefault();
        const sequence = $(this).closest("tr").find("#sequence").text();
        const writer =$(this).closest("tr").find("#inquiryEmail").val();
        console.log(writer)

        $.ajax({
            url: "/checkAuthorizedUser/"+writer,
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


$(document).ready(function() {
    $("#allowAdminUserOnly").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/checkAdminUser",
            type: "POST",
            async: true,
            success: function(response) {
                $("#deleteInquiryAnswer").submit();
            },
            error: function(error) {
                alert("권한이 없습니다.");
                event.preventDefault();
            }
        });
    });
});