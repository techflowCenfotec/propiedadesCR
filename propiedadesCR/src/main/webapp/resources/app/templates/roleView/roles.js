(function(){
	'use strict';

	angular.module('app.roles', [])
	.controller('rolesController',['$scope','$filter','$http',function($scope,$filter,$http){
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
//
		var link = 'rest/protected/roles/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","role": {}};
		var init;
		
		$http.post(link,request).success(function(response) {
			$scope.rolesList= response.role;
			init();
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
	}]);
})();