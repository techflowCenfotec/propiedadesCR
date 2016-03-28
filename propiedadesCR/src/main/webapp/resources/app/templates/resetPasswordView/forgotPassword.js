
	"use strict";

	angular.module("app.forgotPassword",[])
	.controller('forgotPasswordController', ['$scope','$rootScope','$http', function($scope,$rootScope,$http) {
		
		$scope.isCorrect = true;
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
		
			   $http.post('rest/password/sendEmail',req).success(function(data){
				   $rootScope.$broadcast('preloader:active');
				   if(data.code==200){
				   localStorage.setItem('changePass', data.idUser);
				   var path = "/propiedadesCR/";
	    			window.location.href = path;
				   }else
					   $scope.isCorrect = false;
				   
				  
			   }).error(function(){
				   $scope.isCorrect = false;
			   });
			   }
	}]);
