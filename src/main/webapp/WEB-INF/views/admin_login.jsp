<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payments System - Admin</title>
    </head> 

    <body>
        
        <h1>Log In:</h1>
        
        <c:if test="${not empty errorMessage}">
            <h3 style="color: #b6531d;">${errorMessage}</h3>
        </c:if>
                    
        <form method="POST" action="admin">
            <table>
                <tr>
                    <td>Login:</td>
                    <td>
                        <input type="text" name="login" value="${login}"/> 
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <input type="password" name="password" value="${password}"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <input type="submit" value="Log in" />
                    </td>
                </tr>
            </table>            
        </form>
                
    </body>
</html>