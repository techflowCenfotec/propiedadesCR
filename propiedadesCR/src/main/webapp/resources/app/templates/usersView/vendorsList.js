(function() {
	"use strict";

	angular.module("app.vendorsList",[])

	.controller("VendorsListController",["$scope","$http",'dbService',function($scope,$http,dbService) {
		
		
	
		
		$scope.vendors = [];

		var link = 'rest/protected/users/getAllVendors';
		var request = {
				  "pageNumber": 0,
				  "pageSize": 0,
				  "direction": "string",
				  "sortBy": [
				    "string"
				  ],
				  "searchColumn": "string",
				  "searchTerm": "string",
				  "user": {}
				}
		dbService.checkDB();
		$http.post(link,request).success(function(response) {
			   validate();	
			   $scope.vendors= response.users;
			   $scope.vendorsList = $scope.vendors;
			   $scope.vendorsList = _.difference($scope.vendorsList,_.where($scope.vendorsList,{active:0}));
	            
			   
		});
		
		$scope.consultVendor= function(myId){
			localStorage.setItem('idVendor',myId);
		};
		
	}])
})();