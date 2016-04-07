(function() {
	"use strict";

	angular.module('app.properties',[])

	.controller('PropertiesListController', ['$scope', '$http', '$uibModal', '$rootScope', '$log', PropertiesListController]);
	
	function PropertiesListController($scope, $http, $uibModal, $rootScope, $log) {
		 $scope.propertiesList = [];
		 $scope.compareList = [];
		 $scope.favorites = [];
		 $scope.isCollapsed = false;
		 $scope.selectedBenefits = [];
		
		$scope.init = function() {
			var active = 1,
				sold = 0;
			
			$http.get('rest/protected/properties/getAll')
			.success(function(response) {
				for (var i = 0; i < response.properties.length; i++) {
					if(response.properties[i].active == active && response.properties[i].isSold == sold) 
						$scope.propertiesList.push(response.properties[i]);
				}
				
				$http.get('rest/protected/users/getUserById/' + $rootScope.userLogged.idUser)
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
			$http.get('rest/protected/properties/saveView/' + pIdProperty).success(
					function(){
						
					});
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.checkFavorites = function(pIdProperty) {
			var idx = $scope.favorites.indexOf(pIdProperty);
			if (idx > -1) return "btn btn-danger";
			else return "btn btn-default"
		}
		
		$scope.isReadyToCompare = function() {
			if ($scope.compareList.length == 2) {
				return true;
			} else {
				return false;
			}
		}
		
		// Bring BenefitList from modal. Assigned to keyword for search
		$scope.$on("filterAction", function(e, benefitsList) {
			$scope.selectedBenefits = benefitsList;
			$scope.keyword = _.pluck($scope.selectedBenefits, 'benefit').join(', ');
		});
		
		$scope.keywords = function(post) {
			var isMatch = false;
		      
		    if ($scope.keyword) {
		      var parts = $scope.keyword.split(', ');
		        
		      parts.forEach(function(part) {
		        var rx = new RegExp(part, "i"); //i: case insensitive
		        post.tbenefits.forEach(function(caract) {
		          if (rx.test(caract.benefit)) {
		            isMatch = true;
		          }
		        });
		      });
		     } else {
		       isMatch = true;
		     }
		   return isMatch;
		};
		
		$scope.addToFavorites = function(pIdProperty) {
			var db = 'rest/protected/users/addToFavorite/' + $rootScope.userLogged.idUser;
			var dbRemove = 'rest/protected/users/removeFavorite/' + $rootScope.userLogged.idUser;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.get('rest/protected/users/getUserById/' + $rootScope.userLogged.idUser)
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
