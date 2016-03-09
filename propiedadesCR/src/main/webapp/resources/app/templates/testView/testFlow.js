(function(){
	'use strict';

	angular.module('app.testFlow', [])
	.controller('testFlowController',['$scope','$http', '$timeout', 'WizardHandler',function($scope,$http, $timeout, WizardHandler){

		//necesario para guardar las respuestas
		var userId;

		//respuestas
		$scope.answers = [];
		
		//preguntas
		$scope.questions = 
		[{"id":1,"question":"Que?","options":[{
			"id":1,
			"option":"si",
			"result":"yep",
			"id_next_question":2},

			{"id":2,"option":"no",
			"result":"nope",
			"id_next_question":4
		}]},
		
		{"id":2,"question":"Como?","options":[{
			"id":3,
			"option":"tal vez",
			"result":"maybe",
			"id_next_question":3},
			{"id":4,
			"option":"puede ser",
			"result":"could be",
			"id_next_question":4
		}]},
		
		{"id":3,"question":"Por que?","options":[{
			"id":5,
			"option":"oww",
			"result":"miawww",
			"id_next_question":4},
			{"id":6,
			"option":"yes",
			"result":"zaaa",
			"id_next_question":4
		}]},

		{"id":4,"question":"is that fair?","options":[{
			"id":7,
			"option":"thats it",
			"result":"it it it",
			"id_next_question":0},
			{"id":8,
			"option":"trouble?",
			"result":"quero ser feliz tambem",
			"id_next_question":0
		}]}

		];//["como?","que?","cuando?","donde?"];
		

		$scope.catchAnswer = function(option, idQuestion){
			$scope.answers.push({id_question:idQuestion, "result":option.result});
			//console.log(option);
			console.log($scope.answers);
			getNextQuestion(option.id_next_question);
		};	

		function getNextQuestion(pidNextQuestion){
			//console.log(pidNextQuestion);

			for (var i = WizardHandler.wizard().currentStepNumber() - 1 ; i < $scope.questions.length; i++) {

				if($scope.questions[i].id==pidNextQuestion){
					//console.log($scope.questions[i]);
					WizardHandler.wizard().goTo(i);
					//WizardHandler.wizard().next();
					break;
				}
			}
		}

		$scope.resetAnswers = function(){
			$scope.answers = [];
		};


        //wizard

        $scope.canExit = false;
        $scope.stepActive = true;

        $scope.finished = function() {
            console.log("Wizard finished :)");
        };

        $scope.logStep = function() {
            console.log("Step continued");
        };

        // $scope.goBack = function() {
        //     WizardHandler.wizard().goTo(0);
        // };

        // $scope.exitWithAPromise = function() {
        //     var d = $q.defer();
        //     $timeout(function() {
        //         d.resolve(true);
        //         console.log($q);
        //     }, 1000);
        //     return d.promise;
        // };
        
        // $scope.exitToggle = function() {
        //     $scope.canExit = !$scope.canExit;
        // };
        // $scope.stepToggle = function() {
        //     $scope.stepActive = !$scope.stepActive;
        // }
        // $scope.exitValidation = function() {
        //     return $scope.canExit;
        // };

        // $scope.firstQuestion = function(){
        // 	if(WizardHandler.wizard().currentStepNumber()==1)
        // 		return true;
        // 	else
        // 		return false;
        // };

	}]);
	
})();