(function() {
	"use strict";

	angular.module("app.createUserLogin", ['validation.match'])
	.controller(
			'CreateUserLoginController',
			[
					'$scope',
					'$http',
					'$location',
					'$upload',
					function($scope, $http, $location, $upload) {

						var original;
						$scope.dateWithFormat = '';
						$scope.maxDate = new Date();
						$scope.roles ={};
						$scope.emailExist =false;
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
							role: '',
							gender:'',
						};
						
						validate();
						
						function validate(){
						$http.get("rest/protected/database/checkDB").success(function(data){
							if(data.code!==200){
								var path = "#/templates/errorsView/500";
								
				    			window.location.href = path;
							}
						});
						}
						

						  var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {}};
						
						$http.post('rest/local/getAll',request).success(function(response) {
							$scope.roles= response.role;
							$scope.roles= _.without($scope.roles,_.findWhere($scope.roles,{idRole:1}));

							return $scope.roles;
								
						});
						
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
							validate();
							$scope.getDateWithFormat();
							var file
							
							if($files[0] == undefined)
								file = new File([],'default_user_image_PropiedadesCR.png');
							
							else
							 file = $files[0].file;
							
							$scope.upload = $upload.upload({
								url : 'rest/local/createUser',
								data : {
									idRol : $scope.form.role,
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
								if($files[0] != undefined)
								$files[0].cancel();
								
								$scope.showInfoOnSubmit= true;
								var inf={
										  "pageNumber": 0,
										  "pageSize": 0,
										  "direction": "string",
										  "sortBy": [
										    "string"
										  ],
										  "searchColumn": "string",
										  "searchTerm": "string",
										  "user": {"email":$scope.form.email}
										}
								$http.post('rest/local/welcomeEmail',inf).success(function(){
									
									
								})
								
								var path = "/propiedadesCR/";
								window.location.href = path;
								return revert();
				
							}).error(function(data){
								$scope.emailExist =true;
							});

						};
						$scope.open1 = function() {
							$scope.popup1.opened = true;
						};
						
						$scope.getDateWithFormat = function() {
							var month = $scope.form.birthday.getMonth()+1;
					         $scope.dateWithFormat = $scope.form.birthday.getFullYear()
					           + '-' + month + '-'
					           + $scope.form.birthday.getDate() + ' '
					           + $scope.form.birthday.getHours() + ':'
					           + $scope.form.birthday.getMinutes() + ':'
					           + $scope.form.birthday.getSeconds();
					        
					        };



					} ]);
	

})();