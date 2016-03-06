(function() {
	"use strict";

	angular.module("app.login",[])

	.controller('LoginController', ['$scope','$http', function($scope,$htpp) {
			
		$scope.user ={
			userName:'',
			password:'',
		}
		
		$scope.checkUser = function(){
			
			
			$htpp.post('rest/login/checkUser/',$scope.user).success(function (loginResponse){
				console.log(loginResponse);
				if(loginResponse.user!=null){
					var path = "/propiedadesCR/app#/home";
	    			window.location.href = path;
				}
			});
	
		}
		
	}]);
})();