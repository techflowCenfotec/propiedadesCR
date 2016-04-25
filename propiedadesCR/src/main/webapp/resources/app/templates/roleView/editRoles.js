(function(){
	'use strict';

	angular.module('app.editRoles', [])
	.controller('modifyingRoles',
		['$scope',
		'$http',
		'$upload',
		'$mdToast',
		'$location',
		'$timeout',
		function($scope, $http, $upload, $mdToast, $location, $timeout){
		
		$scope.permissionsList = [];
		$scope.consultPermissions = [];
		$scope.onError = false;
		$scope.consultRoleName = '';
		$scope.form = { 	
	        newRoleName: '' ,
	        newPermissions: []

	    };
	    $scope.checkbox;
		$scope.done = false;
		$scope.toastPosition = angular.extend({},last);
		//var newPermissions = [];
		var original;
		var link = 'rest/protected/permissions/getAll';
		var linkRole ='rest/protected/roles/edit';
		var linkIdRole = 'rest/protected/roles/getRoleById';
		var idRole = localStorage.getItem('idRoleModify');
		var roleRequest = {"pageNumber": 0,
							"pageSize": 0,
							"direction": "",
							"sortBy": [""],
							"searchColumn": "string",
							"searchTerm": "",
							"role": {"idRole":idRole
						}};
		var request = {"permission": {}};
		var otherObject = [];
		var last = {
			bottom: false,
			top: true,
			left: false,
			right: false
		};

		$http.post(link,request).success(function(response) {
			$scope.permissionsList = response.permission;
			
			$http.post(linkIdRole,roleRequest).success(function(response) {
				roleRequest = response;
				otherObject = roleRequest.role[0];
				$scope.consultRoleName = otherObject.rolName;
				$scope.consultPermissions = otherObject.tpermissions;
				$scope.form.newRoleName = $scope.consultRoleName;

				init();
			});

		});

		$scope.editRoles = function editRoles(event){
			var name = $scope.form.newRoleName;
			var permList = 	$scope.form.newPermissions;

			if(this.form_addRoles.$valid){
				$scope.onError = false;
				var roleObject = {"pageNumber": 0,
								"pageSize": 0,
								"direction": "string",
								"sortBy": ["string"],
								"searchColumn": "string",
  								"searchTerm": "string",
  								"role": {
								"idRole": idRole,
								"rolName": name,
								"tpermissions":permList
							}};

				$http.post(linkRole,roleObject).success(function(response) {
					$mdToast.show(
						$mdToast.simple()
						    .content('Se ha modificado el rol!')
						    .position($scope.getToastPosition())
						    .hideDelay(1000)
						    );
						 return revert();
				});
				$timeout(function(){
		        $location.path("/templates/roleView/roles"); 
		    }, 1000);

			
			}else{
	    		$scope.onError = true;
	    	}
	    	$http.post(link,request).success(function(response) {
				$scope.permissionsList= response.permission;
			});

		};
		
		$scope.addPermissions = function toggleSelection(idPermissions,checkbox){
			var newPermissionObj = {"idPermissions":idPermissions};
			if(checkbox){
				for( var i in 	$scope.form.newPermissions){
					if(idPermissions === $scope.form.newPermissions[i].idPermissions){
						$scope.form.newPermissions.splice(i,1);
					}
				}
			}else{
				$scope.form.newPermissions.push(newPermissionObj);
			}	
  		};
		// $scope.addPermissions = function toggleSelection(idPermissions,checkbox){

		// 	if(!isModuleSelected(idPermissions,checkbox)){
		// 		singleItemSelected(idPermissions,checkbox);
		// 	}
			
  // 		};

  		original = angular.copy($scope.form);
	    function revert(){
	    	$scope.form = angular.copy(original);
	        $scope.form_addRoles.$setPristine();
	        $scope.form_addRoles.$setUntouched();
	        return;
	    };

	    $scope.canRevert = function () {
	        return !angular.equals($scope.form, original) || !$scope.form_addRoles.$pristine;
	    };
	  	
	  	$scope.canSubmit = function() {
	  		if($scope.form_addRoles.$valid && !angular.equals($scope.form, original)==true && $scope.form.newPermissions.length > 0){
	  				return false;
	  		}else{
	  			return true;
	  		}
	    }; 

	    $scope.submitForm = function(event) {
		    $scope.editRoles(event);
	        $scope.showInfoOnSubmit = true;
	        return revert();
	    };

	    $scope.getToastPosition = function() {
			sanitizePosition();
			return Object.keys($scope.toastPosition).filter(function(pos) 
				{ return $scope.toastPosition[pos]; 
				}).join(' ')
		};

		function sanitizePosition() {
			var current = $scope.toastPosition;

			if ( current.bottom && last.top ) current.top = false;
			if ( current.top && last.bottom ) current.bottom = false;
			if ( current.right && last.left ) current.left = false;
			if ( current.left && last.right ) current.right = false;

			last = angular.extend({},current);
		};

		function init(){
			console.log($scope.permissionsList);

			$scope.form.newPermissions = $scope.consultPermissions;

			for (var i = 0; i < $scope.permissionsList.length; i++) {
				for (var j = 0; j < $scope.consultPermissions.length; j++) {
					if($scope.consultPermissions[j].idPermissions == $scope.permissionsList[i].idPermissions){
						$scope.permissionsList[i].state = true;
						break;
					}else{
						$scope.permissionsList[i].state = false;
					}
				}
			}
			
		};


		////////////////////////////////////////////////////////autoseleccionar por modulos
		/////indices 1,6,11,16,20
		// function isModuleSelected(idPermissions,deactive){
		// 	var module = false;
		// 	switch(idPermissions){
		// 		case 1:
		// 		if(deactive)
		// 			autoSelectByModule(4,idPermissions,false);
		// 		else
		// 			autoSelectByModule(4,idPermissions,true);
		// 			module=true;
		// 		break;
		// 		case 6:

		// 		if(deactive)
		// 			autoSelectByModule(4,idPermissions,false);
		// 		else
		// 			autoSelectByModule(4,idPermissions,true);
		// 			module=true;
		// 		break;
		// 		case 11:

		// 		if(deactive)
		// 			autoSelectByModule(4,idPermissions,false);
		// 		else
		// 			autoSelectByModule(4,idPermissions,true);
		// 			module=true;
		// 		break;
		// 		case 16:

		// 		if(deactive)
		// 			autoSelectByModule(2,idPermissions,false);
		// 		else
		// 			autoSelectByModule(2,idPermissions,true);
		// 			module=true;
		// 		break;
		// 		case 20:

		// 		if(deactive)
		// 			autoSelectByModule(2,idPermissions,false);
		// 		else
		// 			autoSelectByModule(2,idPermissions,true);
		// 			module=true;
		// 		break;					
		// 	}	
		// 	return module;
		// }

		// function autoSelectByModule(numberOfElements,idStart,state){
		// 	for (var i = idStart; i < idStart+numberOfElements; i++) {
		// 		$scope.permissionsList[i].state = state;
		// 		addToPermissionsList(i,state);
		// 	}
		// }

		// function singleItemSelected(idPermissions,deactive){
		// 	addToPermissionsList(idPermissions,!deactive);
		// }

		// function addToPermissionsList(idPermissions,deactive){
		// 	var listOfPermissions = [];
		// 	var newPermissionObj = {"idPermissions":idPermissions};
		// 	if(!deactive){
		// 		for( var i in 	$scope.form.newPermissions){
		// 			if(idPermissions == $scope.form.newPermissions[i].idPermissions){
		// 				$scope.form.newPermissions.splice(i,1);
		// 				console.log($scope.form.newPermissions[i]);
		// 				}
		// 			}
		// 	}else{
		// 		$scope.form.newPermissions.push(newPermissionObj);
		// 	}
		// 				console.log($scope.form.newPermissions)
	
		// }

				        
	}]);
	
})();
