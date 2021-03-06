(function(){
	'use strict';

	angular.module('app.testFlow', [])
	.controller('testFlowController',['$scope','$http', '$timeout', 'WizardHandler','$location','$rootScope',function($scope,$http, $timeout, WizardHandler,$location,$rootScope){

		//necesario para guardar las respuestas
		var userId = $rootScope.userLogged.idUser;

		//historial de preguntas respondidas
		var questionsAnswered = [];

		//comprobar si se acabo
		//var finished = false;

		//respuestas
		$scope.answers = [];
		var userAnswers = [];
		//preguntas
		var restLink = "rest/protected/questions/getquestionswithoptions";
		var request = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","question": {}};
		$http.post(restLink, request).success(function(response) {
			$scope.questions = response.questions;
		});
		
		function saveSurvey(){

			var newUserSurvey;
			var userSurvey =  {"tanswers": $scope.answers,"tuser": {"idUser":userId}};

			var saveLink = "rest/protected/usersurveys/create";
			var userSurveyRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": userSurvey};
			
			$http.post(saveLink, userSurveyRequest).success(function(response) {
			 	newUserSurvey = response.userSurveys[0];
			 	generateMatchResult(newUserSurvey);
			});
		};

		function generateMatchResult(newUserSurvey){

			var matchLink = "rest/protected/usersurveys/generatematchbysurvey";
			var userSurveyMatchResultRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": newUserSurvey};
			
			$http.post(matchLink, userSurveyMatchResultRequest).success(function(response) {
			 	$rootScope.matchedPropertiesList = response;
			 	$location.url("templates/testView/matchedPropertiesList");
			});
		};


		$scope.catchAnswer = function(option, idQuestion){

			if(option.result != ""){
				if(!answerAlreadyExist(option, idQuestion)){
					$scope.answers.push({ "result":option.result, "tquestion": {"idQuestion":idQuestion} });
				}
			}else{
				answerAlreadyExist("delete", idQuestion)

			}			
			getNextQuestion(option.idNextQuestion);

		};	

		function getNextQuestion(pidNextQuestion){
			if(pidNextQuestion == 0){
					saveSurvey();
			}else{
				var steps = WizardHandler.wizard().getEnabledSteps();
				for (var i = WizardHandler.wizard().currentStepNumber() ; i < $scope.questions.length; i++) {

					if($scope.questions[i].idQuestion ==pidNextQuestion){
						WizardHandler.wizard().currentStep().completed = true;
						WizardHandler.wizard().goTo(i);
						break;
					}
					steps[i].completed = false;
					answerAlreadyExist("delete",steps[i].title);
				}
			}

		}

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

	}]);
	
})();