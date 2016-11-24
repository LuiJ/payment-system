<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${baseName}"/>

<c:url value="/j_spring_security_logout" var="logoutUrl" />

<div id="header">
    <a href="#" id="logo">Payment System</a>
    <c:if test="${not empty loggedUser}">        
        <form method="POST" action="${logoutUrl}" id="h-logout-form">
            <input class="btn" id="h-logout-btn" type="submit" name="logout" value="<fmt:message key="button.label.logout"/>" />
        </form>
        <form>
            <div id="lang-select-wrap">
                <select name="locale" onchange="this.form.submit();">
                    <option value="en" ${locale == "en" ? "selected" : ""}><fmt:message key="input.option.english"/></option>
                    <option value="ru" ${locale == "ru" ? "selected" : ""}><fmt:message key="input.option.russian"/></option>
                </select>
            </div>
        </form>
        <p id="welcome"><fmt:message key="text.welcome"/>, ${loggedUser.firstName}</p>
        <div class="clear"></div>
    </c:if>    
</div>
        
        