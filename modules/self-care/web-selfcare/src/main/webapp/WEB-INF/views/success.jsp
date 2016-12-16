<!DOCTYPE html>
<html>
<head lang="en">
    <title>Order Success</title>
    <link rel="shortcut icon" href="">

    <%@include file="includes/include.jsp" %>
    <%@include file="includes/smart-wizard.jsp" %>
</head>
<body>
<div class="loader-anim"></div>
<%@include file="includes/new-header.jsp" %>
<div class="main-div">
    <div class="section">
        <div class="panel panel-default">
            <div class="panel-heading">
                <img class="icons" src="<c:url value="/resources/images/icons/list-correct.png"/>">
                <h3 class="header-panel">Order placed</h3>
            </div>
            <div class="panel-body">
                <div id="smartwizard">
                    <ul>
                        <li class="done"><a href=""><fmt:message key="delivery" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li class="done"><a href=""><fmt:message key="delivery.payment" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.payment.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li class="done"><a href=""><fmt:message key="delivery.verify" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.verify.msg" bundle="${lang}"/></small>
                        </a></li>
                        <li class="done"><a href=""><fmt:message key="delivery.success" bundle="${lang}"/><br/>
                            <small><fmt:message key="delivery.success.msg" bundle="${lang}"/></small>
                        </a></li>
                    </ul>
                </div>

                <div class="container" style="width:1000px; margin-top: 30px;">
                    <div style="text-align: center;">
                        <img src="<c:url value="/resources/images/icons/success.png"/>"  width="120px" height="120px">

                        <h1><fmt:message key="success.thank" bundle="${lang}"/></h1>
                    </div>
                    <div>
                        <h4><fmt:message key="success.your.order" bundle="${lang}"/></h4>
                    </div>

                    <div>
                        <a href="home"><fmt:message key="success.click" bundle="${lang}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<a href="#" class="scrollup"></a>
<script>
    $('#smartwizard').smartWizard({
        selected:5,
        theme:'arrows',
        anchorSettings:{
            anchorClickable: false
        }
    });
</script>
</body>
</html>