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
						$scope.totalPages =0;
						$scope.pageSize = 10;
						$scope.pageNumber = 0;
				        $scope.numPerPageOpt = [2, 5, 10, 20];
				        $scope.numPerPage = $scope.numPerPageOpt[2];
				        $scope.currentPage = 1;
				        $scope.currentPage = [];

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
						
						$scope.changePage = function(page){
							$scope.pageNumber = page-1;
							$scope.consultGuides();
						};
					        
						 $scope.onNumPerPageChange = function(){
							 $scope.pageSize = $scope.numPerPage;
							 $scope.consultGuides();
					     };
						
						$scope.consultGuides = function() {
							var req = {
								"pageNumber" : $scope.pageNumber,
								"pageSize" : $scope.pageSize,
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
									o$scope.totalPages = response.totalPages;
								});
							
						}
	} ]);

})();