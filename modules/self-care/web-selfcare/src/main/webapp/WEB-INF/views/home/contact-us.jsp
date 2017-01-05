<!DOCTYPE html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<head>
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="contactus" bundle="${lang}"/></title>
    <link href="<c:url value="/resources/css/prettify.css"/>">
    <script src="<c:url value="/resources/js/prettify.js"/>"></script>
    <script src="<c:url value="/resources/js/loader.js"/>"></script>

    <script>
        $(document).ready(function () {

            var name = "${name}";
            var mobile = "${mobile}";
            var email = "${email}";

            $("#txt-fname").val(name);
            $("#txt-email").val(email);
            $("#txt-contactno").val(mobile);
        });
    </script>

    <link href="<c:url value="/resources/css/contactus.css"/>" rel="stylesheet" type="text/css" media="all"/>

    <script>
        $("#location-bar a").click(function (event) {
            event.preventDefault();

            var $this = $(this),
                    $li = $this.parent(),
                    selectedMap = $this.attr("href"),
                    selectedLocation = $this.data('location');

            $li.addClass('active').siblings('li').removeClass('active');
            //Update #map bkimage with the image from the location
            $('#map').css('background-image', 'url(' + selectedMap + ')');
            //update tooltip 'address'
            $('.selectedLocation').text(selectedLocation);
        });
    </script>


    <style>
        .successContact {
            margin-top: 65%;
            margin-bottom: 60%;
        }
    </style>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>

<div class="loader-anim"></div>
<div id='browser'>

    <div id='browser-bar'>
        <div class='circles'></div>
        <div class='circles'></div>
        <div class='circles'></div>
        <h2 style="color: #f5f5f5; text-align: center; margin-top: 5px"><fmt:message key="new.header.contact"
                                                                                     bundle="${lang}"/></h2>
        <span class='arrow entypo-resize-full'></span>
    </div>

    <div id='content'>
        <div id='left'>
            <div id='map'>
                <div class='zoom'></div>
            </div>
            <ul id='location-bar'>

                <li>
                    <a class='location' target="_blank" data-location='USA'
                       href='https://www.google.lk/maps/place/Pizza+Hut/@6.8692043,79.8881169,17z/data=!4m13!1m7!3m6!1s0x3ae25a449198c277:0x3a66d5a707da2344!2sPizza+Hut!3b1!8m2!3d6.869199!4d79.890311!3m4!1s0x3ae25a449198c277:0x3a66d5a707da2344!8m2!3d6.869199!4d79.890311'>Nugegoda</a>
                </li>
                <li>
                    <a class='location' target="_blank" data-location='The Netherlands'
                       href='https://www.google.lk/maps/place/Pizza+Hut/@6.9199283,79.8567739,17z/data=!4m13!1m7!3m6!1s0x3ae2591349f2a849:0xfc866a05259056c4!2sPizza+Hut!3b1!8m2!3d6.919923!4d79.858968!3m4!1s0x3ae2591349f2a849:0xfc866a05259056c4!8m2!3d6.919923!4d79.858968'>Galle
                        Road</a>
                </li>
                <li>
                    <a class='location' target="_blank" data-location='Singapore'
                       href='https://www.google.lk/maps/place/Piza+Hut/@6.8394186,79.9751891,17.25z/data=!4m5!3m4!1s0x3ae251b82e0bc0e1:0x8175e7598d18dd25!8m2!3d6.8397156!4d79.97585'>Kottawa</a>
                </li>
            </ul>
        </div>

        <div id='right'>
            <p style="color: whitesmoke"><fmt:message key="contactus.connect" bundle="${lang}"/></p>

            <div id='social'>
                <a href="http://www.facebook.com/" class='social' target="_blank">
                    <span class='fa fa-facebook-official'></span>
                </a>
                <a href="https://twitter.com/" class='social' target="_blank">
                    <span class='fa fa-twitter'></span>
                </a>
                <a href="https://www.linkedin.com/" class='social' target="_blank">
                    <span class='fa fa-linkedin'></span>
                </a>
                <a href="https://plus.google.com/" class='social' target="_blank">
                    <span class='fa fa-google-plus'></span>
                </a>
                <a href="https://www.instagram.com/" class='social' target="_blank">
                    <span class='fa fa-instagram'></span>
                </a>
            </div>

            <c:if test="${empty validForm}">

                <form:form action="/web-selfcare/contact-us" method="post" modelAttribute="contactus">
                    <c:if test="${not empty invalidForm}">
                        <div class="row" style="background: #efe6f4; height: 35px">
                            <center><h6 style="color:darkred;">${invalidForm}</h6></center>
                        </div>
                    </c:if>
                    <form:select cssClass="form-control" path="inquiryType">
                        <form:option value="1"><fmt:message key="contactus.complaint" bundle="${lang}"/></form:option>
                        <form:option value="2"><fmt:message key="contactus.suggestion" bundle="${lang}"/></form:option>
                        <form:option value="3"><fmt:message key="contactus.clarification"
                                                            bundle="${lang}"/></form:option>
                        <form:option value="4"><fmt:message key="contactus.other" bundle="${lang}"/></form:option>
                    </form:select>
                    <form:errors path="inquiryType"/>

                    <form:select class="form-control" path="title">
                        <form:option value="Mr"><fmt:message key="signup.mr" bundle="${lang}"/></form:option>
                        <form:option value="Mrs"><fmt:message key="signup.mrs" bundle="${lang}"/></form:option>
                        <form:option value="Miss"><fmt:message key="signup.miss" bundle="${lang}"/></form:option>
                        <form:option value="Dr"><fmt:message key="signup.dr" bundle="${lang}"/></form:option>
                        <form:option value="Rev"><fmt:message key="signup.rev" bundle="${lang}"/></form:option>
                        "<fmt:message key="contactus.yourn" bundle="${lang}"/>"
                    </form:select>
                    <form:errors path="title"/>

                    <form:input cssClass="form-control" path="name" id="txt-fname" type="text"
                                placeholder="   Your Name"/>
                    <form:errors path="name"/>

                    <form:input cssClass="form-control" path="contactEmail" id="txt-email" type="text"
                                placeholder="   example@domain.com"></form:input>
                    <form:errors path="contactEmail"/>

                    <form:input cssClass="form-control" id="txt-contactno" type="text" placeholder="   0-XXXXXXXXXX"
                                path="contactNo"/>
                    <form:errors path="contactNo"/>

                    <form:textarea path="msg" cssClass="form-control" id="txt-inquirymsg" type="text"
                                   placeholder=" Your Message"/>
                    <form:errors path="msg"/>

                    <br>
                    <center>
                        <button class="btn btn-success" id="btn-proceed-submit" type="submit">
                            <fmt:message key="contactus.submit" bundle="${lang}"/>
                        </button>
                    </center>

                </form:form>

            </c:if>
            <c:if test="${not empty validForm}">
                <div class="successContact">
                    <center><fmt:message key="contactus.submit.success" bundle="${lang}"/></center>
                </div>
            </c:if>

            <p style="color: whitesmoke"><fmt:message key="contactus.other.contact" bundle="${lang}"/></p>

            <p class='other'>
                <a href='#' style="color: whitesmoke"> <i class="fa fa-envelope" aria-hidden="true"></i> <fmt:message
                        key="contactus.email" bundle="${lang}"/></a>
            </p>

            <p class='other' style="color: whitesmoke"><i class="fa fa-phone" aria-hidden="true"></i> <fmt:message
                    key="contactus.number" bundle="${lang}"/></p>

        </div>
    </div>
</div>


</body>
</html>