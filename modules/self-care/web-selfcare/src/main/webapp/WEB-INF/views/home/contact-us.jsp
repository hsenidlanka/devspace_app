<!DOCTYPE html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<head>
    <%@include file="../includes/include.jsp" %>
    <title><fmt:message key="contactus" bundle="${lang}"/></title>
    <link href="<c:url value="/resources/css/prettify.css"/>">
    <script src="<c:url value="/resources/js/prettify.js"/>"></script>
    <script src="<c:url value="/resources/js/loader.js"/>"></script>

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

    <script>
        $(document).ready(function () {
            var username = "${username}";
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
        .successContact{
            margin-top: 70%;
            margin-bottom: 70%;
        }
    </style>
</head>
<body>
<%@include file="../includes/new-header.jsp" %>

<div id='browser'>
    <div id='browser-bar'>
        <div class='circles'></div>
        <div class='circles'></div>
        <div class='circles'></div>
        <p><fmt:message key="new.header.contact" bundle="${lang}"/></p>
        <span class='arrow entypo-resize-full'></span>
    </div>
    <div id='content'>
        <div id='left'>
            <div id='map'>
                <p><fmt:message key="contactus.where.to.find" bundle="${lang}"/></p>
                <div class='map-locator'>
                    <div class='tooltip'>
                        <ul>
                            <li>
                                <span class='entypo-location'></span>
                                <span class='selectedLocation'><fmt:message key="location.sri" bundle="${lang}"/></span>
                            </li>
                            <li>
                                <span class='entypo-mail'></span>
                                <a href='#'><fmt:message key="contactus.email" bundle="${lang}"/></a>
                            </li>
                            <li>
                                <span class='entypo-phone'></span>
                                <fmt:message key="contactus.number" bundle="${lang}"/>
                            </li>
                        </ul>
                    </div>
                </div>
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
                <a class='social'>
                    <span class='entypo-facebook'></span>
                </a>
                <a class='social'>
                    <span class='entypo-twitter'></span>
                </a>
                <a class='social'>
                    <span class='entypo-linkedin'></span>
                </a>
                <a class='social'>
                    <span class='entypo-gplus'></span>
                </a>
                <a class='social'>
                    <span class='entypo-instagrem'></span>
                </a>
            </div>

            <c:if test="${empty validForm}">


            <form:form action="/web-selfcare/contact-us" method="post" modelAttribute="contactus">
                    ${invalidForm}

                <form:select cssClass="form-control" path="inquiryType">

                    <form:option value="1"><fmt:message key="contactus.complaint"
                                                        bundle="${lang}"/></form:option>
                    <form:option value="2"><fmt:message key="contactus.suggestion"
                                                        bundle="${lang}"/></form:option>
                    <form:option value="3"><fmt:message key="contactus.clarification"
                                                        bundle="${lang}"/></form:option>
                    <form:option value="4"><fmt:message key="contactus.other" bundle="${lang}"/></form:option>

                </form:select>

                <div class="has-error">
                    <form:errors path="inquiryType" class="help-inline"/>
                </div>


                <form:select class="form-control" path="title">
                    <form:option value="Mr"><fmt:message key="signup.mr" bundle="${lang}"/></form:option>
                    <form:option value="Mrs"><fmt:message key="signup.mrs" bundle="${lang}"/></form:option>
                    <form:option value="Miss"><fmt:message key="signup.miss" bundle="${lang}"/></form:option>
                    <form:option value="Dr"><fmt:message key="signup.dr" bundle="${lang}"/></form:option>
                    <form:option value="Rev"><fmt:message key="signup.rev" bundle="${lang}"/></form:option>
                    "<fmt:message key="contactus.yourn" bundle="${lang}"/>"
                </form:select>

                <div class="has-error">
                    <form:errors path="title" class="help-inline"/>
                </div>

                <form:input path="name" cssClass="form-control" id="txt-fname" type="text" placeholder="Your Name"/>
                <%--<label id="fname_error" class="error-labels"></label>--%>
                <div class="has-error">
                    <form:errors path="name" class="help-inline"/>
                </div>


                <form:input path="contactEmail" cssClass="form-control" id="txt-email" type="text"
                            placeholder="example@domain.com"></form:input>
                <form:errors path="contactEmail"/>


                <form:input cssClass="form-control" id="txt-contactno" type="text" placeholder="+94-XXXXXXXXXX"
                            path="contactNo"/>

                <div class="has-error">
                    <form:errors path="contactNo" class="help-inline"/>
                </div>

                <form:textarea path="msg" cssClass="form-control" id="txt-inquirymsg" type="text"
                               placeholder="Your Message"/>

                <div class="has-error">
                    <form:errors path="msg" class="help-inline"/>
                </div>


                <center>
                    <button class="btn btn-success" id="btn-proceed-submit" type="submit">
                        <fmt:message key="contactus.submit" bundle="${lang}"/>
                    </button>
                </center>


            </form:form>

            </c:if>
            <c:if test="${not empty validForm}">
            <div class="successContact"><center>Your Form Successfully Submitted</center></div>
            </c:if>


            <p style="color: whitesmoke">other way</p>

            <p class='other entypo-mail'>
                <a href='#'><fmt:message key="contactus.email" bundle="${lang}"/></a>
            </p>

            <p class='other entypo-phone'><fmt:message key="contactus.number" bundle="${lang}"/></p>
        </div>
    </div>
</div>


</body>
</html>