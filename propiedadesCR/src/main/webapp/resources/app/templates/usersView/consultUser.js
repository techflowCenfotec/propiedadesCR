(function() {
	"use strict";

	angular.module("app.consultUser",[])

	.controller('ConsultUserController', ['$scope','$http','$rootScope','$mdDialog', function($scope,$http,$rootScope,$mdDialog) {
		
		$scope.user={};
		
		
		
		
		var link = 'rest/protected/users/getAttendUser/3';
		$http.get(link).success(function(response) {
			 
			  $scope.user = response.user;
			   
			  return $scope.user;
		});
	
	  
	}]);
})();