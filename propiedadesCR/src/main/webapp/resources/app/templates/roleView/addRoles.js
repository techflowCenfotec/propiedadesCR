(function(){
	'use strict';

	angular.module('app.addRoles', [])
	.controller('obteinPermissions',['$scope','$http',function($scope,$http){
		
		$scope.permissionsList = [];
		var newPermissions=[];
		$scope.checkbox;
		$scope.done= false;
		var original;
		$scope.onError = false;

		$scope.form = { 	
	        newRoleName: ''
	    };

		var link = 'rest/protected/permissions/getAll';
		var linkRole='rest/protected/roles/create';
		var request ={"permission": {}};

		$http.post(link,request).success(function(response) {
			$scope.permissionsList= response.permission;
		});

		$scope.addRoles= function addRoles(event){
			var name= $scope.form.newRoleName;
			var permList= newPermissions;
			if(this.form_addRoles.$valid && newPermissions!== 'undefined'){
				$scope.onError = false;
				var roleObject={"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {"rolName":name,"tpermissions":permList}};
				$http.post(linkRole,roleObject).success(function(response) {
				});
				$scope.showInfoOnSubmit=true;
			}else{
	    		$scope.onError = true;
	    	}
		};

		$scope.addPermissions = function toggleSelection(idPermissions,checkbox){
			var newPermissionObj={"idPerm":idPermissions};

			if(checkbox){
				for( var i in newPermissions){
					if(idPermissions==newPermissions[i].idPerm){
						newPermissions.splice(i,1);
					}
				}
			}else{
				newPermissions.push(newPermissionObj);
			}	
  		};

  		original = angular.copy($scope.form);
	    function revert(){
	        $scope.form = angular.copy(original);
	        return $scope.form_addRoles.$setPristine();
	    };

	    $scope.canRevert =  function () {
	        return !angular.equals($scope.form, original) || !$scope.form_addRoles.$pristine;
	    };
	  	
	  	$scope.canSubmit = function() {

	        return $scope.form_addRoles.$valid && !angular.equals($scope.form, original);
	    }; 

	    $scope.submitForm = function(event) {
		  
		    $scope.addRoles(event);
	        $scope.showInfoOnSubmit = true;
	        return revert();
	    };

	}]);
	
})();