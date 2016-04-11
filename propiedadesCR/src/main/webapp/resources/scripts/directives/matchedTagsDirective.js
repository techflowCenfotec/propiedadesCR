(function() {
	angular.module('app.matchedTagsDirective', [])
	.directive('tags', ['$uibModal', '$log', tags])
	.controller('MatchedTagsModalController', ['$scope', '$uibModalInstance', '$http', MatchedTagsModalController]);
	
	function tags($uibModal, $log) {
		return {
			name:"tags",
			restrict: 'E',
			//transclude: true,
			templateUrl: 'resources/app/templates/testView/matchedTagsButton.html',
			
			link: function($scope,scope,attr) {
				$scope.animationsEnabled = true;
				$scope.buttonLabel = 'RESULTADO';

				$scope.idProperty = attr.idproperty;
				$scope.idSurvey = attr.idsurvey;
				
				console.log(attr.idproperty);
				console.log(attr.idsurvey);

		        $scope.open = function () {
		        	
		            var modalInstance = $uibModal.open({
		                animation: $scope.animationsEnabled,
		                templateUrl: 'resources/app/templates/testView/matchedTagsModal.html',
		                controller: 'MatchedTagsModalController',
		                scope: $scope,
		                size: 'md'
		            });

		        };

		        $scope.toggleAnimation = function () {
		            $scope.animationsEnabled = !$scope.animationsEnabled;
		        };
			}
		}
	}
	
	function MatchedTagsModalController($scope, $uibModalInstance, $http) {
		console.log($scope.idProperty);
		console.log($scope.idSurvey);

        $scope.userTags = [];
        $scope.tagsMatched = [];
		
		$scope.init = function() {
			var link = 'rest/protected/usersurveys/getMatchedTagsByProperty'

			$http({
			  	url: link,
			  	method: 'POST',
			  	params: {idSurvey:$scope.idSurvey,idProperty:$scope.idProperty}
			}).success(function(response){
				$scope.userTags = response.tags;
				$scope.tagsMatched = response.matchedTags;
			});
		}
		
		$scope.init();

        $scope.cancel = function() {
            $uibModalInstance.dismiss("cancel");
        };

        $scope.setColor = function(indice){
        	if($scope.tagsMatched[indice]){
        		return 'btn btn-w-lg btn-success btn-gap ui-wave';
        	}else{
        		return 'btn btn-w-lg btn-danger btn-gap ui-wave';
        	}
        }

        $scope.setMessage = function(indice){
        	if($scope.tagsMatched[indice]){
        		return 'Coincide';
        	}else{
        		return 'No coincide';
        	}
        }

    }
	
})();