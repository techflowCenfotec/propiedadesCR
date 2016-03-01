(function() {
	"use strict";

	angular.module("app.properties",[])

	.controller("PropertiesController",["$scope","$http",function($scope, $http) {
		
		$scope.properties = [];
		$scope.requestObject = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
		$http.post('rest/protected/properties/getAll', $scope.requestObject).success(function(response) {
			console.log(response);
		}).error(function(err) {
			console.log(err);
		});
	}])
})();