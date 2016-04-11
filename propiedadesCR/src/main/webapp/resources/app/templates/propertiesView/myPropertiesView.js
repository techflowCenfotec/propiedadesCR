(function() {
	'use strict';
	
	angular.module('app.properties.myProperties', [])
	
	.controller('MyPropertiesController', ['$scope', '$http', '$rootScope', '$state', '$mdDialog', MyPropertiesController]);
	
	function MyPropertiesController($scope, $http, $rootScope, $state, $mdDialog) {
		$scope.propertiesList = [];
		
		$scope.init = function() {
			var active = 1,
				sold = 0;
			
			$http.get('rest/protected/properties/getAll')
			.success(function(response) {
				for (var i = 0; i < response.properties.length; i++) {
					if(response.properties[i].active == active && response.properties[i].isSold == sold) 
						$scope.propertiesList.push(response.properties[i]);
				}
			});
		};
		
		$scope.init();
		
		$scope.modifyProperty = function(pIdProperty) {
			localStorage.setItem('idProperty', pIdProperty);
			$state.go('templates/propertiesView/propertiesModify');
		};
		
		$scope.deleteProperty = function(pIdProperty, ev) {
			
			 var confirm = $mdDialog.confirm()
			             .title('Desea eliminar la propiedad?')
			             .content('Una vez eliminada la información no podrá ser obtenida de nuevo')
			             .ariaLabel('Eliminar Propiedad')
			             .targetEvent(ev)
			             .ok('Eliminar')
			             .cancel('Cancelar');
			 $mdDialog.show(confirm).then(function() {
				 $http.put('rest/protected/properties/delete/' + pIdProperty)
					.success(function(response) {
						$state.reload('templates/propertiesView/myPropertiesView');
					});
			 });
			
		}
	}
	
})();