(function() {
	'use strict';
	
	angular.module('app.properties.view', [])
	
	.controller('PropViewController', ['$scope', '$http', PropViewController]);
	
	function PropViewController($scope, $http) {
		var self = this;
		self.district = {};
		$scope.imageList = [];
		$scope.propertyTypeList = [];
		$scope.provinceList = [];
		$scope.property = {};
		$scope.selectedType = {};
		$scope.selectedDistrict = {};
		$scope.selectedCounty = {};
		$scope.selectedProvince = {};
		$scope.requestObject = {
				province: '',
				county: '',
				district: '',
		};
		
		$scope.init = function() {
			var bd = 'rest/protected/properties/getByPropertyId/' + +localStorage.getItem('idProperty');
			$http.get(bd)
			.success(function(response) {
				$scope.property = response.property;
				$scope.imageList = response.property.tpropertyImages;
				$scope.selectedType = $scope.property.tpropertyType;
				$scope.selectedDistrict = $scope.property.tdistrict;
				
				$http.get('rest/protected/districts/getDistrcitById/'+ 
						$scope.property.tdistrict.idDisctrict)
				.success(function(response) {
					$scope.selectedCounty = response.district.tcounty;
					
					// Provincia del cantón seleccionado
					$http.get('rest/protected/counties/getCountyById/'+ 
							$scope.selectedCounty.idCounty)
					.success(function(response) {
						$scope.selectedProvince = response.county.tprovince;
					});
				});
			});
			
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
			
			$http.get('rest/protected/propertyTypes/getAll', $scope.requestObject)
			.success(function(typeResponse) {
				$scope.propertyTypeList = typeResponse.pTypes;
			});
		}
		
		$scope.init();
		
		$scope.onChangeProvince = function() {
			$scope.countyList = [];
			console.log($scope.selectedProvince);
			$http.get('rest/protected/counties/getAll')
			.success(function(countyResponse) {
				for(var i = 0; i < countyResponse.counties.length; i++) {
					if(countyResponse.counties[i].tprovince.idProvince === $scope.selectedProvince.idProvince){
						$scope.countyList.push(countyResponse.counties[i]);
					}
				}
			});
		};
		
		$scope.onChangeCounty = function() {
			$scope.districtList = [];
			
			$http.get('rest/protected/districts/getAll')
			.success(function(districtResponse) {
				for(var i = 0; i < districtResponse.districts.length; i++) {
					if(districtResponse.districts[i].tcounty.idCounty === $scope.selectedCounty.idCounty){
						$scope.districtList.push(districtResponse.districts[i]);
					}
				}
			});
		};
	};
	
})();