<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">

    <title>Events - event view</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customcss/style1.css" rel="stylesheet">
</head>

<body>

    <%@include file="../jspf/userPanel.jspf"%>
    <%@include file="../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test='${requestScope.get("errorMessage") != null}'>
            <div class="btn-danger btn-lg mt-5 mb-2">${requestScope.get("errorMessage")}</div>
        </c:if>

        <c:if test="${requestScope.get('event') != null}">

            <c:set var="eventModel" value="${requestScope.get('event')}"/>

            <div class="well well-sm mt-5 mb-5">
                <h3>Event: <strong>${eventModel.getName()}</strong></h3>
            </div>

            <form>
                <div class="form-group row">
                    <label for="staticBeginningDate" class="col-sm-2 col-form-label">Beginning Date:</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticBeginningDate" value="${eventModel.getBeginningDate().toLocalDateTime()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="staticDescription" class="col-sm-2 col-form-label">Description:</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticDescription" value="${eventModel.getDescription()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="staticOrganizer" class="col-sm-2 col-form-label">Organizer:</label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext" id="staticOrganizer" value="${eventModel.getUsers().iterator().next().getName()}">
                    </div>
                </div>
            </form>

            <div class="well well-sm mt-5 mb-2">
                <h3><strong>Event location:</strong></h3>
            </div>

            <div class="container">
                <div class="row">
                </div>
            </div>

            <div id="map_container">
                <div id="map"></div>
            </div>

        </c:if>

    </div>

    <%@include file="../jspf/footer.jspf"%>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/jquery-3.3.1.js"></script>
    <script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>
    <script src="popper/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<%@include file = "../publickeys/googleMapsApiKey" %>&callback=initMap" defer></script>
    <script type="text/javascript" defer>
        var map;
        function initMap() {

            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: ${eventModel.getLatitude()}, lng: ${eventModel.getLongitude()}},
                zoom: 13
            });

            var myMarker = new google.maps.Marker({
                position: new google.maps.LatLng(${eventModel.getLatitude()}, ${eventModel.getLongitude()}),
                draggable: false
            });

            map.setCenter(myMarker.position);
            myMarker.setMap(map);
        }
    </script>
</body>
</html>
