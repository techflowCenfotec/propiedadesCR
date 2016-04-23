'use strict';

angular.module('DataBaseService', [])

.service('dbService',['$http','$rootScope',function($http,$rootScope) {
 
  
  this.checkDB = function(){
		$http.get("rest/protected/database/checkDB").success(function(data){
			if(data.code!==200){
			$rootScope.userLogged = null;
			$http.get('rest/protected/signOut/signOut').success(function(){
			
			});
			}
		});
  };
  this.localCheckDB = function(){
//		$http.get("rest/local/checkDB").success(function(data){
//			if(data.code!==200){
//				
//				var path = "#/templates/errorsView/500";	
//  			    window.location.href = path;
//			}
//		});
};
    
}]);
