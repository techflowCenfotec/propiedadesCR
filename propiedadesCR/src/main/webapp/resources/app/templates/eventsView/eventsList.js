(function() {
	"use strict";

	angular.module("app.eventsList",[])

	.controller("EventsListController",["$scope","$http",function(e,a) {

		var conn="/rest/protected/events/getAll";
		e.properties=[],
		e.requestObject={pageNumber:0,pageSize:0,direction:"",sortBy:[""],searchColumn:"string",searchTerm:"",user:{}},
		a.post(conn,e.requestObject)
		.success(function(e){
			console.log(e)
		}).error(function(e) {
			console.log(e)
		})
	}])
})();