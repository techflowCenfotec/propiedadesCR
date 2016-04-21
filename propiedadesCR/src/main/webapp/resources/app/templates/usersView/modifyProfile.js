(function() {
	"use strict";

	angular.module("app.modifyProfile", [ 'validation.match', ])

	.controller(
			'ModifyProfileController',
			[ '$scope', '$http', '$location', '$upload','$mdToast','$timeout','$rootScope','$state','dbService',
					function($scope, $http, $location, $upload, $mdToast,$timeout,$rootScope,$state,dbService) {
				
						var original;
						$scope.dateWithFormat = '';
						$scope.user = {};
						$scope.roles = {};

						$scope.popup1 = {
							opened : false
						};
					
						
						$scope.form = {
							name : '',					
							firstName : '',
							lastName : '',
							phone1 : '',
							phone2 : '',
							birthday : new Date(),
							gender : '',
						};
					
					    $scope.user = $rootScope.userLogged;
						$scope.form.name = $scope.user.userName;	
						$scope.form.firstName = $scope.user.firstName;
						$scope.form.lastName = $scope.user.lastName;
						$scope.form.phone1 = $scope.user.phone1;
						$scope.form.phone2 = $scope.user.phone2;
						$scope.form.gender = $scope.user.gender;
						$scope.form.birthday = new Date($scope.user.birthday);
							
						dbService.checkDB();
						
						$scope.canSubmit = function() {
							return $scope.form_createUser.$valid
									&& !angular.equals($scope.form, original);
						};
						$scope.submitForm = function(event, $files) {
							$scope.modifyUser(event, $files);
						};					

						$scope.open1 = function() {
							$scope.popup1.opened = true;
						};
						
						$scope.modifyUser = function(event, $files) {
							dbService.checkDB();
							$scope.getDateWithFormat();
							var file;
	
							if($files[0] == undefined)
								file = new File([],$scope.user.userImage);
							
							else
							 file = $files[0].file;
							
							$scope.upload = $upload.upload({
								url : 'rest/protected/users/modifyUser',
								data : {
									idUser : $rootScope.userLogged.idUser,
									idRol : $rootScope.userLogged.role.idRole,
									userName : $scope.form.name,
									firstName : $scope.form.firstName,
									lastName : $scope.form.lastName,
									phone1 : $scope.form.phone1,
									phone2 : $scope.form.phone2,
									email : $rootScope.userLogged.email,
									password : '',
									birthday : $scope.dateWithFormat,
									gender : $scope.form.gender,
								},
								file : file,
							}).success(function(data, status, headers, config) {
								
								$rootScope.userLogged = data.user;
								$scope.showInfoOnSubmit= true;
								  $timeout(function(){
							          $scope.showInfoOnSubmit = false;
							        
							          $state.go("templates/usersView/consultUser")
							       }, 3000);
								 
							}).error(function(data){
						
							});

						};
						
						$scope.getDateWithFormat = function() {
					         var month = $scope.form.birthday.getMonth()+1;
					        
					         $scope.dateWithFormat = $scope.form.birthday.getFullYear()
					           + '-'+month+ '-'
					           + $scope.form.birthday.getDate() + ' '
					           + $scope.form.birthday.getHours() + ':'
					           + $scope.form.birthday.getMinutes() + ':'
					           + $scope.form.birthday.getSeconds();
					        };

						

						
					} ]);

})();