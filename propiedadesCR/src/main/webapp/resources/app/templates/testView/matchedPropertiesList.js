(function(){
	'use strict';

	angular.module('app.matchedPropertiesList', [])
	.controller('MatchedPropertiesListController',['$scope','$http', '$location','$rootScope',function($scope,$http,$location,$rootScope){
		
		 $scope.favorites = [];


		var userSurvey = {"idSurvey":localStorage.getItem('idSurvey')};//$rootScope.userSurvey;
		$scope.userSurvey = userSurvey;

		//console.log($rootScope.userSurvey);
		//function getMatchResult(){

			var matchLink = "rest/protected/usersurveys/generatematchbysurvey";
			var userSurveyMatchResultRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": userSurvey};
			
			$http.post(matchLink, userSurveyMatchResultRequest).success(function(response) {
			 	$scope.matchedPropertiesList = response;
			 	console.log(localStorage.getItem("idSurvey"));
			 	init();
			});
		//};

		function init(){

			$scope.properties =  $scope.matchedPropertiesList.properties;
			var porcentages = $scope.matchedPropertiesList.porcentages;

			for (var i = 0 ; i < $scope.properties.length; i++) {
				$scope.properties[i].porcentage = porcentages[i];
			}
		}
		

		$scope.viewProperty = function(pIdProperty,idUser) {
						var viewRequest = {"pageNumber": 0,
					"pageSize": 0,
					"direction": "",
					"sortBy": [""],
					"searchColumn": "string",
					"searchTerm": "",
					"property": {
						"idProperty":pIdProperty,
						"tuser":{"idUser":idUser}
					},
					"idBenefits": [
					               0
					             ]
			}
			$http.post('rest/protected/properties/saveView', viewRequest).success(
					function(){
						
					});
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.addToFavorites = function(pIdProperty) {
			
			var idx = $scope.favorites.indexOf(pIdProperty);
			var db = 'rest/protected/users/updateFavorite/' + $rootScope.userLogged.idUser;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.get('rest/protected/users/getUserById/' + $rootScope.userLogged.idUser)
			.success(function(response) {
				
				if (idx == -1) $scope.favorites.push(pIdProperty);
				else $scope.favorites.splice(idx, 1);
				
				$http.put(db, data)
				.success(function(response) {
					
				});
				
				
			});
		}

		$scope.checkFavorites = function(pIdProperty) {
			var idx = $scope.favorites.indexOf(pIdProperty);
			if (idx > -1) return "btn btn-danger";
			else return "btn btn-default"
		}

	}]);
	
})();