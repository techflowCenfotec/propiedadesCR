(function(){
	'use strict';

	angular.module('app.addRoles', [])
	.controller('obteinPermissions',['$scope','$http',function($scope,$http){
		
		$scope.permissionsList = [];
		var newPermissions=[];
		$scope.newRoleName='';
		$scope.done= false;

		var link = 'rest/protected/permissions/getAll';
		var linkRole='rest/protected/roles/create';
		var request ={"permission": {}};

		$http.post(link,request).success(function(response) {
			$scope.permissionsList= response.permission;
		});

		$scope.addRoles= function addRoles(){
			var name= $scope.newRoleName;
			var permList= newPermissions;
			var roleObject={"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {"rolName":name,"tpermissions":permList}};
			$http.post(linkRole,roleObject).success(function(response) {
			});
		};


		$scope.addPermissions = function toggleSelection(idPermissions,checkbox) {
			if(checkbox){
				for(var i=0; i<newPermissions.length;i++){
					if(idPermissions==newPermissions[i].idPermission){
						newPermissions.splice(i,1);
					}
				}
			}else{
				var newPermissionObj={idPermissions};
				newPermissions.push(newPermissionObj);
			}

  		};

	}]);
	
})();