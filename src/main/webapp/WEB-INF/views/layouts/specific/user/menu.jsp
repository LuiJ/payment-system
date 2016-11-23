<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="userAccountsUrl" value="/user/accounts"></c:url>
<c:url var="userCardsUrl" value="/user/cards"></c:url>
<c:url var="userPaymentsUrl" value="/user/payment"></c:url>
<c:url var="userOperationssUrl" value="/user/operations"></c:url>

<a href="${userAccountsUrl}" class="item <c:if test="${page == 'accounts'}">active</c:if>"><fmt:message key="menu.link.accounts"/></a>
<a href="${userCardsUrl}" class="item <c:if test="${page == 'cards'}">active</c:if>"><fmt:message key="menu.link.cards"/></a>
<a href="${userPaymentsUrl}" class="item <c:if test="${page == 'payment'}">active</c:if>"><fmt:message key="menu.link.makePayment"/></a>
<a href="${userOperationssUrl}" class="item <c:if test="${page == 'operations'}">active</c:if>"><fmt:message key="menu.link.operationHistory"/></a>  
        