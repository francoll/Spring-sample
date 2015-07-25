<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting}</h1>
				<p>${tagline}</p>
			</div>
			<p>
				<a
					href=" <spring:url value="/accounts" />"
					class="btn btn-primary">Account List</a>
			</p>
			<p>
                <a
                    href=" <spring:url value="/products" />"
                    class="btn btn-primary">Product List</a>
            </p>
		</div>
	</section>
</body>
</html>