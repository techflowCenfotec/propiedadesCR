(function() {
	"use strict";

	angular.module("app.banktodolistAdminItems",[])

	.controller('banktodolistAdminItemsController',['$scope','$http',function($scope,$http){
		var original;
		var idToDoList = localStorage.getItem('idToDoList');
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.form = { 	
	        name: ''
	        // description: '',
	        // bank: ''
	    };
	    $scope.items = [];

	    ////todo list
	    var toDoListLink = 'rest/protected/banktodolist/getById';
	    var toDoListrequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"idtBank_to_do_list": idToDoList }};
	    		 
	     $http.post(toDoListLink,toDoListrequest).success(function(response){
	     	console.log(response);
	     	$scope.items = response.bankToDoList[0].tbankItems;
	     });


		


	    original = angular.copy($scope.form);
	    function revert(){
	        $scope.form = angular.copy(original);
	        $scope.form_banktodolistAdminItems.$setUntouched();
	        return $scope.form_banktodolistAdminItems.$setPristine();

	    };
	   	$scope.canRevert =  function () {
	        return !angular.equals($scope.form, original) || !$scope.form_banktodolistAdminItems.$pristine;
	    };
	  	$scope.canSubmit = function() {
	        return $scope.form_banktodolistAdminItems.$valid && !angular.equals($scope.form, original);
	    };    
	   	$scope.submitForm = function(event) {
		  
		    $scope.saveItem(event);
	        $scope.showInfoOnSubmit = true;
	        return revert();
	    };
	    
	    $scope.saveItem = function(event){
	    	
	    	if(this.form_banktodolistAdminItems.$valid){
				var addItemLink = 'rest/protected/banktodolist/createItem';
				var addItemRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoListItem": {"name": $scope.form.name,"tbankToDoList": {"idtBank_to_do_list":idToDoList}}};


	    		$scope.onError = false;
	    		$http.post(addItemLink,addItemRequest).success(function(response){
	    			console.log(response);
	    		});
	    	}else{
	    		$scope.onError = true;
	    	}
	    };
	}]);
})();