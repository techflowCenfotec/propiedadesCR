
	"use strict";

	app.core
	.controller('HomeController', ['$scope','$rootScope', function($scope,$rootScope) {
		
		console.log("El root scope es:", $rootScope.userLogged);
	}]);
