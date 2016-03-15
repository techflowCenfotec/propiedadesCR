<!doctype html>
<!--[if lt IE 8]>         <html class="no-js lt-ie8"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv='X-UA-Compatible' content='IE=edge'>
        <title>Propiedades CR - Soluciones de Vivienda</title>

        <!-- Needs images, font... therefore can not be part of main.css -->
        <link rel="stylesheet" href="resources/styles/loader.css">
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,500,700,300,300italic,500italic|Roboto+Condensed:400,300' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="resources/bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="resources/bower_components/material-design-iconic-font/css/material-design-iconic-font.min.css">
        <!-- end Needs images -->
        
             <link rel="stylesheet" href="resources/styles/main.css">

    </head>
    <body data-ng-app="app"
          id="app"
          class="app"
          data-custom-page 
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
        
       

        <header data-ng-include=" 'resources/app/layout/header.html' "
                 id="header"
                 class="header-container "
                 data-ng-class="{ 'header-fixed': main.fixedHeader,
                                  'bg-white': ['11','12','13','14','15','16','21'].indexOf(main.skin) >= 0,
                                  'bg-dark': main.skin === '31',
                                  'bg-primary': ['22','32'].indexOf(main.skin) >= 0,
                                  'bg-success': ['23','33'].indexOf(main.skin) >= 0,
                                  'bg-info': ['24','34'].indexOf(main.skin) >= 0,
                                  'bg-warning': ['25','35'].indexOf(main.skin) >= 0,
                                  'bg-danger': ['26','36'].indexOf(main.skin) >= 0
                 }"></header>

        <div class="main-container"
             data-ng-class="{ 'app-nav-horizontal': main.menu === 'horizontal' }">
            <aside data-ng-include=" 'resources/app/layout/sidebar.html' "
                   id="nav-container"
                   class="nav-container"  
                   data-ng-class="{ 'nav-fixed': main.fixedSidebar,
                                    'nav-horizontal': main.menu === 'horizontal',
                                    'nav-vertical': main.menu === 'vertical',
                                    'bg-white': ['31','32','33','34','35','36'].indexOf(main.skin) >= 0,
                                    'bg-dark': ['31','32','33','34','35','36'].indexOf(main.skin) < 0
                   }">
            </aside>
       
            <div id="content" class="content-container">
                <section data-ui-view
                         class="view-container {{main.pageTransition.class}}"></section>
            </div>
        </div>


        <script src="http://maps.google.com/maps/api/js"></script>

          <!--Core JS-->
        <script src="resources/scripts/vendor.js"></script>
        <script src="resources/scripts/ui.js"></script>
        <script src="resources/scripts/app.js"></script>
        <script src="resources/scripts/app.route.js"></script>
        <script src="resources/non_bower_components/angular-file-upload-shim.min.js"></script>
  		<script src="resources/non_bower_components/angular-file-upload.min.js"></script>
  		<script src="resources/non_bower_components/ng-flow-standalone.min.js "></script>

        <!--Custom JS files-->
        
        <script src="resources/app/templates/homeView/home.js"></script>
        
        <script src="resources/app/templates/propertiesView/propertiesList.js"></script>
 		<script src="resources/app/templates/propertiesView/propertiesCreate.js"></script>
 		
        <script src="resources/app/templates/usersView/usersList.js"></script>
        <script src="resources/app/templates/usersView/createUser.js"></script>
        <script src="resources/app/templates/usersView/consultUser.js"></script>
        <script src="resources/app/templates/usersView/modifyUser.js"></script>
           
        <script src="resources/app/templates/eventsView/createEvent.js"></script>
        <script src= "resources/app/templates/eventsView/eventsList.js"></script>
	    <script src= "resources/app/templates/eventsView/eventConsult.js"></script>

        <script src="resources/app/templates/roleView/roles.js"></script>
        <script src="resources/app/templates/roleView/addRoles.js"></script>
        
        <script src="resources/app/templates/banktodolistView/banktodolist.js"></script>
        <script src="resources/app/templates/banktodolistView/banktodolistCreate.js"></script>


