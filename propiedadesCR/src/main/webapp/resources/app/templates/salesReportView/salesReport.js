(function(){
	'use strict';

	angular.module('app.salesReport', ['ngecharts'])
	.controller('salesReportController',['$scope','$http','$timeout',function($scope,$http,$timeout){
        // Build ECharts with Bar, Line, Pie, Radar, Scatter, Chord, Gauge, Funnel

        //llamar a backend para tener mis propiedades

		var monthTotals = [0,0,0,0,0,0,0,0,0,0,0,0];
		var propertiesSold = [0,0,0,0,0,0,0,0,0,0,0,0];
		var offersSold = [0,0,0,0,0,0,0,0,0,0,0,0];

        var properties = [
		    {
		      // "squareMeters": 0,
		      // "address": "string",
		      "soldDate": new Date(1459760227000),
		      // "tuser": {},
		      // "saleType": "string",
		      // "coordinates": "string",
		      // "active": "string",
		      // "idProperty": 0,
		      "offerPecentage": 0,
		      // "tpropertyType": {},
		      "price": 25000,
		      "isSold": 1
		    },
		    {
		      // "squareMeters": 0,
		      // "address": "string",
		      "soldDate": new Date(),
		      // "tuser": {},
		      // "saleType": "string",
		      // "coordinates": "string",
		      // "active": "string",
		      // "idProperty": 0,
		      "offerPecentage": 0,
		      // "tpropertyType": {},
		      "price": 100000,
		      "isSold": 1
		    }

  		];
  		console.log(properties)
  		//llamar metodos desde un init();
  		calculateTotalSoldByMoth();
  		calculatePropertiesSoldByMoth();

  		function calculateTotalSoldByMoth(){
  			var actualYear = new Date().getFullYear();
  			console.log(properties[0].soldDate.getMonth());
  			console.log(actualYear);

  			for (var i = 1; i < 13; i++) {
  				for (var j = 0; j < properties.length; j++) {
  					if(properties[j].soldDate.getMonth()==i && properties[j].soldDate.getFullYear()==actualYear){
  						monthTotals[i-1] += properties[j].price;
  					}
  				}
  			}
  			console.log(monthTotals);
  		}

  		function calculatePropertiesSoldByMoth(){
  			var actualYear = new Date().getFullYear();

  			for (var i = 1; i < 13; i++) {
  				for (var j = 0; j < properties.length; j++) {
  					if(properties[j].soldDate.getMonth()==i && properties[j].soldDate.getFullYear()==actualYear){
  						propertiesSold[i-1] += 1;
  					}
  				}
  			}
  			console.log(propertiesSold);
  		}


















        $scope.chart = {};
        
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
        
        
       
    
	}]);
	
})();