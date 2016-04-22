(function(){
	'use strict';

	angular.module('app.testFlow', [])
	.controller('testFlowController',['$scope','$http', '$timeout', 'WizardHandler','$location','$rootScope',function($scope,$http, $timeout, WizardHandler,$location,$rootScope){

		//cuestionario creado
		var newUserSurvey;

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

			var userSurvey =  {"tanswers": $scope.answers,"tuser": {"idUser":userId}};

			var saveLink = "rest/protected/usersurveys/create";
			var userSurveyRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": userSurvey};
			
			$http.post(saveLink, userSurveyRequest).success(function(response) {
			 	newUserSurvey = response.userSurveys[0];
			 	generateMatchResult();
			});
		};

		function generateMatchResult(){
			//guarda el cuestionario recien creado
			$rootScope.userSurvey = newUserSurvey;
			localStorage.setItem("idSurvey",newUserSurvey.idSurvey);
			console.log($scope.answers);
			// var matchLink = "rest/protected/usersurveys/generatematchbysurvey";
			// var userSurveyMatchResultRequest = {"pageNumber": 0,"pageSize": 0,"direction": "","sortBy": [""],"searchColumn": "","searchTerm": "","userSurvey": newUserSurvey};
			
			// $http.post(matchLink, userSurveyMatchResultRequest).success(function(response) {
			//  	$rootScope.matchedPropertiesList = response;
			 	$location.url("templates/testView/matchedPropertiesList");
			// });
		};


		$scope.catchAnswer = function(option, idQuestion){

			if(option.result != null){
				if(!answerAlreadyExist(option, idQuestion)){
					var tags = calculateCantAnswersByOption(option);
					for (var i = 0; i < tags.length; i++) {
						$scope.answers.push({ "result":tags[i], "tquestion": {"idQuestion":idQuestion} });
					}
				}
			}else{
				answerAlreadyExist("delete", idQuestion)
			}
			getNextQuestion(option.idNextQuestion);
        	console.log($scope.answers);
        	isThereAPrevious();
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
	       	isThereAPrevious();
		};


        //wizard

        $scope.canExit = false;
        $scope.stepActive = false;
        $scope.isPrevious = true;
        //$scope.disabled = 'true';


        function calculateCantAnswersByOption(option){
        	var optionCharArray = option.result.split('');
        	var optionsResult = [''];
        	// console.log(option.result)
        	// console.log(optionCharArray);
        	var isThereAComa = false;
        	
        	var optionsResultIndex = 0;
        	for (var i = 0; i < optionCharArray.length; i++) {

        		if(optionCharArray[i] == ','){
        			isThereAComa = true;
        		}else{
        			isThereAComa = false;
        		}

        		if(!isThereAComa){
        			optionsResult[optionsResultIndex] += optionCharArray[i];
        		}else{
        			optionsResultIndex++;
        			optionsResult[optionsResultIndex] = '';
        		}

        	}

        	return optionsResult;
        };

        $scope.previous = function(){
			WizardHandler.wizard().previous();
			isThereAPrevious();
        };

        function isThereAPrevious(){
        	if(WizardHandler.wizard().currentStepNumber() == 0){
        		$scope.isPrevious = true;
        	}else{
        		$scope.isPrevious = false;
        	}
        };

	}]);
	
})();