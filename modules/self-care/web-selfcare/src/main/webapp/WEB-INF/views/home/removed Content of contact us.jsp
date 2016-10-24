<div class="form-group">

  <form:label cssClass="col-sm-4 control-label" path="inquiryType" for="typeOfRespond">
    <fmt:message key="contactus.inquiry" bundle="${lang}"/>
  </form:label>

  <div class="col-sm-2">
    <div class="dropdown">

      <form:select cssClass="form-control" path="inquiryType">

        <form:option value="complaint"><fmt:message key="contactus.complaint" bundle="${lang}"/></form:option>
        <form:option value="suggestion"><fmt:message key="contactus.suggestion" bundle="${lang}"/></form:option>
        <form:option value="clarify"><fmt:message key="contactus.clarification" bundle="${lang}"/></form:option>
        <form:option value="other"><fmt:message key="contactus.other" bundle="${lang}"/></form:option>

      </form:select>
    </div>
  </div>
</div>
<div class="form-group">

  <form:label cssClass="col-sm-4 control-label " path="title" for="title">
    Your Name
  </form:label>

  <div class="dropdown col-sm-2">

    <form:select class="form-control" path="title">
      <form:option value="mr"><fmt:message key="signup.mr" bundle="${lang}"/></form:option>
      <form:option value="mrs"><fmt:message key="signup.mrs" bundle="${lang}"/></form:option>
      <form:option value="miss"><fmt:message key="signup.miss" bundle="${lang}"/></form:option>
      <form:option value="miss"><fmt:message key="signup.dr" bundle="${lang}"/></form:option>
      <form:option value="miss"><fmt:message key="signup.rev" bundle="${lang}"/></form:option>
      "<fmt:message key="contactus.yourn" bundle="${lang}"/>"
    </form:select>
  </div>
  <%--<spring:message code="contactus.yourn" var="yourName"/>--%>
  <div class="col-sm-4 row">
    <form:input path="name" cssClass="form-control" id="txt-fname" type="text" placeholder="Test" />
    <label id="fname_error" class="error-labels"></label>

  </div>
</div>

<div class="form-group">
  <%--<form:input path="contactEmail"></form:input>--%>
  <form:label cssClass="col-sm-4 control-label" path="sss">
    <fmt:message key="signup.email" bundle="${lang}"/> </form:label>

  <div class="col-sm-6">

    <form:input cssClass="form-control" id="txt-email" type="text" placeholder="example@domain.com" path="contactEmail" ></form:input>
    <label id="email_error" class="error-labels"></label>

  </div>
</div>
<form:input path="name"/>
<div class="form-group">

  <form:label cssClass="col-sm-4 control-label" path="">
    <fmt:message key="signup.phone" bundle="${lang}"/></form:label>

  <div class="col-sm-6">

    <form:input cssClass="form-control" id="txt-contactno" type="text" placeholder="+94-XXXXXXXXXX" path="contactNo" />
    <%--<label id="contactno_error" class="error-labels"></label>--%>

  </div>
</div>
<div class="form-group">

  <form:label cssClass="col-sm-4 control-label" path="msg">
    <fmt:message key="contactus.message" bundle="${lang}"/></form:label>

  <div class="col-sm-6">
    <%--<spring:message code="contactus.your.inquiry" var="EnterMsg"/>--%>

    <form:textarea path="msg" cssClass="form-control" id="txt-inquirymsg" type="text"
                   placeholder="Testtextarea" />
    <%--<label id="inquirymsg_error" class="error-labels"></label>--%>

  </div>
</div>


<div class="form-group">
  <button id="btn-proceed-submit" type="submit" class="btn btn-success">
    <fmt:message key="contactus.submit" bundle="${lang}"/>
  </button>

</div>
