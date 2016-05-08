angular
    .module("ngApp", ['ngMaterial', 'ui.router'])
    .config(function ($stateProvider) {


        $stateProvider
            .state('default', {
                url: '/',
                template: null
                //templateUrl: '/partials/mainapp.tpl.html',
                //controller: 'main-app-ctr'
            })
            .state('t1', {
                url: '/t1',
                template: '<h1>test 1</h1>'
            })
            .state('t2', {
                url: '/t2',
                template: '<h1>test 2</h1>'
            })
            .state('t3', {
                url: '/t3',
                templateUrl: 'test.tpl.html'
            })
            .state('logged-in', {
                url: '/logged-in',
                templateUrl: '/partials/mainapp.tpl.html',
                controller: 'main-app-ctr'
            })
            .state('about-app', {
                url: '/about-app',
                templateUrl: '/partials/about-app.tpl.html',
                controller: 'toolbar-ctr'
            })
            .state('about-author', {
                url: '/about-author',
                templateUrl: '/partials/about-author.tpl.html',
                controller: 'toolbar-ctr'
            }); // end of state provider

    }); //end of config


// global data
angular.module('ngApp')
    .run(function ($rootScope) {
        var r = $rootScope;
        r.rootTest = new Date();
        r.dbData = null;
        //r.isMainViewHidden = true;
        r.isLoggedIn = false;
    });