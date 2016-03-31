(function(){

	'use strict';

	angular.module('app.permissionsManagment', [])
	.controller('permissionsController',['$scope','$http',function($scope,$http){

		$scope.showPrincipal = false;
		$scope.showProperties = false;
		$scope.showEvents = false;
		$scope.showUsers = false;
		$scope.showSecurity = false;
		$scope.showReports = false;

		$scope.showPrincipalAdmin = false;
		$scope.showRegisterProperty = false;
		$scope.showEventsCreate = false;
		$scope.showEventsAdmin =false;
		var userResponse = {};
		var userPermissionsList = [];
		var link = 'rest/protected/users/getUserLogged';


		$http.get(link).success(function(response) {
			userResponse = response;
			userPermissionsList = userResponse.user.role.tpermissions;
			var permission = {};
			var idPermission = 0;

			for(var i = 0; i<userPermissionsList.length;i++){
				permission = userPermissionsList[i];
				idPermission = permission.idPermissions;
				

				//1.admin
				//2.comprador
				//3.vendedor
				if(idPermission == 1){
					$scope.showPrincipal = true;
					if(userResponse.user.role.idRole==1){
						$scope.showPrincipalAdmin = true;
					}
				}else if(idPermission == 2){
					$scope.showProperties = true;
					if(userResponse.user.role.idRole==2){
						$scope.showRegisterProperty = true;
					}
				}else if(idPermission == 3){
					$scope.showEvents = true;
					if(userResponse.user.role.idRole==2){
						$scope.showEventsCreate = true;
					}
					if(userResponse.user.role.idRole==1){
						$scope.showEventsAdmin = true;
					}
					
				}else if(idPermission == 4){
					if(userResponse.user.role.idRole==1){
						$scope.showUsers = true;
					}
				}else if(idPermission == 5){
					$scope.showSecurity = true;
				}else{
					$scope.showReports = true;
				}
			}
		});

	}]);

})();