
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope', function($scope,$rootScope) {
		
		console.log("El root scope es:", $rootScope.userLogged);
		localStorage.setItem("idUser",1);
	}]);
