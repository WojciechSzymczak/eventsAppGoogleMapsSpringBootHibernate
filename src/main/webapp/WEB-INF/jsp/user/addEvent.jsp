<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">

    <title>Add event</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customcss/style1.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="css/timepicki.css">

</head>

<body>

    <%@include file="../../jspf/userPanel.jspf"%>
    <%@include file="../../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test='${requestScope.get("eventAddFailure") != null}'>
            <div class="btn-danger btn-lg mt-5 mb-2">${requestScope.get("eventAddFailure")}</div>
        </c:if>

        <c:if test='${requestScope.get("eventAddSucces") != null}'>
            <div class="btn-success btn-lg mt-5 mb-2">${requestScope.get("eventAddSucces")}</div>
        </c:if>


            <h2 class="mt-5">To add an event, simply fill the form below:</h2><hr>


                <form action="/addEvent" method="post">
                    <fieldset>

                        <div class="form-group">
                            <label for="eventName">Event name</label>
                            <input type="text" class="form-control input-md" name="eventName" id="eventName" aria-describedby="eventNameHelp" placeholder="Enter event name" required="">
                            <small id="eventNameHelp" class="form-text text-muted">This is the name of your event visible to other users.</small>
                        </div>

                        <div class="form-group">
                            <label for="beginningDate">Beginning time</label><br>
                                    <input type="date" class="col-xl-12 col-lg-12" id="beginningDate" name="beginningDate">
                                    <input type="text" class="col-xl-12 col-lg-12" id="beginningTime" name="beginningTime">
                            <small id="eventTimeHelp" class="form-text text-muted">Specify when your event is going to start.</small>
                        </div>

                        <div class="form-group">
                            <label for="eventDescription">Tell something about your event!</label>
                            <textarea class="form-control" id="eventDescription" name="eventDescription" rows="3" placeholder="Enter event description" required=""></textarea>
                            <small id="eventDescriptionHelp" class="form-text text-muted">Short description of the event.</small>
                        </div>

                        <div class="form-inline">

                            <div class="row col-12">
                                <div class="col-xs-6 col-md-6 col-lg-6 form-group">
                                    <label for="longitude" class="col-4">Longitude</label>
                                    <input class="form-control col-8" id="longitude" name="longitude" placeholder="" type="text" readonly/>
                                </div>

                                <div class="col-xs-6 col-md-6 col-lg-6 form-group">
                                    <label for="latitude"class="col-4">Latitude</label>
                                    <input class="form-control col-8" id="latitude" name="latitude" placeholder="" type="text" readonly/>
                                </div>
                            </div>

                        </div>


                        <div class="container">
                            <div class="row">
                            </div>
                        </div>

                        <hr>
                        <div class="form-group">
                            <label>Put the location of your event on the map:</label>
                        </div>

                        <div id="map_container">
                            <div id="map"></div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-10"></div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <button type="submit" class="btn btn-primary btn-lg col-2 right">Add my event!</button>
                            </div>
                        </div>

                    </fieldset>
                </form>

        <%@include file="../../jspf/footer.jspf"%>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/jquery-3.3.1.js"></script>
    <script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>
    <script src="popper/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/timepicki.js"></script>
    <script>
        $(document).ready(function(){
            $("#beginningTime").timepicki();
        });
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=<%@include file = "../../publickeys/googleMapsApiKey" %>&callback=initMap" defer></script>
    <script type="text/javascript" defer>
        var map;
        function initMap() {

            map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: 51.7592485, lng: 19.4559833},
                zoom: 13
            });

            var myMarker = new google.maps.Marker({
                position: new google.maps.LatLng(51.7592485, 19.4559833),
                draggable: true
            });

            google.maps.event.addListener(myMarker, 'dragend', function(evt){
                document.getElementById("longitude").value = evt.latLng.lng();
                document.getElementById("latitude").value = evt.latLng.lat();
            });

            map.setCenter(myMarker.position);
            myMarker.setMap(map);
        }
    </script>
</body>
</html>
