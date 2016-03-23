(function() {
	"use strict";

	angular.module("app.banktodolistCreate",[])

	.controller('banktodolistCreateController',['$scope','$http','$timeout','$location',function($scope,$http,$timeout,$location){
	    var original;
	    var modify = false;
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.form = { 	
	        name: '',
	        description: '',
	        bank: ''
	    };


	    if(localStorage.getItem('idToDoList')!=undefined){
	    	////todo list
   			var idToDoList = localStorage.getItem('idToDoList');
		    var toDoListLink = 'rest/protected/banktodolist/getById';
		    var toDoListrequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"idtBank_to_do_list": idToDoList }};
		    		 
		     $http.post(toDoListLink,toDoListrequest).success(function(response){
		     	//console.log(response);
		     	localStorage.setItem('idToDoList',undefined);
		     	//console.log(response);
		     	var bankToDoList = response.bankToDoList[0];
		     	$scope.form.name = bankToDoList.name;
		     	$scope.form.description = bankToDoList.description;
		     	$scope.form.bank = bankToDoList.tbank.idBank;
		     	modify = true;
		     });
	    }




	    ////lista de bancos
	    var linkBanks = 'rest/protected/banks/getAll';
	    request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bank": {} };
	    		 
	    $http.post(linkBanks,request).success(function(response){
	    	$scope.banks = response.banks;
	    });


	   	var link = 'rest/protected/banktodolist/create';
		var request = {};
		
	    original = angular.copy($scope.form);
	    function revert(){
	        $scope.form = angular.copy(original);
	        $scope.form_banktodolistCreate.$setUntouched();
	        return $scope.form_banktodolistCreate.$setPristine();
	    };
	   	$scope.canRevert =  function () {
	        return !angular.equals($scope.form, original) || !$scope.form_banktodolistCreate.$pristine;
	    };
	  	$scope.canSubmit = function() {
	        return $scope.form_banktodolistCreate.$valid && !angular.equals($scope.form, original);
	    };    
	   	$scope.submitForm = function(event) {
		  
		    $scope.saveBankToDoList(event);
	        $scope.showInfoOnSubmit = true;
	        $timeout(function() {
		        $scope.showInfoOnSubmit = false;
       			$location.url("templates/banktodolistView/banktodolistAdminList");

		    }, 3000);
	        return revert();
	    };
	    
	    $scope.saveBankToDoList = function(event){
	    	
	    	if(this.form_banktodolistCreate.$valid){
	    		$scope.onError = false;
	    		if(modify){
		    		request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"idtBank_to_do_list": idToDoList, "name":$scope.form.name ,"tbank": {"idBank": $scope.form.bank } ,"description": $scope.form.description}};	    			
	    		}else{
		    		request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"name":$scope.form.name ,"tbank": {"idBank": $scope.form.bank } ,"description": $scope.form.description}};
	    		}
	    		$http.post(link,request).success(function(){
		    		});
	    	}else{
	    		$scope.onError = true;
	    	}
	    };
	}]);
})();