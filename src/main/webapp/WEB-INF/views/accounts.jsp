<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payments System</title>
    </head> 

    <body>
        
        <h1>Accounts List:</h1>
            
        <c:choose>
            
            <c:when test="${not empty accounts}">                
                <table cellspacing="0" cellpadding="10" rules="all" border="1">
                    <thead>
                        <tr>
                            <td><b>Number</b></td>
                            <td><b>Status</b></td>
                            <td><b>Amount</b></td>
                            <td><b>Cards</b></td>
                        </tr>
                    </thead>
                    <tbody>                        
                        <c:forEach items="${accounts}" var="account">

                            <tr>
                                <td>${account.number}</td>
                                <td>${account.status}</td>
                                <td>${account.amount}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty account.cards}"> 
                                            <c:forEach items="${account.cards}" var="card">
                                                ${card.number}<br/>
                                            </c:forEach>
                                        </c:when> 
                                        <c:otherwise>
                                            <p>No cards.</p>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>                        
                    </tbody> 
                </table>
            </c:when> 

            <c:otherwise>
                <p>No accounts.</p>
            </c:otherwise>

        </c:choose>
                
    </body>
</html>