<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>

  <title>Add Sub-Category</title>

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<center>
  <div class="panel-body">
    <form:form role="form" id="admin_addsubcatform"  method="POST" class="form-horizontal"
           action="/admin/category/addSubCategory">
      <fieldset class="scheduler-border">
        <legend class="scheduler-border" id="legendHeading"></legend>

        <div class="col-xs-6" >
          <div class="form-group">
            <div class="row">
              <label  class="col-xs-8 control-label" >
                <fmt:message key="category.categoryadd.form.categories" bundle="${bundle1}"/>
              </label>
            </div>
           <br>
              <c:forEach var="list" items="${listCat}">
                    <div class="row">
                        <div class="col-xs-2"></div>
                        <div class=" col-xs-5 radio" style="text-align: left">
                            <label class="cat" >
                                <form:radiobutton path="categoryName" value=" ${list}" name="catName" />
                                    ${list}
                            </label>
                        </div>
                    </div>
                    <div class="row"></div>
              </c:forEach>
          </div>
        </div>
        <div class="col-xs-6" style="display: none"></div>
      </fieldset>
  </form:form>

  </div>
</center>
</body>
</html>
