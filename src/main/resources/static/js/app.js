angular
    .module("ngApp", ['ngMaterial', 'ui.router'])
    .config(function ($stateProvider) {


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
            .state('logged-in', {
                url: '/logged-in',
                templateUrl: 'mainapp.tpl.html'
            })
            .state('about-app', {
                url: '/about-app',
                templateUrl: 'about-app.tpl.html',
                controller: 'toolbar.ctr.js'
            })
            .state('about-author', {
                url: '/about-author',
                templateUrl: 'about-author.tpl.html',
                controller: 'toolbar-ctr'
            }); // end of state provider

    }); //end of config

