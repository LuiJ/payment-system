<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="homeUrl" value="/"></c:url>
<c:url var="userAccountsUrl" value="/user/accounts"></c:url>

<div id="header">
    <a href="${homeUrl}" id="logo">Payment System</a>    
    <a href="${userAccountsUrl}" class="btn" id="h-login-btn">Login</a>
    <div class="clear"></div>
</div>
        
        