
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope','$http','dbService','$location', function($scope,$rootScope,$http,dbService,$location) {
		
		$("#myCarousel").carousel();
			console.log(localStorage.getItem('load'));
			if(localStorage.getItem('load')!=='1'){
				console.log('Entra');
				localStorage.setItem('load','1');
				$location.path('home');
			}
		
		
		
		dbService.checkDB();
		
	}]);
