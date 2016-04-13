(function() {
	angular.module('app.bankReport', [])
	.controller("BankReportController", ['$scope', '$timeout', '$http',
	
	                                     
	function($scope,$timeout,$http) {
		   
		   $scope.bar1 = {};
		   $scope.banks = [];
		   $scope.banksNames = [];
		   $scope.bankUsers = [];
		   $scope.idUser;
		   $scope.toDoList = [];
		   $scope.myList = [];
		   $scope.totalUsers= 0;
		   request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bank": {} };
		   $http.post('rest/protected/banks/getAll',request).success(function(responseBanks){
			  for(var i in responseBanks.banks){
				  $scope.banks.push(responseBanks.banks[i].name);
			  }
			  
			  $scope.bankUsers.length = $scope.banks.length;
			  
			  initializeTotalUser();
          
		  $http.get('rest/protected/todolist/getAllFinished').success(function(response){
			
		   
			$scope.toDoList = response.toDoList;
					
				  while($scope.toDoList.length>0){
					  getUserList();
					  calculateUsers();
				 
			  }
				  $scope.bar1.options = {
				            tooltip : {
				                trigger: 'axis'
				            },
				            legend: {
				                data:['2016']
				            },
				            toolbox: {
				                show : true,
				                feature : {
				                    restore : {show: true, title: "restore"},
				                    saveAsImage : {show: true, title: "save as image"}
				                }
				            },
				            calculable : true,
				            xAxis : [
				                {
				                    type : 'category',
				                    data : $scope.banks
				                }
				            ],
				            yAxis : [
				                {
				                    type : 'value'
				                }
				            ],
				            series : [
				                {
				                    name:'2016',
				                    type:'bar',
				                    data:$scope.bankUsers,
				                    markPoint : {
				                        data : [
				                            {type : 'max', name: 'Banco con mayor usuarios'},
				                            {type : 'min', name: 'Banco con menor usuarios'}
				                        ]
				                    },
				                  
				                },
				               
				            ]
				        };
				 
			   });	
				  
		  });	 
			
			
		   function initializeTotalUser(){  
			 for(var i =0; i<$scope.bankUsers.length;i++)
				 $scope.bankUsers[i] = 0;
				 
			 
		   }
		   function getUserList(){
			   $scope.idUser = $scope.toDoList[0].tuser.idUser;
				  for (var f in $scope.toDoList){
					  if($scope.toDoList[f].tuser.idUser === $scope.idUser)	
						  $scope.myList.push($scope.toDoList[f]);	
					  
				  }
				  removeUser();
		  }
		   //Se remueve el usuario al que se le acaba de sacar la lista
		   function removeUser(){
			   $scope.toDoList = _.difference($scope.toDoList,$scope.myList);	
			   $scope.totalUsers =  $scope.totalUsers+1;
		  }
		   
		   function calculateUsers(){
			 
			   var hasCheckListWithBank =[];
			   hasCheckListWithBank.length = $scope.banks.length;
			   for(var i =0; i<$scope.bankUsers.length;i++){
				   hasCheckListWithBank[i] = false; 
				 }
			   
			  for(var i in $scope.banks){
				  for(var f in $scope.myList){
					  if($scope.banks[i]===$scope.myList[f].name){
						  if(  hasCheckListWithBank[i]===false){
							  $scope.bankUsers[i] =  $scope.bankUsers[i]+1;
							  hasCheckListWithBank[i] = true;
						  }
					  }
				  }
			  }
			  $scope.myList = [];
		   }
		   
	}])
	
})();