(function() {
	"use strict";

	angular.module("app.consultUser", [])

	.controller(
			'ConsultUserController',
			[
					'$scope',
					'$http',
					'$rootScope',
					'$mdDialog',
					function($scope, $http, $rootScope, $mdDialog) {

						$scope.user = {};
						$scope.rate = 0;
						$scope.max = 5;
						$scope.notVendor;

						var link = 'rest/protected/users/getUserById/'
								+ localStorage.getItem('idUser');
						$http.get(link).success(function(response) {

							$scope.user = response.user;
							if ($scope.user.role.rolName === "Vendedor")
								$scope.notVendor = false;
							else
								$scope.notVendor = true;

							return $scope.user;
						});
						$scope.hoveringOver = function(value) {

						}

					} ]);
})();