(function() {
	'use strict';
	
	angular.module('app.properties.create', [])
	
	.controller('CreatePropController', ['$scope', '$http', '$upload', 'NgMap', CreatePropController]);

	function CreatePropController($scope, $http, $upload, NgMap) {
		
		var original;
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
		$scope.markerLoc = null;
		//scope variables
		$scope.requestObject = {
				province: '',
				county: '',
				district: '',
				address: '',
				price: '',
				meters: ''
		};
		
		
		$scope.onError = false;
		$scope.selected = [];

		original = angular.copy($scope.requestObject);
		
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
		
		// Google maps
		NgMap.getMap().then(function(map) {
			self.markerPos =  function() {
				$scope.markerLoc = '['+ map.markers[0].position.lat() + ',' + map.markers[0].position.lng() +']';
			};
		});
		
		
		// Form validations
		$scope.canSubmit = function(length) {
			return this.propertiesForm.$valid && length > 0
					&& !angular.equals($scope.requestObject, original);
		};
		$scope.submitForm = function(event, $files) {
			$scope.saveProperty(event, $files);
		};
		$scope.canAddImg = function(length) {
			return length >= 5;
		};
        //Submits new property information
		$scope.saveProperty = function(event, $files) {
			if(this.propertiesForm.$valid) {
				$scope.onError = false;
				var idBenefits = [];

				for(var i = 0; i < self.tags.length; i++){
					idBenefits.push(self.tags[i].idBenefit);
				}
				var request = {
						"pageNumber": 0,
						"pageSize": 0,
						"direction": "",
						"sortBy": [""],
						"searchColumn": "string",
						"searchTerm": "",
						"property": {
							"squareMeters": $scope.requestObject.meters,
							"price": $scope.requestObject.price,
							"tdistrict": { "idDisctrict": $scope.requestObject.district},
							"tpropertyType": { "idPropertyType": $scope.requestObject.type},
							"address": $scope.requestObject.address,
							"coordinates": $scope.markerLoc
						},
						"idBenefits": idBenefits
				};

				$http.post('rest/protected/properties/create', request)
					.success(function(res) {
						for(var i = 0; i < $files.length; i++) {
							var file = $files[i].file;
							
							$scope.upload = $upload.upload({
								url: 'rest/protected/properties/createImage',
								data: {
									userId: res.property.idProperty
								},
								file: file
							});
						}
						$scope.showInfoOnSubmit= true;
				}).error(function(err) {
					console.log(err);
				});
			}
		}
	}
})();