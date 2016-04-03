(function() {
	"use strict";

	angular
			.module("app.consultVendor", [])

			.controller(
					'ConsultVendorController',
					[
							'$scope',
							'$http',
							'$rootScope',
							'$mdDialog',
							'$timeout',
							function($scope, $http, $rootScope, $mdDialo,
									$timeout) {
								
								var original;
								$scope.user = {};
								$scope.rate = 0;
								$scope.btnRateAndComment = 'Guardar';
								$scope.max = 5;
								$scope.isReadonly = true;
								$scope.hasRateAndComment = false;
								$scope.myRate = 0;
								$scope.userRate = 0;
								$scope.userRating = {};
								$scope.clients = {};
								$scope.isCreated = false;
								$scope.edit = true;
								$scope.myReadOnly = false;
								$scope.myCommentTextArea = false;
								$scope.myCommentP = true;
								$scope.form = {
									comment : '',
								}
								$scope.showInfo = false;
								original = angular.copy($scope.form);
								
								validate();

								function validate() {
									$http
											.get(
													"rest/protected/database/checkDB")
											.success(
													function(data) {
														if (data.code !== 200) {
															var path = "#/templates/errorsView/500";

															window.location.href = path;
														}
													});
								}

								var link = 'rest/protected/users/getVendorById/'
										+ localStorage.getItem('idVendor');
								$http
										.get(link)
										.success(
												function(response) {
													validate();
													generalRating(response.user.vendorRatings);
													$scope.user = response.user;
													$scope.clients = response.user.vendorRatings;
													
													for(var index in $scope.clients){
														if($scope.clients[index]!=undefined){
															if($scope.clients[index].tuser1.idUser===$rootScope.userLogged.idUser)
																$scope.clients =  _.without($scope.clients, $scope.clients[index]);	
														}
													}
													for(var index in $scope.clients){
														if($scope.clients[index]!=undefined){
															if($scope.clients[index].tuser1.active===0)
																$scope.clients =  _.without($scope.clients, $scope.clients[index]);	
														}
													}
													
													getRate();

													return $scope.user;
												});
								$scope.hoveringOver = function(value) {

								}
								$scope.saveRating = function(value) {
									$scope.userRate = value;
								}

								$scope.saveUserComment = function() {
									var request;
									if ($scope.hasRateAndComment != true) {
										request = {
											"pageNumber" : 0,
											"pageSize" : 0,
											"direction" : "",
											"sortBy" : [ "" ],
											"searchColumn" : "string",
											"searchTerm" : "",
											"rating" : {
												"averageRating" : $scope.userRate
											},
											"idClient" : localStorage
													.getItem('idUser'),
											"idVendor" : $scope.user.idUser
										};
										$http
												.post(
														'rest/protected/userRating/saveRating',
														request)
												.success(
														function() {

															$scope.showInfo = true;
															getRate();
															$timeout(
																	function() {
																		$scope.showInfo = false;
																	}, 3000);
														});
									} else {

										request = {
											"pageNumber" : 0,
											"pageSize" : 0,
											"direction" : "",
											"sortBy" : [ "" ],
											"searchColumn" : "string",
											"searchTerm" : "",
											"rating" : {
												"idRating" : $scope.userRating.idRating,
												"averageRating" : $scope.userRate
											},
											"idClient" : localStorage
													.getItem('idUser'),
											"idVendor" : $scope.user.idUser
										};
										$http
												.post(
														'rest/protected/userRating/modifyRating',
														request)
												.success(
														function() {
															$scope.showInfo = true;
															
															$timeout(
																	function() {
																		$scope.showInfo = false;
																		disable();
																	}, 3000);
														});
									}

									var linkRequestVendor = 'rest/protected/users/getVendorById/'
											+ localStorage.getItem('idVendor');
									$http
											.get(linkRequestVendor)
											.success(
													function(response) {
														validate();
														generalRating(response.user.vendorRatings);
													});

								}

								function generalRating(ratings) {
									var generalRating = 0;
									for (var i = 0; i < ratings.length; i++) {
										generalRating += ratings[i].averageRating;
									}
									$scope.rate = generalRating / i;
								}
								
								
								function getRate(){

									var userRatingRequest = {
										"pageNumber" : 0,
										"pageSize" : 0,
										"direction" : "",
										"sortBy" : [ "" ],
										"searchColumn" : "string",
										"searchTerm" : "",
										"rating" : {
											"averageRating" : $scope.userRate
										},
										"idClient" : localStorage
												.getItem('idUser'),
										"idVendor" : $scope.user.idUser
									};
									$http
									.post(
											'rest/protected/userRating/getRating',
											userRatingRequest)
									.success(
											function(
													response) {
												if (response.userRating != null) {
													$scope.myRate = response.userRating.averageRating;
													$scope.hasRateAndComment = true;
													$scope.btnRateAndComment = 'Modificar';
													$scope.userRating = response.userRating;
												}
												disable();
											});
								}
								function disable(){
									$scope.isCreated = true;
									$scope.myCommentTextArea = true;
									$scope.myCommentP = false;
									$scope.edit = false;
									$scope.myReadOnly = true;
								}
								$scope.editComment = function(){
									$scope.isCreated = false;
									$scope.myCommentTextArea = false;
									$scope.myCommentP = true;
									$scope.edit = true;
									$scope.myReadOnly = false;
								}

							} ]);
})();