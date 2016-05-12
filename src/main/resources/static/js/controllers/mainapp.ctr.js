(function () {
    "use strict";
    angular.module("ngApp")
        .controller("main-app-ctr", function ($rootScope, $scope, $state) {

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


            $scope.goContractors = function () {
                //console.info("contractors cliced");
                $state.go('contractors');
                //console.info($rootScope.dbData);
            }

            $scope.goSalesmen = function () {
                $state.go('salesmen');
            }

            $scope.goServices = function () {
                $state.go('services');
            }

        }); // end of ctrl
})();