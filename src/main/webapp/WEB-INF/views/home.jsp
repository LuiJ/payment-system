<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="userAccountsUrl" value="/user/accounts"></c:url>


<h1><fmt:message key="text.weclomePhrase1"/></h1>
<br/>
<h3><fmt:message key="text.weclomePhrase2"/></h3>
<br/>
<br/>
<a href="${userAccountsUrl}" class="btn center"><fmt:message key="button.label.login"/></a>
