(function(){
	'use strict';

	angular.module('app.matchedPropertiesList', [])
	.controller('MatchedPropertiesListController',['$scope','$http', '$location','$rootScope',function($scope,$http,$location,$rootScope){
		

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
		

		$scope.viewProperty = function(pIdProperty) {
			localStorage.setItem('idProperty', pIdProperty);
		}
		
		$scope.addToFavorites = function(pIdProperty) {
			
			// Cambiar a UserLogged
			var bd = 'rest/protected/users/addToFavorite/' + $rootScope.userLogged.idUser;
			var data = {
				"idProperty": pIdProperty	
			};
			
			$http.put(bd, data)
			.success(function(response) {
			});
		}
	}]);
	
})();