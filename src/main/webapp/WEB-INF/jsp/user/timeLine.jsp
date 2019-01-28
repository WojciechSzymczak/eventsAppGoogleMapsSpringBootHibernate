<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.png">
    <title>Events - time line</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/customcss/style1.css" rel="stylesheet">
    <link rel="stylesheet" href="fonts/fontawesome-free-5.6.3-web/css/all.css">
</head>
<body>

    <%@include file="../../jspf/userPanel.jspf"%>
    <%@include file="../../jspf/navigationBar.jspf"%>

    <div class="container">

        <c:if test="${requestScope.get('timeLineTitle') != null}">

            <h2 class="mt-4 mb-4 text-center">${requestScope.get('timeLineTitle')}</h2>

            <c:if test="${requestScope.get('canPost') != null && requestScope.get('canPost').equals('true')}">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 mb-4">
                        <div class="card">
                            <div class="card-header"> Create post</div>
                            <div class="card-body">
                                <form action="/timeLine" method="post">
                                    <div class="form-group">
                                        <textarea class="form-control" name="textArea" id="textArea" rows="3" placeholder="What's going on?">

                                        </textarea>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 justify-content-end d-flex">
                                            <input type="hidden" name="actionPath" value="${requestScope.get("actionPath")}" />
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button type="submit" class="btn btn-primary">Publish</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>

            <c:forEach var="posts" items="${requestScope.get('timeLinePosts')}">
                <div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 mb-4">
                                <div class="card">
                                    <div class="card-header"> ${posts.getUser().getName()} posted on ${posts.getPublishedDate().toLocalDateTime()}:</div>
                                    <div class="card-body">
                                        <h4>${posts.getText()}</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${fn:length(requestScope.get('timeLinePosts')) == 0}">
                <h2 class="text-center"> No posts yet!</h2>
            </c:if>

        </c:if>

        <c:if test='${requestScope.get("errorMessage") != null}'>
            <div class="btn-danger btn-lg mt-5 mb-2">${requestScope.get("errorMessage")}</div>
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
