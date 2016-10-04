<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payments System</title>
    </head> 

    <body>
        
        <h1>Welcome to your Payment System</h1>
        
        <c:url var="userAccountsUrl" value="/user/accounts"></c:url>
        <h3>Please, <a href="${userAccountsUrl}"><i>Log in</i></a></h3>
                
    </body>
</html>