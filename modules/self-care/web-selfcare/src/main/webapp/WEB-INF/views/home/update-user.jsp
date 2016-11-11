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

            $('input:radio[name="titlejs"]').filter('[value=' + titlejs + ']').attr('checked', true);

//            $("#titlejs").val(titlejs);
            $("#firstNamejs").val($.trim(firstNamejs));
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
<style>

    hr {
        margin-top: 12px;
        margin-bottom: 12px;
        box-shadow: 0 10px 10px -10px #88C470 inset !important;
    }

    b {
        text-align: justify;
        font-style: italic;
        font-size: 36px;
    }

    .updatesuccess {
        font-size: 27px;
    }

    input[type=text] {
        display: inline-block;
        -webkit-box-sizing: content-box;
        -moz-box-sizing: content-box;
        box-sizing: content-box;
        padding: 6px auto 6px 20px;
        border: 4px solid rgba(226,183,226,0.7);
        -webkit-border-radius: 13px;
        border-radius: 13px;
        font: normal 17px/normal Arial, Helvetica, sans-serif;
        color: rgba(0,0,0,0.9);
        text-align: center;
        -o-text-overflow: ellipsis;
        text-overflow: ellipsis;
        letter-spacing: 1px;
        text-shadow: 1px 1px 0 rgba(9,10,10,0.66) ;
        -webkit-transition: background-color 200ms cubic-bezier(0.23, 1, 0.32, 1);
        -moz-transition: background-color 200ms cubic-bezier(0.23, 1, 0.32, 1);
        -o-transition: background-color 200ms cubic-bezier(0.23, 1, 0.32, 1);
        transition: background-color 200ms cubic-bezier(0.23, 1, 0.32, 1);
    }
</style>

<body>
    <%@include file="../includes/new-header.jsp" %>
<div class="container" style="width: 60%">

    <center>
        <form:form action="/web-selfcare/updateuser" method="post" modelAttribute="updateuser"
                   cssClass="well form-horizontal">
            <center><b>Update User?</b></center>
            <center><p class="updatesuccess">${updatedMsg}</p></center>
            <hr>
            <div class="form-group">
                <label class="control-label col-sm-2"><p align="right">Title</p></label>

                <div class="row col-sm-10">

                    <form:radiobutton path="title" name="titlejs" id="titlejs" value="Mr"/>Mr
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:radiobutton path="title" id="titlejs" name="titlejs" value="Mrs"/>Mrs
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:radiobutton path="title" id="titlejs" name="titlejs" value="Ms"/>Ms
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:radiobutton path="title" id="titlejs" value="Dr"/>Dr
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:radiobutton path="title" id="titlejs" value="Rev"/>Rev

                </div>
                <div class="row">
                    <form:errors path="title" cssClass="help-inline" cssStyle="color: red"/>
                </div>


            </div>

            <hr>

            <div class="form-group">
                <form:label path="firstName" for="firstName" cssClass="col-md-2 control-label">
                    <p align="right">First Name</p>
                </form:label>

                <div class="col-md-10">
                    <div class="row inline">
                        <form:input id="firstNamejs" path="firstName" cssClass="enjoy-css" cssStyle="width: 72%"/>
                    </div>
                    <div class="row">
                        <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
            </div>
            <%----%>
            <hr>
            <div class="form-group">

                <form:label path="lastName" for="lastName" cssClass="col-md-2 control-label">
                    <p align="right">Last Name</p>
                </form:label>

                <div class="col-md-10">
                    <div class="row inline">
                        <form:input path="lastName" id="lastNamejs" cssStyle="width: 72%"/>
                    </div>
                    <div class="row">
                        <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>

            </div>
            <hr>
            <div class="form-group">
                <form:label path="username" for="username" cssClass="control-label col-sm-2">
                    <p align="right">Username</p>
                </form:label>

                <div class="col-sm-10">
                    <div class="row inline">
                        <form:input id="usernamejs" path="username" cssStyle="width:72%" readonly="true"/>
                    </div>
                    <div class="row">
                        <form:errors path="username" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <form:label path="email" for="email" cssClass="col-md-2 control-label">
                    <p align="right">Email</p>
                </form:label>

                <div class="col-sm-10">
                    <div class="row inline">
                        <form:input id="emailjs" path="email" cssStyle="width:72%"/>
                    </div>
                    <div class="row">
                        <form:errors path="email" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>

            </div>
            <hr>
            <div class="form-group">
                <form:label path="mobile" for="mobile" cssClass="col-md-2 control-label">
                    <p align="right">Mobile</p>
                </form:label>

                <div class="col-sm-10">
                    <div class="row inline">
                        <form:input id="mobilejs" path="mobile" cssStyle="width:72%"/>
                    </div>
                    <div class="row">
                        <form:errors path="mobile" cssClass="help-inline" cssStyle="color: red"/>
                    </div>
                </div>
            </div>
            <hr>
            <div class="form-group">
                <form:label path="addressLine01" for="addressLine01" cssClass="col-md-2 control-label">
                    <p align="right">Address</p>
                </form:label>
                <div class="col-sm-10">

                    <div class="row">
                        <div class="row inline">
                            <form:input id="addr1js" path="addressLine01" cssStyle="width:72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="addressLine01" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="row inline">
                            <form:input id="addr2js" path="addressLine02" cssStyle="width:72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="addressLine02" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="row inline">
                            <form:input id="addr3js" path="addressLine03" cssStyle="width:72%"/>
                        </div>
                        <div class="row">
                            <form:errors path="addressLine03" cssClass="help-inline" cssStyle="color: red"/>
                        </div>
                    </div>
                </div>
            </div>
            <hr>

            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <button type="submit" class="btn btn-warning">Update Profile<span
                            class="glyphicon glyphicon-send"></span></button>
                </div>
                <div class="col-sm-4"></div>

            </div>
        </form:form>
    </center>

</div>
</body>

</html>
