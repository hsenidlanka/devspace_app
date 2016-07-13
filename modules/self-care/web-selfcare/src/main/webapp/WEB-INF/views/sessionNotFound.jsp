<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:bundle basename="messages">
    <div class="container">
        <div class="error">
            <fmt:message>Session not found</fmt:message>
        </div>
        <c:url var="Back" value="/"/>
        <input id="btn" class="button" type="submit" value="Back" onclick="window.location='${Back}';"/>
    </div>
    </body>
</fmt:bundle>