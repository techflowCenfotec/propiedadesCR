(function() {
	"use strict";

	angular.module("app.events",[])

	.controller('CreateEventController',['$scope','$http','$location','$upload',function($scope,$http,$location,$upload){
				
		        var original;
		        $scope.onError = false;
		        $scope.requestObject = {};
		        $scope.maxDate = new Date(2017, 5, 22);
		        $scope.dateWithFormat='';
		        $scope.files={};
		        $scope.form = { 
		            name: '',
		            description: '',
		            dt: new Date(),
		        };
		       var request ={"pageNumber":0,"pageSize":0,"direction":"","sortBy":[""],"searchColumn":"string","searchTerm":"","event":{}};
		      
		       original = angular.copy($scope.form);
		        function revert(){		   
		            $scope.form = angular.copy(original);
		            return $scope.form_createEvent.$setPristine();
		        };
		       $scope.canRevert =  function () {		        	
		            return !angular.equals($scope.form, original) || !$scope.form_createEvent.$pristine;
		        };
		      $scope.canSubmit = function() {
		            return $scope.form_createEvent.$valid && !angular.equals($scope.form, original);
		        };    
		       $scope.submitForm = function(event) {
		    	
		    	    $scope.saveEvent(event);
		    	   
		            $scope.showInfoOnSubmit = true;
		            return revert();
		        };
		        
		        $scope.dateOptions = {
		                formatYear: 'yyyy',
		                startingDay: 1
		            };
		        
		        $scope.toggleMin = function() {
		            $scope.minDate = $scope.minDate ? null : new Date();
		        };
		        $scope.open1 = function() {
		        	$scope.toggleMin();
		            $scope.popup1.opened = true;
		        };
		        $scope.popup1 = {
		                opened: false
		        };

		        $scope.onFileSelect = function($files) {
		        	$scope.files = $files;
		        
		        };
		        $scope.saveEvent = function(event){
		        	$scope.getDateWithFormat();
		        	if(this.form_createEvent.$valid){
		        		$scope.onError = false;
		        		
		        		//$files: an array of files selected, each file has name, size, and type.
		        		for ( var i = 0; i < $scope.files.length; i++) {
		        			var file = $scope.files[i];
		        			$scope.upload = $upload.upload({
		        				url : 'rest/protected/events/create',
		        				data : {
		        					name:$scope.form.name,
		        					description:$scope.form.description,
		        					start_date:$scope.dateWithFormat,
		        					id_user:0,
		        				},
		        				file : file,
		        			}).progress(
		    					function(evt) {
		    						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
		    					}).success(function(data, status, headers, config) {
		    						$("#fileButton").replaceWith("<input id='fileButton'type='file' name='inputfile' required data-ng-file-select='onFileSelect($files)' >").html();
		    					});
		    	    			
		        		}
		        	}else{
		        		$scope.onError = true;
		        	}
		        };
		       $scope.getDateWithFormat = function(){
		    	   var date =$scope.form.dt;
		    	   $scope.dateWithFormat=date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate()+
				    	   ' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
		    	   console.log('Fecha con formato'+$scope.dateWithFormat);
		       }
		    
	}]);
})();