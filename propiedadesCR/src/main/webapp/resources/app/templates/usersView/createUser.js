(function() {
	"use strict";

	angular.module("app.createUsers", ['validation.match'])

	.controller(
			'CreateUserController',
			[
					'$scope',
					'$http',
					'$location',
					'$upload',
					function($scope, $http, $location, $upload) {

						var original;
						$scope.dateWithFormat = '';

				        $scope.popup1 = {
				            opened: false
				        };
						
						$scope.form = {
							name : '',
							email : '',
							firstName : '',
							lastName : '',
							phone1 : '',
							phone2 : '',
							password : '',
							confirmPassword : '',
							birthday: new Date(),
							gender:'',
						};

						original = angular.copy($scope.form);
						
						function revert() {
							    $scope.form = angular.copy(original);	
							    $scope.form_createUser.$setPristine()
					            $scope.form_createUser.$setUntouched();
							    return;
						}
						;
						$scope.canRevert = function() {
							return !angular.equals($scope.form, original)
									|| !$scope.form_createUser.$pristine;
						};
						$scope.canSubmit = function() {
							return $scope.form_createUser.$valid
									&& !angular.equals($scope.form, original);
						};
						$scope.submitForm = function(event, $files) {
							$scope.saveUser(event, $files);
						};
						$scope.saveUser = function(event, $files) {
							$scope.getDateWithFormat();
							
							var file = $files[0].file;
							$scope.upload = $upload.upload({
								url : 'rest/protected/users/create',
								data : {
									idRol : 0,
									userName : $scope.form.name,
									firstName : $scope.form.firstName,
									lastName : $scope.form.lastName,
									phone1 : $scope.form.phone1,
									phone2 : $scope.form.phone2,
									email : $scope.form.email,
									password : $scope.form.password,
									birthday : $scope.dateWithFormat,
									gender : $scope.form.gender,
								},
								file : file,
							}).success(function(data, status, headers, config) {
								$files[0].cancel();
								$scope.showInfoOnSubmit= true;
								return revert();
							}).error(function(data){
						
							});

						};
						$scope.open1 = function() {
							$scope.popup1.opened = true;
						};
						
						$scope.getDateWithFormat = function() {
					         
					         $scope.dateWithFormat = $scope.form.birthday.getFullYear()
					           + '-' + $scope.form.birthday.getMonth() + '-'
					           + $scope.form.birthday.getDate() + ' '
					           + $scope.form.birthday.getHours() + ':'
					           + $scope.form.birthday.getMinutes() + ':'
					           + $scope.form.birthday.getSeconds();
					       
					        };



					} ]);
})();