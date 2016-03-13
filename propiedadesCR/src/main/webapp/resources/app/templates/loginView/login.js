
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope', function($scope,$htpp,$rootScope) {
			
		$scope.incorrect = true;
		$scope.user ={
			userName:'',
			password:'',
		}
		
		$scope.checkUser = function(){
			
			
			$htpp.post('rest/login/checkUser/',$scope.user).success(function (loginResponse){
				
				if(loginResponse.code ==200){
					var path = "/propiedadesCR/app#/home";
					
	    			window.location.href = path;
				}else{
					$scope.incorrect = false;
				}
				
					
			});			
					
	
		}
		
	}]);
