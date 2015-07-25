<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">


<title>Accounts</title>
</head>
<body>
    <section>
        <div class="jumbotron">
            <div class="container">
                <h1>Accounts</h1>
                <p>List of accounts for the app</p>
            </div>
        </div>
    </section>

    <section class="container">
        <div class="row">
            <c:forEach items="${accounts}" var="account">
                <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                    <div class="thumbnail">
                        <div class="caption">
                            <h3>${account.fName} ${account.lName}</h3>
                            <p>Account Id: ${account.accountId}</p>
                            <p>Edition: ${account.edition}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</body>
</html>