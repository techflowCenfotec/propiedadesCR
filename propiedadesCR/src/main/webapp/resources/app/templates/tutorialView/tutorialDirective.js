(function(){
    'use strict';

    angular.module('app.tutorial', [])
    .controller('tutorialController',['$scope','$mdDialog',function($scope,$mdDialog){
        

//         $scope.showConfirm = function() {
//             $scope.status = '  ';
//             var confirm = $mdDialog.confirm()
//             .title('¿Está seguro que desea eliminar el evento?')
// //          .content('All of the banks have agreed to <span class="debt-be-gone">forgive</span> you your debts.')
//             .ariaLabel('Lucky day')
//             .ok('Eliminar')
//             .cancel('Cancelar');
//             $mdDialog.show(confirm).then(function() {
//             }, function() {

//             });
//         };
    $scope.status = '  ';


        $scope.showWelcomeDialog = function(ev) {
            $mdDialog.show({
            controller: DialogController,
            templateUrl: 'dialog1.tmpl.html',
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

        function showPrincipal(ev) {
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

        function showPrincipalQA(ev) {
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

        function DialogController($scope, $mdDialog) {
            $scope.hide = function() {
                $mdDialog.hide();
            };
            $scope.cancel = function() {
                $mdDialog.cancel();
            };
            $scope.answer = function(answer,ev) {
                $mdDialog.hide(answer);
                showPropertiesDialog(ev);
            };

            $scope.closePropDialog = function(ev) {
                $mdDialog.hide();
                showPrincipal(ev);
            };

            $scope.closePrincPDialog = function(ev) {
                $mdDialog.hide();
                showPrincipalQA(ev);
            };

            $scope.closePrincioalQA = function(ev) {
                $mdDialog.hide();
            };
        };


    }]);
    
})();
