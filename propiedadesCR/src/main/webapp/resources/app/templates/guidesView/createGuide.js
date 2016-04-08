(function(){
	'use strict';

	angular.module('app.createGuide', [])
	.controller('CreateGuideController',['$scope','$http', '$location','$upload','$timeout',function($scope,$http,$location,$upload,$timeout){
		var original;
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.files={};

	    $scope.form = { 	
	        name: '',
	        bank: ''
	    };

	    ////lista de bancos
	    var linkBanks = 'rest/protected/banks/getAll';
	    request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "string","searchTerm": "","bank": {} };
	    		 
	    $http.post(linkBanks,request).success(function(response){
	    	$scope.banks = response.banks;
	    });
	    
	   	var link = 'rest/protected/guides/saveguide';
		var request = {};
		
	    original = angular.copy($scope.form);
	    function revert(){
	        $scope.form = angular.copy(original);
	        $scope.form_banktodolistCreate.$setUntouched(); 
	        return $scope.form_banktodolistCreate.$setPristine();
	    };
	   	$scope.canRevert =  function () {
	        return !angular.equals($scope.form, original) || !$scope.form_banktodolistCreate.$pristine;
	    };
	  	$scope.canSubmit = function() {
	        return $scope.form_banktodolistCreate.$valid && !angular.equals($scope.form, original) && $scope.$flow.files.length;
	    };    
	   	$scope.submitForm = function(event,$files) {
		  
		    $scope.saveGuide(event, $files);
		    $timeout(function() {
		        $scope.showInfoOnSubmit = false;
		    }, 3000);
	       
	    };
	    
	    $scope.saveGuide = function($files){
	    	
	    	var file;

			file = $files[0].file;

			$scope.upload = $upload
				.upload(
						{
							url : link,
							data : {
								idBank : $scope.form.bank,
								name : $scope.form.name
							},
							file : file,
						})
				.success(
						function(response) {
							if($files[0] != undefined)
								$files[0].cancel();
							console.log(response);
							if(response.code!=501){
								revert();
								$scope.message = 'Se ha agregado la guia';
							}else{
								$scope.message = 'Formato del archivo invalido, se esperaba un PDF'
							}

							$scope.showInfoOnSubmit = true;
							
						});
			};
	}]);
	
})();