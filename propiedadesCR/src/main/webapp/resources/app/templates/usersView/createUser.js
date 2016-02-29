(function() {
	"use strict";

	angular.module("app.createUsers",[])

	.controller('CreateUserController',['$scope','$http','$location','$upload',function($scope,$http,$location,$upload){

		        var original;
		        $scope.onError = false;
		        $scope.requestObject = {};
		    	$scope.files = {};
		        $scope.form = { 	
		            name: '',
		            email: '',
		            firstName: '',
		            lastName: '',
		            phone1: '',
		            phone2: '',
		            password: '',
		            confirmPassword: '',
		            
		        };
		        
		       
//				var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
//				var init;
				
		        original = angular.copy($scope.form);
		        function revert(){
		            $scope.form = angular.copy(original);
		            return $scope.form_createUser.$setPristine();
		        };
		       $scope.canRevert =  function () {
		            return !angular.equals($scope.form, original) || !$scope.form_createUser.$pristine;
		        };
		      $scope.canSubmit = function() {
		            return $scope.form_createUser.$valid && !angular.equals($scope.form, original);
		        };    
		       $scope.submitForm = function(event) {
		    	   console.log(event)
		    	    $scope.saveUser(event);
		            $scope.showInfoOnSubmit = true;
		            return revert();
		        };
		        $scope.onFileSelect = function($files) {
		        	$scope.files = $files;
		        	console.log('File on file select ',$files);
		        };
		        $scope.saveUser = function(event){
		        	console.log('File'+$scope.files);
		        	if(this.form_createUser.$valid){
		        		$scope.onError = false;
		        		
		        		//$files: an array of files selected, each file has name, size, and type.
		        		for ( var i = 0; i < $scope.files.length; i++) {
		        			var file = $scope.files[i];
		        			$scope.upload = $upload.upload({
		        				url : 'rest/protected/users/create',
		        				data : {
		        					idRol:0,
		        					userName:$scope.form.name,
		        					firstName:$scope.form.firstName,
		        					lastName:$scope.form.lastName,
		        					phone1:$scope.form.phone1,
		        					phone2:$scope.form.phone2,
		        					email:$scope.form.email,
		        					password:$scope.form.password,
		        				},
		        				file : file,
		        			}).progress(
		    					function(evt) {
		    						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
		    					}).success(function(data, status, headers, config) {
		    						console.log(data);
		    					});
		    	    			
		        		}
		        	}else{
		        		$scope.onError = true;
		        	}
		        };
		        
		    
	}]);
})();