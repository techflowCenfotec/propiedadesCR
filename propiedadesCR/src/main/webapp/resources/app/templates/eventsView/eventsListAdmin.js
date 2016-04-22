(function() {
	"use strict";

	angular.module("app.eventsListAdmin",[])
		.controller('listAdminEventController',['$scope','$filter','$http','$mdDialog','$location',function($scope,$filter,$http,$mdDialog,$location){
		
		$scope.status = '  ';
		$scope.events=[];
        $scope.searchKeywords = '';
        $scope.filteredEvents = [];
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
		
        var link = 'rest/protected/events/getAllEvents';
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","event": {}};
		var init;
		$http.post(link,request).success(function(response) {
			$scope.events= response.events;
			
			init();
		
			
		});
        function select(page) {
            var end, start;
            start = (page - 1) * $scope.numPerPage;       
            end = start + $scope.numPerPage;
            return $scope.currentPageList = $scope.filteredEvents.slice(start, end);
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
            $scope.filteredEvents = $filter('filter')($scope.events, $scope.searchKeywords);
            return $scope.onFilterChange();
        };

        function order(rowName) {
            if ($scope.row === rowName) {
            return;
            }
            $scope.row = rowName;
            $scope.filteredEvents = $filter('orderBy')($scope.events, rowName);
            return $scope.onOrderChange();
        };

        init = function() {
            $scope.search();
            return $scope.select($scope.currentPage);
        };
        $scope.deleteEvent = function(id){
        	  var data = $.param({
                  id: id,
              });
    		 
    		$http["delete"]('rest/protected/events/delete?'+data)
            .success(function (data, status, headers) {
            	$scope.currentPageList = _.without($scope.currentPageList,_.findWhere($scope.currentPageList,{idEvent:id}));
            })
        }
        
         $scope.showConfirm = function(id) {
        	$scope.status = '  ';
            var confirm = $mdDialog.confirm()
                        .title('¿Está seguro que desea eliminar el evento?')
                        .ariaLabel('Lucky day')
                        .ok('Eliminar')
                        .cancel('Cancelar');
            $mdDialog.show(confirm).then(function() {
                $scope.status = 'Evento eliminado.';
                $scope.deleteEvent(id);
            }, function() {
                $scope.status = 'Evento no eliminado.';
            });
        };
        
        $scope.modifyEvents = function(id){
        	localStorage.setItem('idEventModify',id);
        	
        }
        $scope.go = function(){
        	$location.path("/templates/eventsView/createEvent");	
        }
		
	}]);
})();