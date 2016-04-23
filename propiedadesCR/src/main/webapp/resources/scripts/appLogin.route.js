(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider", function($stateProvider,$urlRouterProvider){ 
		
		var n,t;
		n = [
			"templates/loginView/login"
			
			,"templates/resetPasswordView/forgotPassword"
			,"templates/resetPasswordView/resetPassword"
			,"templates/landingPageView/landingPage"
			
			//,"templates/errorsView/500"
			
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
			
		$urlRouterProvider.when("/","/landingPage")
		.otherwise('/landingPage');

		$stateProvider.state( "landingPage", {
			url:"/landingPage",
			templateUrl:"resources/app/templates/landingPageView/landingPage.html"
		})
	}])
})();