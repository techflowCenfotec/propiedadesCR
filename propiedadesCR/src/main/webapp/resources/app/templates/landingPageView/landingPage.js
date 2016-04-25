"use strict";

angular.module("app.landing", []).controller(
		'LandingController',
		[ '$scope', '$rootScope', '$http', 'dbService', '$state',"sessionService",
				function($scope, $rootScope, $http, dbService, $state,sessionService) {
					$scope.vendors = [];
					$scope.hasVendors = false;
					sessionService.checkSession();
					$("#landingCarousel").carousel();
					dbService.localCheckDB();
					$http.get('rest/local/signOut').success(function(response) {

					});
					
					var link = 'rest/local/getAllVendors';
			 		var request = {
			 				  "pageNumber": 0,
			 				  "pageSize": 3,
			 				  "direction": "string",
			 				  "sortBy": [
			 				    "string"
			 				  ],
			 				  "searchColumn": "string",
			 				  "searchTerm": "string",
			 				  "user": {}
			 				}
			 		dbService.localCheckDB();
			 		$http.post(link,request).success(function(response) {
			 			  $scope.vendors = response.users;
			 			  $scope.hasVendors = true;
			 			 
			 			  
			 		});
			 		dbService.localCheckDB();
					$scope.login = function() {
						$state.go("templates/loginView/login");
					}
					$scope.createAccount = function() {
						$state.go("templates/usersView/createUserLogin");

					}
					$scope.aboutPropiedadesCR = function(){
						$('html,body').animate({
							scrollTop:$('#about').offset().top
							
						},
						'slow'
						)
					}
				

} ]);
