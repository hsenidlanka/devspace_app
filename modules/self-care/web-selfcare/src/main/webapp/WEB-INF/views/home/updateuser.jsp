<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Update user ${username}</title>

    <script>
        $(document).ready(function () {

            var usernamejs = "${username}";
            var namejs = "${name}";

            var titlejs = "${title}";
            var firstNamejs = "${firstName}";
            var lastNamejs = "${lastName}";
            var addr1js = "${addr1}";
            var addr2js = "${addr2}";
            var addr3js = "${addr3}";
            var mobilejs = "${mobile}";
            var emailjs = "${email}";

            $("#titlejs").val(titlejs);
            $("#firstNamejs").val(firstNamejs);
            $("#lastNamejs").val(lastNamejs);
            $("#addr1js").val(addr1js);
            $("#addr2js").val(addr2js);
            $("#addr3js").val(addr3js);
            $("#mobilejs").val(mobilejs);
            $("#emailjs").val(emailjs);
            $("#usernamejs").val(usernamejs);

        });
    </script>

</head>
<body>

<%@include file="../includes/NewHeader.jsp" %>

<div class="container" style="width: 70%">

    <form:form cssClass="well form-horizontal" action="/web-selfcare/updateuser" modelAttribute="updateuser" method="post">
        <legend>Update profile</legend>
        <div class="form-group">
            <label class="col-md-4 control-label">State</label>
            <div class="col-md-4" style="width: 36%">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                    <form:select path="title" id="titlejs" cssClass="form-control selectpicker">
                        <form:option value="Mr">Mr</form:option>
                        <form:option value="Mrs">Mrs</form:option>
                        <form:option value="Ms">Ms</form:option>
                        <form:option value="Dr">Dr</form:option>
                        <form:option value="Rev">Rev</form:option>
                    </form:select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="username" for="username" cssClass="col-md-4 control-label">Username</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="usernamejs" path="username" disabled="true"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="firstName" for="firstName" cssClass="col-md-4 control-label">First Name</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="firstNamejs" path="firstName"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="lastName" for="lastName" cssClass="col-md-4 control-label">Last Name</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="lastNamejs" path="lastName"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="email" for="email" cssClass="col-md-4 control-label">Email</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                    <form:input id="emailjs" path="email"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="mobile" for="mobile" cssClass="col-md-4 control-label">Mobile</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                    <form:input id="mobilejs" path="mobile"/>
                </div>
            </div>
        </div>

        <div class="form-group">
            <form:label path="addressLine01" for="addressLine01" cssClass="col-md-4 control-label">Address</form:label>
            <div class="col-md-4 inputGroupContainer">
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr1js" path="addressLine01"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr2js" path="addressLine02"/>
                </div>
                <div class="input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                    <form:input id="addr3js" path="addressLine03"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"></label>

            <div class="col-md-4">
                <button type="submit" class="btn btn-warning" >Update Profile<span class="glyphicon glyphicon-send"></span></button>
            </div>
        </div>
    </form:form>
</div>

<%--<div class="container">

&lt;%&ndash;<form:form action="/web-selfcare/updateuser" modelAttribute="updateuser" method="post">&ndash;%&gt;
    <form:form cssClass="well form-horizontal" action="/web-selfcare/updateuser" method="post"  id="contact_form">
        <fieldset>

            <!-- Form Name -->
            <legend>Contact Us Today!</legend>

            <!-- Text input-->

            &lt;%&ndash;<form:label path="title" for="title">Title</form:label>
            <form:select path="title">
                <form:option value="Mr">Mr</form:option>
                <form:option value="Mrs">Mrs</form:option>
                <form:option value="Ms">Ms</form:option>
                <form:option value="Dr">Dr</form:option>
                <form:option value="Rev">Rev</form:option>
            </form:select>&ndash;%&gt;

            <div class="form-group">
                <label class="col-md-4 control-label">State</label>
                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                        <form:select path="title" name="title" cssClass="form-control" >

                            <form:option value=" " >Please select your state</form:option>
                            <form:option value="">Alabama</form:option>
                            <form:option value="">Alaska</form:option>
                            <form:option value=""> Uttah</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label">First Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input  name="first_name" placeholder="First Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label" >Last Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="last_name" placeholder="Last Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label">E-Mail</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <input name="email" placeholder="E-Mail Address" class="form-control"  type="text">
                    </div>
                </div>
            </div>


            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Phone #</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                        <input name="phone" placeholder="(845)555-1212" class="form-control" type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Address</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <input name="address" placeholder="Address" class="form-control" type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">City</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <input name="city" placeholder="city" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Select Basic -->

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Zip Code</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <input name="zip" placeholder="Zip Code" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label">Website or domain name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
                        <input name="website" placeholder="Website or domain name" class="form-control" type="text">
                    </div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-4 control-label">Project Description</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
                        <textarea class="form-control" name="comment" placeholder="Project Description"></textarea>
                    </div>
                </div>
            </div>

            <!-- Success message -->


            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning" >Send <span class="glyphicon glyphicon-send"></span></button>
                </div>
            </div>

        </fieldset>
    </form:form>
</div>--%>


${name}
${number}
<%--
<form:form action="sendUpdateUser" >
    <form:select path="title">
        <form:option value="Mr">Mr</form:option>
        <form:option value="Mrs">Mrs</form:option>
        <form:option value="Ms">Ms</form:option>
        <form:option value="Dr">Dr</form:option>
        <form:option value="Rev">Rev</form:option>
    </form:select>
    <form:input path="username"/>
    <form:input path="firstName"/>
    <form:input path="lastName"/>
    <form:input path="email"/>
    <form:input path="mobile"/>
    <form:input path="addressLine01"/>
    <form:input path="addressLine02"/>
    <form:input path="addressLine03"/>

    <input type="submit" value="Update User">
</form:form>--%>


<%--<div class="container" style="width: 65%">

    <form:form cssClass="well form-horizontal" action="/web-selfcare/sendUpdateUser" method="post" id="contact_form">
        <fieldset>

            <!-- Form Name -->
            <legend>Update User Profile</legend>

            <div class="form-group">
                <form:label path="title2" for="title" cssClass="col-md-4 control-label">Title</form:label>

                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                        <form:select path="title" id="title" name="title" cssClass="form-control">
                            <form:option value="Mr">Mr</form:option>
                            <form:option value="Mrs">Mrs</form:option>
                            <form:option value="Ms">Ms</form:option>
                            <form:option value="Dr">Dr</form:option>
                            <form:option value="Rev">Rev</form:option>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="username" for="username" cssClass="col-md-4 control-label">First Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="username" id="notUsername" name="notUsername" placeholder="Username" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="firstName" for="firstName" cssClass="col-md-4 control-label">First Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="firstName" id="firstName" name="firstName" placeholder="First Name" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <!--Last Name -->

            <div class="form-group">
                <form:label path="lastName" for="lastName" cssClass="col-md-4 control-label">Last Name</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <form:input path="lastName" name="lastName" id="lastName" placeholder="Last Name" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:label path="email" for="email" cssClass="col-md-4 control-label">E-Mail</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <form:input path="email" name="email" id="notEmail" placeholder="E-Mail Address" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

                &lt;%&ndash;Address with three lines&ndash;%&gt;
            <div class="form-group">
                <form:label path="addressLine01" cssClass="col-md-4 control-label">Address</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine01" id="addr1" name="addr1" placeholder="Address Line 1" cssClass="form-control" type="text"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine02" id="addr2" name="addr2" placeholder="Address Line 2" cssClass="form-control" type="text"/>
                            &lt;%&ndash;<input name="addr2" id="addr2" placeholder="Address Line 2" class="form-control" type="text">&ndash;%&gt;
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <form:input path="addressLine03" id="addr3" name="addr3" placeholder="Address Line 3" cssClass="form-control" type="text"/>
                            &lt;%&ndash;<input name="addr3" id="addr3" placeholder="Address Line 3" class="form-control" type="text">&ndash;%&gt;
                    </div>
                </div>
            </div>

                &lt;%&ndash;Mobile number&ndash;%&gt;
            <div class="form-group">
                <form:label path="mobile" for="mobile" modelAttribute="updateuser" cssClass="col-md-4 control-label">Mobile No</form:label>

                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                        <form:input path="mobile" name="mobile" id="mobile" placeholder="(94)xx-xxxxxxx" cssClass="form-control" type="text"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label"></label>

                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning">Update profile <span class="glyphicon glyphicon-send"></span></button>
                </div>
            </div>

        </fieldset>
    </form:form>
</div>--%>

</body>
</html>
