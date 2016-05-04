angular
    .module("ngApp", ['ngMaterial', 'ui.router'])
    .config(function ($stateProvider) {


        $stateProvider
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
            }); // end of state provider

    }); //end of config

