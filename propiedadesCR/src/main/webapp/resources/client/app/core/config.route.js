(function () {
    'use strict';

    angular.module('app')
        .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
            var routes, setRoutes;

            routes = [
                'templates/propertiesView/propertiesList',
                'templates/todolistView/todolistList'
            ]

            setRoutes = function(route) {
                var config, url;
                url = '/' + route;
                config = {
                    url: url,
                    templateUrl: 'app/' + route + '.html'
                };
                $stateProvider.state(route, config);
                return $stateProvider;
            };

            routes.forEach(function(route) {
            return setRoutes(route);
            });

            $urlRouterProvider
                .when('/', '/home')
                .otherwise('/home');


            $stateProvider.state('home', {
                url: '/home',
                templateUrl: 'app/templates/homeView/home.html'
            });

        }]
    );

})(); 