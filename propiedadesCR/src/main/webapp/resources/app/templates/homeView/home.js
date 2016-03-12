
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope', function($scope,$rootScope) {
		
		
		localStorage.setItem("idUser",1);
	}]);
