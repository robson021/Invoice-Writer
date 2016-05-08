(function () {
    "use strict";
    angular.module("ngApp")
        .controller("main-app-ctr", function ($rootScope, $scope) {


            $scope.testText = "test text binded to model";

        });
})();