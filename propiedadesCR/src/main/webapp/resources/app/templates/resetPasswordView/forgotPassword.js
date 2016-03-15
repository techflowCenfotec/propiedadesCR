
	"use strict";

	angular.module("app.forgotPassword",[])
	.controller('forgotPasswordController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		
		$scope.form={
				email:''
		}
		
		$scope.sendEmail = function(){
		var req={
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": "string",
				  "userEmail": $scope.form.email,
				};
		
			   $http.post('rest/protected/password/sendEmail',req).success(function(){
				   
			   });
			   }
	}]);
