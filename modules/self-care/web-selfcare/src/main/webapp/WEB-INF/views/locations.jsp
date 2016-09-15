<!DOCTYPE html>
<html>
<head>
    <title>Locations</title>
    <%@include file="include.jsp" %>
    <div class="loader-anim"></div>
</head>

<!------------------------------------------------------------------------->
<!--------------Body------------------------->
<body style="overflow: visible" >

<%@include file="headertest2.jsp" %>

<%--'https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyAWJ-UMsacU4LmjA3dtfctz2UsPl5s2tZw'--%>

<div class="col-sm-12" style="height: 40px"></div>
<div class="col-sm-1"></div>
<div class="col-sm-4">
    <script src="<c:url value="/resources/js/googlemaps.js"/>" ></script>
    <div style='overflow:hidden;height:400px;width:520px;border:outset #f5f5f5;'>
        <div id='gmap_canvas' style='height:400px;width:520px;'>

        </div>
        <style>#gmap_canvas img{max-width:none!important;background:none!important}</style>
    </div>
    <a href='https://www.add-map.net/' >.</a>
    <script type='text/javascript' src="<c:url value="/resources/js/googlemaps2.js"/>"></script>
    <script type='text/javascript'>
        function init_map(){var myOptions =
        {zoom:12,center:new google.maps.LatLng(6.91767744776597,79.86787278331086),mapTypeId: google.maps.MapTypeId.ROADMAP};
            map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
            marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(6.91767744776597,79.86787278331086)});
            infowindow = new google.maps.InfoWindow({content:'<strong>PizzaShefu</strong><br>Ward Place<br>10250 Colombo7<br>'});
            google.maps.event.addListener(marker, 'click', function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);
    </script>
</div>
<div class="col-sm-2"></div>
<div class="col-sm-5">
    <p style="color: #c09853;font-size: x-large">location</p>
</div>
<div class="col-sm-3"></div>
<div class="col-sm-4 location">
    <p>PizzaShefu </p>
    <p>Ward Place</p>
    <p> Colombo 7</p>
    <p> Sri Lanka</p>
</div>
<div class="col-sm-2"></div>
<div class="col-sm-4" style="padding-top: 40px">
    <p style="color: #c09853;font-size: x-large">hotline</p>
</div>
<div class="col-sm-3"></div>
<div class="col-sm-4 location">
    <p>(+94)011-25432343</p>
    <p>(+94)011-25432344</p>

</div>


<a href="http://gazpo.com/downloads/tutorials/jquery/scrolltop/#" class="scrollup" style="display: none;">Scroll</a>

<%@include file="loginload.jsp" %>
<%@include file="signupmodal.jsp" %>
</body>
</html>