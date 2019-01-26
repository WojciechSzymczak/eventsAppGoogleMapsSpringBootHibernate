<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">
    <title>Events - details view</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customcss/style1.css" rel="stylesheet">
    <link rel="stylesheet" href="fonts/fontawesome-free-5.6.3-web/css/all.css">
</head>
<body>

    <%@include file="../../jspf/userPanel.jspf"%>
    <%@include file="../../jspf/navigationBar.jspf"%>

    <div class="container">
        <c:if test='${requestScope.get("errorMessage") != null}'>
            <div class="btn-danger btn-lg mt-5 mb-2">${requestScope.get("errorMessage")}</div>
        </c:if>
        <c:if test='${requestScope.get("successMessage") != null}'>
            <div class="btn-success btn-lg mt-5 mb-2">${requestScope.get("successMessage")}</div>
        </c:if>
        <c:if test="${requestScope.get('userModel') != null}">
            <c:set var="userModel" value="${requestScope.get('userModel')}"/>
            <div class="well well-sm mt-5 mb-5">
                <div class="d-flex">
                    <h3>User: <strong>${userModel.getName()}</strong></h3>
                    <c:if test="${requestScope.get('edit').equals('true')}">
                        <a class="btn btn-outline-primary ml-5" href="/userDetailsManager">Edit
                            <i class="fas fa-edit"></i>
                        </a>
                    </c:if>
                </div>
            </div>
            <form>
                <c:if test="${userModel.getUserDetailsModel().getFirstname() != null || userModel.getUserDetailsModel().getFirstname().trim().equals('')}">
                    <div class="form-group row">
                        <label for="staticFirstName" class="col-sm-2 col-form-label">First name:</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticFirstName" value="${userModel.getUserDetailsModel().getFirstname()}">
                        </div>
                    </div>
                </c:if>
                <c:if test="${userModel.getUserDetailsModel().getLastname() != null || userModel.getUserDetailsModel().getLastname().trim().equals('')}">
                    <div class="form-group row">
                        <label for="staticLastName" class="col-sm-2 col-form-label">Last name:</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticLastName" value="${userModel.getUserDetailsModel().getLastname()}">
                        </div>
                    </div>
                </c:if>
                <c:if test="${userModel.getUserDetailsModel().getBirthdate() != null}">
                    <div class="form-group row">
                        <label for="staticBirthDate" class="col-sm-2 col-form-label">Birth date:</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticBirthDate" value="${userModel.getUserDetailsModel().getBirthdate().toLocalDateTime()}">
                        </div>
                    </div>
                </c:if>
                <c:if test="${userModel.getUserDetailsModel().getJoindate() != null}">
                    <div class="form-group row">
                        <label for="staticJoinDate" class="col-sm-2 col-form-label">Join date:</label>
                        <div class="col-sm-10">
                            <input type="text" readonly class="form-control-plaintext" id="staticJoinDate" value="${userModel.getUserDetailsModel().getJoindate().toLocalDateTime()}">
                        </div>
                    </div>
                </c:if>
            </form>
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