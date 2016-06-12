angular
    .module("ngApp", ['ngMaterial', 'ui.router', 'ngMessages'])
    .config(function ($stateProvider, $urlRouterProvider) {


        $stateProvider
            .state('default', {
                url: '/',
                template: null
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
            .state('test', {  // todo disable on production
                url: '/test',
                templateUrl: '/partials/mainapp.tpl.html',
                controller: 'main-app-ctr'
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
            })
            .state('contractors', {
                url: '/contractors',
                templateUrl: '/partials/data/contractors.tpl.html',
                controller: 'data-ctr'
            })
            .state('salesmen', {
                url: '/salesmen',
                templateUrl: '/partials/data/salesmen.tpl.html',
                controller: 'data-ctr'
            })
            .state('services', {
                url: '/services',
                templateUrl: '/partials/data/services.tpl.html',
                controller: 'data-ctr'
            })
            .state('uplad', {
                url: '/upload',
                templateUrl: '/partials/data/img-uploader.tpl.html',
                controller: 'data-ctr'
            })
            .state('before-use', {
                url: '/before-use',
                templateUrl: '/partials/before-use.tpl.html',
                controller: 'other-views-ctr'
            }); // end of state provider

        $urlRouterProvider.otherwise("/");

    }); //end of config


// global data
angular.module('ngApp')
    .run(function ($rootScope) {
        var r = $rootScope;
        //r.rootTest = new Date();
        r.dbData = null;
        //r.isMainViewHidden = true;
        r.isLoggedIn = false;
        r.loginButtonEnabled = true;
        r.token = null;
    });