"use strict";

var app =	angular.module ("app", [
	"mgo-angular-wizard"
	,"ui.tree"
	,"ngMap"
	,"textAngular"
	]).run(['$rootScope', function($rootScope){
		$rootScope.userLogged = null;
}]);

app.route = angular.module("app.route", ["ui.router"])

.config(["$stateProvider","$urlRouterProvider", function($stateProvider,$urlRouterProvide){ 
	
	var n,t;
	n = [
		"templates/propertiesView/propertiesList"
		,"templates/reportsView/reports"

		,"templates/usersView/usersList"
		,"templates/usersView/createUser",			

		,"templates/eventsView/createEvent"
		,"templates/eventsView/eventsList"
		,"templates/loginView/login"
	], 

	t = function(a) {

		var n, t;

		return t = "/" + a,

		n = { 
			url: t,
			templateUrl: "resources/app/" + a + ".html"
		},
		
		$stateProvider.state($urlRouterProvide,n),$stateProvider
	},
		
	n.forEach(function(e) {
	 	return t(e)
	}),
		
	$stateProvider.state( "login", {
		url:"/login",
		templateUrl:"resources/app/templates/loginView/index.html"
	})
	.state("app", {
		url: "/app",
		templateUrl: "resources/app/layout/mainLayout.jsp"
	})
	.state( "app.home", {
		url:"/home",
		templateUrl:"resources/app/templates/homeView/home.html"
	})
	/**
	Ejemplo
	.state("app.nuevaRuta", {
		url:"/nuevaRuta",
		templateUrl:"resources/app/templates/url/nueva/ruta.jsp"
	})
	*/

	.otherwise("app.home");
}]);

app.layout = angular.module("app.layout",[]);

app.core = angular.module("app.core", [
   	// Angular modules
   	"ngAnimate"
   	,"ngAria"
   	,"ngMessages"
   	
   	// Custom modules
   	,"app.layout"
   	,"app.i18n"
   	,"app.route"

   	//3rd Party Modules
   	,"ngMaterial"
   	,"ui.bootstrap"
   	,"duScroll"
   	,"angularFileUpload"
   	,"flow"
]);




(function(){
	"use strict";

	function e(){
		var e=[{
			name:"Fade up","class":"animate-fade-up"
		}, {
			name:"Scale up","class":"ainmate-scale-up"
		}, {
			name:"Slide in from right","class":"ainmate-slide-in-right"
		}, {
			name:"Flip Y","class":"animate-flip-y"
		}],

		a=new Date,n=a.getFullYear(),
		t= {
			brand:"Propiedades CR",
			name:"User",
			year:n,
			layout:"wide",
			menu:"vertical",
			isMenuCollapsed:!1,
			fixedHeader:!0,
			fixedSidebar:!0,
			pageTransition:e[0],
			skin:"12"
		},
		r= {
			primary:"#009688",
			success:"#8BC34A",
			info:"#00BCD4",
			infoAlt:"#7E57C2",
			warning:"#FFCA28",
			danger:"#F44336",
			text:"#3D4051",
			gray:"#EDF0F1"
		};
		return {
			pageTransitionOpts:e,main:t,color:r
		}
	}

	function a(e) {
		var a=e.extendPalette("cyan",{
			contrastLightColors:"500 600 700 800 900",
			contrastStrongLightColors:"500 600 700 800 900"
		}),
		n=e.extendPalette("light-green",{
			contrastLightColors:"500 600 700 800 900",
			contrastStrongLightColors:"500 600 700 800 900"
		});
		e.definePalette("cyanAlt",a).definePalette("lightGreenAlt",n),
		e.theme("default").primaryPalette("teal",{
			"default":"500"
		}).accentPalette("cyanAlt",{
			"default":"500"
		}).warnPalette("red",{
			"default":"500"
		}).backgroundPalette("grey")
	}
	app.core

	.factory("appConfig",[e])

	.config(["$mdThemingProvider",a])
	}());
	
function a(e) {
    var a = e.extendPalette("cyan", {
            contrastLightColors: "500 600 700 800 900",
            contrastStrongLightColors: "500 600 700 800 900"
        }),
        n = e.extendPalette("light-green", {
            contrastLightColors: "500 600 700 800 900",
            contrastStrongLightColors: "500 600 700 800 900"
        });
    e.definePalette("cyanAlt", a).definePalette("lightGreenAlt", n),
        e.theme("default").primaryPalette("teal", {
            "default": "500"
        }).accentPalette("cyanAlt", {
            "default": "500"
        }).warnPalette("red", {
            "default": "500"
        }).backgroundPalette("grey")
}
app.core.factory("appConfig", [e]).config(["$mdThemingProvider", a]);

function e(e,a,n,t,r) {
	e.pageTransitionOpts=r.pageTransitionOpts,
	e.main=r.main,e.color=r.color,e.$watch("main", function(n,t) {
		"horizontal"===n.menu&&"vertical"===t.menu&&a.$broadcast("nav:reset"),
		n.fixedHeader===!1&&n.fixedSidebar===!0&&(t.fixedHeader===!1&&t.fixedSidebar===!1&&(e.main.fixedHeader=!0,
			e.main.fixedSidebar=!0),
		t.fixedHeader===!0&&t.fixedSidebar===!0&&(e.main.fixedHeader=!1,e.main.fixedSidebar=!1)),
		n.fixedSidebar===!0&&(e.main.fixedHeader=!0),
		n.fixedHeader===!1&&(e.main.fixedSidebar=!1)},!0),
	a.$on("$stateChangeSuccess", function(e,a,n) {
		t.scrollTo(0,0)
	})
}

app.core.controller("AppCtrl",["$scope","$rootScope","$state","$document","appConfig",e]);

	(function(){
		"use strict";
		function e(e) {
			return {
				restrict:"A",
				template:'<span class="bar"></span>',
				link: function(e,a,n) {
					a.addClass("preloaderbar hide"),
					e.$on("$stateChangeStart",function(e) {
						a.removeClass("hide").addClass("active")
					}),
					e.$on("$stateChangeSuccess",function(e,n,t,r) {
						e.targetScope.$watch("$viewContentLoaded",function() {
							a.addClass("hide").removeClass("active")
						})
					}),
					e.$on("preloader:active",function(e) {
						a.removeClass("hide").addClass("active")
					}),e.$on("preloader:hide",function(e) {
						a.addClass("hide").removeClass("active")
					})
				}
			}
		}
		app.layout

		.directive("uiPreloader",["$rootScope",e])});

(function() {
		function e() {
			$("#loader-container").fadeOut("slow")
		}$(window).load(function() {
			setTimeout(e,1e3)
		})
});

(function(){
	"use strict";

	function e(e) {
		function a(a,n,t) {
			var r;r=$("#app"),n.on("click",function(a) {
				return r.hasClass("nav-collapsed-min")?r.removeClass("nav-collapsed-min"):(r.addClass("nav-collapsed-min"),
					e.$broadcast("nav:reset")),a.preventDefault()
			})
		}
		var n= {
			restrict:"A",
			link:a
		};
		return n
	}
	function a() {
		function e(e,a,n) {
			var t,r,i,o,s,l,c,u,d,p,f;
			p=250,
			c=$(window),
			o=a.find("ul").parent("li"),
			o.append('<i class="fa fa-angle-down icon-has-ul-h"></i>'),
			t=o.children("a"),
			t.append('<i class="fa fa-angle-down icon-has-ul"></i>'),
			s=a.children("li").not(o),
			r=s.children("a"),
			i=$("#app"),
			l=$("#nav-container"),
			t.on("click",
				function(e) {
					var a,n;
					return i.hasClass("nav-collapsed-min")||l.hasClass("nav-horizontal")&&c.width()>=768?!1:(n=$(this),
						a=n.parent("li"),
						o.not(a).removeClass("open").find("ul").slideUp(p),
						a.toggleClass("open").find("ul").stop().slideToggle(p),
						void e.preventDefault())
				}),
			r.on("click",
				function(e) {
					o.removeClass("open").find("ul").slideUp(p)
				}),

			e.$on("nav:reset",function(e) {
				o.removeClass("open").find("ul").slideUp(p)
			}),
			u=void 0,d=c.width(),f=function() {

				var e;
				e=c.width(),
				768>e&&i.removeClass("nav-collapsed-min"),
				768>d&&e>=768&&l.hasClass("nav-horizontal")&&o.removeClass("open").find("ul").slideUp(p),
				d=e},
				c.resize(function() {

					var e;

					clearTimeout(e),e=setTimeout(f,300)
				})
			}

			var a= {
				restrict:"A",link:e
			};

			return a
		}

		function n() {
			function e(e,a,n,t) {
				
				var r,i,o;
				
				i=a.find("a"),o=function() {
					return t.path()
				},

				r=function(e,a) {
					
					return a="#"+a,
					
					angular.forEach(e,function(e) {
						var n,t,r;
						return t=angular.element(e), 

						n=t.parent("li"), 
						r=t.attr("href"),
						n.hasClass("active")&&n.removeClass("active"),
						0===a.indexOf(r)?n.addClass("active"):void 0
					})
				},

				r(i,t.path()),e.$watch(o,function(e,a) {
					return e!==a?r(i,t.path()):void 0
				})
			}

			var a= {
				restrict:"A",
				controller:["$scope","$element","$attrs","$location",e]
			};
			return a
		}
		function t() {
			function e(e,a,n) {
				a.on("click",function() {
					return $("#app").toggleClass("on-canvas")
				})
			}

			var a= {
				restrict:"A",
				link:e
			};

			return a
		}

		app.layout

		.directive("toggleNavCollapsedMin",["$rootScope",e])

		.directive("collapseNav",a)

		.directive("highlightActive",n)

		.directive("toggleOffCanvas",t)
});
