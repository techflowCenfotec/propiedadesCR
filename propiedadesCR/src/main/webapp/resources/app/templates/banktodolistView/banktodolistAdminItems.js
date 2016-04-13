(function() {
	"use strict";

	angular.module("app.banktodolistAdminItems",[])

	.controller('banktodolistAdminItemsController',['$scope','$http','$timeout','dbService',function($scope,$http,$timeout,dbService){
		var original;
		var idToDoList = localStorage.getItem('idToDoList');
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.form = { 	
	        name: ''
	    };
	    $scope.items = [];

	    dbService.checkDB();
	    
	    ////todo list
	    var toDoListLink = 'rest/protected/banktodolist/getById';
	    var toDoListrequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {"idtBank_to_do_list": idToDoList }};
	    		 
	     $http.post(toDoListLink,toDoListrequest).success(function(response){
	     	//console.log(response);
	     	$scope.items = response.bankToDoList[0].tbankItems;
	     	localStorage.setItem('idToDoList',undefined);
	     	//console.log(localStorage.getItem('idToDoList'));
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
	   	$scope.submitForm = function(Item) {
		  
		    $scope.saveItem(Item);
	        $scope.showInfoOnSubmit = true;
	        $timeout(function() {
		                $scope.showInfoOnSubmit = false;
		    }, 3000);
	        return revert();
	    };
	    
	    $scope.saveItem = function(Item){
	    	 dbService.checkDB();
	    	if(this.form_banktodolistAdminItems.$valid){
				var addItemLink = 'rest/protected/banktodolist/createItem';
				var addItemRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoListItem": {"name": $scope.form.name,"tbankToDoList": {"idtBank_to_do_list":idToDoList}}};


	    		$http.post(addItemLink,addItemRequest).success(function(response){
	    			//console.log(response);
	    			$scope.items.push(response.bankToDoListItems[0]);
	    			 $scope.onError = false;
	    		
	    		});
	    	}else{
	    		$scope.onError = true;
	    	}
	    };

	     $scope.deleteItem = function(id){
	    	 dbService.checkDB();
        	  var data = $.param({
                  id: id,
              });

    		$http["delete"]('rest/protected/banktodolist/deleteItem?'+data)
            .success(function (data, status, headers) {
            	$scope.items = _.without($scope.items,_.findWhere($scope.items,{idtBank_item:id}));
            })
        };
	}]);
})();