"use strict";

angular.module("app.landing", []).controller(
		'LandingController',
		[ '$scope', '$rootScope', '$http', 'dbService', '$state',
				function($scope, $rootScope, $http, dbService, $state) {

					$("#landingCarousel").carousel();
					dbService.localCheckDB();
					$http.get('rest/local/signOut').success(function(response) {

					});

					$scope.login = function() {
						$state.go("templates/loginView/login");
					}
					$scope.createAccount = function() {
						$state.go("templates/usersView/createUserLogin");

					}
					dbService.localCheckDB();

} ]);
