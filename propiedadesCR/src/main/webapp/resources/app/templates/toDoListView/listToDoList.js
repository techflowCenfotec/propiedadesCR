(function(){
    'use strict';

    angular.module('app.usertoDoList', [])
    .controller('usertoDoListController',
        ['$scope','$filter','$http', '$upload','$mdToast',"$location",
        function($scope,$filter,$http,$upload, $mdToast,$location){
//datagrid
        $scope.rolesList = [];
        $scope.searchKeywords = '';
        $scope.filteredRole = [];
        $scope.row = '';
        $scope.select = select;
        $scope.onFilterChange = onFilterChange;
        $scope.onNumPerPageChange = onNumPerPageChange;
        $scope.onOrderChange = onOrderChange;
        $scope.search = search;
        $scope.order = order;
        $scope.numPerPageOpt = [3, 5, 10, 20];
        $scope.numPerPage = $scope.numPerPageOpt[2];
        $scope.currentPage = 1;
        $scope.currentPage = [];
        $scope.toastPosition = angular.extend({},last);
        $scope.userLoggedId = 0;
//
        var link = 'rest/protected/todolist/getAll';
        var init;
        var linDelete = 'rest/protected/todolist/delete';
        var userLoggedObj = {};
        var userLoggedLink = 'rest/protected/users/getUserLogged';
        var last = {
            bottom: false,
            top: true,
            left: false,
            right: false
        };

        function select(page) {
            var end, start;
            start = (page - 1) * $scope.numPerPage;       
            end = start + $scope.numPerPage;
            return $scope.currentPageList = $scope.filteredEvents.slice(start, end);
        };

        $http.get(userLoggedLink).success(function(response) {
            
            userLoggedObj = response.user;
            $scope.userLoggedId = userLoggedObj.idUser;
           

                    var request = {
                                  "pageNumber": 0,
                                  "pageSize": 0,
                                  "direction": "string",
                                  "sortBy": [
                                    "string"
                                  ],
                                  "searchColumn": "string",
                                  "searchTerm": "string",
                                  "toDoList": {
                                   "tuser": {"idUser": $scope.userLoggedId}
                                }
                                };
                    $http.post(link,request).success(function(response) {
                        $scope.rolesList = response.toDoList;
                        init();
                       
                    });
        });
        
         
       

        function select(page) {
            var end, start;
            start = (page - 1) * $scope.numPerPage;
            end = start + $scope.numPerPage;
            return $scope.currentPageList = $scope.filteredRole.slice(start, end);
        };

        function onFilterChange() {
            $scope.select(1);
            $scope.currentPage = 1;
            return $scope.row = '';
        };

        function onNumPerPageChange() {
            $scope.select(1);
            return $scope.currentPage = 1;
        };

        function onOrderChange() {
            $scope.select(1);
            return $scope.currentPage = 1;
        };

        function search() {
            $scope.filteredRole = $filter('filter')($scope.rolesList, $scope.searchKeywords);
            return $scope.onFilterChange();
        };

        function order(rowName) {
            if ($scope.row === rowName) {
            return;
            }
            $scope.row = rowName;
            $scope.filteredRole = $filter('orderBy')($scope.rolesList, rowName);
            return $scope.onOrderChange();
        };

        init = function() {
            $scope.search();
            return $scope.select($scope.currentPage);
        };

        $scope.modifyRole = function(id){
            localStorage.setItem('idRoleModify',id);
        }
        $scope.deleteRole = function(id, name, description){
           
            var requestDelete ={
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "string",
            "sortBy": [
            "string"
            ],
            "searchColumn": "string",
            "searchTerm": "string",
            "toDoList": {"idToDoList": id}
            };
            $http.post(linDelete,requestDelete).success(function(response) {
                $mdToast.show(
                    $mdToast.simple()
                        .content('Se ha eliminado la tarea!')
                        .position($scope.getToastPosition())
                        .hideDelay(3000)
                );
                $scope.currentPageList = _.without($scope.currentPageList,_.findWhere($scope.currentPageList,{idToDoList:id}));
            });
        }

        $scope.getToastPosition = function() {
            sanitizePosition();
            return Object.keys($scope.toastPosition).filter(function(pos) 
                { return $scope.toastPosition[pos]; 
                }).join(' ')
        };

        function sanitizePosition() {
            var current = $scope.toastPosition;

            if ( current.bottom && last.top ) current.top = false;
            if ( current.top && last.bottom ) current.bottom = false;
            if ( current.right && last.left ) current.left = false;
            if ( current.left && last.right ) current.right = false;

            last = angular.extend({},current);
        };
        $scope.consultToDoList = function(id){
        	
        	localStorage.setItem('idMyToDoList',id);
        	$location.path("/templates/toDoListView/myToDoList");
        	
        }
    }]);
})();