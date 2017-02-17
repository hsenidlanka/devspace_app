
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

</div> <!-- END #content -->

<footer>
    <div id="copyright">
        <p><spring:message code="copyright" /></p>
        <p id="footer-sub-msg"><spring:message code="apereo.powered.by"/> <a href="http://www.apereo.org/cas"><spring:message code="apereo.cas.name"/> <%=org.jasig.cas.CasVersion.getVersion()%></a>
        <br><spring:message code="company.name"/> </p>
    </div>
</footer>

</div> <!-- END #container -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/headjs/1.0.3/head.min.js"></script>
<spring:theme code="cas.javascript.file" var="casJavascriptFile" text="" />
<script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>
</body>
</html>

