(function() {
	"use strict";

	angular.module("app.vendorsList",[])

	.controller("VendorsListController",["$scope","$http",function($scope,$http) {
		
		
		validate();
		
		function validate(){
		$http.get("rest/protected/database/checkDB").success(function(data){
			if(data.code!==200){
				var path = "#/templates/errorsView/500";
				
    			window.location.href = path;
			}
		});
		}
		
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