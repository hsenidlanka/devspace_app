<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="menu.title" bundle="${lang}"/></title>
    <link rel="shortcut icon" href="">
    <link href="<c:url value="/resources/css/component.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/modernizr.custom.js"/>"></script>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>
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
                <div style="display: inline-block; float:right;">
                    <div class="form-group row">
                        <div class="col-sm-6" style="padding-right: 0px;">
                            <input type="text" class="form-control input-md" placeholder="<fmt:message key="searchresults.search.placeholder"
                                                                                      bundle="${lang}"/>"
                                   name="txt-search-menu" id="txt-menu-search">
                        </div>
                        <div class="col-sm-4" id="div_source1">
                            <select class="form-control input-md" id="ch_user1">
                                <%--<option value="select"></option>--%>
                            </select>
                        </div>
                        <script>
                            $.ajax({
                                type: "GET",
                                url: "http://localhost:2222/pizza-shefu/api/v1.0/categories/list",
                                dataType: "json",
                                success: function (data) {
                                    $.each(data.data, function (i, obj) {
//                                            alert(obj.name + ":" + obj.name);
                                        var div_data = "<option value=" + obj.name + ">" + obj.name + "</option>";
                                        // alert(div_data);
                                        $(div_data).appendTo('#ch_user1');
                                    });
                                }
                            });
                        </script>
                        <div class="col-sm-2" style="padding-left: 2px;">
                            <button class="btn btn-primary input-md" id="btn-menu-search" type="button"><span><i
                                    class="glyphicon glyphicon-search"></i></span></button>
                        </div>


                    </div>

                </div>
            </div>
            <div class="panel-body">
                <img src="<c:url value="/resources/images/icons/bullet_blue.png"/>" style="width: 25px;height: 25px; margin-bottom: 8px;"/>
                <h4 style="display:inline-block;"><fmt:message key="menu.main.panel.subheader"
                                 bundle="${lang}"/></h4>

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
                                        <figure><img src="${category.baseUrl}${category.name}.jpg" style="height: 200px;width: 200px">
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
<script>
    $("#btn-menu-search").click(function () {
        var searchKey = $("#txt-menu-search").val();
        var category = $("#ch_user1").val();
        if ($("#txt-menu-search").val().length == 0) {
            $("#txt-menu-search").css("border-color", "red");
            $.notify(" Search field is empty...", {
                align: "center",
                verticalAlign: "top",
                delay: 2000,
                animationType: "fade",
                color: "#fff",
                background: "#D44950"
            });
        } else {
            window.location.href = "http://localhost:8080/web-selfcare/search-menu/" + searchKey + "/" + category;

        }

    });
</script>
</html>