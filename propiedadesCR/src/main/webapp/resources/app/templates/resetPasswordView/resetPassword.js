
	"use strict";

	angular.module("app.resetPassword",['validation.match'])
	.controller('resetPasswordController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		
		$scope.notEquals = false;
		$scope.form={
				password:'',
				confirmPassword:''
		}
		
		$scope.changePassword = function(){
			
			if($scope.form.password===$scope.form.confirmPassword){
				//console.log(localStorage.getItem("changePassword"));
			}else
				
				$scope.notEquals = true;
		}
		
		
	}]);
