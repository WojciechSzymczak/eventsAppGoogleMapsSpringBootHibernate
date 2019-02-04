<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">
    <title>Events - contacts</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customcss/style1.css" rel="stylesheet">
    <link rel="stylesheet" href="fonts/fontawesome-free-5.6.3-web/css/all.css">
</head>
<body>

    <%@include file="../../jspf/userPanel.jspf"%>
    <%@include file="../../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test='${requestScope.get("successMessage") != null}'>
            <div class="btn-success btn-lg mt-3 mb-3">${requestScope.get("successMessage")}</div>
        </c:if>

        <c:if test='${requestScope.get("errorMessage") != null}'>
            <div class="btn-danger btn-lg mt-3 mb-3">${requestScope.get("errorMessage")}</div>
        </c:if>

        <c:set var="users" value="${requestScope.get('contactsList')}"/>
        <%--We need a variable to check if current contact is odd, if so we are going to create new div and place it there.--%>
        <%--In result, contacts will be displayed in 2 columns.--%>
        <c:set var="count" value="1"/>

        <div class="py-5">
            <div class="container">
                <div class="row">
                    <c:forEach var="user" items="${users}">
                        <c:if test="${count%2 != 0}">
                            <div class="col-lg-12 d-flex mb-5">
                        </c:if>
                                <div class="media w-50 ml-3">
                                    <a class="pull-left" href="/userDetailsView?name=${user.getName()}">
                                        <img class="media-object dp rounded-circle" src="images/user_icon.png">
                                    </a>
                                    <div class="media-body ml-3">
                                        <h4 class="media-heading">${user.getName()}</h4>
                                        <hr style="margin:8px auto">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <span class="label label-default">Contact/Friend</span>
                                            </div>
                                            <div class="col-md-6">
                                                <form action="/contactsViewDelete" method="post">
                                                    <input type="hidden" name="userName" value="${user.getName()}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    <button type="submit" class="btn btn-outline-danger">Delete friend<i class="fas fa-minus-square ml-1"></i></button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        <c:if test="${count%2 == 0}">
                            </div>
                        </c:if>
                        <c:set var="count" value="${count = count + 1}"/>
                    </c:forEach>
                </div>
            </div>
        </div>

        <c:if test="${fn:length(requestScope.get('contactsList')) == 0}">
            <h2 class="text-center"> Sorry, no results...</h2>
        </c:if>

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
