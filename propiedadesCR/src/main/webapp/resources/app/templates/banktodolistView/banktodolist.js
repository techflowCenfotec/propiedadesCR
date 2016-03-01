(function(){
	'use strict';

	angular.module('app.banktodolist', [])


	.controller('banktodolistController',['$scope','$filter','$http',function($scope,$filter,$http){

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
//
		var link = 'rest/protected/banktodolist/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bankToDoList": {}};
		var init;
		//$http.defaults.headers.common['Access-Control-Allow-Origin'] = '*'
		
		 // var req = {
		 //  	method: 'POST',
		 // 	url: link,
		 //  	headers: {
		 //    	'Content-Type': 'aplication/json',
		 //  	},
		 //  	data: { test: 'test' }
		 // }

		$http.post(link,request).success(function(response) {
			$scope.todolistList= response.bankToDoList;
			init();
			console.log($scope.todolistList)
		});

		console.log(request);
		console.log(link);


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
    
	}]);

})();