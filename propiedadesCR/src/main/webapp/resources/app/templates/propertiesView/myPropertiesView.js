(function() {
	'use strict';
	
	angular.module('app.properties.myProperties', [])
	
	.controller('MyPropertiesController', ['$scope', '$http', '$rootScope', MyPropertiesController]);
	
	function MyPropertiesController($scope, $http, $rootScope) {
		$scope.propertiesList = [];
		
		$scope.init = function() {
			var active = 1;
			
			$http.get('rest/protected/properties/getAll')
			.success(function(response) {
				for (var i = 0; i < response.properties.length; i++) {
					if(response.properties[i].active == active) 
						$scope.propertiesList.push(response.properties[i]);
				}
			});
		};
		
		$scope.init();
		
		$scope.deleteProperty = function(pIdProperty) {
			$http.put('rest/protected/properties/delete/' + pIdProperty)
			.success(function(response) {
				console.log('deleted');
			});
		}
	}
	
})();