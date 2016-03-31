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
		function($scope, $http, $upload, $mdToast, $location, $$timeout){
		
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
		});

		$http.post(linkIdRole,roleRequest).success(function(response) {
			roleRequest = response;
			otherObject = roleRequest.role[0];
			$scope.consultRoleName = otherObject.rolName;
			$scope.consultPermissions = otherObject.tpermissions;
			$scope.form.newRoleName = $scope.consultRoleName;
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
					
						
						// $timeout(function(){ 
  				 			$location.path("/templates/roleView/roles"); 
						// },5000);
						 
						 // .setTimeout($scope.reload(), 5000);

						 return revert();
				});
			
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
				        
	}]);
	
})();