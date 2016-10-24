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



function handleNumberInput(input){
    var inputValue = "" + $(input).val();
    if (inputValue.length == 0){
        return;
    }
    var lastChar = inputValue.charAt(inputValue.length - 1);
    var digitsRegExp = new RegExp(/^\d+$/);
    var isDigit = digitsRegExp.test(lastChar);
    if (!isDigit || inputValue.length > 16){
        var correctValue = inputValue.substr(0, inputValue.length - 1)
        $(input).val(correctValue);
    }
}


