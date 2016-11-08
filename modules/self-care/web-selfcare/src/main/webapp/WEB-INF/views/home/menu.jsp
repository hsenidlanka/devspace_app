<!DOCTYPE html>
<html>
<head lang="en">
    <title>Menu</title>
    <link rel="shortcut icon" href="">
    <%@include file="../includes/include.jsp" %>
    <link href="<c:url value="/resources/css/component.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
</head>
<body>
<%@include file="../includes/NewHeader.jsp" %>
<div class="loader-anim"></div>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div style="display: inline-block;">
                    <img class="icons" src="<c:url value="/resources/images/icons/menu.png"/>"/>

                    <h3 id="menu-panel-heading" class="header-panel"><fmt:message key="menu.main.panel.header"
                                                                                  bundle="${lang}"/></h3>
                </div>
            </div>
            <div class="panel-body">
                <h1><fmt:message key="menu.main.panel.subheader"
                                 bundle="${lang}"/></h1>

                <div class="row" style="margin-top: 15px;">
                    <div class="col-md-9">
                        <div class="row" style="margin-top: 10px;">
                            <c:forEach items="${categories}" var="category" varStatus="loop">
                            <c:if test="${not loop.first and loop.index % 4 == 0}">
                        </div>
                        <div class="row" style="margin-top: 10px;">
                            </c:if>
                            <div class="col-md-3">
                                    <ul class="grid cs-style-3">
                                        <li>
                                            <figure><img src="<c:url value="/resources/images/image_placeholder.gif"/>">
                                                <figcaption>
                                                    <h3>${category.name}</h3>
                                                    <a href="menu\<c:out value="${category.name}"/>">Browse</a>
                                                </figcaption>
                                            </figure>
                                        </li>
                                    </ul>
                                <div style="text-align: center;">${category.name}</div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <%@include file="../includes/menu-pizza-carousel.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>