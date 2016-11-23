<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<h3><fmt:message key="text.operationsList"/>:</h3>

<c:choose>

    <c:when test="${not empty operations}">                
        <table class="table">
            <thead>
                <tr>
                    <td><fmt:message key="column.label.date"/></td>
                    <td><fmt:message key="column.label.operation"/></td>
                    <td><fmt:message key="column.label.description"/></td>
                    <td><fmt:message key="column.label.number"/></td>
                    <td><fmt:message key="column.label.amount"/></td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${operations}" var="operation">
                    <tr>
                        <td>
                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"  timeZone="Europe/Moscow" value="${operation.date}"/>
                        </td>
                        <td>${operation.type}</td>
                        <td>${operation.type.description}</td>
                        <td>
                            <c:if test="${not empty operation.account}">
                                ${operation.account.number}
                            </c:if>
                            <c:if test="${not empty operation.card}">
                                ${operation.card.number}
                            </c:if>
                        </td>
                        <td>${operation.amount}</td>
                    </tr>
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg"><fmt:message key="text.noOperations"/></p>
    </c:otherwise>

</c:choose>