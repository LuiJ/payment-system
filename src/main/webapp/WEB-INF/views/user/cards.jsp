<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="cardBlockUrl" value="/user/card-block"></c:url>

<h3>Cards List:</h3>

<c:choose>

    <c:when test="${not empty cards}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Card No.</td>
                    <td>Account No.</td>
                    <td>Amount</td>
                    <td>Status</td>
                    <td>Expiration Date</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${cards}" var="card">
                    <tr>
                        <td>${card.number}</td>
                        <td>${card.account.number}</td>
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
                                        <input type="submit" class="btn" name="block" value="Block" />
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    No actions available.
                                </c:otherwise>
                            </c:choose>                            
                        </td>
                    </tr>
                </c:forEach>                        
            </tbody> 
        </table>
    </c:when> 

    <c:otherwise>
        <p class="info-msg">No cards.</p>
    </c:otherwise>

</c:choose>