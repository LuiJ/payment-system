<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="userAccountsUrl" value="/user/accounts"></c:url>
<c:url var="userCardsUrl" value="/user/cards"></c:url>
<c:url var="userPaymentsUrl" value="/user/payments"></c:url>
<c:url var="userOperationssUrl" value="/user/operations"></c:url>

<a href="${userAccountsUrl}" class="item <c:if test="${page == 'accounts'}">active</c:if>">Accounts</a>
<a href="${userCardsUrl}" class="item <c:if test="${page == 'cards'}">active</c:if>">Cards</a>
<a href="${userPaymentsUrl}" class="item <c:if test="${page == 'payments'}">active</c:if>">Make Payment</a>
<a href="${userOperationssUrl}" class="item <c:if test="${page == 'operations'}">active</c:if>">Operations History</a>        
        