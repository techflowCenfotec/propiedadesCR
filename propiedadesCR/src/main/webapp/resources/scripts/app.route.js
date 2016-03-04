(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function(e,a){ 
		
		var n,t;
		n = [
			"templates/propertiesView/propertiesList"
			,"templates/reportsView/reports"
<<<<<<< HEAD
			,"templates/usersView/usersList"
			,"templates/usersView/createUser",			
=======
			,"templates/eventsView/createEvent",
			 "templates/eventsView/eventsList"
>>>>>>> b9f7faca40bfafee4bd9ef38ccabcc1791d7c86d
		], 

		t = function(a) {

			var n, t;

			return t = "/" + a,

			n = { 
				url: t,
				templateUrl: "resources/app/" + a + ".html"
			},
			
			e.state(a,n),e
		},
			
		n.forEach(function(e) {
			return t(e)
		}),
			
		a.when("/","/home")
		.otherwise("/home"),

		e.state( "home", {
			url:"/home",
			templateUrl:"resources/app/templates/homeView/home.html"
		})
	}])
})();