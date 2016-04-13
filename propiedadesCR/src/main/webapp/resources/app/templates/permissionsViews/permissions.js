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
		//PRINCIPAL
		$scope.addTodolist = false;
		$scope.viewTodolist = false;
		$scope.adminTodolist = false;
		$scope.test = false;
		$scope.addGuide = false;
		$scope.viewGuides = false;
		$scope.viewMyTodolist = false;
		//VER VENDEDORES
		$scope.sellersView = false;
		//PROPIEDADES
		$scope.addProperty = false;
		$scope.myProperties = false;
		$scope.viewProperties = false;
		$scope.viewFavorites = false;
		//EVENTOS
		$scope.addEvent = false;
		$scope.viewEvents = false;
		$scope.eventList= false;
		//USUARIOS
		$scope.addUser = false;
		$scope.viewUsers = false;
		$scope.viewRoles = false;
		$scope.addRole = false;
		//REPORTES
		$scope.sellsReport = false;
		$scope.banksReport = false;
		
		var userResponse = {};
		var userPermissionsList = [];
		var link = 'rest/protected/users/getUserLogged';

		$scope.makeShow = function(){
			var display = localStorage.getItem('style');
			if(display === null){
				display = "display: none;";
			}
			return display;
		};

		$scope.propShow = function(){
			var display = localStorage.getItem('propStyle');
			if(display === null){
				display = "display: none;";
			}
			return display;
		};

		$scope.eventShow = function(){
			var display = localStorage.getItem('eveStyle');
			if(display === null){
				display = "display: none;";
			}
			return display;
		};
		
		var me = $scope.makeShow;
		$http.get(link).success(function(response) {	
			userResponse = response;
			userPermissionsList = userResponse.user.role.tpermissions;
			var permission = {};
			var idPermission = 0;
			//var modules = [1,9,14,18,23];

			for(var i = 0; i<userPermissionsList.length;i++){
				permission = userPermissionsList[i];
				idPermission = permission.idPermissions;
				
				//1.admin
				//2.comprador
				//3.vendedor
				if(idPermission == 1){
					$scope.showPrincipal = true;
				}
				if(idPermission == 9){
					$scope.showProperties = true;
				}
				if(idPermission == 14){
					$scope.showEvents = true;
				}
				if(idPermission == 18){
					$scope.sellersView = true;
				}
				if(idPermission == 19){
					$scope.showUsers = true; 
				}
				if(idPermission == 24){
					$scope.showReports = true;
				}
			}

			checkPermissionsByModule();

		});

		function checkPermissionsByModule(){
			
				if($scope.showPrincipal){
					for (var i = 0; i < userPermissionsList.length; i++) {
							
						if(userPermissionsList[i].idPermissions == 2){
							$scope.addTodolist = true;
						}
						if(userPermissionsList[i].idPermissions == 3){
							$scope.viewTodolist = true;
						}
						if(userPermissionsList[i].idPermissions == 4){
							$scope.adminTodolist = true;
						}
						if(userPermissionsList[i].idPermissions == 5){
							$scope.test = true; 
						}
						if(userPermissionsList[i].idPermissions == 6){
							$scope.addGuide = true;
						}
						if(userPermissionsList[i].idPermissions == 7){
							$scope.viewGuides = true;
						}
						if(userPermissionsList[i].idPermissions == 8){
							$scope.viewMyTodolist = true;
						}
					}
				}
				if($scope.showProperties){
					for (var i = 0; i < userPermissionsList.length; i++) {
						
						if(userPermissionsList[i].idPermissions == 10){
							$scope.addProperty = true;
						}
						if(userPermissionsList[i].idPermissions == 11){
							$scope.myProperties = true;
						}
						if(userPermissionsList[i].idPermissions == 12){
							$scope.viewProperties = true;
						}
						if(userPermissionsList[i].idPermissions == 13){
							$scope.viewFavorites = true; 
						}
					}
				}
				if($scope.showEvents){
					for (var i = 0; i < userPermissionsList.length; i++) {
						
						if(userPermissionsList[i].idPermissions == 15){
							$scope.addEvent = true;
						}
						if(userPermissionsList[i].idPermissions == 16){
							$scope.viewEvents = true;
						}
						if(userPermissionsList[i].idPermissions == 17){
							$scope.eventList = true;
						}
					}
				}
				if($scope.showUsers){
					for (var i = 0; i < userPermissionsList.length; i++) {
						
						if(userPermissionsList[i].idPermissions == 20){
							$scope.addUser = true;
						}
						if(userPermissionsList[i].idPermissions == 21){
							$scope.viewUsers = true;
						}
						if(userPermissionsList[i].idPermissions == 22){
							$scope.viewRoles = true;
						}
						if(userPermissionsList[i].idPermissions == 23){
							$scope.addRole = true; 
						}
						
					}
				}
				if($scope.showReports){
					for (var i = 0; i < userPermissionsList.length; i++) {
						
						if(userPermissionsList[i].idPermissions == 25){
							$scope.sellsReport = true;
						}
						if(userPermissionsList[i].idPermissions == 26){
							$scope.banksReport = true;
						}
						
					}
				}
		};

	}]);

})();
