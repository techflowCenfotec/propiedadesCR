(function() {
	'use strict';
	
	angular.module('app.properties.create', [])
	
	.controller('CreatePropController', ['$scope', '$http', '$upload', CreatePropController]);

	function CreatePropController($scope, $http, $upload) {
		
		var self = this;
		self.readonly = false;
		self.tags = [];
		self.provinceList = [];
		self.countyList = [];
		self.benefitsList = [];
		self.propertyTypeList = [];
		
		//scope variables
		$scope.requestObject = {};
		$scope.onError = false;
		$scope.selected = [];

		$scope.init = function() {
			$http.get('rest/protected/province/getAll', $scope.requestObject)
			.success(function(provincesResponse) {
				$scope.provinceList = provincesResponse.provinces;
			});
			
			$http.get('rest/protected/counties/getAll', $scope.requestObject)
			.success(function(countyResponse) {
				$scope.countyList = countyResponse.counties;
			});
			
			$http.get('rest/protected/benefits/getAll', $scope.requestObject)
			.success(function(benefitsResponse) {
				$scope.benefitsList = benefitsResponse.benefits;
			});
			
			$http.get('rest/protected/propertyTypes/getAll', $scope.requestObject)
			.success(function(typeResponse) {
				$scope.propertyTypeList = typeResponse.pTypes;
			});
		};
		
		$scope.init();
		
		
	    //Push items into selected array
		$scope.toggle = function (item, list) {
            var idx = list.indexOf(item);
            if (idx > -1) list.splice(idx, 1);
            else list.push(item);
        };
		
        //Submits new property data
		$scope.saveProperty = function(event, $files) {
			if(this.propertiesForm.$valid) {
				$scope.onError = false;
				
				for(var i = 0; i < $files.length; i++) {
					
					var file = $files[i].file;
					
					$scope.upload = $upload.upload({
						url: 'rest/protected/properties/create',
						data: {
							idProvince: $scope.requestObject.province,
							location: $scope.requestObject.county,
							nearby_areas: self.tags,
							benefits: $scope.selected,
							price: $scope.requestObject.price,
							id_property_type: $scope.requestObject.type,
							square_meters: $scope.requestObject.meters
						},
						file : file
					}).progress(function(evt) {
						console.log('percent: '+ parseInt(100.0 * evt.loaded / evt.total));
					}).success(function(data, status, header, config) {
						console.log(data, status, header, config);
					}).error(function(data, status) {
						console.log("the error is: ",  data)
					});
				}
			} else {
				$scope.onError = true;
			}
		}
	}
})();