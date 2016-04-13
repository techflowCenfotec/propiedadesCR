(function(){
    'use strict';

    angular.module('app.tutorial', [])
    .controller('tutorialController',['$scope','$mdDialog','$http','$location','$timeout',function($scope,$mdDialog,$http,$location,$timeout){

    $scope.status = '  ';
    $scope.propertiesMenu = true;
    $scope.principalMenu = true;
    $scope.eventsMenu = true;
    $scope.userId = 0;
    $scope.show = "display: block;"
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

        function showBankToDo(ev) {
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

        function showPropertiesQA(ev) {
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

        function showConsultGuides(ev) {
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

        function showUserToDo(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog6.tmpl.html',
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
//************************************************************************
        function showPropertiesDialog(ev) {
            $scope.principalMenu = true;
            $scope.propertiesMenu = false;
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog7.tmpl.html',
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

        function showPropertiesList(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog8.tmpl.html',
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

        function showFaveProperties(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog9.tmpl.html',
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
//***************************************************************************************************

        function showEvents(ev) {
            $scope.propertiesMenu = true;
            $scope.eventsMenu = false;
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog10.tmpl.html',
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

        function showListEvents(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog11.tmpl.html',
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

//*********************************************************

        function showSellers(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog12.tmpl.html',
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
            $http.post(userLink, userRequest).success(function(response) {
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
                showBankToDo(ev);
            };

            $scope.closesBankToDo = function(ev) {
                $mdDialog.hide();
                showPropertiesQA(ev);
            };
            $scope.closePropertiesQA = function(ev) {
                $mdDialog.hide();
                showConsultGuides(ev);
            };
            $scope.closeConsultGuides = function(ev) {
                $mdDialog.hide();
                showUserToDo(ev);
            };
            $scope.closeUserToDo = function(ev) {
                $mdDialog.hide();
                showPropertiesDialog(ev);
            };


            $scope.closePropDialog = function(ev) {
                $mdDialog.hide();
                showPropertiesList(ev);
            };

            $scope.closePropertiesList = function(ev) {
                $mdDialog.hide();
                showFaveProperties(ev);
            };

            $scope.closeFaveProperties = function(ev) {
                $mdDialog.hide();
                showEvents(ev);
            };


            $scope.closeEvents = function(ev) {
                $mdDialog.hide();
                showListEvents(ev);
            };

            $scope.closeListEvents = function(ev) {
                $mdDialog.hide();
                showSellers(ev);
            };

            $scope.closeSellers = function(ev) {
                $mdDialog.hide();
                $timeout(function(){
                    $location.path("/propiedadesCR/app#/home"); 
                }, 2000);
            };
        };


    }]);
    
})();
