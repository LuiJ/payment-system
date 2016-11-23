$('input[type="submit"], a').click(function(){
    $("#preloader").show();
});


function chooseCard(obj){
    var expirationDate = $(obj).find("option:selected")
                               .attr("data-expiration-date");
    var amountAvailable = $(obj).find("option:selected")
                                .attr("data-available-amount");
    var noneSelectedCards = $(obj).find("option:selected").siblings("option");
    var accountId = $(obj).find("option:selected").val();
    if (accountId > -1){
        $("#expiration-date span").html(expirationDate);
        $("#amount-available span").html(amountAvailable);
        $('#payment-part input[type="text"]').val("");
        $("#payment-part").show();  
        initCardToTransferSelect(accountId, noneSelectedCards);             
    }
    else {
        $("#payment-part").hide();
    }
    $(".success-msg").hide();
}


function separateCardNumber(){
    var blocksWithCardNumber = $(".cand-number");
    $.each(blocksWithCardNumber, function(){
        var number = "" + $(this).html();
        var separatedNumber = number.substr(0, 4) + " " +
                              number.substr(4, 4) + " " +
                              number.substr(8, 4) + " " +
                              number.substr(12, 4);
        $(this).html(separatedNumber);
    });
}


separateCardNumber();


function sendPaymentForm(obj){
    var accountId = $('select[name="accountId"]').val();
    var amount = $('input[name="amount"]').val();
    var number = "" + $('input[name="number"]').val();
    if (number == "undefined"){
        number = "" + $("#card-to-transfer").find("option:selected").html();
    }
    var trimmedNumber = number.replace(/\s/g,'');
    if (amount != "" && accountId > 0 && 
        trimmedNumber != "" && trimmedNumber.length == 16)
    {
        $(obj).siblings('input[type="submit"]').click();
    }
}


function initCardToTransferSelect(selectedAccountId, noneSelectedCards){
    $("#card-select-wrap select").html("");    
    var options = $(noneSelectedCards).clone();
    $.each(options, function(){
        var accountId = $(this).val();
        if (selectedAccountId != accountId){
            var cardNumber = $(this).html();
            $(this).val(cardNumber);
            $("#card-select-wrap select").append(this);
        }
    }); 
}


function chooseOperationType(obj){
    var operationType = $(obj).val();
    if (operationType == "PAYMENT"){
        $("#card-select-wrap select").attr("name", "");
        $("#card-select-wrap").hide();
        $("#number-input-wrap input").attr("name", "number");
        $("#number-input-wrap").show();
    }
    if (operationType == "TRANSFER"){
        $("#number-input-wrap input").attr("name", "");
        $("#number-input-wrap").hide();
        $("#card-select-wrap select").attr("name", "number");
        $("#card-select-wrap").show();
    }
}

function changeLanguage(obj){
    $(obj).closest("form").submit();
}

