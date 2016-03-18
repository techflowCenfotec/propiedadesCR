
	"use strict";

	angular.module("app.resetPassword",['validation.match'])
	.controller('resetPasswordController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		
		$scope.notEquals = false;
		var original;
		$scope.form={
				password:'',
				confirmPassword:''
		}
		var link = 'rest/protected/password/changePassword';
		
	
		
		original = angular.copy($scope.form);
		
		
		$scope.changePassword = function(){
			var request=
			{
					  "pageNumber": 0,
					  "pageSize": 0,
					  "direction": "string",
					  "sortBy": [
					    "string"
					  ],
					  "searchColumn": "string",
					  "searchTerm": "string",
					  "userEmail":"" ,
					  "id": localStorage.getItem("changePass"),
					  "newPass": $scope.form.password
					};
			
			if($scope.form.password===$scope.form.confirmPassword){
				$http.post(link,request).success(function(){
				var path = "/propiedadesCR/";
    			window.location.href = path;
			});
				
			}else
				
				$scope.notEquals = true;
		}
		
		$scope.canSubmit = function() {
			return $scope.form_resetPass.$valid
					&& !angular.equals($scope.form, original);
		};
		
	}]);
