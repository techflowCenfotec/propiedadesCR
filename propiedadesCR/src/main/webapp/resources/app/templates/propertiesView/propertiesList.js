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
		
		$scope.viewProperty = function(pIdProperty) {
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.addToFavorites = function(pIdProperty) {
			
			var bd = 'rest/protected/users/addToFavorite/' + 1;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.put(bd, data)
			.success(function(response) {
				console.log(response);
			});
		}
	}
	
})();
