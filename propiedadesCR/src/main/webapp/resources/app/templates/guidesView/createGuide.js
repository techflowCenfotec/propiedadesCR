(function(){
	'use strict';

	angular.module('app.createGuide', [])
	.controller('CreateGuideController',['$scope','$http', '$location','$upload',function($scope,$http,$location,$upload){
		var original;
	    $scope.onError = false;
	    $scope.requestObject = {};
	    $scope.files={};

	    $scope.form = { 	
	        name: '',
	        description: '',
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
	        return $scope.guideCreate.$setPristine();
	    };
	   	$scope.canRevert =  function () {
	        return !angular.equals($scope.form, original) || !$scope.guideCreate.$pristine;
	    };
	  	$scope.canSubmit = function() {
	        return $scope.guideCreate.$valid && !angular.equals($scope.form, original);
	    };    
	   	$scope.submitForm = function(event,$files) {
		  
		    $scope.saveGuide(event, $files);
	        //$scope.showInfoOnSubmit = true;
	        //return revert();
	    };
	    
	    $scope.saveGuide = function($files){
	    	
	    	var file;

			file = $files[0].file;

			console.log($upload);
			console.log($files)
			$scope.upload = $upload
					.upload(
							{
								url : link,
								data : {
									idBank : $scope.form.bank,
								},
								file : file,
							})
					.success(
							function(data, status,
									headers, config) {
								if($files[0] != undefined)
									$files[0].cancel();
								
								$scope.showInfoOnSubmit = true;
								return revert();
							});
};
	}]);
	
})();