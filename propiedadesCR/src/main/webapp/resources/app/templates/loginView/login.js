
	"use strict";

	app.core
	.controller('LoginController', ['$scope','$http','$rootScope', function($scope,$htpp,$rootScope) {
			
		$scope.user ={
			userName:'',
			password:'',
		}
		
		$scope.checkUser = function(){
			
			
			$htpp.post('rest/login/checkUser/',$scope.user).success(function (loginResponse){
				//console.log(loginResponse);
				if(loginResponse.user!=null){
					var path = "/propiedadesCR/app#/home";
					$rootScope.userLogged = loginResponse.user;
					
	    			window.location.href = path;
					//$route.go('/home');
				}
			});
	
		}
		
	}]);
