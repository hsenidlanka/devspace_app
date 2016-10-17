<html xmlns:jsp="http://java.sun.com/JSP/Page">

<!--including JSTL to the page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<head>
  <!--include common CSS, fonts and js-->
  <jsp:include page="../adminTop.jsp"/>

  <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>

  <link href="${css1}" rel="stylesheet">
  <link href="${css2}" rel="stylesheet">


  <spring:url value="/themes/hsenid/js/userMgt.js" var="js2"/>
  <!--include the jQuery toaster plugin's script-->
  <spring:url value="/themes/hsenid/js/jquery.toaster.js" var="js3"/>
  <spring:url value="/themes/hsenid/js/toaster.js" var="js4"/>


  <script src="${js2}"></script>
  <script src="${js3}"></script>
  <script src="${js4}"></script>

<%--<spring:url value="/themes/hsenid/js/simpleToastMessage.js" var="js3"/>--%>

</head>
<body>
<fmt:setLocale value="En"/>
<fmt:bundle basename="messages_en">

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
      <fmt:message key="user.useradd.heading"/>
  </div>
</div>
<br>
<br>
<div class="breadcrumbPosition" id="userBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >
      <li><a href="https://localhost:8443/admin/users/list"> <fmt:message key="user.useradd.breadcrumb.home"/></a></li>
      <li><a href="https://localhost:8443/admin/users/list"><fmt:message key="user.useradd.breadcrumb.usermanagement"/></a></li>
      <li class="active"><a href="https://localhost:8443/admin/users/add"><fmt:message key="user.useradd.breadcrumb.adduser"/></a></li>
    </ul>
</div>

<center>
  <div class="form-box" id="userAdd_box">
    <div class="panel panel-default">

      <div class="panel-heading common-form-headings">
        <h3 class="default-panel-headings "><fmt:message key="user.useradd.panel.heading"/></h3>
      </div>
      <div class="panel-body">
        <form:form role="form" id="admin_adduser_form"  method="POST" class="form-horizontal"
                   action="/admin/users/addCustomer"  >

          <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="legendHeading"></legend>

            <div class="form-group">
                <div class="row">
                    <label  class="col-xs-3 control-label">
                        <fmt:message key="user.useradd.form.usertype"/>
                    </label>
                  <div class="col-xs-8">
                    <div class="col-xs-4">
                      <label  class="radio-inline">
                        <input type="radio" name="radioName" id="customer" value="customer" checked="checked"/>
                          <fmt:message key="user.useradd.form.usertype.customer"/>
                      </label>
                    </div>
                    <div class="col-xs-4">
                      <label  class="radio-inline">
                        <input  type="radio" name="radioName"  id="staff" value="staff"/>
                          <fmt:message key="user.useradd.form.usertype.staff"/>
                      </label>
                    </div>
                  </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <label  class="col-xs-3 control-label" >
                        <fmt:message key="user.useradd.form.name"/>
                    </label>
                  <div class="col-xs-8">
                    <div class="col-xs-6">
                      <div class="col-xs-4">
                        <form:select path="title" class="form-control" id="selectTitle" style="width: 65px">
                          <form:option value="mr"><fmt:message key="user.useradd.form.title.mr"/></form:option>
                          <form:option value="mrs"><fmt:message key="user.useradd.form.title.mrs"/></form:option>
                          <form:option value="miss"><fmt:message key="user.useradd.form.title.miss"/></form:option>
                        </form:select>
                      </div>
                      <div class="col-xs-5">
                        <form:input path="firstName" class="form-control" id="fname" placeholder="First Name"
                                    type="text"  style="width: 180px" required="required"/>
                      </div>
                    </div>
                    <div class="col-xs-6">
                      <%--<div class="col-xs-1"></div>--%>
                      <div class="col-xs-5">
                        <form:input path="lastName" class="form-control" id="lname" placeholder="Last Name"
                                    type="text"  style="width: 200px"/>
                      </div>
                    </div>
                </div>
            </div>
            </div><br>

          <div class="form-group">
              <div class="row">
                <label  class="col-xs-3 control-label" >
                    <fmt:message key="user.useradd.form.address"/>
                </label>
                <div class="col-xs-8">
                  <div class="col-xs-12">
                    <div class="col-xs-12">
                      <form:input path="addressL1" class="form-control" id="addLine1" placeholder="Street Address"
                                  type="text"/>
                    </div>
                  </div>
                  <div class="col-xs-6"></div>
                </div>
              </div>
              <div class="row"> <label class="control-label"></label>   </div>
              <div class="row">
                <label  class="col-xs-3 control-label" >
                </label>
                <div class="col-xs-8">
                  <div class="col-xs-12">
                    <div class="col-xs-12">
                      <form:input path="addressL2" class="form-control" id="addLine2" placeholder="Address Line 2"
                                  type="text" required="required"/>
                    </div>
                  </div>
                  <div class="col-xs-6"></div>
                </div>
              </div>
              <div class="row"> <label class="control-label"></label>   </div>
              <div class="row">
                  <label  class="col-xs-3 control-label">
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                            <form:input path="addressL3" class="form-control" id="city" placeholder="City" type="text"
                                        style="width: 260px" required="required"/>
                          </div>
                      </div>
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                            <input path="provincein" class="form-control" id="province" placeholder="Province" type="text"/>
                          </div>
                      </div>
                  </div>
              </div>
          </div><br>

              <div class="form-group">
                  <div class="row">
                      <form:label path="mobile"  class="col-xs-3 control-label">
                          <fmt:message key="user.useradd.form.mobile"/>
                      </form:label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-12">
                                  <form:input path="mobile" class="form-control" id="mobileNo" placeholder="+94XXXXXXXXX"
                                              type="text" style="width: 260px" pattern="^\(?(\+94)\)?([0-9]{9})$"
                                              required="required"/>
                              </div>
                          </div>
                          <div class="col-xs-6"></div>
                      </div>
                  </div>
                  <div class="row"> <label class="control-label"></label>   </div>
                  <div class="row">
                      <form:label path="email" class="col-xs-3 control-label">
                          <fmt:message key="user.useradd.form.email"/>
                      </form:label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-12">
                                  <form:input path="email" class="form-control" id="email" placeholder="myname@example.com"
                                              type="text" style="width: 260px" pattern="^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.
                                              [a-zA-Z0-9-]+)*\.(([0-9]{1,3})|([a-zA-Z]{2,3}))$" required="required"/>
                              </div>
                          </div>
                          <div class="col-xs-6"></div>
                      </div>
                  </div>
                  <div class="row"> <label class="control-label"></label>   </div>
                  <div class="row">
                      <form:label path="username" class="col-xs-3 control-label" >
                          <fmt:message key="user.useradd.form.username"/>
                      </form:label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-12">
                                  <form:input path="username" class="form-control" name="username_admin" id="username_admin"
                                              placeholder="User Name" type="text" style="width: 260px" required="required"/>

                              </div>
                          </div>
                          <div class="col-xs-6"></div>
                      </div>
                  </div>
                  <div class="row"><label class=" control-label"></label></div>

                  <div class="row">
                      <form:label path="password" class="col-xs-3 control-label" >
                          <fmt:message key="user.useradd.form.password"/>
                      </form:label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-12">
                                  <form:input path="password" class="form-control" id="password1" placeholder="Password"
                                              type="text" style="width: 260px"  required="required"
                                              onchange="validatePassword(password1)"/>

                              </div>
                          </div>
                          <div class="col-xs-6"></div>
                      </div>
                  </div>
                  <div class="row"><label class=" control-label"></label></div>
                  <div class="row">
                      <form:label path="password" class="col-xs-3 control-label" >
                          <fmt:message key="user.useradd.form.cpassword"/>
                      </form:label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-12">
                                  <form:input path="password" class="form-control" id="cpassword"
                                              placeholder="Password Confirm" type="text" style="width: 260px"
                                              required="required" onchange="passwordsEqual(cpassword,password1)"/>

                              </div>
                          </div>
                          <div class="col-xs-6"></div>
                      </div>
                  </div>
              </div><br>


             <!--when the user is a customer-->
             <div class="form-group">
                 <div class="col-xs-12">
                 <fieldset class="scheduler-border" id="staffDetail">
                   <legend class="scheduler-border" id="legendStaff"><fmt:message key="user.useradd.form.cpassword"/>
                   </legend>
                   <div class="row">
                     <form:label path="designation" class="col-xs-4 control-label" >
                         <fmt:message key="user.useradd.form.designation"/>
                     </form:label>
                       <form:label path="department" class="col-xs-4 control-label" >
                           <fmt:message key="user.useradd.form.department"/>
                       </form:label>
                       <form:label path="branch" class="col-xs-4 control-label"  >
                           <fmt:message key="user.useradd.form.branch"/>
                       </form:label>
                   </div>
                     <div class="row">
                         <div class="col-xs-4">
                            <form:select path="designation" class="form-control" id="designation">
                                <form:option value="GROUP_SYS_ADMIN"><fmt:message key="user.useradd.form.staff.designation1"/>
                                </form:option>
                                <form:option value="GROUP_ADM_MANAGER"><fmt:message key="user.useradd.form.staff.designation2"/>
                                </form:option>
                                <form:option value="GROUP_CC_MANAGER"><fmt:message key="user.useradd.form.staff.designation3"/>
                                </form:option>
                                <form:option value="GROUP_CUSTOMERCARE"><fmt:message key="user.useradd.form.staff.designation4"/>
                                </form:option>
                                <form:option value="GROUP_CUSTOMERCARE"><fmt:message key="user.useradd.form.staff.designation5"/>
                                </form:option>
                                <form:option value="GROUP_RPT_MANAGER"><fmt:message key="user.useradd.form.staff.designation6"/>
                                </form:option>
                            </form:select>
                         </div>
                         <div class="col-xs-4">
                            <form:select path="department" class="form-control" id="department">
                                <form:option value="Finance"><fmt:message key="user.useradd.form.staff.department1"/>
                                </form:option>
                                <form:option value="Administration"><fmt:message key="user.useradd.form.staff.department2"/>
                                </form:option>
                                <form:option value="Logistic"><fmt:message key="user.useradd.form.staff.department3"/>
                                </form:option>
                                <form:option value="Reporting"><fmt:message key="user.useradd.form.staff.department4"/>
                                </form:option>
                            </form:select>
                         </div>
                         <div class="col-xs-4">
                            <form:select path="branch" class="form-control" id="branch">
                                <form:option value="Colombo"><fmt:message key="user.useradd.form.staff.branch1"/>
                                </form:option>
                                <form:option value="Gampaha"><fmt:message key="user.useradd.form.staff.branch2"/>
                                </form:option>
                                <form:option value="Ja-Ela"><fmt:message key="user.useradd.form.staff.branch3"/>
                                </form:option>
                                <form:option value="Kadana"><fmt:message key="user.useradd.form.staff.branch4"/>
                                </form:option>
                            </form:select>
                         </div>
                     </div><br>
                 </fieldset>
                 </div>
             </div>

              <%--buttons--%>
              <div class="form-group">
                  <div class="row">
                      <label  class="col-xs-3 control-label" >
                      </label>
                      <div class="col-xs-8">
                          <div class="col-xs-6">
                              <div class="col-xs-5">
                                <form:button type="reset" value="Reset" class="btn btn-success btn-md btnAddItem">
                                    <fmt:message key="user.useradd.form.button.reset"/></form:button>
                              </div>
                          </div>
                          <div class="col-xs-6">
                              <div class="col-xs-5">
                                <form:button type="submit" class="btn btn-success btn-md" id="btnAddItem"><span
                                    class="glyphicon glyphicon-plus"></span>
                                    <fmt:message key="user.useradd.form.button.submit"/>
                                </form:button>
                              </div>
                          </div>
                      </div>
                  </div>
              </div>
          </fieldset>
        </form:form>
      </div>
    </div>
  </div>
</center>
</fmt:bundle>
</body>


</html>