(function() {
	"use strict";

	angular.module("app.modifyUser", [ 'validation.match', ])

	.controller(
			'ModifyUserController',
			[ '$scope', '$http', '$location', '$upload','$mdToast',
					function($scope, $http, $location, $upload, $mdToast) {
				
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
						$scope.users = {};
						$scope.roles = {};

						$scope.popup1 = {
							opened : false
						};
					
						var link = 'rest/protected/users/getUserById/'+localStorage.getItem('idUserModify');
						$scope.form = {
							name : '',
							email : '',
							firstName : '',
							lastName : '',
							phone1 : '',
							phone2 : '',
							birthday : new Date(),
							role : '',
							gender : '',
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
							$scope.user = response.user;
							$scope.form.name = $scope.user.userName;
							$scope.form.email = $scope.user.email;
							$scope.form.firstName = $scope.user.firstName;
							$scope.form.lastName = $scope.user.lastName;
							$scope.form.phone1 = $scope.user.phone1;
							$scope.form.phone2 = $scope.user.phone2;
							$scope.form.role = $scope.user.role.idRole;
							$scope.form.gender = $scope.user.gender;
							$scope.form.birthday = new Date($scope.user.birthday.replace(/-/g , ","));
						
							
							
							return $scope.user
					
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
						
						$http.post('rest/protected/roles/getAll',request).success(function(response) {
							$scope.roles= response.role;
		
							return $scope.roles;
								
						});
												
					
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
							$scope.getDateWithFormat();
							var file;
							
							if($files[0] == undefined)
								file = new File([],$scope.user.userImage);
							
							else
							 file = $files[0].file;
							
							$scope.upload = $upload.upload({
								url : 'rest/protected/users/modifyUser',
								data : {
									idUser : $scope.user.idUser,
									idRol : $scope.form.role,
									userName : $scope.form.name,
									firstName : $scope.form.firstName,
									lastName : $scope.form.lastName,
									phone1 : $scope.form.phone1,
									phone2 : $scope.form.phone2,
									email : $scope.form.email,
									password : $scope.user.password,
									birthday : $scope.dateWithFormat,
									gender : $scope.form.gender,
								},
								file : file,
							}).success(function(data, status, headers, config) {
								$mdToast.show(
						                $mdToast.simple()
						                    .content('Se ha modificado el usuario!')
						                    .position($scope.getToastPosition())
						                    .hideDelay(3000)
						            );
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