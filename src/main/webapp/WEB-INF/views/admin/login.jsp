<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/j_spring_security_check"></c:url>

<div id="common-content">
    <h2>Admin Log In:</h2>
    <form action="${loginUrl}" method="POST" id="login-form">
        <c:if test="${not empty errorMessage}">
            <p class="error-msg">${errorMessage}</p>
        </c:if>
        <div class="input-wrap">
            <input type="text" placeholder="Login" name="j_username"/> 
        </div>
        <div class="input-wrap">
            <input type="password" placeholder="Password" name="j_password"/>
        </div> 
        <input type="submit" name="submit" class="btn" id="login-form-btn" value="Submit" />          
    </form>
</div>