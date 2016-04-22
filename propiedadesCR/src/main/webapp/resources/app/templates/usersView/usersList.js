(function() {
	"use strict";

	angular.module("app.usersList",[])
		.controller('listUsersController',['$scope','$filter','$http','dbService','$location','$rootScope',
		                                   function($scope,$filter,$http,dbService,$location,$rootScope){
		
	
		
	
		
		$scope.users=[];
        $scope.searchKeywords = '';
        $scope.filteredUsers = [];
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

		
        
        dbService.checkDB();
       
        
        var link = 'rest/protected/users/getAll';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": {}};
		var init;
		$http.post(link,request).success(function(response) {
			
			$scope.users= response.users;

			init();
		
			
		});
        function select(page) {
            var end, start;
            start = (page - 1) * $scope.numPerPage;       
            end = start + $scope.numPerPage;
            $scope.currentPageList = $scope.filteredUsers.slice(start, end);
            
            $scope.currentPageList = _.without($scope.currentPageList,_.findWhere($scope.currentPageList,{idUser: parseInt(localStorage.getItem('idUser'))}));
            $scope.currentPageList = _.difference($scope.currentPageList,_.where($scope.currentPageList,{active:0}));
            
            return $scope.currentPageList;
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
            $scope.filteredUsers = $filter('filter')($scope.users, $scope.searchKeywords);
            return $scope.onFilterChange();
        };

        function order(rowName) {
            if ($scope.row === rowName) {
            return;
            }
            $scope.row = rowName;
            $scope.filteredUsers = $filter('orderBy')($scope.users, rowName);
            return $scope.onOrderChange();
        };

        init = function() {
            $scope.search();
            return $scope.select($scope.currentPage);
        };
        
        $scope.modifyUser = function(id){
        	localStorage.setItem('idUserModify',id);
        }
        
        $scope.deleteUser = function(user){
        	
        	var deleteRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","user": 
        	{"idUser": user.idUser, "userName":user.userName , "firstName": user.firstName, "lastName": user.lastName, "email": user.email,
        	"active":0, "birthday": user.birthday, "firstTime": 0, "gender": user.gender, "password": user.password,
        	"phone1":user.phone1, "phone2": user.phone2,
        	"role":{"idRole":user.role.idRole},
        	"userImage": user.userImage}}
            	
        	$http.post('rest/protected/users/deleteUser',deleteRequest).success(function(){
        		$scope.currentPageList = _.without($scope.currentPageList,_.findWhere($scope.currentPageList,{idUser:parseInt(user.idUser)}));
        	});
        	
        }
        $scope.go = function(){
        	$location.path("/templates/usersView/createUser");
        	
        }
        
	}]);
})();