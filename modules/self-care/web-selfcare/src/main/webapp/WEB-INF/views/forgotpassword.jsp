<!DOCTYPE html>
<html>
<head>
    <%@include file="include.jsp" %>

    <script src="<c:url value="/resources/js/contactus_validate.js"/>" ></script>

    <script src="<c:url value="/resources/js/validate-inputs.js"/>" ></script>
    <script type="text/javascript">

        function usage(){
            $.notify("Alert!");
        }

        function api_type(type){
            $.notify("Your verification email has send to reset the password!", {type:type});
        }

        function api_position(){
            var an = $(":radio[name=align]:checked").val();
            var vn = $(":radio[name=verticalAlign]:checked").val();
            $.notify("Alert!", {align:an, verticalAlign:vn});
        }

        function api_delay(){
            $.notify("Alert!", {delay: $("#delayValue").val()});
        }

        function myFunc(){
            alert("삭제하였습니다");
        }

        function example1(){
            $.notify({
                delay : 999999,
                message : "hello world!",
                type : "warning",
                close : "true",
                animation : true,
                animationType : "scale",
                align: "center",
                verticalAlign: "middle",
                color: "#777",
                background: "#eee"
            });
        }

        function example2(){
            $.notify({
                delay : 0,
                message : "hello world!",
                animation : true,
                align: "center",
                verticalAlign: "middle",
                buttons: ["확인","취소"],
                buttonFunc: ["test"],
                buttonAlign: "right",
                blur: 0.2
            });
        }

        function test(){
            alert("확인");
        }

        $(function(){
            $("input[name=align]:radio").change(function(){
                $("#positionBtn").html("align : <var>"+$(this).val()+"</var>, verticalAlign : <var>"+$(":radio[name=verticalAlign]:checked").val()+"</var>");
            });
            $("input[name=verticalAlign]:radio").change(function(){
                $("#positionBtn").html("align : <var>"+$(":radio[name=align]:checked").val()+"</var>, verticalAlign : <var>"+$(this).val()+"</var>");
            });
        });


        !function ($) {
            $(function(){
                window.prettyPrint && prettyPrint()
            })
        }(window.jQuery)



    </script>
    <%--<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</head>
<div class="loader-anim"></div>


<body style="overflow: visible">
<%@include file="headertest2.jsp" %>

<div class="brand"></div>
<center>
    <div class="inner-bg">
        <div class="container">
            <div class="col-sm-12" style="height: 60px"></div>

            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">

                </div>
            </div>

            <div class="panel panel-success" style="width: 800px">
                <div class="panel-heading" style="height: 50px">
                    <p style="font-size: xx-large;text-align: left">Reset Password</p>
                </div>
                <div class="panel-body">
                    <form role="form" id="adduser_form" action="" method="post" class="form-horizontal">

                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                E-Mail
                            </label>

                            <div class="col-sm-6">

                                <input class="form-control" id="txt-email" type="email" placeholder=""  required>
                                <label id="email_error" class="error-labels"></label>

                            </div>

                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                New Password
                            </label>

                            <div class="col-sm-6">
                                <input class="form-control" id="passnew" type="text" placeholder="" required>
                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                Re-Enter Password
                            </label>

                            <div class="col-sm-6">
                                <input class="form-control" id="repwd" type="text" placeholder="" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <button id="btn-proceed-submit" type="submit" class="btn btn-success"  onclick="api_type('info')">Reset Password</button>
                        </div>
                    </form>

                </div>

            </div>
        </div>
    </div>
</center>
<%@include file="loginload.jsp" %>
<%@include file="signupmodal.jsp" %>


</body>
<%@include file="footer.jsp" %>
</html>

