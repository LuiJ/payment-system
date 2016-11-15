<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="userPaymentUrl" value="/user/payment"></c:url>

<h3>Make payment or money transfer:</h3>   

<form action="${userPaymentUrl}" method="POST" id="payment-form">
    <c:if test="${not empty successMessage}">
        <p class="success-msg">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p class="error-msg">${errorMessage}</p>
    </c:if>
    
    <div class="select-wrap">
        <select id="chose-card" name="accountId" onchange="chooseCard(this);">
            <option value="-1">Chose the card</option>
            <c:if test="${not empty cards}">
                <c:forEach items="${cards}" var="card">
                    <fmt:formatDate pattern="MM / yyyy" value="${card.expirationDate}" var="formattedDate"/>
                    <option class="cand-number" data-available-amount="${card.account.amount}" data-expiration-date="${formattedDate}" value="${card.accountId}">${card.number}</option>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div id="payment-part">
        <p id="expiration-date">
            <b>Expiration Date:</b>&nbsp;
            <span></span>
        </p>
        <p id="amount-available">
            <b>Amount available:</b>&nbsp;
            <span></span>
        </p>
        <div class="input-wrap">
            <input type="text" placeholder="Card or Account Nubmer" name="number" class="number"/>             
        </div>
        <p class="note"><b>Note:</b> Enter Card or Account Nubmer for which you want to pay or transfer money</p>
        <div class="input-wrap">
            <input type="text" placeholder="Amount of money" name="amount" class="amount"/>             
        </div>
        <p class="note"><b>Note:</b> Amount of money you want to pay or transfer.</p>
        <input type="submit" name="submit" value="1" class="hidden"/> 
        <div class="btn center" id="payment-form-btn" onclick="sendPaymentForm(this);">Go</div>
    </div>          
</form>
   
    
<script type="text/javascript">
    
    $(".number").mask('0000 0000 0000 0000');
    $(".amount").mask('#0.00', {reverse: true});
    
    $(document).keypress(function(e) {
        if(e.which == 13) {
            sendPaymentForm($("#payment-form-btn"));
            return false;
        }
    });
</script>