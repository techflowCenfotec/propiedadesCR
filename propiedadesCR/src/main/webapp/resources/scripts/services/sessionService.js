'use strict';

angular.module('SessionService', [])

.service('sessionService',['$http','$rootScope','$state',function($http,$rootScope,$state) {
 
  
  this.checkSession = function(){
	  if(sessionStorage.getItem('reload') !='1'){
			sessionStorage.setItem('reload','1');
			$state.reload();
		}	
  };
    
}]);
