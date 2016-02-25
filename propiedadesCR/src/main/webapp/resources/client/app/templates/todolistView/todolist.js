(function(){
	'use strict';

	angular.module('app.todolist', [])


	.controller('todolistController',['$scope','$filter','$http',function($scope,$filter,$http){
		var link = 'http://localhost:8080/propiedadesCR/rest/protected/todolist/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","toDoList": {}};
		
		//$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
		
		 var req = {
		  	method: 'POST',
		 	url: link,
		  	headers: {
		    	'Content-Type': 'aplication/json',
		    	// 'Access-Control-Allow-Origin':'*'
		  	},
		  	data: { test: 'test' }
		 }

		$http.post(link,request).success(function(response) {
			$scope.names= response.toDoList;
			console.log($scope.names)
		});

		console.log(request);
		console.log(link);

	}]);

})();