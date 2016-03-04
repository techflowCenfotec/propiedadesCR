(function() {
	"use strict";

	angular.module("app.createUsers", [])

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
							dt: new Date(),
							gender:'',
						};

						original = angular.copy($scope.form);
						function revert() {
							$scope.form = angular.copy(original);
							return $scope.form_createUser.$setPristine();
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
							console.log($scope.form.gender);
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

								$files[0].cancel()
								$scope.showInfoOnSubmit = true;
								return revert();
							});

						};
						$scope.open1 = function() {
							$scope.popup1.opened = true;
						};
						
						$scope.getDateWithFormat = function() {
					         var date = $scope.form.dt;
					         $scope.dateWithFormat = date.getFullYear()
					           + '/' + date.getMonth() + '/'
					           + date.getDate() + ' '
					           + date.getHours() + ':'
					           + date.getMinutes() + ':'
					           + date.getSeconds();

					        }

					} ]);
})();