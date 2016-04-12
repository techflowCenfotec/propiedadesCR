(function() {
	angular.module('app.properties.modify', [])
	
	.controller('propertyModifyController', ['$scope', '$http', '$upload', 'NgMap', '$state','$uibModal','$log', propertyModifyController])
    .controller('OfferModalCtrl', ['$scope', '$uibModalInstance','$http','$timeout', OfferModalCtrl]);
	
	function propertyModifyController($scope, $http, $upload, NgMap, $state,$uibModal,$log) {
		var self = this;
		var original;
		self.district = {};
		self.tags = [];
		self.benefitsList = [];
		$scope.markerLoc = null;
		$scope.animationsEnabled = true;
		$scope.imageList = [];
		$scope.propertyTypeList = [];
		$scope.provinceList = [];
		$scope.property = {};
		$scope.selectedType = {};
		$scope.selectedDistrict = {};
		$scope.requestObject = {
				province: '',
				county: '',
				district: '',
				address: '',
				price: '',
				meters: ''
		};
		$scope.hasAnOffer = false;
		
		$scope.onError = false;
		
		original = angular.copy($scope.requestObject);
		
		$scope.init = function() {
			var bd = 'rest/protected/properties/getByPropertyId/' + localStorage.getItem('idProperty');
			$http.get(bd)
			.success(function(response) {
			
				$scope.property = response.property;
				$scope.imageList = response.property.tpropertyImages;
				$scope.selectedType = $scope.property.tpropertyType;
				$scope.requestObject.district = $scope.property.tdistrict;
				
				if($scope.property.offerPercentage!=0)
					$scope.hasAnOffer =true;
				
				$http.get('rest/protected/districts/getDistrcitById/'+ 
						$scope.property.tdistrict.idDisctrict)
				.success(function(response) {
					$scope.requestObject.county = response.district.tcounty;
					
					// Provincia del cantón seleccionado
					$http.get('rest/protected/counties/getCountyById/'+ 
							$scope.requestObject.county.idCounty)
					.success(function(response) {
						$scope.requestObject.province = response.county.tprovince;
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
			
			$http.get('rest/protected/benefits/getAll', $scope.requestObject)
			.success(function(benefitsResponse) {
				self.benefitsList = benefitsResponse.benefits;
			});
		}
		
		$scope.init();
		
		$scope.deletePropertyImage = function(pidPropertyImage) {
			var data = $.param({
				imageId: pidPropertyImage,
			});
			var idx = $scope.imageList.indexOf(pidPropertyImage);
			if (idx == -1) { $scope.imageList.splice(idx, 1) };
			$http["delete"]('rest/protected/properties/deleteImage?' + data);
		}
		
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
		
		$scope.onChangeProvince = function() {
			$scope.countyList = [];
			$scope.districtList = [];
			
			$http.get('rest/protected/counties/getAll')
			.success(function(countyResponse) {
				for(var i = 0; i < countyResponse.counties.length; i++) {
					if(countyResponse.counties[i].tprovince.idProvince === $scope.requestObject.province.idProvince){
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
					if(districtResponse.districts[i].tcounty.idCounty === $scope.requestObject.county.idCounty){
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
		$scope.canSubmit = function(imgList, length) {
			return this.propertiesForm.$valid && imgList > 0 || this.propertiesForm.$valid && length > 0
					&& !angular.equals($scope.requestObject, original);
		};
		$scope.updateProperty = function(event) {
			$scope.updateProperty(event, $files);
		};
		$scope.canAddImg = function(length) {
			return length >= 5;
		};
		
		// Routes to list view on cancel
		$scope.cancel = function() {
			$state.go('templates/propertiesView/myPropertiesView', {},  {reload: true});
		}
		
		$scope.updateProperty = function(event, $files) {
			if(this.propertiesForm.$valid) {
				$scope.onError = false;
				var request = {
						"pageNumber": 0,
						"pageSize": 0,
						"direction": "",
						"sortBy": [""],
						"searchColumn": "string",
						"searchTerm": "",
						"property": {
							"squareMeters": $scope.property.squareMeters,
							"price": $scope.property.price,
							"tdistrict": { "idDisctrict": $scope.requestObject.district.idDisctrict},
							"tpropertyType": { "idPropertyType": $scope.selectedType.idPropertyType},
							"tbenefits": $scope.property.tbenefits,
							"address": $scope.property.address,
							"coordinates": $scope.markerLoc
						}
				};

				$http.put('rest/protected/properties/update/' + localStorage.getItem('idProperty'), request)
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
						$state.go('templates/propertiesView/myPropertiesView', {},  {reload: true});
				});
				  
			}
		}
		$scope.removeOffer = function (){
			var offerRequest = {
          		  "pageNumber": 0,
          		  "pageSize": 0,
          		  "direction": "string",
          		  "sortBy": [
          		    "string"
          		  ],
          		  "searchColumn": "string",
          		  "searchTerm": "string",
          		  "property": {"idProperty":localStorage.getItem('idProperty'),
          			  "offerPercentage":0},
          		  "idBenefits": [
          		    0
          		  ]
          }
          		
          $http.post('rest/protected/properties/setPropertyOnSale',offerRequest).success(function(){
       		
          	$scope.hasAnOffer = false;
          });
            
		}
		
	
	        $scope.open = function (size) {
	        	localStorage.setItem('propertyPrice',$scope.property.price);
	            var modalInstance = $uibModal.open({
	                animation: $scope.animationsEnabled,
	                templateUrl: 'offerModal.html',
	                controller: 'OfferModalCtrl',
	                size: size,
	                resolve: {
	                    price: function () {
	                      
	                    }
	                }
	            });

	            modalInstance.result.then(function (selectedItem) {
	            
	                $scope.selected = selectedItem;
	            }, function (responseFromModal) {
	            	if(responseFromModal==='hasAnOffer')
	            		$scope.hasAnOffer = true;
	              
	            });
	        };

	        $scope.toggleAnimation = function () {
	            $scope.animationsEnabled = !$scope.animationsEnabled;
	        };  
	
	};
	
	function OfferModalCtrl($scope,$uibModalInstance,$http,$timeout){
        var modalOriginal;
      
        $scope.price
        $scope.offerForm = { 	
	        offer: '',
	        originalPrice: localStorage.getItem('propertyPrice'),
	       
	    };
        $scope.cancel = function() {
        
            $uibModalInstance.dismiss("cancel");
        };

        modalOriginal = angular.copy($scope.offerForm);

        $scope.canSubmit = function() {
	        return $scope.form_offerCreate.$valid && !angular.equals($scope.offerForm, modalOriginal);
	    };

	    $scope.saveOffer = function() {
            var offerRequest = {
            		  "pageNumber": 0,
            		  "pageSize": 0,
            		  "direction": "string",
            		  "sortBy": [
            		    "string"
            		  ],
            		  "searchColumn": "string",
            		  "searchTerm": "string",
            		  "property": {"idProperty":localStorage.getItem('idProperty'),
            			  "offerPercentage":$scope.offerForm.offer},
            		  "idBenefits": [
            		    0
            		  ]
            }
            		
            $http.post('rest/protected/properties/setPropertyOnSale',offerRequest).success(function(){
            	$scope.showInfoOnSubmit= true;
				  $timeout(function(){
			          $scope.showInfoOnSubmit = false;
			          $uibModalInstance.dismiss("hasAnOffer");
			       }, 1500);
            	
            });
	    	
	    };
	    
	    $scope.calculateOffer = function(){
	    	var porcentage = $scope.offerForm.offer/100*$scope.offerForm.originalPrice;
	    	$scope.price = $scope.offerForm.originalPrice - porcentage;
	    }
    };
	
})();