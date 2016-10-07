
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SessionTest</title>
</head>
<body>
<p>This is ${thought}</p>
  <form action="remember" method="post">
    <input name="thoughtParam" type="text">
    <input type="submit">
  </form>
</body>
</html>
