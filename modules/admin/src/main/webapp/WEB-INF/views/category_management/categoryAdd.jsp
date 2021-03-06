<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>

    <title>Add Category</title>

</head>

<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<center>
   <%-- <div class="form-box" id="categoryAdd_box">
    <div class="panel panel-default">--%>
        <div class="panel-body">
            <form:form role="form" id="admin_addcatform"  method="POST" class="form-horizontal" enctype="multipart/form-data" accept-charset="utf-8"
                       action="/admin/category/addCategory">
                <fieldset class="scheduler-border">
                    <legend class="scheduler-border" id="legendHeading"></legend>

                    <%--add category form fields--%>
                    <div class="form-group"></div>

                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
                                <fmt:message key="category.categoryadd.form.catname"/>
                            </label>

                            <div class="col-xs-8">
                                <div class="col-xs-12">
                                    <form:input path="categoryName" type="text" class="form-control" id="categoryname"
                                     placeholder="Please Enter Category Name" required="required"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label  class=" col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
                                <fmt:message key="category.categoryadd.form.catdescription" />
                            </label>

                            <div class="col-xs-8">
                                <div class="col-xs-12">
                                    <form:textarea path="catDescription" type="text" class="form-control" id="categorydes"
                                        placeholder="Please Enter Category Description"></form:textarea>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="row">
                            <label for="btnUpldImage" class="col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
                                <fmt:message key="category.categoryadd.form.catimage" />
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-2">
                                    <form:input type="file" class="file_upload btn btn-default" path="imageUrl" id="btnUpldImage"
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
                            <label for="viewability" class="col-xs-3 control-label" style="text-align: left;margin: 0 -60px 0 60px;">
                                <fmt:message key="category.categoryadd.form.catvisibility" />
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-2">
                                    <form:select path="status" class="form-control" id="viewability" style="width:75px">
                                        <form:option value="1">
                                            <fmt:message key="category.categoryadd.form.catvisibility.option1"/>
                                        </form:option>
                                        <form:option value="0">
                                            <fmt:message key="category.categoryadd.form.catvisibility.option2" />
                                        </form:option>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group"></div>
                    <br>
                    <%--subcategory addition grid--%>
                    <div class="form-group">
                        <legend class="scheduler-border">
                            <fmt:message key="category.categoryadd.form.add.subcategories" />
                        </legend>


                        <div class="row cat_add_table col-xs-12" id="cat_table" style="margin-left: 1%;">

                            <div class="row cat-tbl-row1 cat-tbl-hdr" style="width:600px;">
                                <div class="col-xs-4">
                                    <strong><fmt:message key="category.categoryadd.form.subcatname"/></strong>
                                </div>
                                <div class="col-xs-5">
                                    <strong><fmt:message key="category.categoryadd.form.subcatdescription" /></strong>
                                </div>
                                <div class="col-xs-3">
                                        <strong><fmt:message key="category.categoryadd.form.addOrRemove" /></strong>
                                </div>
                            </div>

                            <div class="row cat-tbl-row">
                                <div class="col-xs-4">
                                    <label class="checkbox-inline">
                                        <input name="subcategory_name[]" type="text" class="form-control" id="subcategoryname"
                                                    required="required" placeholder="Enter Subcategory Name"/>
                                    </label>
                                </div>
                                <div class="col-xs-5">
                                    <textarea name="subcategory_des[]"  class="form-control" id="subcategorydes"
                                              required="required" placeholder="Enter Subcategory Description" ></textarea>
                                </div>
                                <div class="col-xs-3">
                                    <a href="#" class="addcat ">
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </a>
                                    <a href="#" class="deletecat">
                                        <span class=" glyphicon glyphicon-minus"></span> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group"></div>

                    <%--buttons--%>
                    <div class="form-group">
                        <div class="row">
                            <label  class="col-xs-3 control-label" >
                            </label>
                            <div class="col-xs-8">
                                <div class="col-xs-2">
                                        <form:button type="reset" value="Reset" class="btn btn-success btn-md btnAddItem">
                                            <fmt:message key="user.useradd.form.button.reset" /></form:button>

                                </div>
                                <div class="col-xs-10">

                                        <form:button type="submit" class="btn btn-success btn-md" id="btnAddItem"><span
                                                class="glyphicon glyphicon-plus"></span>
                                            <fmt:message key="category.add.submit.button"  />
                                        </form:button>

                                </div>
                            </div>
                        </div>
                    </div>

                </fieldset>
            </form:form>
        </div>
</center>


<div class="footerposition">
    <%--<jsp:include page="../footer.jsp"/>--%>

</div>

</body>
</html>