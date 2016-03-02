(function() {
	"use strict";

	angular.module("app.banktodolistCreate",[])

	.controller('banktodolistCreateController',['$scope','$http',function($scope,$http){
	    var original;
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.form = { 	
	        name: '',
	        description: '',
	        bank: ''
	    };

	    ////lista de bancos
	    var linkBanks = 'rest/protected/banks/getAll';
	    request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bank": {} };
	    		 
	    $http.post(linkBanks,request).success(function(response){
	    	$scope.banks = response.banks;
	    	console.log($scope.banks);
	    });


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
	    		console.log('valid',$scope.selectedId);
	    		request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"name":$scope.form.name ,"tbank": {"idBank": $scope.form.bank } ,"description": $scope.form.description}};
	    		console.log(request);
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