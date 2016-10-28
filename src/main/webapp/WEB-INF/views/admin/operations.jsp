<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Operations List:</h3>

<c:choose>

    <c:when test="${not empty operations}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Date</td>
                    <td>Operation</td>
                    <td>Description</td>
                    <td>Number</td>
                    <td>Amount</td>
                    <td>User</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${operations}" var="operation">
                    <tr>
                        <td>
                            <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${operation.date}"/>
                        </td>
                        <td>${operation.type}</td>
                        <td>${operation.description}</td>
                        <td>${operation.itemNumber}</td>
                        <td>${operation.amount}</td>
                        <td>${operation.user.lastName}, ${operation.user.firstName}</td>
                    </tr>
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg">No operations.</p>
    </c:otherwise>

</c:choose>