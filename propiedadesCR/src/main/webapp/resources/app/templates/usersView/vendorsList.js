(function() {
	"use strict";

	angular.module("app.vendorsList",[])

	.controller("VendorsListController",["$scope","$http",function($scope,$http) {
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
		
		$http.post(link,request).success(function(response) {
			   $scope.vendors= response.users;
			   $scope.vendorsList = $scope.vendors;
			   
		});
		
		$scope.consultVendor= function(myId){
			localStorage.setItem('idVendor',myId);
		};
		
	}])
})();