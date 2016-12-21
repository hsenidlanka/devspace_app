<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:set var="salary" value="Test Page"/>

${salary}
<br>
<c:set var="string2" value="${fn:replace(salary, ' ', '')}" />
${string2}
</body>
</html>
