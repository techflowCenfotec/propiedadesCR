(function() {
	'use strict';
	
	angular.module('app.properties.compare', [])
	
	.controller('CompareController', ['$scope', '$http', '$state', CompareController]);
	
	function CompareController($scope, $http, $state) {
		$scope.properties = localStorage.getItem("properties");
		$scope.property1 = {};
		$scope.property2 = {};
		
		$scope.init = function() {
			var bdProp1 = 'rest/protected/properties/getByPropertyId/' + $scope.properties[1];
			var bdProp2 = 'rest/protected/properties/getByPropertyId/' + $scope.properties[3];
			$http.get(bdProp1)
			.success(function(response) {
				$scope.property1 = response.property;
			});
			$http.get(bdProp2)
			.success(function(response) {
				$scope.property2 = response.property;
			});
		}
		
		$scope.init();
		
		// Stores single id value
		$scope.viewProperty = function(pIdProperty) {
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.cancel = function() {
			$state.go('templates/propertiesView/propertiesList');
		}
	}
})();