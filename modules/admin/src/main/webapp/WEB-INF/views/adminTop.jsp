<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- CSS -->
<spring:url value="/themes/hsenid/bootstrap/css/bootstrap.min.css" var="bootstrapCss"/>
<link href="${bootstrapCss}" rel="stylesheet">

<spring:url value="/themes/hsenid/css/business-casual.css" var="formalCss"/>
<link href="${formalCss}" rel="stylesheet">


<!-- Javascript -->
<spring:url value="/themes/hsenid/bootstrap/js/jquery-3.1.0.min.js" var="jqueryJs"/>
<spring:url value="/themes/hsenid/bootstrap/js/bootstrap.min.js" var="bootstrapJs"/>

<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>


<!-- Fonts -->
<spring:url value="/themes/hsenid/bootstrap/css/fonts.css" var="fontCss"/>
<link href="${fontCss}" rel="stylesheet">

<!--bootstrap table-->
<spring:url value="/themes/hsenid/bootstrap/css/bootstrap-table.min.css" var="cssTable"/>
<link href="${cssTable}" rel="stylesheet">

<spring:url value="/themes/hsenid/bootstrap/js/bootstrap-table.min.js" var="tableJs"/>
<script src="${tableJs}"></script>

<%--jquery toaster--%>
<spring:url value="/themes/hsenid/js/jquery.toaster.js" var="jsToast"/>
<script src="${jsToast}"></script>

<%--bootstrap typeahead--%>
<spring:url value="/themes/hsenid/bootstrap/js/bootstrap-typeahead.js" var="jsTypeahead"/>
<script src="${jsTypeahead}"></script>

<%--bootstrap paginator--%>
<spring:url value="/themes/hsenid/bootstrap/js/simple-bootstrap-paginator.js" var="jsPaginator"/>
<script src="${jsPaginator}"></script>



