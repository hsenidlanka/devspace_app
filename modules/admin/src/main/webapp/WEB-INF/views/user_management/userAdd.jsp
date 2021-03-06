<!DOCTYPE html>
<head>
  <!--include common CSS, fonts and js-->
    <%@include file="../adminTop.jsp" %>

  <spring:url value="/themes/hsenid/css/userMgt.css" var="css1"/>
  <spring:url value="/themes/hsenid/css/form-elements.css" var="css2"/>

  <link href="${css1}" rel="stylesheet">
  <link href="${css2}" rel="stylesheet">


  <spring:url value="/themes/hsenid/js/userMgt.js" var="js2"/>
  <!--include the jQuery toaster plugin's script-->


  <script src="${js2}"></script>


<%--<spring:url value="/themes/hsenid/js/simpleToastMessage.js" var="js3"/>--%>

</head>
<body>
<%--<fmt:setLocale value="En"/>
<fmt:setBundle basename="messages_en" var="bundle1" />--%>
<fmt:setBundle basename="system" scope="session" var="bundle2"/>

<jsp:include page="../header.jsp"/>

<div class="brand">
  <div style="position: relative; left: -50%;">
      <fmt:message key="user.heading" />
  </div>
</div>
<br>
<br>

<%--breadcrumb div--%>
<div class="breadcrumbPosition" id="userBreadcrump">
    <ul class="breadcrumb breadcrumb-menu" >

            <fmt:message key="admin.home.url" var="url1" bundle="${bundle2}"/>
            <fmt:message key="admin.usermanage.useradd.url" var="url2" bundle="${bundle2}"/>

        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.home" /></a>
        </li>
        <li>
            <a href="<c:out value="${url1}"/>"><fmt:message key="user.breadcrumb.usermanagement" /></a>
        </li>
        <li class="active">
            <a href="<c:out value="${url2}"/>"><fmt:message key="user.useradd.breadcrumb.adduser"/></a>
        </li>
    </ul>
</div>

<%--form box div--%>
<center>
  <div class="form-box" id="userAdd_box">
    <div class="panel panel-default">

      <div class="panel-heading common-form-headings">
        <h3 class="default-panel-headings "><fmt:message key="user.useradd.panel.heading" /></h3>
      </div>
      <div class="panel-body">
        <form:form role="form" id="admin_adduser_form"  method="POST" class="form-horizontal" modelAttribute="newUser"
                   action="/admin/users/addCustomer">

          <fieldset class="scheduler-border">
            <legend class="scheduler-border" id="legendHeading"></legend>

            <div class="form-group">
                <div class="row">
                    <label  class="col-xs-3 control-label">
                        <fmt:message key="user.useradd.form.usertype" />
                    </label>
                  <div class="col-xs-8">
                    <div class="col-xs-4">
                      <label  class="radio-inline">
                        <input type="radio" name="radioName" id="customer" value="customer" checked="checked"/>
                          <fmt:message key="user.useradd.form.usertype.customer" />
                      </label>
                    </div>
                    <div class="col-xs-4">
                      <label  class="radio-inline">
                        <input  type="radio" name="radioName"  id="staff" value="staff"/>
                          <fmt:message key="user.useradd.form.usertype.staff" />
                      </label>
                    </div>
                  </div>
                </div>

            </div>

            <div class="form-group">
                <div class="row">
                    <label  class="col-xs-3 control-label" >
                        <fmt:message key="user.useradd.form.name" />
                    </label>
                  <div class="col-xs-8">
                    <div class="col-xs-6">
                      <div class="col-xs-4">
                        <form:select path="title" class="form-control" id="selectTitle" style="width: 65px">
                          <form:option value="Mr">
                              <fmt:message key="user.useradd.form.title.mr" />
                          </form:option>
                          <form:option value="Mrs">
                              <fmt:message key="user.useradd.form.title.mrs"  />
                          </form:option>
                          <form:option value="Miss">
                              <fmt:message key="user.useradd.form.title.miss" />
                          </form:option>
                        </form:select>
                         <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
                      </div>
                      <div class="col-xs-5">
                        <form:input path="firstName" class="form-control" id="fname" placeholder="First Name"
                                    type="text"  style="width: 150px;" />
                      </div>
                    </div>
                    <div class="col-xs-6">
                      <%--<div class="col-xs-1"></div>--%>
                      <div class="col-xs-5">
                        <form:input path="lastName" class="form-control" id="lname" placeholder="Last Name"
                                    type="text"  style="width: 170px;"/>

                      </div>
                    </div>
                </div>
            </div>
                <div class="row">
                    <label  class="col-xs-3 control-label" ></label>
                    <div class="col-xs-8">
                        <div class="col-xs-6">
                            <div class="col-xs-12">
                                <form:errors path="firstName" cssClass="help-inline" cssStyle="margin: 0 9px 0 -19px;"/>
                            </div>
                        </div>

                        <div class="col-xs-6">
                            <form:errors path="lastName" cssClass="help-inline" cssStyle="margin: 0 10px 0 -20px;"/>
                        </div>
                    </div>
                </div>
            </div><br>

          <div class="form-group">
              <div class="row">
                <label  class="col-xs-3 control-label" >
                    <fmt:message key="user.useradd.form.address" />
                </label>
                <div class="col-xs-8">
                  <div class="col-xs-12">
                    <div class="col-xs-12">
                      <form:input path="addressL1" class="form-control" id="addLine1" placeholder="Street Address"
                                  type="text"/>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label" ></label>
                  <div class="col-xs-8">
                      <div class="col-xs-8">
                          <div class="col-xs-12">
                            <form:errors path="addressL1" cssClass="help-inline" cssStyle="margin: 0 60px 0 -40px;"/>
                          </div>
                      </div>
                      <div class="col-xs-4"></div>
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
                                  type="text" />

                    </div>
                  </div>
                  <div class="col-xs-6"></div>
                </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label" ></label>
                  <div class="col-xs-8">
                      <div class="col-xs-8">
                        <div class="col-xs-12">
                            <form:errors path="addressL2" cssClass="help-inline" cssStyle="margin: 0 60px 0 -40px;"/>
                        </div>
                      </div>
                  </div>
              </div>
              <div class="row"> <label class="control-label"></label>   </div>
              <div class="row">
                  <label  class="col-xs-3 control-label">
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input path="addressL3" class="form-control" id="addLine3" placeholder="Address Line 3"
                                          type="text" />

                          </div>
                      </div>
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                            <input  class="form-control" id="province" placeholder="Province" type="text"/>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
              <br>
          <div class="form-group">
              <div class="row">
                  <label  class="col-xs-3 control-label">
                      <fmt:message key="user.useradd.form.mobile" />
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input path="mobile" class="form-control" id="mobileNo" placeholder="+94XXXXXXXXX"
                                              type="text" style="width: 260px;"/>
                          </div>
                      </div>
                      <div class="col-xs-6"></div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                            <form:errors path="mobile" cssClass="help-inline" cssStyle=""/>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label">
                      <fmt:message key="user.useradd.form.email"/>
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input path="email" class="form-control" id="email" placeholder="myname@example.com"
                                              type="text" style="width: 260px"/>

                          </div>
                      </div>
                      <div class="col-xs-6"></div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:errors path="email"  cssClass="help-inline" cssStyle="color: red;margin: 0 10px 0 -30px;"/>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label" >
                      <fmt:message key="user.useradd.form.username" />
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input path="username" class="form-control" name="username_admin" id="username_admin"
                                              placeholder="User Name" type="text" style="width: 260px" />

                          </div>
                      </div>
                      <div class="col-xs-6"></div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-8">
                          <div class="col-xs-12">
                              <form:errors path="username" cssClass="help-inline" cssStyle=" margin: 0 10px 0 -85px;"/>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-10">
                          <div class="col-xs-12">
                              <label class="pswdLabel" >Minimum 8 characters at least 1 Alphabet and 1 Number:</label>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label" >
                      <fmt:message key="user.useradd.form.password" />
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input path="password" class="form-control" id="password1" placeholder="Password"
                                              type="password" style="width: 260px"
                                           />
                          </div>
                      </div>
                      <div class="col-xs-6"></div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-8">
                          <div class="col-xs-12">
                              <form:errors path="password" cssClass="help-inline" cssStyle="margin: 0 10px 0 -90px;"/>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="row">
                  <label  class="col-xs-3 control-label" >
                      <fmt:message key="user.useradd.form.cpassword"/>
                  </label>
                  <div class="col-xs-8">
                      <div class="col-xs-6">
                          <div class="col-xs-12">
                              <form:input  class="form-control" path="cpassword" placeholder="Password Confirm"
                              type="password" style="width: 260px" onchange="passwordsEqual(cpassword,password1)"/>
                          </div>
                      </div>
                      <div class="col-xs-6"></div>
                  </div>
              </div>
              <div class="row">
                  <label class="col-xs-3 control-label"></label>
                  <div class="col-xs-8">
                      <div class="col-xs-8">
                          <div class="col-xs-12">
                              <form:errors path="cpassword" cssClass="help-inline" cssStyle="margin: 0 20px 0 -85px;"/>
                          </div>
                      </div>
                  </div>
              </div>
          </div><br>

          <!--when the user is a customer-->
          <div class="form-group">
              <div class="col-xs-12">
                 <fieldset class="scheduler-border" id="staffDetail">
                   <legend class="scheduler-border" id="legendStaff"><fmt:message key="user.useradd.form.staff.legend.title"
                                                                                />
                   </legend>
                   <div class="row">
                       <label  class="col-xs-4 control-label" >
                            <fmt:message key="user.useradd.form.designation" />
                       </label>
                       <label  class="col-xs-4 control-label" >
                           <fmt:message key="user.useradd.form.department"/>
                       </label>
                       <label class="col-xs-4 control-label"  >
                           <fmt:message key="user.useradd.form.branch"  />
                       </label>
                   </div>
                   <div class="row">
                         <div class="col-xs-4">
                            <form:select path="designation" class="form-control" id="designation">
                                <form:option value="System Admin"><fmt:message key="user.staff.designation1"  />
                                </form:option>
                                <form:option value="System Manager"><fmt:message key="user.staff.designation2" />
                                </form:option>
                                <form:option value="Customer Manager"><fmt:message key="user.staff.designation3"/>
                                </form:option>
                                <form:option value="Cashier"><fmt:message key="user.staff.designation4" />
                                </form:option>
                                <form:option value="Main Chef"><fmt:message key="user.staff.designation5"/>
                                </form:option>
                                <form:option value="Reporting Manager"><fmt:message key="user.staff.designation6" />
                                </form:option>
                            </form:select>
                         </div>
                         <div class="col-xs-4">
                            <form:select path="department" class="form-control" id="department">
                                <form:option value="Finance"><fmt:message key="user.staff.department1" />
                                </form:option>
                                <form:option value="Administration"><fmt:message key="user.staff.department2" />
                                </form:option>
                                <form:option value="Logistic"><fmt:message key="user.staff.department3" />
                                </form:option>
                                <form:option value="Reporting"><fmt:message key="user.staff.department4" />
                                </form:option>
                            </form:select>
                         </div>
                         <div class="col-xs-4">
                            <form:select path="branch" class="form-control" id="branch">
                                <form:option value="Colombo"><fmt:message key="user.staff.branch1"/>
                                </form:option>
                                <form:option value="Gampaha"><fmt:message key="user.staff.branch2"/>
                                </form:option>
                                <form:option value="Ja-Ela"><fmt:message key="user.staff.branch3"  />
                                </form:option>
                                <form:option value="Kadana"><fmt:message key="user.staff.branch4"/>
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
                                  <fmt:message key="user.useradd.form.button.reset" /></form:button>
                          </div>
                      </div>
                      <div class="col-xs-6">
                          <div class="col-xs-5">
                              <form:button type="submit" class="btn btn-success btn-md" id="btnAddItem"><span
                                    class="glyphicon glyphicon-plus"></span>
                                  <fmt:message key="user.useradd.form.button.submit" />
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

</body>
</html>