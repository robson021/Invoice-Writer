(function () {
    "use strict";
    angular.module("ngApp")
        .controller("main-app-ctr", function ($rootScope, $scope, $state, $timeout) {


            // selected right now
            $scope.salesman = null;
            $scope.contractor = null;
            $scope.service = null;

            // local arrays
            $scope.salesmen = null;
            $scope.contractors = $rootScope.dbData.contractors;
            $scope.services = null;

            $scope.getSelectedText = function () {
                if ($scope.contractor !== undefined) {
                    return "selected: " + $scope.contractor.name + " " + $scope.contractor.surname;
                } else {
                    return "Please select someone";
                }
            };


            $scope.loadSalesmen = function () {
                return $timeout(function () {
                    $scope.salesmen = $rootScope.dbData.salesmen;
                }, 10);
            };
            $scope.loadContractors = function () {
                return $timeout(function () {
                    $scope.contractor = $rootScope.dbData.contractors;
                }, 10);
            };
            $scope.loadServices = function () {
                return $timeout(function () {
                    $scope.service = $rootScope.dbData.services;
                }, 10);
            };


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
            //exposure
            $scope.exposureDate = new Date();
            $scope.minExposureDate = new Date(
                $scope.exposureDate.getFullYear(),
                $scope.exposureDate.getMonth() - 2,
                $scope.exposureDate.getDate());
            $scope.maxExposureDate = new Date(
                $scope.exposureDate.getFullYear(),
                $scope.exposureDate.getMonth() + 2,
                $scope.exposureDate.getDate());


            $scope.testFun = function () {
                console.info("callendars:");
                console.info($scope.sellDate);
                console.info($scope.deadDate);
                console.info($scope.exposureDate);
                console.info("3 selected items:")
                console.info($scope.contractor);
                console.info($scope.salesman);
                console.info($scope.service);

            };


            $scope.goContractors = function () {
                $state.go('contractors');
                //console.info($rootScope.dbData);
            };

            $scope.goSalesmen = function () {
                $state.go('salesmen');
            };

            $scope.goServices = function () {
                $state.go('services');
            };

        }); // end of ctrl
})();