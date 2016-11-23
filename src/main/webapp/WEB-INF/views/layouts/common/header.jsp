<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url var="homeUrl" value="/"></c:url>
<c:url var="userAccountsUrl" value="/user/accounts"></c:url>

<div id="header">
    <a href="${homeUrl}" id="logo">Payment System</a>
    <a href="${userAccountsUrl}" class="btn" id="h-login-btn"><fmt:message key="button.label.login"/></a>
    <form>
        <div id="lang-select-wrap">
            <select name="locale" onchange="changeLanguage(this);">
                <option value="en" ${locale == "en" ? "selected" : ""}><fmt:message key="input.option.english"/></option>
                <option value="ru" ${locale == "ru" ? "selected" : ""}><fmt:message key="input.option.russian"/></option>
            </select>
        </div>
    </form>
    <div class="clear"></div>
</div>
        
        