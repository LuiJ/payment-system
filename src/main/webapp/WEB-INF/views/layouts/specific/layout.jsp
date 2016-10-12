<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<c:url var="cssUrl" value="/resources/styles/style.css"></c:url>


<html>
    <head>
        <title>
            <tiles:insertAttribute name="title" ignore="true"/>
        </title>
        <link rel="stylesheet" href="${cssUrl}" type="text/css" />
    </head>
    
    <body>
        <div id="page-wrap">
            <tiles:insertAttribute name="header"/>
            <div id="content-wrap">
                <div id="menu">
                    <tiles:insertAttribute name="menu"/>
                </div>
                <div>
                    <div id="specific-content">
                        <tiles:insertAttribute name="content"/>
                    </div>                    
                </div>
                <div class="clear"></div>
            </div>            
            <tiles:insertAttribute name="footer"/>
        </div>        
    </body>
</html>