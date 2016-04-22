(function() {
	"use strict";

	angular.module("app.eventsList",[])

	.controller("EventsListController",["$scope","$http",'$mdDialog','$rootScope','dbService',function($scope,$http,$mdDialog,$rootScope,dbService) {
		$scope.events = [];
		$scope.totalPages =0;
		$scope.pageSize = 10;
		$scope.pageNumber = 0;
        $scope.numPerPageOpt = [2, 5, 10, 20];
        $scope.numPerPage = $scope.numPerPageOpt[2];
        $scope.currentPage = 1;
        $scope.currentPage = [];
		
		
		getAllEvents();
		
		$scope.consultEvent = function(id){
		  localStorage.setItem('idEvent',id);
		}
		$scope.attendEvent = function(event){
			$scope.showAlert(event);
		}
		
	      $scope.showAlert = function(event) {
	    	  
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
	    	$scope.changePage = function(page){
				$scope.pageNumber = page-1;
				getAllEvents();
			};
		        
			 $scope.onNumPerPageChange = function(){
				 $scope.pageSize = $scope.numPerPage;
				 getAllEvents();
		     };
		     
		     
		     function getAllEvents(){
		    	 var link = 'rest/protected/events/getAll';
		 		var request = {"pageNumber":  $scope.pageNumber,"pageSize": $scope.pageSize,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","event": {}};
				dbService.checkDB();
		 		$http.post(link,request).success(function(response) {
		 			   $scope.events= response.events;
		 			   $scope.eventsList = $scope.events;
		 	           $scope.totalPages = response.totalPages;
		 			   
		 		});
		 
		 			  
		 		
		     };  
	   
		
	}])
})();