(function(){
	'use strict';

	angular.module('app.addRoles', [])
	.controller('obteinPermissions',['$scope','$http','$location','$timeout',function($scope,$http,$location,$timeout){
		
		$scope.permissionsList = [];
		$scope.done= false;
		var original;
		$scope.onError = false;
		$scope.form = { 	
	        newRoleName: '',
	        newPermissions: []
	    };

		var link = 'rest/protected/permissions/getAll';
		var linkRole='rest/protected/roles/create';
		var request ={"permission": {}};

		$http.post(link,request).success(function(response) {
			$scope.permissionsList= response.permission;
		});

		$scope.addRoles= function addRoles(event){
			var name= $scope.form.newRoleName;
			var permList= $scope.form.newPermissions;
			if(this.form_addRoles.$valid){
				$scope.onError = false;
				var roleObject={"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {"rolName":name,"tpermissions":permList}};
					$http.post(linkRole,roleObject).success(function(response) {
						$scope.showInfoOnSubmit=true;
						return revert();
						$location.path("/templates/roleView/roles");
				});
			}else{
	    		$scope.onError = true;
	    	}
	    	$http.post(link,request).success(function(response) {
				$scope.permissionsList= response.permission;
			});
			$timeout(function(){
		        $location.path("/templates/roleView/roles"); 
		    }, 1000);
		};

		$scope.addPermissions = function toggleSelection(idPermissions,checkbox){
			console.log(checkbox);
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

	    $scope.canRevert =  function () {

	        return !angular.equals($scope.form, original) || !$scope.form_addRoles.$pristine;
	    };
	  	
	  	$scope.canSubmit = function(){

	  		if($scope.form_addRoles.$valid && !angular.equals($scope.form, original)==true && $scope.form.newPermissions.length > 0){
	  				return false;
	  		}else{
	  			return true;
	  		}
	    }; 

	    $scope.submitForm = function(event) {
		  
		    $scope.addRoles(event);
	        $scope.showInfoOnSubmit = true;
	        return revert();
	    };

	}]);
	
})();