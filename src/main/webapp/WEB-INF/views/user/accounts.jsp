<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="accountCloseUrl" value="/user/account-close"></c:url>

<h3><fmt:message key="text.accountsList"/>:</h3>

<c:choose>

    <c:when test="${not empty accounts}">                
        <table class="table">
            <thead>
                <tr>
                    <td><fmt:message key="column.label.accountNumber"/></td>
                    <td><fmt:message key="column.label.status"/></td>
                    <td><fmt:message key="column.label.cards"/></td>
                    <td><fmt:message key="column.label.amount"/></td>
                    <td><fmt:message key="column.label.action"/></td>
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
                                    <p><fmt:message key="text.noCards"/></p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${account.amount}</td>
                        <td>
                            <form method="POST" action="${accountCloseUrl}">
                                <input type="hidden" name="accountId" value="${account.id}"/>
                                <input type="submit" class="btn" name="close" value="<fmt:message key="button.label.close"/>" />
                            </form>
                        </td>
                    </tr>   
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg"><fmt:message key="text.noAccounts"/></p>
    </c:otherwise>

</c:choose>