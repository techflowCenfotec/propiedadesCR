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
				$scope.buttonLabel = 'Características';
					
		        $scope.open = function (size) {
		        	
		            var modalInstance = $uibModal.open({
		                animation: $scope.animationsEnabled,
		                templateUrl: 'resources/app/templates/propertiesView/benefitModal.html',
		                controller: 'ModalInstanceCtrl',
		                scope: $scope,
		                size: 'lg'
		            });

		            modalInstance.result.then(function (selectedItem) {
		                $scope.selected = selectedItem;
		            }, function () {
		                $log.info('Modal dismissed at: ' + new Date());
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
		
        $scope.ok = function() {
        	
            $uibModalInstance.close();
        };

        $scope.cancel = function() {
            $uibModalInstance.dismiss("cancel");
        };

    }
	
})();