<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="#" class="item <c:if test="${page == 'accounts'}">active</c:if>">Accounts</a>
<a href="#" class="item <c:if test="${page == 'cards'}">active</c:if>">Cards</a>
<a href="#" class="item <c:if test="${page == 'payments'}">active</c:if>">Make Payment</a>
<a href="#" class="item <c:if test="${page == 'operations'}">active</c:if>">Operations History</a>        
        