(function() {
	"use strict";

	angular.module("app.modifyEvents", [ 'validation.match', ])

	.controller(
			'ModifyEventsController',
			[ '$scope', '$http', '$location', '$upload','$mdToast', 'NgMap','$rootScope','$timeout','dbService',
					function($scope, $http, $location, $upload, $mdToast, NgMap,$rootScope,$timeout,dbService) {
				
						validate();
						
						function validate(){
						$http.get("rest/protected/database/checkDB").success(function(data){
							if(data.code!==200){
								var path = "#/templates/errorsView/500";
								
				    			window.location.href = path;
							}
						});
						}
						var original;
						$scope.dateWithFormat = '';
						$scope.events = {};
						

						$scope.popup1 = {
							opened : false
						};
					
						var link = 'rest/protected/events/getById/'+localStorage.getItem('idEventModify');
						$scope.form = {
							name : '',
							direction : '',
							description : '',
							eventDate : new Date(),
							coordinates: '',
							
						};
						
					    var last = {
				                bottom: false,
				                top: true,
				                left: false,
				                right: false
				            };
					    
					    
						$scope.toastPosition = angular.extend({},last);
						
						original = angular.copy($scope.form);
						
						$http.get(link).success(function(response) {
							validate();
							console.log(response.event)
							$scope.event = response.event;
							$scope.form.name = $scope.event.name;
							$scope.form.description = $scope.event.description;
							$scope.form.eventDate = new Date($scope.event.startDate.replace(/-/g , ","));
							$scope.form.direction = $scope.event.address;
							$scope.markerLoc = $scope.event.coordinates;
							
						
							return $scope.event
					
						});
						
						$scope.getToastPosition = function() {
				            sanitizePosition();

				            return Object.keys($scope.toastPosition)
				                .filter(function(pos) { return $scope.toastPosition[pos]; })
				                .join(' ');
				        };
				        
				        function sanitizePosition() {
				            var current = $scope.toastPosition;

				            if ( current.bottom && last.top ) current.top = false;
				            if ( current.top && last.bottom ) current.bottom = false;
				            if ( current.right && last.left ) current.left = false;
				            if ( current.left && last.right ) current.right = false;

				            last = angular.extend({},current);
				        }

						 var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {}};
						
					
												
					
						$scope.canSubmit = function() {
							return $scope.form_createEvent.$valid
									&& !angular.equals($scope.form, original);
						};
						$scope.submitForm = function(event, $files) {
							$scope.modifyEvents(event, $files);
						};					

						$scope.open1 = function() {
							$scope.popup1.opened = true;
						};
						
						$scope.modifyEvents = function(event, $files) {
							$scope.getDateWithFormat();
							var file;
							dbService.checkDB();
							if($files[0] == undefined)
								file = new File([],$scope.event.eventImage);
							
							else
							 file = $files[0].file;
							
							$scope.upload = $upload.upload({
								url : 'rest/protected/events/modifyEvent',
								data : {
									idEvent : $scope.event.idEvent,
								    name : $scope.form.name,
									description : $scope.form.description,
									start_date : $scope.dateWithFormat,
									address : $scope.form.direction,
									coordinates : $scope.markerLoc,
									id_user : $rootScope.userLogged.idUser,
									
								},
								file : file,
							}).success(function(data, status, headers, config) {
								$scope.showInfoOnSubmit = true;
								$timeout(function(){
									$scope.showInfoOnSubmit = false;
							        $location.path("/templates/eventsView/eventsList"); 
							    }, 1000);

							}).error(function(data){
						
							});

						};
						
						$scope.getDateWithFormat = function() {
					         var month = $scope.form.eventDate.getMonth()+1;
					        
					         $scope.dateWithFormat = $scope.form.eventDate.getFullYear()
					           + '-'+month+ '-'
					           + $scope.form.eventDate.getDate() + ' '
					           + $scope.form.eventDate.getHours() + ':'
					           + $scope.form.eventDate.getMinutes() + ':'
					           + $scope.form.eventDate.getSeconds();
					         
					        
					       
					        };

					    	NgMap.getMap().then(function(map) {
								$scope.markerPos =  function() {
									$scope.markerLoc = '['+ map.markers[0].position.lat() + ',' + map.markers[0].position.lng() +']';
									
								};
								
								
							});

						
					} ]);

})();