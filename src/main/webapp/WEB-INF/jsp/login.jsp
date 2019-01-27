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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customcss/style1.css" rel="stylesheet">
    <link rel="stylesheet" href="fonts/fontawesome-free-5.6.3-web/css/all.css">
</head>
<body>

    <%@include file="../jspf/userPanel.jspf"%>
    <%@include file="../jspf/navigationBar.jspf"%>

    <div class="container">

        <div class="column">
            <div class="d-flex justify-content-center m-5">
                <div class="card">
                    <article class="card-body">
                        <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
                        <hr>
                        <p class="text-danger text-center" id="error"></p>
                        <form name='loginForm' action="<c:url value='/login' />" method='POST'>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input name="email" class="form-control" placeholder="Login" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                                    </div>
                                    <input class="form-control" placeholder="******" type="password" name="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <button type="submit" class="btn btn-primary btn-block"> Login  </button>
                            </div>
                            <p class="text-center"><a href="#" class="btn">Forgot password?</a></p>
                        </form>
                    </article>
                </div>
            </div>
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
                    'Login was not successful.';
            }
        });
    </script>
</body>
</html>
