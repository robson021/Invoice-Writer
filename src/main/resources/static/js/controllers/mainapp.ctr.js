(function () {
    "use strict";
    angular.module("ngApp")
        .controller("main-app-ctr", function ($rootScope, $scope, $state, $timeout, $mdToast, $http, $window) {


            // selected right now
            $scope.salesman = null;
            $scope.contractor = null;
            $scope.selectedServices = [];
            $scope.service = null;
            $scope.copyOnMail = false;

            $scope.formOfPayment = 'cash';
            $scope.placeOfPayment = 'Cracow';
            $scope.invoiceNumber = '1/2016';

            // local arrays
            $scope.salesmen = $rootScope.dbData.salesmen;
            $scope.contractors = $rootScope.dbData.contractors;
            $scope.services = $rootScope.dbData.services;

            $scope.getSelectedC = function () {
                if ($scope.contractor !== undefined) {
                    return "selected: " + $scope.contractor.name + " " + $scope.contractor.surname;
                } else {
                    return "Please select someone";
                }
            };
            $scope.getSelectedS = function () {
                if ($scope.salesman !== undefined) {
                    return "selected: " + $scope.salesman.name + " " + $scope.salesman.surname;
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


            // CALENDARS
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
                console.info($scope.service + $scope.selectedServices);

            };

            // md-toast config
            var last = {
                bottom: false,
                top: true,
                left: false,
                right: true
            };
            $scope.toastPosition = angular.extend({}, last);
            $scope.getToastPosition = function () {
                sanitizePosition();
                return Object.keys($scope.toastPosition)
                    .filter(function (pos) {
                        return $scope.toastPosition[pos];
                    })
                    .join(' ');
            };
            function sanitizePosition() {
                var current = $scope.toastPosition;
                if (current.bottom && last.top) current.top = false;
                if (current.top && last.bottom) current.bottom = false;
                if (current.right && last.left) current.left = false;
                if (current.left && last.right) current.right = false;
                last = angular.extend({}, current);
            }


            // bottom buttons
            $scope.saveData = function () {
                console.info("data to send:\n" + $rootScope.dbData);
                var ajax = $http.post('/data/update-user-data', $rootScope.dbData);
                ajax.success(function (data) {
                    $window.scrollTo(0, 0);
                    $mdToast.show($mdToast.simple().textContent(data.text).position($scope.getToastPosition())
                        .hideDelay(3000));
                });
            };

            $scope.submitInvoice = function () {
                var invoiceTemplate = {
                    'formOfPayment': $scope.formOfPayment,
                    'placeOfPayment': $scope.placeOfPayment,
                    'deadDate': $scope.deadDate,
                    'sellDate': $scope.sellDate,
                    'exposureDate': $scope.exposureDate,
                    'copyOnMail': $scope.copyOnMail,
                    'salesman': $scope.salesman,
                    'contractor': $scope.contractor,
                    'selectedServices': $scope.selectedServices
                };
                var ajax = $http.post('/data/submit-invoice', invoiceTemplate);
                ajax.success(function (data) {
                    $window.scrollTo(0, 0);
                    $mdToast.show($mdToast.simple().textContent(data.text).position($scope.getToastPosition())
                        .hideDelay(3000));
                });
            };


            $scope.goContractors = function () {
                $state.go('contractors');
            };

            $scope.goSalesmen = function () {
                $state.go('salesmen');
            };

            $scope.goServices = function () {
                $state.go('services');
            };
            $scope.goUpload = function () {
                $state.go('uplad');
            };

        }); // end of ctrl
})();