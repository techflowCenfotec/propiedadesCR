(function(){
	'use strict';

	angular.module('app.matchedPropertiesList', [])
	.controller('MatchedPropertiesListController',['$scope','$http', '$location','$rootScope',function($scope,$http,$location,$rootScope){
		$scope.properties =  $rootScope.matchedPropertiesList.properties;
		var porcentages = $rootScope.matchedPropertiesList.porcentages;

		for (var i = 0 ; i < $scope.properties.length; i++) {
			$scope.properties[i].porcentage = porcentages[i];
		}
	}]);
	
})();