<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <jsp:include page="../adminTop.jsp"/>

    <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
    <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>

    <link href="${css1}" rel="stylesheet">
    <link href="${css2}" rel="stylesheet">

    <meta charset="UTF-8">
    <title>Add Category</title>

   <%-- <link rel="import" href="adminTop.html">
    <link rel="stylesheet" href="../css/add-category.css">--%>

</head>

<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
    <div style="position: relative; left: -50%;">
        <fmt:message key="category.heading" bundle="${bundle1}"/>
    </div>
</div>
<br>
<br>

<div class="breadcrumbPosition" id="userBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

        <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
        <fmt:message key="admin.categorymanage.categoryadd.url" var="url2" bundle="${bundle2}"/>

        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" bundle="${bundle1}"/></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="category.breadcrumb.categorymanagement" bundle="${bundle1}"/></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url2}"/>"><fmt:message key="category.categoryadd.breadcrumb.addcat" bundle="${bundle1}"/></a>
        </li>
    </ul>
</div>

<center>
    <div class="form-box" id="categoryAdd_box">
    <div class="panel panel-default">
        <div class="panel-heading common-form-headings" style="vertical-align:middle">
            <h3 class="default-panel-headings"><fmt:message key="category.categoryadd.panel.heading" bundle="${bundle1}"/></h3>
            <br>
        </div>
        <div class="panel-body">
            <form:form role="form" id="admin_addcatform"  method="POST" class="form-horizontal"
                       action="/admin/category/addCategory">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border" id="legendHeading"></legend>

                    <%--add category form fields--%>
                    <div class="form-group"></div>
                    <div class="form-group"></div>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label ">
                                <fmt:message key="category.categoryadd.form.catname" bundle="${bundle1}"/>
                            </label>

                            <div class="col-xs-8">
                                <div class="col-xs-12">
                                    <form:input path="categoryName" type="text" class="form-control" id="categoryname"
                                     placeholder="Please Enter Category Name"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label  class=" col-xs-3 control-label">
                                <fmt:message key="category.categoryadd.form.catdescription" bundle="${bundle1}"/>
                            </label>

                            <div class="col-xs-8">
                                <div class="col-xs-12">
                                    <form:textarea path="description" type="text" class="form-control" id="categorydes"
                                        placeholder="Please Enter Category Description"></form:textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label for="btnUpldImage" class="col-xs-3 control-label">
                                <fmt:message key="category.categoryadd.form.catimage"/>
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-5">
                                    <form:input type="file" class="btn btn-success" path="image" id="btnUpldImage" value="Browse"/>
                                </div>
                                <div class="col-xs-4">
                                <span id="itmImgErr" class="input-group-error"> </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label for="subcategory" class="control-label col-sm-4">
                                <fmt:message key="category.categoryadd.form.subcatname" bundle="${bundle1}"/>
                            </label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="subcategory"
                                   placeholder="Please Enter Sub Category Name">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label  class="control-label col-sm-4">
                                <fmt:message key="category.categoryadd.form.subcatdescription" bundle="${bundle1}"/>
                            </label>

                            <div class="col-sm-8">
                                <input type="text" class="form-control" placeholder="Please Enter Sub Category Description">
                            </div>
                        </div>
                    </div>



                    <div class="col-xs-6">
                        <div class="form-group">
                            <div class="row">
                                <label  class="col-xs-8 control-label" >
                                    <fmt:message key="category.categoryadd.form.categories" bundle="${bundle1}"/>
                                </label>
                            </div>
                            <div class="row"></div>
                            <div class="row">
                                <div class="col-xs-2"></div>
                                <div class=" col-xs-5 radio" style="text-align: left">
                                    <label><input type="radio" name="optradio" align="left">
                                        <fmt:message key="category.categoryadd.category1" bundle="${bundle1}"/></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2"></div>
                                <div class=" col-xs-5 radio" style="text-align: left">
                                    <label class="cat"><input type="radio" name="optradio">
                                        <fmt:message key="category.categoryadd.category2" bundle="${bundle1}"/></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2"></div>
                                <div class="col-xs-5 radio " style="text-align: left">
                                    <label><input type="radio" name="optradio">
                                        <fmt:message key="category.categoryadd.category3" bundle="${bundle1}"/></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2"></div>
                                <div class="col-xs-5 radio" style="text-align: left">
                                    <label><input type="radio" name="optradio">
                                        <fmt:message key="category.categoryadd.category4" bundle="${bundle1}"/></label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2"></div>
                                <div class="col-xs-5 radio "  style="text-align: left">
                                    <label><input type="radio" name="optradio">
                                        <fmt:message key="category.categoryadd.category5" bundle="${bundle1}"/></label>
                                </div>
                            </div>
                        </div>



                    </div>
                    <div class="col-xs-6" style="display: none"></div>


            <%--        <div class="form-group">
                        <label for="categoryname" class="control-label col-sm-3">Category Name :</label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="categoryname"
                                   placeholder="Please Enter Category Name">

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="categoryname" class="control-label col-sm-3">Category Description :</label>

                        <div class="col-sm-9">
                            <textarea type="text" class="form-control" id=""
                                      placeholder="Please Enter Category Description"></textarea>

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-3" style="text-align: right">
                            <input type="checkbox" name="vehicle" value="Bike">
                            <label for="checkbox">
                                Sub Category :
                            </label>
                        </div>

                        <div class="col-sm-9">
                            <select style="width: 100%; height: 30px" class="selectpicker">
                                <option>Pizza</option>
                                <option>Pasta</option>
                                <option>Salad</option>
                                <option>Desserts</option>
                            </select>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="subcategory" class="control-label col-sm-3">Sub Category Name : </label>

                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="subcategory"
                                   placeholder="Please Enter Sub Category Name">
                        </div>
                    </div>
                    <br>
                    <br>

                    <div class="form-group" style="text-align: center">

                        <div class="col-sm-3"></div>

                        <div class="col-sm-2" >
                            <button class="btn btn-success btn-lg" type="button" data-toggle="modal"
                                    data-target="#myModal">
                                <span class="glyphicon glyphicon-ok"></span> Submit Category
                            </button>
                        </div>

                        <div class="col-sm-2"></div>

                        <div class="col-sm-2">
                            <button class="btn btn-success btn-lg">
                                <span class="glyphicon glyphicon-remove"></span> Cancel
                            </button>
                        </div>

                        <div class="col-sm-3"></div>

                    </div>--%>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>
</center>


<div id="myModal" class="modal fade" role="dialog" style="top: 25%; height: 70%">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="panel panel-default">
                <div class="panel-heading common-form-headings" style="vertical-align:middle">
                    <h3 class="modal-title" style="text-align:center;"><span class="glyphicon glyphicon-ok"> </span>
                        Category Successfully Submitted !!!</h3>

                </div>
            </div>

            <div class="modal-body" style="padding-bottom: 5%">
                <div class="row">
                    <div class="col-xs-12">
                        <h4>
                                You have successfully submittted the category<br><br>
                                What do you want to do next?<br>
                        </h4>

                    </div>
                </div>


                <div class="row" style="padding-top: 5%">
                    <div class="col-sm-4" style="text-align: right">
                        <button class="btn btn-success">Add New Category</button>
                    </div>
                    <div class="col-sm-4" style="text-align: center">
                        <button class="btn btn-success">Home</button>
                    </div>
                    <div class="col-sm-4" style="text-align: left">
                        <button class="btn btn-success">View Categories</button>
                    </div>
                </div>

            </div>

        </div>

    </div>
</div>

<div class="footerposition">
    <link rel="import" href="footer.html">

</div>

</body>
</html>
