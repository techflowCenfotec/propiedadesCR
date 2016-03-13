(function(){
	'use strict';

	angular.module('app.testFlow', [])
	.controller('testFlowController',['$scope','$http', '$timeout', 'WizardHandler',function($scope,$http, $timeout, WizardHandler){

		//necesario para guardar las respuestas
		var userId = 1;

		//historial de preguntas respondidas
		var questionsAnswered = [];

		//comprobar si se acabo
		//var finished = false;

		//respuestas
		$scope.answers = [];
		var userAnswers = [];
		//preguntas
		var restLink = "rest/protected/questions/getQuestionsWithOptions";
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","question": {}};
		$http.post(restLink, request).success(function(response) {
			$scope.questions = response.questions;
			console.log(response);
		});
		
		function saveSurvey(){
			var userSurvey =  {"tanswers": $scope.answers,"tuser": {"idUsuario":userId}};

			var saveLink = "rest/protected/usersurveys/create";
			var userSurveyRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": userSurvey};
			
			$http.post(saveLink, userSurveyRequest).success(function(response) {
				//$scope.questions = response.questions;
				console.log(response);
			});
		};

		$scope.generateMatchResult = function(){

		};

		// [{"id":1,"question":"Que?","options":[{
		// 	"id":1,
		// 	"option":"si",
		// 	"result":"yep",
		// 	"id_next_question":2},

		// 	{"id":2,
		// 	"option":"no",
		// 	"result":"nope",
		// 	"id_next_question":4
		// }]},
		
		// {"id":2,"question":"Como?","options":[{
		// 	"id":3,
		// 	"option":"tal vez",
		// 	"result":"maybe",
		// 	"id_next_question":3},
		// 	{"id":4,
		// 	"option":"puede ser",
		// 	"result":"could be",
		// 	"id_next_question":4},
		// 	{"id":9,
		// 	"option":"damn",
		// 	"result":"",
		// 	"id_next_question":3
		// }]},
		
		// {"id":3,"question":"Por que?","options":[{
		// 	"id":5,
		// 	"option":"oww",
		// 	"result":"miawww",
		// 	"id_next_question":4},
		// 	{"id":6,
		// 	"option":"yes",
		// 	"result":"zaaa",
		// 	"id_next_question":4
		// }]},

		// {"id":4,"question":"is that fair?","options":[{
		// 	"id":7,
		// 	"option":"thats it",
		// 	"result":"",
		// 	"id_next_question":0},
		// 	{"id":8,
		// 	"option":"trouble?",
		// 	"result":"quero ser feliz tambem",
		// 	"id_next_question":0
		// }]}

		// ];
		//["como?","que?","cuando?","donde?"];

		$scope.catchAnswer = function(option, idQuestion){
		////SEGUIR AQUI!!!!
		//var steps = WizardHandler.wizard().getEnabledSteps();	
		//console.log(steps);
		//console.log(steps[0]);
		//console.log(steps[0].$id);
		//console.log(steps[3].completed = true);

			if(option.result != ""){
				if(!answerAlreadyExist(option, idQuestion)){
					$scope.answers.push({ "result":option.result, "tquestion": {"idQuestion":idQuestion} });
					//userAnswers.push({"result":option.result, "tquestion": {"idQuestion":idQuestion,}});
				}
			}else{
				answerAlreadyExist("delete", idQuestion)

			}
			console.log($scope.answers);
			
			getNextQuestion(option.idNextQuestion);

			//$scope.questions.push([]);
		};	

		function isFinished(pidNextQuestion){
				if(pidNextQuestion == 0)
					//redireccionar al listado de propiedades con match
				console.log("finish him!!!")
		};

		function getNextQuestion(pidNextQuestion){
			console.log(pidNextQuestion);
			if(pidNextQuestion == 0){
					console.log("finish him!!!");
					saveSurvey();
			}else{
				var steps = WizardHandler.wizard().getEnabledSteps();
				for (var i = WizardHandler.wizard().currentStepNumber() ; i < $scope.questions.length; i++) {
					//console.log(WizardHandler.wizard().currentStepNumber());
					//console.log(question[i].tquestion);

					if($scope.questions[i].idQuestion ==pidNextQuestion){
						//console.log($scope.questions[i]);
						WizardHandler.wizard().currentStep().completed = true;
						WizardHandler.wizard().goTo(i);
						//WizardHandler.wizard().next();
						break;
					}
					steps[i].completed = false;
					answerAlreadyExist("delete",steps[i].title);
				}
			}

		}

		//comprobar si el usuario va cambiar alguna respuesta
		function answerAlreadyExist(option, idQuestion){
			if(option != "delete"){
				for (var i = 0; i < $scope.answers.length; i++) {
					if($scope.answers[i].tquestion.idQuestion == idQuestion){
						$scope.answers[i] = { "result":option.result, "tquestion": {"idQuestion":idQuestion} };
						return true;
					}
				}
			}else{
				for (var i = 0; i < $scope.answers.length; i++) {
					if($scope.answers[i].tquestion.idQuestion == idQuestion){
						$scope.answers.splice(i,1);
						return true;
					}
				}
			}
			return false;
		};

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