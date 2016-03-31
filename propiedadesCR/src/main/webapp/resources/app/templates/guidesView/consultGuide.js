(function() {
	'use strict';

	angular.module('app.consultGuide', []).controller(
			'ConsultGuideController',
			[
					'$scope',
					'$http',
					'$location',
					'$upload',
					function($scope, $http, $location, $upload) {
						var original;
						$scope.onError = false;
						$scope.requestObject = {};
						$scope.files = {};
						$scope.banks = [];
						$scope.guides = [];

						$scope.form = {
							name : '',
							bank : ''
						};

						// //lista de bancos
						var linkBanks = 'rest/protected/banks/getAll';
						var request = {
							"pageNumber" : 0,
							"pageSize" : 0,
							"direction" : "",
							"sortBy" : [ "" ],
							"searchColumn" : "string",
							"searchTerm" : "",
							"bank" : {}
						};

						$http.post(linkBanks, request).success(
								function(response) {
									$scope.banks = response.banks;
									
								});

						$scope.consultGuides = function() {
							var req = {
								"pageNumber" : 0,
								"pageSize" : 0,
								"direction" : "string",
								"sortBy" : [ "string" ],
								"searchColumn" : "string",
								"searchTerm" : "string",
								"guide" : {
									"tbank" : {
										"idBank" : $scope.form.bank
									}
								}
							}
							$http.post('rest/protected/guides/getGuidesByBank',req)
								.success(function(response){
									
									$scope.guides = response.guides;
								});
							
						}
	} ]);

})();