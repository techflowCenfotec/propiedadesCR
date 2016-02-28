(function() {
	"use strict";

	angular.module("app.users",[])

	.controller("UsersController",["$scope","$http",function(e,a) {
		
		var conn="/rest/protected/users/getAll";
		e.users=[],
		e.requestObject={pageNumber:0,pageSize:0,direction:"",sortBy:[""],searchColumn:"string",searchTerm:"",user:{}},
		a.post(conn,e.requestObject)
		.success(function(e){
			console.log(e)
		}).error(function(e) {
			console.log(e)
		})
		
		
	}]);
})();