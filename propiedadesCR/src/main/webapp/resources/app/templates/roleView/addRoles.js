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
			console.log($scope.permissionsList)
		});
		console.log($scope.permissionsList);
		console.log(link);

		$scope.addRoles= function addRoles(){
			var name= $scope.newRoleName;
			var permList= newPermissions;
			console.log(name);
			var roleObject={"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {"nombreRol":name,"tpermissions":permList}};
			console.log(roleObject);
			$http.post(linkRole,roleObject).success(function(response) {
				console.log("success")
		});
			console.log(roleObject)

		};


		$scope.addPermissions = function toggleSelection(idPermissions,checkbox) {

    		console.log(idPermissions);
    		console.log(checkbox);
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
			//console.log(newPermissionObj);
    		console.log(newPermissions);
  		};

  		// $scope.remove = function remove(idPermissions) {
    // 		var idperm = $scope.newPermissions.indexOf(idPermissions);
    // 		console.log(idPermissions); 
    // 		$scope.newPermissions.splice(idPermissions,1);
  		// };

	}]);
	
})();