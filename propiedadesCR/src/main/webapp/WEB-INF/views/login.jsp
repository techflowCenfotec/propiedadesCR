<!doctype html>
<!--[if lt IE 8]>         <html class="no-js lt-ie8"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Propiedades CR - Soluciones de Vivienda</title>

<!-- Needs images, font... therefore can not be part of main.css -->
<link rel="stylesheet" href="resources/styles/loader.css">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,100,500,700,300,300italic,500italic|Roboto+Condensed:400,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="resources/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="resources/bower_components/material-design-iconic-font/css/material-design-iconic-font.min.css">

<!-- end Needs images -->

<link rel="stylesheet" href="resources/styles/main.css">
<link rel="stylesheet" href="resources/styles/carousel.css">

</head>
<body data-ng-app="app" id="app" class="app" data-custom-page
	data-ng-controller="AppCtrl"
	data-ng-class=" { 'layout-boxed': main.layout === 'boxed', 
                            'nav-collapsed-min': main.isMenuCollapsed
          } ">
	<!--[if lt IE 9]>
            <div class="lt-ie9-bg">
                <p class="browsehappy">You are using an <strong>outdated</strong> browser.</p>
                <p>Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            </div>
        <![endif]-->
	<div id="loader-container"></div>




	<div id="content" class="content-container">
		<section data-ui-view
			class="view-container {{main.pageTransition.class}}"></section>
	</div>


	<script src="http://maps.google.com/maps/api/js"></script>

	<!--Core JS-->
	<script src="resources/scripts/vendor.js"></script>
	
	<script src="resources/scripts/ui.js"></script>
	<script src="resources/scripts/appLogin.js"></script>
	<script src="resources/scripts/appLogin.route.js"></script>
	<script src="resources/bower_components/underscore/underscore.js"></script>

	<!-- Scripts-->
	<script
		src="resources/non_bower_components/angular-file-upload-shim.min.js"></script>
	<script src="resources/non_bower_components/angular-file-upload.min.js"></script>
	<script src="resources/non_bower_components/ng-flow-standalone.min.js "></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	<!--Custom JS files-->
	<script src="resources/app/templates/loginView/login.js"></script>
	<script
		src="resources/app/templates/resetPasswordView/forgotPassword.js"></script>
	<script
		src="resources/app/templates/resetPasswordView/resetPassword.js"></script>
	<script src="resources/app/templates/usersView/createUserLogin.js"></script>
	<script src="resources/app/templates/landingPageView/landingPage.js"></script>
	<script src="resources/scripts/services/dbService.js"></script>
	<script src="resources/scripts/services/sessionService.js"></script>
	