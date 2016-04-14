
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope','dbService', function($scope,$http,$rootScope,dbService) {
			
		$scope.incorrect = true;
		$scope.user ={
			userName:'',
			password:'',
		}
		
		dbService.localCheckDB();
		
		$scope.checkUser = function(){
			
			
			$http.post('rest/login/checkUser',$scope.user).success(function (loginResponse){
				var path = "";
				if(loginResponse.code ==200){
					if(loginResponse.user.firstTime === 0 && loginResponse.user.role.idRole === 2 ){
						path = "/propiedadesCR/app#/templates/tutorialView/tutorialView"
					}else{
						path = "/propiedadesCR/app#/home";
					}
	    			window.location.href = path;

				}else{
					$scope.incorrect = false;
				}
				
					
			}).error(function(){
				$scope.incorrect = false;
			})
					
	
		}
		
	}]);
