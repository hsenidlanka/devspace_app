<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <title>Add Category</title>

</head>

<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<center>
   <%-- <div class="form-box" id="categoryAdd_box">
    <div class="panel panel-default">--%>
        <div class="panel-body">
            <form:form role="form" id="admin_addcatform"  method="POST" class="form-horizontal"
                       action="/admin/category/addCategory">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border" id="legendHeading"></legend>

                    <%--add category form fields--%>
                    <div class="form-group"></div>

                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
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
                            <label  class=" col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
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
                            <label for="btnUpldImage" class="col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
                                <fmt:message key="category.categoryadd.form.catimage" bundle="${bundle1}"/>
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-2">
                                    <form:input type="file" class="file_upload btn btn-default" path="image" id="btnUpldImage"
                                                value="Browse" cssStyle="width: 220px"/>
                                </div>
                                <div class="col-xs-4">
                                    <span id="itmImgErr" class="input-group-error"> </span>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="form-group"></div>

                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-3 control-label">
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-4">
                                    <label class="control-label" style="font-style: italic">
                                        <fmt:message key="category.categoryadd.form.subcat1" bundle="${bundle1}"/>
                                    </label>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-3 control-label">
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-3" style="text-align: left">
                                    <label for="subcategory" class="control-label ">
                                        <fmt:message key="category.categoryadd.form.subcatname" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input path="subCategoryName" type="text" class="form-control" id="subcategory"
                                           placeholder="Please Enter Sub Category Name"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label class="col-xs-3 control-label">
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-3">
                                    <label  class="control-label ">
                                        <fmt:message key="category.categoryadd.form.subcatdescription" bundle="${bundle1}"/>
                                    </label>
                                </div>
                                <div class="col-xs-9">
                                    <form:input path="" type="text" class="form-control"
                                                placeholder="Please Enter Sub Category Description"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group"></div>
                    <br>

                        <%--buttons--%>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-2">

                                        <form:button type="reset" value="Reset" class="btn btn-success btn-md btnAddItem">
                                            <fmt:message key="user.useradd.form.button.reset" bundle="${bundle1}" /></form:button>

                                </div>
                                <div class="col-xs-10">

                                        <form:button type="submit" class="btn btn-success btn-md" id="btnAddItem"><span
                                                class="glyphicon glyphicon-plus"></span>
                                            <fmt:message key="user.useradd.form.button.submit" bundle="${bundle1}" />
                                        </form:button>

                                </div>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
 <%--   </div>
</div>--%>
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
