<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="contactus" bundle="${lang}"/></title>
    <%@include file="include.jsp" %>
    <link href="<c:url value="/resources/css/prettify.css"/>" >
    <script src="<c:url value="/resources/js/prettify.js"/>" ></script>

    <script src="<c:url value="/resources/js/contactus_validate.js"/>" ></script>
    <script src="<c:url value="/resources/js/loader.js"/>" ></script>
    <script src="<c:url value="/resources/js/validate-inputs.js"/>" ></script>

    <script type="text/javascript">

        function usage() {
            $.notify("Alert!");
        }

        function api_type(type) {
            $.notify("Thank you for your response.We'll get on to your inquiry as soon as possible", {type: type});
        }

        function api_position() {
            var an = $(":radio[name=align]:checked").val();
            var vn = $(":radio[name=verticalAlign]:checked").val();
            $.notify("Alert!", {align: an, verticalAlign: vn});
        }

        function api_delay() {
            $.notify("Alert!", {delay: $("#delayValue").val()});
        }

        function myFunc() {
            alert("삭제하였습니다");
        }

        function example1() {
            $.notify({
                delay: 999999,
                message: "hello world!hello world!hello world!hello world!hello world!hello world!hello world!hello world!hello world!hello world!",
                type: "warning",
                close: "true",
                animation: true,
                animationType: "scale",
                align: "center",
                verticalAlign: "middle",
                color: "#777",
                background: "#eee"
            });
        }

        function example2() {
            $.notify({
                delay: 0,
                message: "hello world!",
                animation: true,
                align: "center",
                verticalAlign: "middle",
                buttons: ["확인", "취소"],
                buttonFunc: ["test"],
                buttonAlign: "right",
                blur: 0.2
            });
        }

        function test() {
            alert("확인");
        }

        $(function () {
            $("input[name=align]:radio").change(function () {
                $("#positionBtn").html("align : <var>" + $(this).val() + "</var>, verticalAlign : <var>" + $(":radio[name=verticalAlign]:checked").val() + "</var>");
            });
            $("input[name=verticalAlign]:radio").change(function () {
                $("#positionBtn").html("align : <var>" + $(":radio[name=align]:checked").val() + "</var>, verticalAlign : <var>" + $(this).val() + "</var>");
            });
        });


        !function ($) {
            $(function () {
                window.prettyPrint && prettyPrint()
            })
        }(window.jQuery)


    </script>

</head>
<div class="loader-anim"></div>
<style>
    .status {
        display: inline;
        visibility: hidden;
        width: 15px
    }

    .IMGself {
        vertical-align: -10px;
        width: 15px;
        visibility: hidden
    }
</style>
<body style="overflow: visible">
<%@include file="NewHeader.jsp" %>

<div class="brand"></div>
<center>
    <div class="inner-bg">
        <div class="container">
            <div class="col-sm-12" style="height: 25px"></div>

            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">

                </div>
            </div>
            <div class="panel panel-success" style="width: 900px">
                <div class="panel-heading" style="height: 50px">
                    <p style="font-size: xx-large;text-align: left"><fmt:message key="contactus" bundle="${lang}"/></p>
                </div>
                <div class="panel-body">
                    <form role="form" id="contactus_form" action="" method="post" class="form-horizontal">

                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                <fmt:message key="contactus.inquiry" bundle="${lang}"/>
                            </label>

                            <div class="col-sm-2">
                                <div class="dropdown">

                                    <select class="form-control" required>
                                        <option value="complaint"><fmt:message key="contactus.select.type" bundle="${lang}"/></option>
                                        <option value="complaint"><fmt:message key="contactus.complaint" bundle="${lang}"/></option>
                                        <option value="suggestion"><fmt:message key="contactus.suggestion" bundle="${lang}"/></option>
                                        <option value="clarify"><fmt:message key="contactus.clarification" bundle="${lang}"/></option>
                                        <option value="other"><fmt:message key="contactus.other" bundle="${lang}"/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label ">
                                Your Name
                            </label>

                            <div class="dropdown col-sm-2">

                                <select class="form-control" required>
                                    <option value="mr"><fmt:message key="signup.mr" bundle="${lang}"/>Mr</option>
                                    <option value="mrs"><fmt:message key="signup.mrs" bundle="${lang}"/>Mrs</option>
                                    <option value="miss"><fmt:message key="signup.miss" bundle="${lang}"/></option>

                                </select>
                            </div>
                            <div class="col-sm-4 row">
                                <input class="form-control" id="txt-fname" type="text" placeholder="<fmt:message key="contactus.yourn" bundle="${lang}"/>" required>
                                <label id="fname_error" class="error-labels"></label>

                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                <fmt:message key="signup.email" bundle="${lang}"/> </label>

                            <div class="col-sm-6">

                                <input class="form-control" id="txt-email" type="text" placeholder="example@domain.com"
                                       required>
                                <label id="email_error" class="error-labels"></label>

                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                <fmt:message key="signup.phone" bundle="${lang}"/></label>

                            <div class="col-sm-6">

                                <input class="form-control" id="txt-contactno" type="text" placeholder="+94-XXXXXXXXXX"
                                       required>
                                <label id="contactno_error" class="error-labels"></label>

                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                <fmt:message key="contactus.message" bundle="${lang}"/></label>

                            <div class="col-sm-6">

                                <textarea class="form-control" id="txt-inquirymsg" type="text"
                                          placeholder="<fmt:message key="contactus.your.inquiry" bundle="${lang}"/>" required></textarea>
                                <label id="inquirymsg_error" class="error-labels"></label>

                            </div>
                        </div>


                        <div class="form-group">
                            <button id="btn-proceed-submit" type="submit" class="btn btn-success"
                                    onclick="api_type('success')"><fmt:message key="contactus.submit" bundle="${lang}"/>
                            </button>

                        </div>


                    </form>

                </div>

            </div>
        </div>
    </div>
    <div><a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>
    </div>

    <a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>

</center>

<!-- contact details incorrect popup -->
<div id="validation-error-popup" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title"><img src="<c:url value="/resources/images/icons/error.png"/>" >&nbsp;&nbsp;Validation errors
                </h2>
            </div>
            <div class="modal-body">
                <p style="font-size: medium;"><fmt:message key="contactus.cant" bundle="${lang}"/>
                    <b><fmt:message key="contactus.valid" bundle="${lang}"/></b><br>
                    <fmt:message key="contactus.please" bundle="${lang}"/>
                </p>
            </div>
            <div class="modal-footer">
                <a class="btn btn-default" data-dismiss="modal">Ok</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>