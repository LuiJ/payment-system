<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Operations List:</h3>

<c:choose>

    <c:when test="${not empty operations}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Operation</td>
                    <td>Date</td>
                    <td>Description</td>
                    <td>Amount</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${operations}" var="operation">
                    <tr>
                        <td>${operation.type}</td>
                        <td>
                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"  timeZone="Europe/Moscow" value="${operation.date}"/>
                        </td>
                        <td>${operation.description}</td>
                        <td>${operation.amount}</td>
                    </tr>
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg">No operations.</p>
    </c:otherwise>

</c:choose>