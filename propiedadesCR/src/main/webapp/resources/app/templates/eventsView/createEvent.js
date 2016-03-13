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
							function($scope, $http, $location, $upload) {
									
								var original;
								$scope.onError = false;
								$scope.requestObject = {};
								$scope.maxDate = new Date(2020, 5, 22);
								$scope.dateWithFormat = '';
								$scope.files = {};
								$scope.form = {
									name : '',
									description : '',
									eventDate : new Date(),
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
															description : $scope.form.description,
															start_date : $scope.dateWithFormat,
															id_user : 0,
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

							} ]);
})();