<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="cardBlockUrl" value="/admin/card-block"></c:url>
<c:url var="cardActivateUrl" value="/admin/card-activate"></c:url>

<h3><fmt:message key="text.cardsList"/>:</h3>

<c:choose>

    <c:when test="${not empty cards}">                
        <table class="table">
            <thead>
                <tr>
                    <td><fmt:message key="column.label.cardNumber"/></td>
                    <td><fmt:message key="column.label.accountNumber"/></td>
                    <td><fmt:message key="column.label.owner"/></td>
                    <td><fmt:message key="column.label.amount"/></td>
                    <td><fmt:message key="column.label.status"/></td>
                    <td><fmt:message key="column.label.expirationDate"/></td>
                    <td><fmt:message key="column.label.action"/></td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${cards}" var="card">

                    <tr>
                        <td>${card.number}</td>
                        <td>${card.account.number}</td>
                        <td>${card.account.user.lastName}, ${card.account.user.firstName}</td>
                        <td>${card.account.amount}</td>
                        <td>${card.status}</td>
                        <td>
                            <fmt:formatDate pattern="MM / yyyy" value="${card.expirationDate}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${card.status == 'ACTIVE'}">
                                    <form method="POST" action="${cardBlockUrl}">
                                        <input type="hidden" name="cardId" value="${card.id}"/>
                                        <input type="submit" class="btn" name="block" value="<fmt:message key="button.label.block"/>" />
                                    </form>
                                </c:when>
                                <c:when test="${card.status == 'BLOCKED'}">
                                    <form method="POST" action="${cardActivateUrl}">
                                        <input type="hidden" name="cardId" value="${card.id}"/>
                                        <input type="submit" class="btn" name="activate" value="<fmt:message key="button.label.activate"/>" />
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="text.noActionsAvailable"/>
                                </c:otherwise>
                            </c:choose>                            
                        </td>
                    </tr>
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg"><fmt:message key="text.noCards"/></p>
    </c:otherwise>

</c:choose>