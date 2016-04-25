(function() {
	"use strict";

	angular
			.module("app.events", [])

			.controller(
					'CreateEventController',
					[
							'$scope',
							'$http',
							'$location',
							'$upload',
							'NgMap',
							'$rootScope',
							'$timeout',
							'dbService',
							
							function($scope, $http, $location, $upload,NgMap,$rootScope,$timeout,dbService) {
									
								var original;
								$scope.onError = false;
								$scope.markerLoc = '[9.928003, -84.094463]';
								$scope.requestObject = {};
								$scope.maxDate = new Date(2020, 5, 22);
								$scope.dateWithFormat = '';
								$scope.files = {};
								$scope.form = {
									name : '',
									direction:'',
									description : '',
									eventDate : new Date(),
									
								};
								 
							        $scope.hstep = 1;

							        $scope.mstep = 15;

							        $scope.options = {
							            hstep: [1, 2, 3],
							            mstep: [1, 5, 10, 15, 25, 30]
							        };

							        $scope.ismeridian = true;

							        $scope.toggleMode = function() {
							            return $scope.ismeridian = !$scope.ismeridian;
							        };

							        $scope.update = function() {
							            var d;
							            d = new Date();
							            d.setHours(14);
							            d.setMinutes(0);
							            return $scope.form.eventDate = d;
							        };

								
								var request = {
									"pageNumber" : 0,
									"pageSize" : 0,
									"direction" : "",
									"sortBy" : [ "" ],
									"searchColumn" : "string",
									"searchTerm" : "",
									"event" : {}
								};

								original = angular.copy($scope.form);

								function revert() {
									$scope.form = angular.copy(original);
									$scope.form_createEvent.$setPristine()
									$scope.form_createEvent.$setUntouched();
									return;
								}
								;
								$scope.canRevert = function() {
									return !angular.equals($scope.form,
											original)
											|| !$scope.form_createEvent.$pristine;
								};
								$scope.canSubmit = function() {

									return $scope.form_createEvent.$valid
											&& !angular.equals($scope.form,
													original);
								};
								$scope.submitForm = function(event, $files) {

									$scope.saveEvent(event, $files);
									$timeout(function(){
								        $location.path("/templates/eventsView/eventsList"); 
								    }, 1000);

								};

								$scope.dateOptions = {
									formatYear : 'yyyy',
									startingDay : 1
								};

								$scope.toggleMin = function() {
									$scope.minDate = $scope.minDate ? null
											: new Date();
								};
								$scope.open1 = function() {
									$scope.toggleMin();
									$scope.popup1.opened = true;
								};
								$scope.popup1 = {
									opened : false
								};

								$scope.onFileSelect = function($files) {
									$scope.files = $files;

								};
								$scope.saveEvent = function($files) {
									dbService.checkDB();
									$scope.getDateWithFormat();
									
									var file;
									if ($files[0] == undefined)
										file = new File([],
												'default_events_image_PropiedadesCR.png');
									else
										file = $files[0].file;

									$scope.upload = $upload
											.upload(
													{
														url : 'rest/protected/events/create',
														data : {
															name : $scope.form.name,
															description: $scope.form.description,
															start_date : $scope.dateWithFormat,
															address:$scope.form.direction,
															coordinates:$scope.markerLoc,													
															id_user : $rootScope.userLogged.idUser,
														},
														file : file,
													})
											.success(
													function(data, status,
															headers, config) {
														if($files[0] != undefined)
															$files[0].cancel();
														
														$scope.showInfoOnSubmit = true;
														return revert();
													});
								};

								$scope.getDateWithFormat = function() {
									var month= $scope.form.eventDate.getMonth()+1;
									$scope.dateWithFormat = $scope.form.eventDate
											.getFullYear()
											+ '-'
											+ month
											+ '-'
											+ $scope.form.eventDate.getDate()
											+ ' '
											+ $scope.form.eventDate.getHours()
											+ ':'
											+ $scope.form.eventDate
													.getMinutes()
											+ ':'
											+ $scope.form.eventDate
													.getSeconds();

								}
								
								NgMap.getMap().then(function(map) {
									$scope.markerPos =  function() {
										$scope.markerLoc = '['+ map.markers[0].position.lat() + ',' + map.markers[0].position.lng() +']';
										
									};
									
									
								});

							} ]);
})();