(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function(e,a){ 
		
		var n,t;
		n = [
			"templates/loginView/login"
			,"templates/reportsView/reports"


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
			
		a.when("/","/login")
		.otherwise("/login"),

		e.state( "login", {
			url:"/login",
			templateUrl:"resources/app/templates/loginView/login.html"
		})
	}])
})();