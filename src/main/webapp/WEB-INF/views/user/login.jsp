<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="loginUrl" value="/j_spring_security_check"></c:url>


<h2><fmt:message key="text.loginFormLabel"/>:</h2>   
<form action="${loginUrl}" method="POST" id="login-form">
    <c:if test="${not empty errorMessage}">
        <p class="error-msg">${errorMessage}</p>
    </c:if>
    <div class="input-wrap">
        <input type="text" placeholder="<fmt:message key="input.label.login"/>" name="j_username"/> 
    </div>
    <div class="input-wrap">
        <input type="password" placeholder="<fmt:message key="input.label.password"/>" name="j_password"/>
    </div> 
    <input type="submit" name="submit" class="btn" id="login-form-btn" value="<fmt:message key="button.label.login"/>" />          
</form>