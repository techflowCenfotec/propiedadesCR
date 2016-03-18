
	"use strict";

	angular.module("app.home",[])
	.controller('HomeController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		
		$("#myCarousel").carousel();
	
		validate();
		
		function validate(){
		$http.get("rest/database/checkDB").success(function(data){
			if(data.code!==200){
				var path = "#/templates/errorsView/500";
				
    			window.location.href = path;
			}
		});
		}
	}]);
