(function() {
	"use strict";

	angular.module("app.consultVendor", [])

	.controller(
			'ConsultVendorController',
			[
					'$scope',
					'$http',
					'$rootScope',
					'$mdDialog',
					function($scope, $http, $rootScope, $mdDialog) {

						$scope.user = {};
						$scope.rate = 0;
						$scope.max = 5;

						var link = 'rest/protected/users/getUserById/'
								+ localStorage.getItem('idVendor');
						$http.get(link).success(function(response) {

							$scope.user = response.user;

							return $scope.user;
						});
						$scope.hoveringOver = function(value) {

						}
						$scope.saveRating = function(value) {
							
							console.log(localStorage.getItem('idUser'));
							console.log($scope.user.idUser);
							var request = {
								"pageNumber" : 0,
								"pageSize" : 0,
								"direction" : "",
								"sortBy" : [ "" ],
								"searchColumn" : "string",
								"searchTerm" : "",
								"rating" : {
									"averageRating" : value
								},
								"idClient" : localStorage.getItem('idUser'),
								"idVendor" : $scope.user.idUser
							};
							$http.post('rest/protected/userRating/saveRating',
									request).success(function() {

							})
						}
					} ]);
})();