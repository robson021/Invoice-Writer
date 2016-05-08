(function () {
    "use strict";
    angular.module("ngApp")
        .controller("main-app-ctr", function ($rootScope, $scope) {

            // day of selling
            $scope.sellDate = new Date();
            $scope.minDateSell = new Date(
                $scope.sellDate.getFullYear(),
                $scope.sellDate.getMonth() - 2,
                $scope.sellDate.getDate());
            $scope.maxDateSell = new Date(
                $scope.sellDate.getFullYear(),
                $scope.sellDate.getMonth() + 2,
                $scope.sellDate.getDate());
            /*$scope.onlyWeekendsPredicate = function(date) {
             var day = date.getDay();
             return day === 0 || day === 6;
             }*/

            // the day when job was done
            $scope.deadDate = new Date();
            $scope.minDateDead = new Date(
                $scope.deadDate.getFullYear(),
                $scope.deadDate.getMonth() - 2,
                $scope.deadDate.getDate());
            $scope.maxDateDead = new Date(
                $scope.deadDate.getFullYear(),
                $scope.deadDate.getMonth() + 2,
                $scope.deadDate.getDate());


            $scope.testFun = function () {
                console.info($scope.sellDate);
                console.info($scope.deadDate);
            }

        }); // end of ctrl
})();