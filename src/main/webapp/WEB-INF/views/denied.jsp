<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="homeUrl" value="/"></c:url>


<h1>403 - <fmt:message key="error.accessDenied"/></h1>
<br/>
<br/>
<a href="${homeUrl}" class="btn center"><fmt:message key="button.label.backHome"/></a>