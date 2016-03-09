
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope', function($scope,$htpp,$rootScope) {
			
		$scope.user ={
			userName:'',
			password:'',
		}
		
		$scope.checkUser = function(){
			
			
			$htpp.post('rest/login/checkUser/',$scope.user).success(function (loginResponse){
			
				if(loginResponse.user!=null){
					var path = "/propiedadesCR/app#/home";
					$rootScope.userLogged = loginResponse.user;
	    			window.location.href = path;
				
				}
			});
	
		}
		
	}]);
