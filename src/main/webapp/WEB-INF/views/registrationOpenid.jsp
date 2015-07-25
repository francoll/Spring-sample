<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>New Account</title>
</head>
<body>
    <section>
        <div class="jumbotron">
            <div class="container">
                <h1>New Account</h1>
                <p>Verify your information</p>
            </div>
        </div>
    </section>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    
                    <div class="panel-body">
                        
                        <form action="<c:url value= "/accounts"></c:url>"
                            method="post">
                            <fieldset>
                                <p>${account.username}</p>
                                <p>${account.fName}</p>
                                <p>${account.lName}</p>
                                <p>${account.email}</p>
                                
                                <input class="btn btn-lg btn-success btn-block" type="submit"
                                    value="Go to accounts">
                            </fieldset>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>