(function() {
	"use strict";

	angular.module("app.banktodolistCreate",[])

	.controller('banktodolistCreateController',['$scope','$http',function($scope,$http){
			console.log('sirveeeee');
		        var original;
		        $scope.onError = false;
		        $scope.requestObject = {};
		        $scope.form = { 	
		            name: '',
		            description: '',
		            bank: ''
		        };
		        
		       	var link = 'rest/protected/banktodolist/create';
				var request = {};
				
		        original = angular.copy($scope.form);
		        function revert(){
		            $scope.form = angular.copy(original);
		            return $scope.form_banktodolistCreate.$setPristine();
		        };
		       $scope.canRevert =  function () {
		            return !angular.equals($scope.form, original) || !$scope.form_banktodolistCreate.$pristine;
		        };
		      $scope.canSubmit = function() {
		            return $scope.form_banktodolistCreate.$valid && !angular.equals($scope.form, original);
		        };    
		       $scope.submitForm = function(event) {
		    	  
		    	    $scope.saveUser(event);
		            $scope.showInfoOnSubmit = true;
		            return revert();
		        };
		        
		        $scope.saveUser = function(event){
		        	
		        	if(this.form_banktodolistCreate.$valid){
		        		$scope.onError = false;
		        		console.log('valid');
		        		request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"name":$scope.form.name ,"tbank": {"idBank":$scope.form.bank} ,"description": $scope.form.description}};
		        		$http.post(link,request).success(function(){
		        			console.log(request);
		        		});
		        	}else{
		        		console.log('invalid')
		        		$scope.onError = true;
		        	}
		        };
	}]);
})();