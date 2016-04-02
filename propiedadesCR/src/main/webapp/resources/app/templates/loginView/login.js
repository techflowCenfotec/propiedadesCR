
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope', function($scope,$http,$rootScope) {
			
		$scope.incorrect = true;
		$scope.user ={
			userName:'jorge.argds@gmail.com',
			password:'Food9leak',
		}
		
		validate();
		
		function validate(){
		$http.get("rest/local/checkDB").success(function(data){
			if(data.code!==200){
				var path = "#/templates/errorsView/500";
				
    			window.location.href = path;
			}
		});
		}
		
		$scope.checkUser = function(){
			
			validate();
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
