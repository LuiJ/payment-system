$('input[type="submit"], a').click(function(){
    $("#preloader").show();
});

$("#chose-card").unbind().change(function(){
    var amountAvailable = $(this).val();
    if (amountAvailable > 0){
        $("#amount-available span").html(amountAvailable);
        $("#payment-part").show();
    }
    else {
        $("#payment-part").hide();
    }
});

function separateCardNumber(){
    var blocksWithCardNumber = $(".cand-number");
    $.each(blocksWithCardNumber, function(i, item){
        var number = "" + $(item).html();
        var separatedNumber = number.substr(0, 4) + " " +
                              number.substr(4, 4) + " " +
                              number.substr(8, 4) + " " +
                              number.substr(12, 4);
        $(item).html(separatedNumber);
    });
}

separateCardNumber();

$(".number").mask('0000 0000 0000 0000');
$(".amount").mask('#0.00', {reverse: true});

