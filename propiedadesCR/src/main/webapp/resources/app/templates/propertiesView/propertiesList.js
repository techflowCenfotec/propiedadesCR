(function() {
	"use strict";

	angular.module('app.properties',[])

	.controller('PropertiesListController', ['$scope', '$http', '$rootScope', PropertiesListController]);
	
	function PropertiesListController($scope, $http, $rootScope) {
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
			
			// Cambiar a UserLogged
			var bd = 'rest/protected/users/addToFavorite/' + $rootScope.userLogged.idUser;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.put(bd, data)
			.success(function(response) {
			});
		}
	}
	
})();
