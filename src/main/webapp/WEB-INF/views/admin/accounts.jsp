<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<h3><fmt:message key="text.accountsList"/>:</h3>

<c:choose>

    <c:when test="${not empty accounts}">                
        <table class="table">
            <thead>
                <tr>
                    <td><fmt:message key="column.label.accountNumber"/></td>
                    <td><fmt:message key="column.label.owner"/></td>
                    <td><fmt:message key="column.label.status"/></td>
                    <td><fmt:message key="column.label.amount"/></td>
                    <td><fmt:message key="column.label.cards"/></td>
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
                                    <p><fmt:message key="text.noCards"/></p>
                                </c:otherwise>
                            </c:choose>
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