(function(){
	'use strict';

	angular.module('app.banktodolist', [])


	.controller('banktodolistController',['$scope','$filter','$http','$location','$mdToast','dbService',function($scope,$filter,$http,$location,$mdToast,dbService){

//datagrid
		$scope.todolistList = [];
        $scope.searchKeywords = '';
        $scope.filteredToDoList = [];
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
//         toast
        var otherObject = [];
        var last = {
            bottom: false,
            top: true,
            left: false,
            right: false
        };
        $scope.toastPosition = angular.extend({},last);
        dbService.checDB();
        var link = 'rest/protected/banktodolist/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {}};
		var init;

		$http.post(link,request).success(function(response) {
			$scope.todolistList= response.bankToDoList;
			init();
		});



        function select(page) {
            var end, start;
            start = (page - 1) * $scope.numPerPage;
            end = start + $scope.numPerPage;
            return $scope.currentPageList = $scope.filteredToDoList.slice(start, end);
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
            $scope.filteredToDoList = $filter('filter')($scope.todolistList, $scope.searchKeywords);
            return $scope.onFilterChange();
        };

        function order(rowName) {
            if ($scope.row === rowName) {
            return;
            }
            $scope.row = rowName;
            $scope.filteredToDoList = $filter('orderBy')($scope.todolistList, rowName);
            return $scope.onOrderChange();
        };

        init = function() {
            $scope.search();
            return $scope.select($scope.currentPage);
        };

        $scope.generateUserToDoList = function(pidToDoList){
            var idUser = localStorage.getItem("idUser");

            var generateLink = 'rest/protected/todolist/generateUserToDoList';
            var toDoRequest ={
                  "pageNumber": 0,
                  "pageSize": 0,
                  "direction": "string",
                  "sortBy": [
                    "string"
                  ],
                  "searchColumn": "string",
                  "searchTerm": "string",
                  "toDoList": {"idToDoList":pidToDoList, "tuser":{"idUser":idUser}} };

            $http.post(generateLink,toDoRequest).success(function(response) {
                $mdToast.show(
                        $mdToast.simple()
                            .content('Se ha generado la lista de tareas')
                            .position($scope.getToastPosition())
                            .hideDelay(1000)
                            );
                $location.url("templates/toDoListView/listToDoList");
            });

        };

        $scope.consultToDoList = function(pidToDoList){
            localStorage.setItem('idToDoList',pidToDoList);
            $location.url("templates/banktodolistView/banktodolistAdminItems");
        };

        $scope.modifyToDoList = function(pidToDoList){
            localStorage.setItem('idToDoList',pidToDoList);
            $location.url("templates/banktodolistView/banktodolistModify");
        };



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

        $scope.go = function(){
            $location.path("/templates/banktodolistView/banktodolistCreate"); 
        };

        $scope.addGuide = function(){
            $location.path("/templates/guidesView/createGuide"); 
        };
    
	}]);

})();