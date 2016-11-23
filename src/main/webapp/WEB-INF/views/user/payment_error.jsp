<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="userPaymentUrl" value="/user/payment"></c:url>

<h3><fmt:message key="text.operationError"/></h3>   

<c:if test="${not empty errorMessage}">
    <p class="error-msg">${errorMessage}</p>
</c:if>

<br/>    
<a href="${userPaymentUrl}" class="btn center m-t"><fmt:message key="button.label.backToPayments"/></a>