<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="userPaymentUrl" value="/user/payment"></c:url>

<h3>Operation Error</h3>   

<c:if test="${not empty errorMessage}">
    <p class="error-msg">${errorMessage}</p>
</c:if>

<br/>    
<a href="${userPaymentUrl}" class="btn center m-t">Back to Payments Page</a>