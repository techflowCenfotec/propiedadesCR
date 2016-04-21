(function() {
	"use strict";

	angular.module("app.vendorsList",[])

	.controller("VendorsListController",["$scope","$http",'dbService',function($scope,$http,dbService) {
		
		$scope.vendors = [];
		$scope.totalPages =0;
		$scope.pageSize = 10;
		$scope.pageNumber = 0;
        $scope.numPerPageOpt = [2, 5, 10, 20];
        $scope.numPerPage = $scope.numPerPageOpt[2];
        $scope.currentPage = 1;
        $scope.currentPage = [];
     
        dbService.checkDB();
        getAllVendors();
		
		
		$scope.changePage = function(page){
			$scope.pageNumber = page-1;
			getAllVendors();
		};
	        
		 $scope.onNumPerPageChange = function(){
			 $scope.pageSize = $scope.numPerPage;
			 getAllVendors();
	     };
	     
	     
	     function getAllVendors(){
	    	 var link = 'rest/protected/users/getAllVendors';
	 		var request = {
	 				  "pageNumber": $scope.pageNumber,
	 				  "pageSize": $scope.pageSize,
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
	 			   $scope.vendors= response.users;
	 			   $scope.vendorsList = $scope.vendors;
	 			   $scope.vendorsList = _.difference($scope.vendorsList,_.where($scope.vendorsList,{active:0}));
	 	           $scope.totalPages = response.totalPages;
	 			  
	 		});
	     };
	     
	     $scope.consultVendor= function(myId){
				localStorage.setItem('idVendor',myId);
			};
		
	}])
})();