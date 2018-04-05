<%@ page import="org.springframework.web.context.request.RequestScope" %>
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


        <%--<c:url value="/loginpost" var="loginUrl"/>--%>
        <%--<form action="${loginUrl}" method="post">--%>
            <%--<c:if test="${param.error != null}">--%>
                <%--<p>--%>
                    <%--Invalid username and password.--%>
                    <%--${param.keySet()}--%>
                <%--</p>--%>
            <%--</c:if>--%>
            <%--<c:if test="${param.logout != null}">--%>
                <%--<p>--%>
                    <%--You have been logged out.--%>
                <%--</p>--%>
            <%--</c:if>--%>
            <%--<p>--%>
                <%--<label for="email">email</label>--%>
                <%--<input type="text" id="email" name="email"/>--%>
            <%--</p>--%>
            <%--<p>--%>
                <%--<label for="password">Password</label>--%>
                <%--<input type="password" id="password" name="password"/>--%>
            <%--</p>--%>
            <%--&lt;%&ndash;<input type="hidden"&ndash;%&gt;--%>
                   <%--&lt;%&ndash;name="${_csrf.parameterName}"&ndash;%&gt;--%>
                   <%--&lt;%&ndash;value="${_csrf.token}"/>&ndash;%&gt;--%>
            <%--<button type="submit" class="btn">Log in</button>--%>
        <%--</form>--%>


            <h1>Spring Security Login Form (Database Authentication)</h1>

            <div id="login-box">

                <h2>Login with Username and Password</h2>

                <c:if test="${not empty error}">
                    <div class="error">${error}</div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="msg">${msg}</div>
                </c:if>

                <form name='loginForm'
                      <%--action="<c:url value='/j_spring_security_check' />" method='POST'>--%>
                      action="<c:url value='/loginProcess' />" method='POST'>
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

</body>
</html>
