(function() {
	"use strict";

	angular.module("app.consultUser", [])
	.controller(
			'ConsultUserController',
			[
					'$scope',
					'$http',
					'$rootScope',
					'$mdDialog',
					'$state',
					'dbService',
					function($scope, $http, $rootScope, $mdDialog,$state,dbService) {

						$scope.user = {};
						$scope.rate = 0;
						$scope.max = 5;
						$scope.notVendor;

						dbService.checkDB();
							
						var link = 'rest/protected/users/getUserById/'
								+ localStorage.getItem('idUser');
						$http.get(link).success(function(response) {
							
							$scope.user = response.user;
							if ($scope.user.role.rolName === "Vendedor")
								$scope.notVendor = false;
							else
								$scope.notVendor = true;

							return $scope.user;
						});
					
		$scope.hoveringOver = function(value) {
           
		}
		$scope.saveRating = function(value){
			dbService.checkDB();
			var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "",
					"rating": {"averageRating":value},"idClient":$scope.user.idUser,"idVendor":$scope.user.idUser};
			$http.post('rest/protected/userRating/saveRating',request).success(function(){
				
			})
		}
		$scope.editProfile = function(user){
			$state.go("templates/usersView/modifyProfile");
		}
	}]);
})();

