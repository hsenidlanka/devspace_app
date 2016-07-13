
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <title></title>
</head>
<body>

<fmt:setLocale value="es"/>
<fmt:setBundle basename="messages" var="lang"/>

<fmt:message key="login.title" bundle="${lang}"/>

</body>
</html>
