(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function($stateProvider,$urlRouterProvider){ 
		
		var n,t;
		n = [
			"templates/loginView/login"
			
			,"templates/resetPasswordView/forgotPassword"
			,"templates/resetPasswordView/resetPassword"
			
			,"templates/errorsView/500"
			
			,"templates/usersView/createUserLogin"
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
			
		$urlRouterProvider.when("/","/login")
		.otherwise('/login');

		$stateProvider.state( "login", {
			url:"/login",
			templateUrl:"resources/app/templates/loginView/login.html"
		})
	}])
})();