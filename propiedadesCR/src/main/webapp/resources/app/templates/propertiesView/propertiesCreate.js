(function() {
	'use strict';
	
	angular.module('app.properties.create', [])
	
	.controller('CreatePropController', ['$scope', '$http', '$upload', 'NgMap', '$state', '$rootScope', CreatePropController]);

	function CreatePropController($scope, $http, $upload, NgMap, $state, $rootScope) {
		
		var original;
		var self = this;
		self.readonly = false;
		self.tags = [];
		self.provinceList = [];
		self.benefitsList = [];
		self.propertyTypeList = [];
		//Benefits tags info
		self.selectedItem = null;
		self.searchText = null;
		$scope.markerLoc = '[9.928003, -84.094463]';
		//scope variables
		$scope.requestObject = {
				selectedSaleType: '',
				province: '',
				county: '',
				district: '',
				address: '',
				price: '',
				meters: ''
		};
		
		$scope.onError = false;
		$scope.selected = [];
		//Map variables and default values

		original = angular.copy($scope.requestObject);
		
		$scope.init = function() {
			$http.get('rest/protected/province/getAll')
			.success(function(provincesResponse) {
				$scope.provinceList = provincesResponse.provinces;
			});
			
			$http.get('rest/protected/benefits/getAll')
			.success(function(benefitsResponse) {
				self.benefitsList = benefitsResponse.benefits;
			});
			
			$http.get('rest/protected/propertyTypes/getAll')
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
		
		$scope.$on("filterAction", function(e, benefitsList) {
			self.tags = benefitsList;
		});
		
		// Google maps
		NgMap.getMap().then(function(map) {
			$scope.markerPos =  function() {
				$scope.markerLoc = '['+ map.markers[0].position.lat() + ',' + map.markers[0].position.lng() +']';
			};
		});
		
		$scope.onChangeProvince = function() {
			$scope.countyList = [];
			
			$http.get('rest/protected/counties/getAll')
			.success(function(countyResponse) {
				for(var i = 0; i < countyResponse.counties.length; i++) {
					if(countyResponse.counties[i].tprovince.idProvince === $scope.requestObject.province){
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
					if(districtResponse.districts[i].tcounty.idCounty === $scope.requestObject.county){
						$scope.districtList.push(districtResponse.districts[i]);
					}
				}
			});
		};
		
		// Form validations
		function revert() {
		    $scope.requestObject = angular.copy(original);	
		    this.propertiesForm.$setPristine()
            this.propertiesForm.$setUntouched();
		    return;
		};
		$scope.canRevert = function() {
			return !angular.equals($scope.requestObject, original)
					|| !$scope.propertiesForm.$pristine;
		};
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
		
		// Routes to list view on cancel
		$scope.cancel = function() {
			$state.go('templates/propertiesView/propertiesList', {},  {reload: true});
		}
		
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
							"tuser": {"idUser": $rootScope.userLogged.idUser},
							"saleType": $scope.requestObject.selectedSaleType,
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
						$state.go('templates/propertiesView/propertiesList', {},  {reload: true});
				});
			}
		}
	}
})();