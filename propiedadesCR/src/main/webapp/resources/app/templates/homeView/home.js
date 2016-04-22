
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope','$http','dbService', function($scope,$rootScope,$http,dbService) {
		
		
		
		$("#myCarousel").carousel();
		
	
		dbService.checkDB();
		
	}]);
