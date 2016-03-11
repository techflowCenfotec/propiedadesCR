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
		self.districtList = [];
		self.benefitsList = [];
		self.propertyTypeList = [];
		//Benefits tags info
		self.selectedItem = null;
		self.searchText = null;
		//scope variables
		$scope.requestObject = {};
		$scope.onError = false;
		$scope.selected = [];
		//Map variables and default values
		$scope.map = {
				center: '[9.935697,-84.1483646]',
				zoom: 11
		};
		
		$scope.init = function() {
			$http.get('rest/protected/province/getAll', $scope.requestObject)
			.success(function(provincesResponse) {
				$scope.provinceList = provincesResponse.provinces;
			});
			
			$http.get('rest/protected/counties/getAll', $scope.requestObject)
			.success(function(countyResponse) {
				$scope.countyList = countyResponse.counties;
			});
			
			$http.get('rest/protected/districts/getAll', $scope.requestObject)
			.success(function(districtResponse) {
				$scope.districtList = districtResponse.districts;
			});
			
			$http.get('rest/protected/benefits/getAll', $scope.requestObject)
			.success(function(benefitsResponse) {
				self.benefitsList = benefitsResponse.benefits;
			});
			
			$http.get('rest/protected/propertyTypes/getAll', $scope.requestObject)
			.success(function(typeResponse) {
				$scope.propertyTypeList = typeResponse.pTypes;
			});
		};
		
		$scope.init();
		
		$scope.transformChip = function(chip) {
			// If it is an object, it's already a known chip
		      if (angular.isObject(chip)) {
		        return chip
		      }
		};
		
		$scope.querySearch = function(query) {
			var results = query ? self.benefitsList
					.filter($scope.createFilterFor(query)) : [];
		    return results;
		};
		
		$scope.createFilterFor = function(query) {
			var lowercaseQuery = angular.lowercase(query);
			
			self.benefitsList.map(function(ben) {
				ben._lowername = ben.benefit.toLowerCase();
			})
			
			return function filterFn(benefit) {
		        return (benefit._lowername.indexOf(lowercaseQuery) === 0);
		        };
		};
		
        //Submits new property data
		$scope.saveProperty = function(event, $files) {
			if(this.propertiesForm.$valid) {
				$scope.onError = false;
				var idBenefits = [];

				for(var i = 0; i < self.tags.length; i++){
					idBenefits.push(self.tags[i].idBenefit);
				}

				for(var i = 0; i < $files.length; i++) {

					var file = $files[i].file;

					$scope.upload = $upload.upload({
						url: 'rest/protected/properties/create',
						data: {
							square_meters: $scope.requestObject.meters,
							price: $scope.requestObject.price,
							idDistrict: $scope.requestObject.district,
							id_property_type: $scope.requestObject.type,
							address: $scope.requestObject.address,
							benefits: idBenefits
						},
						file: file
					}).progress(function(evt) {
						
					}).success(function(data, status, header, config) {
						
					}).error(function(data, status) {
						
					});
				}
			}
		}
	}
})();