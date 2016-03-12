(function() {
	"use strict";

	angular.module("app.eventsList",[])

	.controller("EventsListController",["$scope","$http",function($scope,$http) {
		$scope.events = [];
		
		
		
		var link = 'rest/protected/events/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","event": {}};
		
		$http.post(link,request).success(function(response) {
			   $scope.events= response.events;
			   $scope.eventsList = $scope.events;
			   console.log($scope.eventsList);
			   
		});
		
		$scope.consultEvent = function(id){
		  localStorage.setItem('idEvent',id);
		}
		
	}])
})();