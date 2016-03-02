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
						$scope.files = {};
						$scope.form = {
							name : '',
							email : '',
							firstName : '',
							lastName : '',
							phone1 : '',
							phone2 : '',
							password : '',
							confirmPassword : '',
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
							
							var i = 0;

							var file = $files[i].file;
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
								},
								file : file,
							}).success(function(data, status, headers, config) {
								//		    					
								$files[0].cancel()
								$scope.showInfoOnSubmit = true;
								return revert();
							});

						};

					} ]);
})();