<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/j_spring_security_logout" var="logoutUrl" />

<div id="header">
    <a href="#" id="logo">Payment System</a>
    <c:if test="${not empty loggedUser}">        
        <form method="POST" action="${logoutUrl}" id="h-logout-form">
            <input class="btn" id="h-logout-btn" type="submit" name="logout" value="Logout" />
        </form>
        <p id="welcome">Welcome, ${loggedUser.firstName}</p>
        <div class="clear"></div>
    </c:if>    
</div>
        
        