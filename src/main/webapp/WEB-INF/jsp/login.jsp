<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">

    <title>Events - Log in</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/customcss/style1.css" rel="stylesheet">
</head>

<body>

    <%@include file="../jspf/userPanel.jspf"%>


    <div class="container">

            <h2 class="mt-5 mb-3">Log in!</h2>

            <div id="login-box">

                <div id="error"></div>
                <div id="logout"></div>

                <form name='loginForm'
                      action="<c:url value='/login' />" method='POST'>
                    <table>
                        <tr>
                            <td>User:</td>
                            <td><input type='text' name='email'></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type='password' name='password' /></td>
                        </tr>
                        <tr>
                            <td colspan='2'><input name="submit" type="submit"
                                                   value="submit" /></td>
                        </tr>
                    </table>

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />

                </form>
            </div>


        <%@include file="../jspf/footer.jspf"%>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/jquery-3.3.1.js"></script>
    <script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>
    <script src="popper/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            if(window.location.href.indexOf("error") > -1) {

                document.getElementById("error").innerHTML =
                    '<div class="btn-danger btn-lg mt-3 mb-3">' +
                    'Login was not successful. Please provide correct user information' +
                    '</div>';

            }

        });
    </script>

</body>
</html>
