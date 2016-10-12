<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Accounts List:</h3>

<c:choose>

    <c:when test="${not empty accounts}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Number</td>
                    <td>Owner</td>
                    <td>Status</td>
                    <td>Amount</td>
                    <td>Cards</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${accounts}" var="account">

                    <tr>
                        <td>${account.number}</td>
                        <td>${account.user.lastName}, ${account.user.firstName}</td>
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