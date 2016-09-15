<!DOCTYPE html>
<html>
<head>
    <title>Give us a feedback</title>
    <%@include file="include.jsp" %>
    <script src="<c:url value="/resources/js/validate-inputs.js"/>" ></script>

    <script type="text/javascript">

        function usage(){
            $.notify("Alert!");
        }

        function api_type(type){
            $.notify("Thank you for your valued feedback", {type:type});
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

</head>
<div class="loader-anim"></div>
<body>
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
                    <p style="font-size: xx-large;text-align: left">Create Feedback</p>
                </div>
                <div class="panel-body">
                    <form role="form" id="adduser_form" action="" method="post" class="form-horizontal">

                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                Your Feedback </label>

                            <div class="col-sm-6">

                                <textarea class="form-control" id="txt-inquirymsg" type="text"
                                          placeholder="Tell how you feel about us" required></textarea>
                                <label id="inquirymsg_error" class="error-labels"></label>

                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-sm-11">
                                <label class="radio">
                                    <input type="radio" name="optradio">Display Feedback
                                </label></div>
                            <div class="col-sm-11">

                                <label class="radio">
                                    <input type="radio" name="optradio">Don't Display Feedback
                                </label>

                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-4 control-label">
                                Display Name </label>

                            <div class="col-sm-6">

                                <input class="form-control" id="txt-fname" type="text" placeholder="Name Shown Publicly"
                                       required>
                                <label id="fname_error" class="error-labels"></label>

                            </div>
                        </div>

                        <div class="form-group" style="width: 200px">
                            <button id="btn-proceed-submit" type="submit" class="btn btn-success" onclick="api_type('success')">Send</button>
                        </div>


                    </form>

                </div>

            </div>
        </div>
    </div>
    <a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>

</center>
</body>
</html>
