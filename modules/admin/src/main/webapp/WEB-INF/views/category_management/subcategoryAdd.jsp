<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>

<!DOCTYPE html>
<head>

  <title>Add Sub-Category</title>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<center>
  <div class="panel-body">
    <form:form role="form" id="admin_addsubcatform"  method="POST" class="form-horizontal" enctype="multipart/form-data"
           action="/admin/category/addSubCategory">
      <fieldset class="scheduler-border">
        <legend class="scheduler-border" id="legendHeading"></legend>

        <div class="col-xs-6" >
          <div class="form-group">
            <div class="row">
              <label  class="col-xs-8 control-label" style="font-size: 17px; font-style: italic" >
                <fmt:message key="category.categoryadd.form.categories" />
              </label>
            </div>
           <br>
              <c:forEach var="list" items="${listCat}">
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class=" col-xs-6 radio" style="text-align: left">
                            <label class="cat" >
                                <%--<form:radiobutton path="categoryName" value=" ${list}" name="catName" />--%>
                                    <input type="radio" name="catName" value=" ${list}" required="required" >${list}
                            </label>
                        </div>
                    </div>
                    <div class="row"></div>
              </c:forEach>
          </div>
        </div>

        <div class="col-xs-6" style="visibility: hidden" >
            <div class="form-group">
                <div class="row">
                    <img id="image" src="" alt="logo" style="width: 200px; height: 150px;margin: 10px 0 -20px -20px;"/>

                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <label  class="col-xs-10 control-label" id="subcat_label" style="font-size: 17px; font-style: italic ">
                        <fmt:message key="category.categoryadd.form.subcategories"/>
                    </label>
                </div>
                <div class="row">
                    <%--<div class="col-xs-2"></div>--%>
                    <div class=" col-xs-12 radio">
                        <label id="subcat" class="col-xs-6 control-label" style="text-align:left">

                        </label>
                    </div>
                </div>
            </div>
        </div>
          <%--<fieldset class="scheduler-border" style="padding-right: 300px;">--%>
              <%--<legend class="scheduler-border">--%>

              <fmt:message key="category.categoryadd.form.add.subcategories" />
              <%--</legend>--%>


              <div class="row subcat_add_table col-xs-12" id="subcat_table" style="margin-left: 1%;">

                  <div class="row subcat-tbl-row1 subcat-tbl-hdr" style="width:600px;">
                      <div class="col-xs-4">
                          <strong><fmt:message key="category.categoryadd.form.subcatname" /></strong>
                      </div>
                      <div class="col-xs-5">
                          <strong><fmt:message key="category.categoryadd.form.subcatdescription" /></strong>
                      </div>
                      <div class="col-xs-3">
                          <%--<strong><fmt:message key="package.packageadd.form.packagedetails.itemsize" bundle="${bundle1}"/></strong>--%>
                      </div>
                  </div>

                  <div class="row subcat-tbl-row">
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
                          <a href="#" class="addsub ">
                              <span class="glyphicon glyphicon-plus"></span>
                          </a>
                          <a href="#" class="deletesub">
                              <span class=" glyphicon glyphicon-minus"></span>
                          </a>
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
                              <fmt:message key="user.useradd.form.button.reset"  /></form:button>
                      </div>
                      <div class="col-xs-10">
                          <form:button type="submit" class="btn btn-success btn-md" id="btnAddItem"><span
                                  class="glyphicon glyphicon-plus"></span>
                              <fmt:message key="category.add.subcategory.submit.button" />
                          </form:button>
                      </div>
                  </div>
              </div>
          </div>

      </fieldset>
  </form:form>

  </div>
</center>
</body>
</html>
