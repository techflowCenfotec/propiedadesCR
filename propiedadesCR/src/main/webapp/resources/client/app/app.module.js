(function () {
    'use strict';

    angular.module('app', [
        // Core modules
         'app.core'
        
        // Custom Feature modules
        ,'app.home'
        ,'app.todolist'

        // 3rd party feature modules
        ,'mgo-angular-wizard'
        ,'ui.tree'
        ,'ngMap'
        ,'textAngular'
    ]);

})();

