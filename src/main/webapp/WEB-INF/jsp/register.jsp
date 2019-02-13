<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">
    <title>Events - register page</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customcss/style1.css" rel="stylesheet">
</head>

<body>

    <%@include file="../jspf/userPanel.jspf"%>
    <%@include file="../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test='${requestScope.get("message") != null}'>
            <div class="btn-danger btn-lg mt-3 mb-3">${requestScope.get("message")}</div>
        </c:if>

        <form class="form-horizontal" role="form" method="POST" action="/register">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6 mt-4">
                    <h1>Register New User</h1>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="username">Name</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-user"></i></div>
                            <input type="text" name="username" class="form-control" id="username"
                                   placeholder="Your username" required autofocus>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="email">E-Mail Address</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                            <input type="text" name="email" class="form-control" id="email"
                                   placeholder="user@email.com" required autofocus>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="password">Password</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="password" required>
                        </div>
                    </div>
                </div>
                <c:if test='${requestScope.get("message") != null}'>
                    <div class="col-md-3">
                        <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <i class="fa fa-close">${requestScope.get("message")}</i>
                            </span>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="row">
                <div class="col-md-3 field-label-responsive">
                    <label for="passwordRepeated">Confirm Password</label>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem">
                                <i class="fa fa-repeat"></i>
                            </div>
                            <input type="password" name="passwordRepeated" class="form-control"
                                   id="passwordRepeated" placeholder="password" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />
                    <button type="submit" class="btn btn-success"><i class="fa fa-user-plus"></i> Register</button>
                </div>
            </div>
        </form>

        <%@include file="../jspf/footer.jspf"%>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="jquery/jquery-3.3.1.js"></script>
    <script>window.jQuery || document.write('<script src="jquery/jquery-3.3.1.js"><\/script>')</script>
    <script src="popper/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
