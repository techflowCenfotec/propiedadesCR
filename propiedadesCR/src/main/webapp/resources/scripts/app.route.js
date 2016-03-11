(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function(e,a){ 
		
		var n,t;
		n = [
			"templates/propertiesView/propertiesList"
			,"templates/reportsView/reports"
			,"templates/usersView/usersList"
			,"templates/usersView/createUser"			
			,"templates/eventsView/createEvent"
			,"templates/eventsView/eventsList"
			,"templates/roleView/roles"
			,"templates/roleView/addRoles"
			, "templates/banktodolistView/banktodolistList"
			,"templates/banktodolistView/banktodolistCreate"
			,"templates/propertiesView/propertiesCreate"
			,"templates/propertiesView/propertyView"
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
