(function() {
	"use strict";

	angular.module("app.myToDoList",[])

	.controller('myToDoListController',['$scope','$http','$timeout',function($scope,$http,$timeout){
		var original;
		var idToDoList = localStorage.getItem('idMyToDoList');
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.isChecked = false;
	    $scope.itemDone
	    $scope.form = { 	
	        name: ''
	    };
	    $scope.items = [];
	    $scope.selected =[];
	    $scope.myItems = [];
	
	    ////todo list
	    var toDoListLink = 'rest/protected/todolist/getMyTodoList';
	    var toDoListrequest = {
	    		  "pageNumber": 0,
	    		  "pageSize": 0,
	    		  "direction": "string",
	    		  "sortBy": [
	    		    "string"
	    		  ],
	    		  "searchColumn": "string",
	    		  "searchTerm": "string",
	    		  "toDoList": {"idToDoList":idToDoList}
	    		}   		 
	     $http.post(toDoListLink,toDoListrequest).success(function(response){
	     	
	     	$scope.items = response.myTodoList.titems;
	     	checkItems();
	     	
	     });

	    $scope.toggle = function (item, list) {
	    	if($scope.selected.length===0){
	    		item.done = 1;
	    		myItems(item)
	    		$scope.selected.push(item);
	    	}
	    	else{
	    		for(var i in $scope.selected){
		    		if(item==$scope.selected[i]){
		    			item.done = 0;
		    			$scope.selected.splice(i,1);
		    			myItems(item)
		    			return;
		    		}
		    	}
	    		item.done = 1;
	    		myItems(item)
	    		$scope.selected.push(item);
	    	}
        };
        $scope.exists = function (item, list) {
        	for(var i in $scope.selected){
	    		if(item==$scope.selected[i]){
	    			return true;
	    		}
	    	}
        	return false;
        };
        $scope.saveItems = function(){
        var myItems =	{
        		  "pageNumber": 0,
        		  "pageSize": 0,
        		  "direction": "string",
        		  "sortBy": [
        		    "string"
        		  ],
        		  "searchColumn": "string",
        		  "searchTerm": "string",
        		  "toDoList": {"idToDoList":idToDoList, "titems":$scope.myItems}
        		}
        $http.post("rest/protected/todolist/saveItems",myItems).success(function(){
        	$scope.showInfoOnSubmit= true;
			  $timeout(function(){
		          $scope.showInfoOnSubmit = false;
		       }, 3000);
        });
        }
        function checkItems(){
        	for(var i in $scope.items){
        		if($scope.items[i].done ==1){
        			$scope.selected.push($scope.items[i])
        			$scope.myItems.push($scope.items[i]);
        		}
        	}
        }
        function myItems(item){
        	
        	if($scope.myItems.length===0){
	    		$scope.myItems.push(item);
	    	}else{
	    		for(var i in $scope.myItems){
	    			if($scope.myItems[i].idItem===item.idItem){
	    				$scope.myItems[i].done = item.done;
	    				return;
	    			}
	    			
	    		}
	    		$scope.myItems.push(item);
	    	}
        	
        }
        
	  
	}]);
})();