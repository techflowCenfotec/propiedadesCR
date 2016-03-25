(function() {
	"use strict";

	angular.module("app.eventConsult",[])

	.controller('EventConsultController', ['$scope','$http','$rootScope','$mdDialog','NgMap',
	                                       function($scope,$http,$rootScope,$mdDialog,NgMap) {
		
		$scope.event={};
		
		
		
		
		var link = 'rest/protected/events/getById/'+localStorage.getItem('idEvent');
		$http.get(link).success(function(response) {
			 
			  $scope.event = response.event;
			  localStorage.getItem('idEvent');
			  $scope.markLoc = $scope.events;
			  
			 
			   
			  return $scope.event;
		});
		$scope.attendEvent = function(event){
			$scope.showAlert(event);
		}
		
	      $scope.showAlert = function(event) {
	    	  var req = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","eventP": $scope.event,"userEmail":$rootScope.userLogged.email};
	    	  
		           $http.post('rest/protected/email/sendEmail',req).success(function(){
	        	   
	           });
	            $mdDialog.show(
	                $mdDialog.alert()
	                    .parent(angular.element(document.querySelector('#popupContainer')))
	                    .clickOutsideToClose(true)
	                    .title('Confirmación de evento')
	                    .content('Se le ha enviado un correo '+
	                    		'con la información del evento.')
	                    .ariaLabel('Confirmación de evento')
	                    .ok('Aceptar')
	                    .targetEvent(event)
	            );
	        };
	        NgMap.getMap().then(function(map) {
				$scope.markerPos =  function() {
					$scope.markerLoc = '['+ map.markers[0].position.lat() + ',' + map.markers[0].position.lng() +']';
				};
	        });
	}]);
})();