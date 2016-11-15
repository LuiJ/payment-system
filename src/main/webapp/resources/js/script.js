$('input[type="submit"], a').click(function(){
    $("#preloader").show();
});

function chooseCard(obj){
    var expirationDate = $(obj).find("option:selected")
                               .attr("data-expiration-date");
    var amountAvailable = $(obj).find("option:selected")
                                .attr("data-available-amount");
    var accountId = $(obj).find("option:selected").val();
    if (accountId > -1){
        $("#expiration-date span").html(expirationDate);
        $("#amount-available span").html(amountAvailable);
        $('#payment-part input[type="text"]').val("");
        $("#payment-part").show();
    }
    else {
        $("#payment-part").hide();
    }
    $(".success-msg").hide();
}

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

function sendPaymentForm(obj){
    var accountId = $('select[name="accountId"]').val();
    var amount = $('input[name="amount"]').val();
    var number = "" + $('input[name="number"]').val();
    var trimmedNumber = number.replace(/\s/g,'');
    if (amount != "" && accountId > 0 && 
        trimmedNumber != "" && trimmedNumber.length == 16)
    {
        $(obj).siblings('input[type="submit"]').click();
    }
}

