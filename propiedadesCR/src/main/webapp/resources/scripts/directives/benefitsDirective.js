(function() {
	angular.module('benefitsDirective', [])
	.directive('benefits', ['$uibModal', '$log', benefits])
	.controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', '$http', ModalInstanceCtrl]);
	
	function benefits($uibModal, $log) {
		return {
			restrict: 'E',
			transclude: 'true',
			templateUrl: 'resources/app/templates/propertiesView/benefitDirective.html',
			link: function($scope) {
				$scope.animationsEnabled = true;
				$scope.buttonLabel = 'Buscar Características';
					
		        $scope.open = function (size) {
		        	
		            var modalInstance = $uibModal.open({
		                animation: $scope.animationsEnabled,
		                templateUrl: 'resources/app/templates/propertiesView/benefitModal.html',
		                controller: 'ModalInstanceCtrl',
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
	
	function ModalInstanceCtrl($scope, $uibModalInstance, $http) {
        $scope.benefitsList = [];
		
		$scope.init = function() {
			$http.get('rest/protected/benefits/getAll')
			.success(function(response) {
				$scope.benefitsList = response.benefits;
				
				for (var i = 0; i < $scope.benefitsList.length; i++) {
	        		$scope.benefitsList[i].checked = false;
				}
			});
		}
		
		$scope.init();
		
        $scope.buscar = function() {
        	$scope.checkedList = [];
        	for (var i = 0; i < $scope.benefitsList.length; i++) {
				if ($scope.benefitsList[i].checked == true) {
					$scope.checkedList.push($scope.benefitsList[i]);
				}
			}
        	$scope.$root.$broadcast("filterAction", $scope.checkedList);
            $uibModalInstance.dismiss();
        };

        $scope.cancel = function() {
            $uibModalInstance.dismiss("cancel");
        };

    }
	
})();