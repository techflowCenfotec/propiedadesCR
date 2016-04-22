(function() {
	'use strict';
	
	angular.module('app.properties.myProperties', [])
	
	.controller('MyPropertiesController', ['$scope', '$http', '$rootScope', '$state', '$mdDialog', MyPropertiesController]);
	
	function MyPropertiesController($scope, $http, $rootScope, $state, $mdDialog) {
		$scope.propertiesList = [];
		
		$scope.init = function() {
			var active = 1,
				sold = 0,
				request = {
		            "pageNumber": 0,
		            "pageSize": 0,
		            "direction": "",
		            "sortBy": [""],
		            "searchColumn": "string",
		            "searchTerm": "",
		            "property": {
		              "tuser":{"idUser":$rootScope.userLogged.idUser}
		            }
		        };
			
			$http.post('rest/protected/properties/getPropertiesByIdVendor', request)
			.success(function(response) {
				for (var i = 0; i < response.properties.length; i++) {
					if(response.properties[i].active == active && response.properties[i].isSold == sold) 
						//calcular el descuento
						response.properties[i].price -= (response.properties[i].price*response.properties[i].offerPercentage)/100;
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
        $scope.showConfirm = function(id) {
        	$scope.status = '  ';
            var confirm = $mdDialog.confirm()
                        .title('¿Desea cambiar el estado de la propiedad a vendido?')
                        .content('Una vez cambiado el estado a vendido la propiedad no podrá ser obtenida de nuevo')
                        .ariaLabel('Lucky day')
                        .ok('Si')
                        .cancel('No');
            $mdDialog.show(confirm).then(function() {
                $scope.status = 'Propiedad vendida.';
                var soldRequest = {
                		  "pageNumber": 0,
                		  "pageSize": 0,
                		  "direction": "string",
                		  "sortBy": [
                		    "string"
                		  ],
                		  "searchColumn": "string",
                		  "searchTerm": "string",
                		  "property": {"idProperty":id},
                		  "idBenefits": [
                		    0
                		  ]
                }
                		
                $http.post('rest/protected/properties/setPropertySold',soldRequest).success(function(){
                	$scope.propertiesList = _.without($scope.propertiesList,_.findWhere($scope.propertiesList,{idProperty:id}));
                });
            }, function() {
                $scope.status = 'Propiedad no vendida.';
            });
        };
	}
	
})();