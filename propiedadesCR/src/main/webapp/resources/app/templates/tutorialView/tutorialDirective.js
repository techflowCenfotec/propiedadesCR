(function(){
    'use strict';

    angular.module('app.tutorial', [])
    .controller('tutorialController',['$scope','$mdDialog','$http','$location','$timeout',function($scope,$mdDialog,$http,$location,$timeout){

    $scope.status = '  ';
    $scope.propertiesMenu = true;
    $scope.principalMenu = true;
    $scope.eventsMenu = true;
    $scope.userId = 0;
    var init = showWelcomeDialog();
    var link = 'rest/protected/users/getUserLogged';

    var userResponse = {};


        function showWelcomeDialog(){
 
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog1.tmpl.html',
            parent: angular.element(document.body),
            clickOutsideToClose:false
            })
                .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                $scope.status = 'You cancelled the dialog.';
                });
        };

        function showPrincipal(ev) {
            $scope.principalMenu = false;
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog2.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false
            })
                .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                $scope.status = 'You cancelled the dialog.';
                });
        };

        function showPropertiesDialog(ev) {
            $scope.principalMenu = true;
            $scope.propertiesMenu = false;
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog3.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false
            })
                .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                $scope.status = 'You cancelled the dialog.';
                });
        };


        function showEvents(ev) {
            $scope.propertiesMenu = true;
            $scope.eventsMenu = false;
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog4.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false
            })
                .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                $scope.status = 'You cancelled the dialog.';
                });
        };


        function showPrincipalQA(ev) {
            $scope.eventsMenu = true;
       
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog5.tmpl.html',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose:false
            })
                .then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                $scope.status = 'You cancelled the dialog.';
                });
        };

            $http.get(link).success(function(response) {
                console.log("hey");
                userResponse = response.user;
                $scope.userId = userResponse.idUser;
                var userLink = 'rest/protected/users/notFirstTime'
                var userRequest = {
                  "pageNumber": 0,
                  "pageSize": 0,
                  "direction": "string",
                  "sortBy": [
                    "string"
                  ],
                  "searchColumn": "string",
                  "searchTerm": "string",
                  "user": {"idUser":$scope.userId}
                };
                console.log(userRequest);
                $http.post(userLink, userRequest).success(function(response) {
                    console.log(response);
                });
        });
        function DialogController($scope, $mdDialog, $location, $timeout) {
        
            $scope.hide = function() {
                $mdDialog.hide();
            };
            $scope.cancel = function() {
                $mdDialog.cancel();
            };
            $scope.answer = function(answer,ev) {
                $mdDialog.hide(answer);
                showPrincipal(ev);
            };


            $scope.closePrincPDialog = function(ev) {
                $mdDialog.hide();
                showPropertiesDialog(ev);
            };

            $scope.closePropDialog = function(ev) {
                $mdDialog.hide();
                showEvents(ev);
            };

            $scope.closeEvents = function(ev) {
                $mdDialog.hide();
                showPrincipalQA(ev);
            };
            $scope.closePrincioalQA = function(ev) {
                $mdDialog.hide();
                $timeout(function(){
                $location.path("/propiedadesCR/app#/home"); 
            }, 2000);
            };
        };


    }]);
    
})();
