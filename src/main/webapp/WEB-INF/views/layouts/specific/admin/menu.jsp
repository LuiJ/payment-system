<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="adminAccountsUrl" value="/admin/accounts"></c:url>
<c:url var="adminCardsUrl" value="/admin/cards"></c:url>
<c:url var="adminOperationssUrl" value="/admin/operations"></c:url>

<a href="${adminAccountsUrl}" class="item <c:if test="${page == 'accounts'}">active</c:if>"><fmt:message key="menu.link.accounts"/></a>
<a href="${adminCardsUrl}" class="item <c:if test="${page == 'cards'}">active</c:if>"><fmt:message key="menu.link.cards"/></a>
<a href="${adminOperationssUrl}" class="item <c:if test="${page == 'operations'}">active</c:if>"><fmt:message key="menu.link.operationHistory"/></a>  
        