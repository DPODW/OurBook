$(document).ready(function() {
    $("#PurchaseRequestLoginCheck").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/checkLogin",
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


$(document).ready(function() {
    $("#PurchaseRequestListLoginCheck").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: "/checkLogin",
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/market/purchase/request/history"
            },
            error: function(error) {
                alert("로그인 후 이용 가능한 서비스 입니다.");
                event.preventDefault();
            }
        });
    });
});


$(document).ready(function() {
    $("#MarketMeCheck").click(function(event) {
        event.preventDefault();
        const writerEmail = $("#UploaderEmail").val();
        const marketNumber = $("#Sequence").val();

        if(writerEmail == ""){
            alert("권한이 없습니다.");
            return false;
        }
        $.ajax({
            url: "/checkMe/"+writerEmail,
            type: "POST",
            async: true,
            success: function(response) {
                window.location.href = "/OurBook/market/sale/edit/"+marketNumber;
            },
            error: function(error) {
                alert("권한이 없습니다.");
                event.preventDefault();
            }
        });
    });
});

$(document).ready(function() {
    $("#MarketAuthorizedCheck").click(function(event) {
        event.preventDefault();
        const writer = $("#UploaderEmail").val();
        console.log(writer)
        if(writer == ""){
            alert("권한이 없습니다(1).");
            return false;
        }
        $.ajax({
            url: "/checkAuthorizedUser/"+writer,
            type: "POST",
            async: true,
            success: function(response) {
                $("#deleteMarketBook").submit();
            },
            error: function(error) {
                alert("권한이 없습니다.");
                event.preventDefault();
            }
        });
    });
});

