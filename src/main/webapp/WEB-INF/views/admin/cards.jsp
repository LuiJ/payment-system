<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="cardBlockUrl" value="/admin/card-block"></c:url>
<c:url var="cardActivateUrl" value="/admin/card-activate"></c:url>

<h3>Cards List:</h3>

<c:choose>

    <c:when test="${not empty cards}">                
        <table class="table">
            <thead>
                <tr>
                    <td>Card No.</td>
                    <td>Account No.</td>
                    <td>Owner</td>
                    <td>Amount</td>
                    <td>Status</td>
                    <td>Action</td>
                </tr>
            </thead>
            <tbody>                        
                <c:forEach items="${cards}" var="card">

                    <tr>
                        <td>${card.number}</td>
                        <td>${card.account.user.lastName}, ${card.account.user.firstName}</td>
                        <td>${card.account.number}</td>
                        <td>${card.account.amount}</td>
                        <td>${card.status}</td>
                        <td>
                            <c:choose>
                                <c:when test="${card.status == 'ACTIVE'}">
                                    <form method="POST" action="${cardBlockUrl}">
                                        <input type="hidden" name="cardId" value="${card.id}"/>
                                        <input type="submit" class="btn" name="block" value="Block" />
                                    </form>
                                </c:when>
                                <c:when test="${card.status == 'BLOCKED'}">
                                    <form method="POST" action="${cardActivateUrl}">
                                        <input type="hidden" name="cardId" value="${card.id}"/>
                                        <input type="submit" class="btn" name="activate" value="Activate" />
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