(function() {
	"use strict";

	angular.module("app.eventsList",[])

	.controller("EventsListController",["$scope","$http",'$mdDialog','$rootScope',function($scope,$http,$mdDialog,$rootScope) {
		$scope.events = [];
		
		
		
		var link = 'rest/protected/events/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","event": {}};
		
		$http.post(link,request).success(function(response) {
			   $scope.events= response.events;
			   $scope.eventsList = $scope.events;
			   
			   
		});
		
		$scope.consultEvent = function(id){
		  localStorage.setItem('idEvent',id);
		}
		$scope.attendEvent = function(event){
			$scope.showAlert(event);
		}
		
	      $scope.showAlert = function(event) {
	    	  console.log($rootScope.userLogged.email);
	    	  var req = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","eventP": event,"userEmail":$rootScope.userLogged.email};
	    	  
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
	   
		
	}])
})();