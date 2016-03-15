(function() {
	"use strict";

	angular.module('app.properties',[])

	.controller('PropertiesListController', ['$scope', '$http', PropertiesListController]);
	
	function PropertiesListController($scope, $http) {
		 $scope.propertiesList = [];
		 
		
		$scope.init = function() {
			$http.get('rest/protected/properties/getAll')
			.success(function(response) {
				$scope.propertiesList = response.properties;
			});
			
		};
		
		$scope.init();
	}
	
})();
