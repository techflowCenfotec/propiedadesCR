(function() {
	"use strict";

	angular.module("app.consultUser",[])

	.controller('ConsultUserController', ['$scope','$http','$rootScope','$mdDialog', function($scope,$http,$rootScope,$mdDialog) {
		
		$scope.user={};
		$scope.rate = 0;
		$scope.max = 5;
		$scope.notVendor;
		
		var link = 'rest/protected/users/getUserById/'+localStorage.getItem('idUserM');
		$http.get(link).success(function(response) {
		
			  $scope.user = response.user;
			   if($scope.user.role.rolName==="Vendedor")
				   $scope.notVendor = false;
			   else
				   $scope.notVendor = true;
			   
			  console.log($scope.user.role.rolName);
			  return $scope.user;
		});
		$scope.hoveringOver = function(value) {
           
		}
		$scope.saveRating = function(value){
			console.log('El valor es',value);
			var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "",
					"rating": {"averageRating":value},"idClient":$scope.user.idUser,"idVendor":$scope.user.idUser};
			$http.post('rest/protected/userRating/saveRating',request).success(function(){
				
			})
		}
	}]);
})();