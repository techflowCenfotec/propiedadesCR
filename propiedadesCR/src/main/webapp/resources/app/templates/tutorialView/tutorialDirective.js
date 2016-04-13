(function(){
    'use strict';

    angular.module('app.tutorial', [])
    .controller('tutorialController',['$scope','$mdDialog','$http','$location','$timeout','dbService',function($scope,$mdDialog,$http,$location,$timeout,dbService){

    $scope.status = '  ';
    $scope.showMenu = false;
    $scope.principalMenu = true;
    $scope.propertiesMenu = true;
    $scope.eventsMenu = true;
    $scope.vendorsMenu = true;
    $scope.showPrincipalMenu = false;
    $scope.bankToDo = true;
    $scope.propertiesQuestions = true;
    $scope.consultGuides = true;
    $scope.userToDo = true;
    $scope.userId = 0;
    var init = showWelcomeDialog();
    var link = 'rest/protected/users/getUserLogged';
    var myStyle = "display: none;";
    localStorage.setItem('style',myStyle);
    localStorage.setItem('propStyle',myStyle);
    localStorage.setItem('eveStyle',myStyle);
    

    var userResponse = {};
    dbService.checkDB();

        function showWelcomeDialog(){
            console.log($scope.$broadcast("dropDown"));
            $scope.showMenu = false;
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
            $scope.showMenu = true;
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
            myStyle = "display: block";
            localStorage.setItem('style',myStyle);
            $scope.showMenu = false;
            $scope.showPrincipalMenu = true;
            $scope.bankToDo = false;
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
            $scope.bankToDo = true;
            $scope.propertiesQuestions = false;
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
            $scope.propertiesQuestions = true;
            $scope.consultGuides = false;
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
            $scope.consultGuides = true;
            $scope.userToDo = false;
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
            myStyle = "display: none";
            localStorage.setItem('style',myStyle);
            $scope.principalMenu = true;
            $scope.showPrincipalMenu = false;
            $scope.userToDo = true;
            $scope.showMenu = true;
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
            var prop = "display: block";
            localStorage.setItem('propStyle',prop);
            $scope.showMenu = false;
            $scope.propertiesMenu = true;
            $scope.showPrincipalMenu = true;
            $scope.propertiesQuestions = false;
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
            $scope.propertiesQuestions = true;
            $scope.consultGuides = false;
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
            var prop = "display: none";
            localStorage.setItem('propStyle',prop);
            $scope.showPrincipalMenu = false;
            $scope.showMenu = true;
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
            var eve = "display: block";
            localStorage.setItem('eveStyle',eve);
            $scope.showMenu = true;
            $scope.eventsMenu = true;
            $scope.vendorsMenu = false;

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
            var eve = "display: none";
            localStorage.setItem('eveStyle',eve);
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
        	dbService.checkDB();
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
