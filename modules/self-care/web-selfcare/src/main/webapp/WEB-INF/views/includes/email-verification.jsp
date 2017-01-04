<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../includes/include.jsp" %>
    <title>Verify Your account</title>
</head>
<body>
    <%@include file="../includes/new-header.jsp" %>
    <script>
        $(document).ready(function(){
            function GetURLParameter(sParam)
            {
                var sPageURL = window.location.search.substring(1);
                var sURLVariables = sPageURL.split('&');
                for (var i = 0; i < sURLVariables.length; i++)
                {
                    var sParameterName = sURLVariables[i].split('=');
                    if (sParameterName[0] == sParam)
                    {
                        return sParameterName[1];
                    }
                }
            }
            var user = GetURLParameter('username')
            $("#hiddenUsername").val(user);
            var test = $("#hiddenUsername").val();
//            alert("Mama Thama -> "+test);
        })
    </script>

    <div class="container">
        <%--<%= request.getParameter("username") %>--%>
        <form:form>
            <input id="hiddenUsername" type="hidden">
            <button onclick="">test</button>
        </form:form>
    </div>

</body>
</html>
