<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="accountCloseUrl" value="/user/account-close"></c:url>

<h3>Accounts List:</h3>

<c:choose>

    <c:when test="${not empty accounts}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Account No.</td>
                    <td>Status</td>
                    <td>Cards</td>
                    <td>Amount</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${accounts}" var="account"> 
                    <tr>
                        <td>${account.number}</td>
                        <td>${account.status}</td>
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
                        <td>${account.amount}</td>
                        <td>
                            <form method="POST" action="${accountCloseUrl}">
                                <input type="hidden" name="accountId" value="${account.id}"/>
                                <input type="submit" class="btn" name="close" value="Close" />
                            </form>
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