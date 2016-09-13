<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 9/13/16
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Image</title>
</head>
<body>
  knock
<img src="/WEB-INF/bg.png">
<img src="<c:url value="/images/bg.png"/>"/>

</body>
</html>
