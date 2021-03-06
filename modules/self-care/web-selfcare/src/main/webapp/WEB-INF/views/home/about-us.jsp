<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="about.us.title" bundle="${lang}"/></title>

</head>
<body style="overflow: visible">
<%@include file="../includes/new-header.jsp" %>
<%--<c:out value="${username}"></c:out>--%>
<div class="loader-anim"></div>
<div class="col-sm-12"></div>

<div class="brand"></div>
<div class="inner-bg">
    <div class="container">
        <div class="col-sm-12" style="height: 60px"></div>

        <div>
            <div class="panel panel-success col-sm-2" style="width: 700px">
                <div class="panel-heading" style="height: 50px">
                    <p style="font-size: xx-large;text-align: left"><fmt:message key="aboutus" bundle="${lang}"/></p>
                </div>
                <div class="panel-body">
                    <form role="form" id="adduser_form" action="" method="post" class="form-horizontal">

                        <p style="font-size: medium; line-height: 35px;">
                            <fmt:message key="aboutus.content" bundle="${lang}"/>
                        </p>

                    </form>

                </div>

            </div>
            <div class="col-sm-4" style="width: 100px"></div>
            <img src="<c:url value="/resources/images/pizza_hut_eabout2.png"/>" style="width: 330px;height: 250px">
        </div>
    </div>
</div>

<a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;"></a>

</body>
</html>
