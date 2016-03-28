(function() {
	"use strict";

	angular.module('app.properties',[])

	.controller('PropertiesListController', ['$scope', '$http', '$rootScope', PropertiesListController]);
	
	function PropertiesListController($scope, $http, $rootScope) {
		 $scope.propertiesList = [];
		 $scope.compareList = [];
		 $scope.favorites = [];
		
		$scope.init = function() {
			var user = 1;
			
			$http.get('rest/protected/properties/getAll')
			.success(function(response) {
				$scope.propertiesList = response.properties;
				
				$http.get('rest/protected/users/getUserById/' + user)
				.success(function(response) {
					for (var i = 0; i < response.user.tproperties2.length; i++) {
						$scope.favorites.push(response.user.tproperties2[i].idProperty);
					}
				});
			});
		};
		
		$scope.init();
		
		// Stores property id for comparison inside array
		$scope.compareProperty = function(pIdProperty) {
			var idx = $scope.compareList.indexOf(pIdProperty);
			if (idx > -1) $scope.compareList.splice(idx, 1);
			else if ($scope.compareList.length < 2) $scope.compareList.push(pIdProperty);
		}
		
		// Change button color if selected
		$scope.selected = function(pIdProperty) {
			var idx = $scope.compareList.indexOf(pIdProperty);
			if (idx > -1) return "btn btn-success";
			else if ($scope.compareList.length < 2) return "btn btn-info";
		}
		
		// Sends data to compare screen
		$scope.compare = function() {
			localStorage.setItem("properties", JSON.stringify($scope.compareList));
			if ($scope.compareList.length < 2) return "btn btn-primary disabled"
			else return "btn btn-primary"
		}
		
		// Stores single id value
		$scope.viewProperty = function(pIdProperty) {
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.checkFavorites = function(pIdProperty) {
			var idx = $scope.favorites.indexOf(pIdProperty);
			if (idx > -1) return "btn btn-danger";
			else return "btn btn-default"
		}
		
		$scope.addToFavorites = function(pIdProperty) {
			var user = 1;
			// $rootScope.userLogged.idUser
			var db = 'rest/protected/users/addToFavorite/' + user;
			var dbRemove = 'rest/protected/users/removeFavorite/' + user;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.get('rest/protected/users/getUserById/' + user)
			.success(function(response) {
				if ($scope.favorites.length == 0) {
					$http.put(db, data)
					.success(function(response) {});
				} else {
					for (var i = 0; i < $scope.favorites.length; i++) {
						if ($scope.favorites[i] == pIdProperty) {
							$http.put(dbRemove, data)
							.success(function(response) {
							});
						} else {
							$http.put(db, data)
							.success(function(response) {
							});
						}
					}
				}
			});
		}
	}
	
})();
