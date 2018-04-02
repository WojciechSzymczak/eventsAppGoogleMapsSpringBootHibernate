<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">

    <title>Events - main page</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customcss/style1.css" rel="stylesheet">
</head>

<body>

    <%@include file="../jspf/userPanel.jspf"%>


    <div class="container">


        <c:url value="/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.error != null}">
                <p>
                    Invalid username and password.
                </p>
            </c:if>
            <c:if test="${param.logout != null}">
                <p>
                    You have been logged out.
                </p>
            </c:if>
            <p>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>
            </p>
            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
            </p>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn">Log in</button>
        </form>


        <%@include file="../jspf/footer.jspf"%>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <%--<script src="jquery/jquery-3.3.1.js"></script>--%>
    <%--<script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>--%>
    <%--<script src="popper/popper.min.js"></script>--%>
    <%--<script src="js/bootstrap.min.js"></script>--%>

</body>
</html>
