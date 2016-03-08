(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function($stateProvider,$urlRouterProvider){ 
		
		var n,t;
		n = [
			"templates/propertiesView/propertiesList"
			,"templates/reportsView/reports"
			,"templates/usersView/usersList"
			,"templates/usersView/createUser"			
<<<<<<< HEAD

			,"templates/eventsView/createEvent"
			,"templates/eventsView/eventsList"
			

=======
			,"templates/eventsView/createEvent"
			,"templates/eventsView/eventsList"
			,"templates/roleView/roles"
			,"templates/roleView/addRoles"
			, "templates/banktodolistView/banktodolistList"
			,"templates/banktodolistView/banktodolistCreate"
>>>>>>> 113418141c1ea53d65f5dfda56a38a902b5992a5
		], 

		t = function($urlRouterProvider) {

			var n, t;

			return t = "/" + $urlRouterProvider,

			n = { 
				url: t,
				templateUrl: "resources/app/" + $urlRouterProvider + ".html"
			},
			
			$stateProvider.state($urlRouterProvider,n),$stateProvider
		},
			
		n.forEach(function($stateProvider) {
			return t($stateProvider)
		}),
			
		$urlRouterProvider.when("/","/home")
		.otherwise("/home"),

		$stateProvider.state( "home", {
			url:"/home",
			templateUrl:"resources/app/templates/homeView/home.html"
		})
	}])
})();
