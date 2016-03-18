(function(){
	'use strict';

	angular.module('app.matchedPropertiesList', [])
	.controller('MatchedPropertiesListController',['$scope','$http', '$location','$rootScope',function($scope,$http,$location,$rootScope){
		$scope.properties =  $rootScope.matchedPropertiesList.properties;
		var porcentages = $rootScope.matchedPropertiesList.porcentages;

		for (var i = 0 ; i < $scope.properties.length; i++) {
			$scope.properties[i].porcentage = porcentages[i];
		}

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
	}]);
	
})();