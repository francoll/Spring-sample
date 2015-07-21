<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<script
    src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js" type="text/javascript"> 
	</script>
	
	<script type="text/javascript" language="javascript">
	var cartApp = angular.module("cartApp", []);

	cartApp.controller("cartCtrl",  function ($scope, $http) {
	  
	  $scope.refreshCart = function(cartId) {
	    $http.get('/webstore/rest/cart/'+$scope.cartId)
	    .success(function(data) {
	      $scope.cart = data;
	    });
	  };
	  
	  $scope.initCartId = function(cartId) {
	    $scope.cartId=cartId;
	    $scope.refreshCart($scope.cartId);
	  };
	  
	  $scope.addToCart = function(productId) {
	    $http.put('/webstore/rest/cart/add/'+ productId)
	    .success(function(data) {
	      $scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
	      alert("Product Successfully added to the Cart!");
	    });
	  };
	  
	  $scope.removeFromCart = function(productId) {
	    $http.put('/webstore/rest/cart/remove/'+productId)
	    .success(function(data) {
	      $scope.refreshCart($http.get('/webstore/rest/cart/get/cartId'));
	      alert("Product successfully removed from the Cart!");
	    });
	  }});</script>
    <title>Products</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Products</h1>
			</div>
		</div>
	</section>
	<section class="container" ng-app="cartApp">
		<div class="row">
			<div class="col-md-5">
				<img src="<c:url value="/resources/images/${product.productId}.jpg"></c:url>"
					alt="image" style="width: 100%" />
			</div>
			<div class="col-md-5">
				<h3>${product.name}</h3>
				<p>${product.description}</p>
				<p>
					<strong>Item Code : </strong><span class="label label-warning">${product.productId}</span>
				</p>
				<p>
					<strong>manufacturer</strong> : ${product.manufacturer}
				</p>
				<p>
					<strong>category</strong> : ${product.category}
				</p>
				<p>
					<strong>Available units in stock </strong> :
					${product.unitsInStock}
				</p>
				<h4>
					<fmt:formatNumber value="${product.unitPrice}" type="currency"
						currencySymbol="$" pattern="¤ #,##0.00;¤ -#,##0.00" />
				</h4>
				<a href="<spring:url value="/products" />" class="btn btn-default">
					<span class="glyphicon-hand-left glyphicon"></span> back
				</a>
				<input type="button" name="test" value="test" onclick="alert(cartApp);">
				<p ng-controller="cartCtrl">
					<a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
					  <span class="glyphicon-shopping-cart glyphicon"></span> Order Now
					</a>
					<a href="<spring:url value="/cart" />" class="btn btn-default">
					  <span class="glyphicon-hand-right glyphicon"></span> View Cart
					</a>
				</p>
			</div>
		</div>
	</section>
</body>
</html>