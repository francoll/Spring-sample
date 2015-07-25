<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
    href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
    <section>
        <div class="jumbotron">
            <div class="container">
                <h1>Registration</h1>
                <p>Verify your information and create a password</p>
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
                                <p>${product.description}</p>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password"
                                        name='j_password' type="password" value="">
                                </div>
                                <input class="btn btn-lg btn-success btn-block" type="submit"
                                    value="Login">
                            </fieldset>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>