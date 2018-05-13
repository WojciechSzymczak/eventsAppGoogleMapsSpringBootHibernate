<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">

    <title>Events - manage events</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customcss/style1.css" rel="stylesheet">

    <!-- Font style for delete button -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>

<body>

    <%@include file="../../jspf/userPanel.jspf"%>
    <%@include file="../../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test='${requestScope.get("errorMessage") != null}'>
            <div class="btn-danger btn-lg mt-5 mb-2">${requestScope.get("errorMessage")}</div>
        </c:if>

        <h2 class="mt-4 mb-4">Your Events:</h2>

        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Beginning Time</th>
                            <th scope="col">Description</th>
                            <th scope="col">Longitude</th>
                            <th scope="col">Latitude</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="event" items="${userModel.events.toArray()}">
                            <tr>
                                <td>${event.getName()}</td>
                                <td>${event.getBeginningDate()}</td>
                                <td>${event.getDescription()}</td>
                                <td>${event.getLongitude()}</td>
                                <td>${event.getLatitude()}</td>
                                <form action="/eventManager" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <td class="text-right"><button type="submit" title="Delete Event" class="btn btn-sm btn-danger" value="${event.getId()}" name="deleteButton"><i class="fa fa-trash"></i> </button> </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <%@include file="../../jspf/footer.jspf"%>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/jquery-3.3.1.js"></script>
    <script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>
    <script src="popper/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>
