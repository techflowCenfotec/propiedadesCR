(function() {
	"use strict";

	angular.module("app.eventConsult",[])

	.controller('EventConsultController', ['$scope','$http','$rootScope','$mdDialog', function($scope,$http,$rootScope,$mdDialog) {
		
		$scope.event={};
		
		
		
		
		var link = 'rest/protected/events/getById/'+localStorage.getItem('idEvent');
		$http.get(link).success(function(response) {
			 
			  $scope.event = response.event;
			  localStorage.getItem('idEvent');
			  console.log(response.event);
			  
			   
			  return $scope.event;
		});
		$scope.attendEvent = function(ev){
			$scope.showAlert(ev);
		}
	      $scope.showAlert = function(ev) {
	            // Appending dialog to document.body to cover sidenav in docs app
	            // Modal dialogs should fully cover application
	            // to prevent interaction outside of dialog
	            $mdDialog.show(
	                $mdDialog.alert()
	                    .parent(angular.element(document.querySelector('#popupContainer')))
	                    .clickOutsideToClose(true)
	                    .title('Confirmación de evento')
	                    .content('Se le ha enviado un correo '+
	                    		'con la información del evento.')
	                    .ariaLabel('Confirmación de evento')
	                    .ok('Aceptar')
	                    .targetEvent(ev)
	            );
	        };
	}]);
})();