
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope','$http','dbService','$state',function($scope,$rootScope,$http,dbService,$state) {
		
		
		
		$("#myCarousel").carousel();
		
	
		dbService.checkDB();

		
		
	}]);
