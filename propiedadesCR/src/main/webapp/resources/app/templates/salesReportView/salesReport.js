(function(){
	'use strict';

	angular.module('app.salesReport', ['ngecharts'])
	.controller('salesReportController',['$scope','$http','$timeout','$rootScope',function($scope,$http,$timeout,$rootScope){
        // Build ECharts with Bar, Line, Pie, Radar, Scatter, Chord, Gauge, Funnel

        //llamar a backend para tener mis propiedades

		var monthTotals = [0,0,0,0,0,0,0,0,0,0,0,0];
		var propertiesSold = [0,0,0,0,0,0,0,0,0,0,0,0];
		var offersSold = [0,0,0,0,0,0,0,0,0,0,0,0];

        var properties = [
		    // {
		    //   // "squareMeters": 0,
		    //   // "address": "string",
		    //   "soldDate": new Date(1459760227000),
		    //   // "tuser": {},
		    //   // "saleType": "string",
		    //   // "coordinates": "string",
		    //   // "active": "string",
		    //   // "idProperty": 0,
		    //   "offerPecentage": 0,
		    //   // "tpropertyType": {},
		    //   "price": 25000,
		    //   "isSold": 1
		    // },
		    // {
		    //   // "squareMeters": 0,
		    //   // "address": "string",
		    //   "soldDate": new Date(),
		    //   // "tuser": {},
		    //   // "saleType": "string",
		    //   // "coordinates": "string",
		    //   // "active": "string",
		    //   // "idProperty": 0,
		    //   "offerPecentage": 0,
		    //   // "tpropertyType": {},
		    //   "price": 100000,
		    //   "isSold": 1
		    // }

  		];
      var soldProperties=[];

      var request = {
            "pageNumber": 0,
            "pageSize": 0,
            "direction": "",
            "sortBy": [""],
            "searchColumn": "string",
            "searchTerm": "",
            "property": {
              "tuser":{"idUser":$rootScope.userLogged.idUser}
            }
        };

        $http.post('rest/protected/properties/getPropertiesByIdVendor', request)
          .success(function(response) {
          properties = response.properties;
          // console.log(properties);
          for (var i = 0; i < properties.length; i++) {
            if(properties[i].isSold==1){
              properties[i].soldDate = new Date(properties[i].soldDate);
              soldProperties.push(properties[i]);
            }
          }
          // console.log(soldProperties);
          init();
        });


      function init(){
        calculateTotalSoldByMoth();
        calculatePropertiesSoldByMoth();
        $scope.chart.options = {
            title : {
                text: 'Ventas',
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['Propiedades vendidas','Ventas totales']
                //,'Ofertas vendidas']
            },
            toolbox: {
                show : true,
                feature : {
                    restore : {show: true, title: "restore"},
                    saveAsImage : {show: true, title: "save as image"}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Setiembre','Octubre','Noviembre','Diciembre']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                // {
                //     name:'Ofertas vendidas',
                //     type:'line',
                //     smooth:true,
                //     itemStyle: {normal: {areaStyle: {type: 'default'}}},
                //     data:[10, 12, 21, 54, 260, 830, 710]
                // },
                {
                    name:'Ventas totales',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:monthTotals
                },
                {
                    name:'Propiedades vendidas',
                    type:'line',
                    smooth:true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data:propertiesSold
                }
            ]
        };
      }
  		function calculateTotalSoldByMoth(){
  			var actualYear = new Date().getFullYear();
  			// console.log(soldProperties);

  			for (var i = 1; i < 13; i++) {
  				for (var j = 0; j < soldProperties.length; j++) {
  					if(soldProperties[j].soldDate.getMonth()==i && soldProperties[j].soldDate.getFullYear()==actualYear){
  						monthTotals[i-1] += soldProperties[j].price;
  					}
  				}
  			}
  			// console.log(monthTotals);
  		}

  		function calculatePropertiesSoldByMoth(){
  			var actualYear = new Date().getFullYear();

  			for (var i = 1; i < 13; i++) {
  				for (var j = 0; j < soldProperties.length; j++) {
  					if(soldProperties[j].soldDate.getMonth()==i && soldProperties[j].soldDate.getFullYear()==actualYear){
  						propertiesSold[i-1] += 1;
  					}
  				}
  			}
  			// console.log(propertiesSold);
  		}


















        $scope.chart = {};
        
        
       
    
	}]);
	
})();