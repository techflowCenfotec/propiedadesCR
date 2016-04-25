
	"use strict";

	angular.module("app.login",[])
	.controller('LoginController', ['$scope','$http','$rootScope','dbService','sessionService', function($scope,$http,$rootScope,dbService,sessionService) {
			
		$scope.incorrect = true;
		$scope.user ={
			userName:'',
			password:'',
		}
		sessionService.checkSession();
		//Metodo para validar la sesion 
		if(sessionStorage.getItem('reload') !='1'){
			sessionStorage.setItem('reload','1');
			$state.reload();
		}
		dbService.localCheckDB();
		$http.get('rest/local/signOut').success(function(response){
			
		});
		$scope.checkUser = function(){
			
			
			$http.post('rest/login/checkUser',$scope.user).success(function (loginResponse){
				var path = "";
				sessionStorage.setItem('reload','0');
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
