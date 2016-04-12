
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope','dbService', function($scope,$http,$rootScope,dbService) {
			
		$scope.incorrect = true;
		$scope.user ={
			userName:'jorge.argds@gmail.com',
			password:'Food9leak',
		}
		
		dbService.localCheckDB();
		
		$scope.checkUser = function(){
			
			
			$http.post('rest/login/checkUser',$scope.user).success(function (loginResponse){
				
				if(loginResponse.code ==200){
					var path = "/propiedadesCR/app#/home";
					
	    			window.location.href = path;
				}else{
					$scope.incorrect = false;
				}
				
					
			}).error(function(){
				$scope.incorrect = false;
			})
					
	
		}
		
	}]);
