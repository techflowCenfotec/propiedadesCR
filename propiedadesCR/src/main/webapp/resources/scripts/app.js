!function(){
	"use strict";

	angular.module ("app", [
		// Core modules
		"app.core"

		// Custom Feature modules
		,"app.route"
		,"flow"

		// 3rd party feature modules
		,"mgo-angular-wizard"
		,"ui.tree"
		,"ngMap"
		,"textAngular"
		]).run(['$rootScope', function($rootScope){
			$rootScope.userLogged = null;
		}]);
}(),

function() {
	"use strict";

	angular.module("app.core", [
		// Angular modules
		"ngAnimate"
		,"ngAria"
		,"ngMessages"
		
		// Custom modules
		,"app.layout"
		,"app.i18n"
		,"app.home"
		,"app.properties"
		
		,"app.roles"
		,"app.addRoles"
		
		,"app.events"
		,"app.eventsList"
		
		,"app.createUsers"
		,"app.usersList"
		,"app.consultUser"
	
		,"app.banktodolist"
		,"app.banktodolistCreate"

		,"app.properties.create"

		//3rd Party Modules
		,"ngMaterial"
		,"ui.bootstrap"
		,"duScroll"
		,'angularFileUpload'
		])
}(),

function() {
	"use strict";

	angular.module("app.layout",[])
}(),

function(){
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
			year:2016,
			layout:"wide",
			menu:"vertical",
			isMenuCollapsed:!0,
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
	angular.module("app.core")

	.factory("appConfig",[e])

	.config(["$mdThemingProvider",a])
	}(),

	function() {
		"use strict";

		function e(e,$rootScope,n,t,r,$http) {
			e.pageTransitionOpts=r.pageTransitionOpts,
			e.main=r.main,e.color=r.color,e.$watch("main", function(n,t) {
				"horizontal"===n.menu&&"vertical"===t.menu&&a.$broadcast("nav:reset"),
				n.fixedHeader===!1&&n.fixedSidebar===!0&&(t.fixedHeader===!1&&t.fixedSidebar===!1&&(e.main.fixedHeader=!0,
					e.main.fixedSidebar=!0),
				t.fixedHeader===!0&&t.fixedSidebar===!0&&(e.main.fixedHeader=!1,e.main.fixedSidebar=!1)),
				n.fixedSidebar===!0&&(e.main.fixedHeader=!0),
				n.fixedHeader===!1&&(e.main.fixedSidebar=!1)},!0),
				$rootScope.$on("$stateChangeSuccess", function(e,$rootScope,n) {
				t.scrollTo(0,0)
			})
			e.user ={};
			e.userLogged = {};
			var link = 'rest/protected/users/getUserLogged';
			
			$http.get(link).success(function(response){
				localStorage.setItem('userLogged',response.user);
				e.user = response.user;
				$rootScope.userLogged = response.user;
				e.userLogged = localStorage.getItem('userLogged');
				console.log('LocalStorage', e.userLogged);
				console.log('Respuesta',response.user);
				return e.user;
			});
			e.consultMyProfile = function(myId){
				localStorage.setItem('idUser',myId);
			};
			
		}

		angular.module("app")

		.controller("AppCtrl",["$scope","$rootScope","$state","$document","appConfig","$http",e])
		
	
		
	}(),

	function() {
		function e(e) {
			e.useStaticFilesLoader({
				prefix:"resources/i18n/",
				suffix:".json"
			}),
			e.preferredLanguage("en"),
			e.useSanitizeValueStrategy(null)
		}function a(e,a) {
			function n(n) {
				switch(n) {
					case"English":a.use("en");
					break;
					case"Español":a.use("es");
					break;
					case"中文":a.use("zh");
					break;
					case"日本語":a.use("ja");
					break;
					case"Portugal":a.use("pt");
					break;
					case"Русский язык":a.use("ru")
				}

				return e.lang=n
			}

			function t() {
				var a;

				switch (a=e.lang) {
					case"English":return"flags-american";
					case"Español":return"flags-spain";
					case"中文":return"flags-china";
					case"Portugal":return"flags-portugal";
					case"日本語":return"flags-japan";
					case"Русский язык":return"flags-russia"
				}
			}

			e.lang="English",e.setLang=n,e.getFlag=t
		}

		angular.module("app.i18n",["pascalprecht.translate"])

		.config(["$translateProvider",e])

		.controller("LangCtrl",["$scope","$translate",a])
	}(),

	function(){
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
		angular.module("app.layout")

		.directive("uiPreloader",["$rootScope",e])}(),function() {
			function e() {
				$("#loader-container").fadeOut("slow")
			}$(window).load(function() {
				setTimeout(e,1e3)
			})
		}(),

function(){
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

		angular.module("app.layout")

		.directive("toggleNavCollapsedMin",["$rootScope",e])

		.directive("collapseNav",a)

		.directive("highlightActive",n)

		.directive("toggleOffCanvas",t)
}();
