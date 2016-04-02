
(function() {
	'use strict';
	
	angular.module('app.properties.view', [])
	
	.controller('PropViewController', ['$scope', '$http', '$rootScope', PropViewController])
    .controller('ModalDemoCtrl', ['$scope', '$uibModal', '$log', ModalDemoCtrl])
    .controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', ModalInstanceCtrl]);

	function PropViewController($scope, $http, $rootScope) {
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
		// Rating data
		//$scope.rate = 3;
        $scope.max = 5;
        $scope.isReadonly = false;
        $scope.popUpVisible = false;
        $scope.isRateVisible = true;
        var ratingId = 0;
        var userLoggerId= 0;
        var userLoggedObj = {};
        var userToNotify = {};
        var userLoggedLink = 'rest/protected/users/getUserLogged';
        var getIdProperty = localStorage.getItem('idProperty');
        var linkById = 'rest/protected/rating/getRatingById';
        var ratingRequest= {
         		"pageNumber": 0,
   				"pageSize": 0,
   				"direction": "string",
   				"sortBy": [
   				"string"
   				],
   				"searchColumn": "string",
   				"searchTerm": "string",
   				"rating": {"tproperty": {"idProperty":getIdProperty}}
		    };

		
		$http.post(linkById, ratingRequest)
        .success(function(response) {
         	$scope.rate = response.rating.averageRating;
         	$scope.isReadonly = false;
         	ratingId = response.rating.idRating;
        });  

        $http.get(userLoggedLink)
        .success(function(response) {
    		userLoggedObj = response.user;
    		userLoggerId = userLoggedObj.idUser;
        });  


        $scope.hoveringOver = function(value) {
            $scope.overStar = value;
            return $scope.overStar;
        };
        
		$scope.init = function() {
			var bd = 'rest/protected/properties/getByPropertyId/' + +localStorage.getItem('idProperty');
			$http.get(bd)
			.success(function(response) {
				$scope.property = response.property;
				$scope.imageList = response.property.tpropertyImages;
				$scope.selectedType = $scope.property.tpropertyType;
				$scope.selectedDistrict = $scope.property.tdistrict;
				$scope.userToNotify = response.tusers;
				
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
		};
		
		$scope.init();
		
		$scope.onChangeProvince = function() {
			$scope.countyList = [];
			
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

		$scope.propertyRate = function(value) {
			console.log(value);
			// Cambiar id user al userLogged
        	var bd = 'rest/protected/rating/addRating';
        	var data = {
        			  "pageNumber": 0,
        			  "pageSize": 0,
        			  "direction": "string",
        			  "sortBy": [
        			    "string"
        			  ],
        			  "searchColumn": "string",
        			  "searchTerm": "string",
        			  "rating": {
        				  "tuser": { "idUser": $rootScope.userLogged.idUser},
        				  "tproperty": { "idProperty": localStorage.getItem('idProperty')},
        				  "averageRating": $scope.rate
        			  }
        			};
        	
        	$http.post(bd, data)
        	.success(function(response) {
        		$scope.isReadonly = true;
        		});

			}else{
				$scope.isRateVisible = false;
				$scope.popUpVisible = true;
				console.log(getIdProperty);
			}
			
        };

		$scope.editRating = function (){
			// Cambiar id user al userLogged
			$scope.isReadonly = false;
			var linkEditRate = 'rest/protected/rating/editRating';
			var dataEdit = {
  				"pageNumber": 0,
  				"pageSize": 0,
 	 			"direction": "string",
  				"sortBy": [
    			"string"
  				],
  				"searchColumn": "string",
  				"searchTerm": "string",
  				"rating": {"idRating": ratingId,
      				"averageRating": $scope.rate,
      				"tproperty": {"idProperty":getIdProperty},
      				"tuser": {"idUser": userLoggerId}}
			}

			$http.post(linkEditRate, dataEdit)
        	.success(function(response) {
         		//$scope.rate = response.rating.averageRating;
         		console.log("successfully");
         		$scope.isReadonly = false;
		 		
        	}); 

        	$scope.popUpVisible = false;
        	$scope.isRateVisible = true;
        };

        $scope.reportVendor = function(){
        	var link = 'rest/protected/AdminEmail/sendEmail'
        	$http.post(link, userToNotify)
        	.success(function(response) {
        		console.log(response);
        	}); 
        	$mdDialog.show(
	            $mdDialog.alert()
	                .parent(angular.element(document.querySelector('#popupContainer')))
	                .clickOutsideToClose(true)
	                .title('Reporte de vendedor')
	                .content('Lamentamos su inconveniente '+
	                    		'Hemos reportado el usuario en el sistema.')
	                .ok('Aceptar')
	            );
        };

        $scope.show = function(){
        	$scope.popUpVisible=false;
        	$scope.isRateVisible = true;
        };



	};

	function ModalDemoCtrl($scope, $uibModal, $log) {
        $scope.items = ['item1', 'item2', 'item3'];

        $scope.animationsEnabled = true;

        $scope.open = function (size) {

            var modalInstance = $uibModal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'myModalContent.html',
                controller: 'ModalInstanceCtrl',
                size: size,
                resolve: {
                    items: function () {
                        return $scope.items;
                    }
                }
            });

            modalInstance.result.then(function (selectedItem) {
                $scope.selected = selectedItem;
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        };
    }

    function ModalInstanceCtrl($scope, $uibModalInstance) {
        var original;
        $scope.form = { 	
	        amount: '',
	        minAmount: '',
	        quota: '',
	        financingAmount:'',
	        netFamilyIncome:''
	    };

        $scope.cancel = function() {
            $uibModalInstance.dismiss("cancel");
        };

        original = angular.copy($scope.form);
        $scope.canSubmit = function() {
	        return $scope.form_banktodolistCreate.$valid && !angular.equals($scope.form, original);
	    };

	    $scope.calculate = function() {
		
		/* variables:*/
			var coin // moneda
			var interestRate // tasa de interes
			var months // meses
			var amount // monto
			var futureValue // futuro valor (siempre en 0 )
			var prima // primaima
			var commission // comision
			var totalAmount // total monto
			var familyIncome // ingreso familiar
			var payment // payment
			var financingAmount // monto a financiar

	    	/*seteo de valores basicos*/

			coin='$'
			interestRate= 0.0060
			months= 300
			amount= $scope.form.amount
			futureValue= 0
			prima= amount*0.2
			commission= amount*0.015
			financingAmount= amount-prima;
			totalAmount= (amount-prima)+commission;
			
			//payment= ( interestRate * ( totalAmount * Math.pow ( (interestRate+1), months ) + futureValue ) ) / ( ( interestRate + 1 ) * ( Math.pow ( (interestRate+1), months) -1 ) );
			
			payment= (-(totalAmount) + (-(totalAmount) / ((Math.pow(1+interestRate,360)) - 1))) * -interestRate;
			
			if((payment/0.5)<1000){ familyIncome=1000 }
			else{ familyIncome=(payment/0.5) };
	       
			$scope.form.minAmount = coin+prima;
			$scope.form.financingAmount = coin+financingAmount;
			$scope.form.quota = coin+(payment = Math.round(payment * 100) / 100);
			$scope.form.netFamilyIncome = coin+(familyIncome = Math.round(familyIncome * 100) / 100);
			
	    };

    };
	
})();
