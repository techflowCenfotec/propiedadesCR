(function(){
	"use strict";

	angular.module("app.route", ["ui.router"])

	.config(["$stateProvider","$urlRouterProvider","$provide","$httpProvider", function($stateProvider,$urlRouterProvider,$provide,$httpProvider){ 
		
		var n,t;
		n = [
			"templates/propertiesView/propertiesList"
			,"templates/reportsView/reports"
						
			,"templates/usersView/usersList"
			,"templates/usersView/createUser"			
			,"templates/usersView/consultUser"

			,"templates/eventsView/createEvent"
			,"templates/eventsView/eventsList"

			,"templates/eventsView/eventConsult"
			,"templates/eventsView/eventListAdmin"

			
			,"templates/roleView/roles"
			,"templates/roleView/addRoles"
			
			, "templates/banktodolistView/banktodolistList"
			,"templates/banktodolistView/banktodolistCreate"


			,"templates/propertiesView/propertiesCreate"
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
		
//		$provide.factory('responseHttpInterceptor', function($q) {
//			  return {
//			    response: function(response) {
			   
//			      return response;
//		    },
//			    responseError: function(response) {
			    	
//			    	if(response.status === 401){
//						window.location.href = "/propiedadesCR/#/login";
//					}
//			      return $q.reject(response);
//			    }
//			  };
//			});
//		$httpProvider.interceptors.push('responseHttpInterceptor');
		
		//RESPONSE INTERCEPTOR FOR ALL THE JQUERY CALLS: EX:THE JQGRID
//		$.ajaxSetup({
//		    beforeSend: function() {
//		    },
//		    complete: function(response) {
//		    	if(response.status === 401){
//		    		window.location.href = "/cenfoteca/login#/";
//				}
//		    }
//		});
		
