<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:url var="cssUrl" value="/resources/styles/style.css"></c:url>
<c:url var="jqueryUrl" value="/resources/js/jquery.js"></c:url>
<c:url var="jqueryMaskUrl" value="/resources/js/jquery.mask.js"></c:url>
<c:url var="jsUrl" value="/resources/js/script.js"></c:url>


<html>
    <head>
        <title>
            <tiles:insertAttribute name="title" ignore="true"/>
        </title>
        <link rel="stylesheet" href="${cssUrl}" type="text/css" />
        <script src="${jqueryUrl}"></script>
        <script src="${jqueryMaskUrl}"></script>
    </head>
    
    <body>
        <tiles:insertAttribute name="header"/>
        <div id="content-wrap">
            <div id="menu">
                <tiles:insertAttribute name="menu"/>
            </div>
            <div id="specific-content">
                <tiles:insertAttribute name="content"/>
            </div> 
            <div class="clear"></div>
        </div>            
        <tiles:insertAttribute name="footer"/>
        
        <div id="preloader">Wait please...</div>
                
        <script src="${jsUrl}"></script>
    </body>
</html>