(function () {
    "use strict";
    angular.module("ngApp")
        .controller('data-ctr', function ($rootScope, $scope, $mdToast, $state, $http, $mdSidenav) {

            var data = $rootScope.dbData;
            $scope.addNew = false;

            $scope.contractors = data.contractors;
            $scope.salesmen = data.salesmen;
            $scope.services = data.services;

            $scope.selectedContractor = null;
            $scope.selectedService = null;
            $scope.selectedSalesman = null;

            if ($scope.contractors == null) {
                $scope.contractors = [];
            }
            if ($scope.salesmen == null) {
                $scope.salesmen = [];
            }
            if ($scope.services == null) {
                $scope.services = [];
            }


            $scope.setValueContractor = function (item) {
                $scope.selectedContractor = item;
                console.info("item clicked: " + $scope.selectedContractor.surname);
                $mdSidenav('right').open();
            };
            $scope.setValueSalesman = function (item) {
                $scope.selectedSalesman = item;
                console.info("item clicked: " + $scope.selectedSalesman.surname);
                $mdSidenav('right').open();
            };
            $scope.setServiceValue = function (item) {
                $scope.selectedService = item;
                console.info("item clicked: " + $scope.selectedService.name);
                $mdSidenav('right').open();
            };

            $scope.updateContractor = function () {
                $scope.closeSidebar();
                if ($scope.addNew) {
                    $scope.addNew = false;
                    $scope.contractors.push($scope.selectedContractor);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedContractor = null;
            };

            $scope.updateSalesman = function () {
                $scope.closeSidebar();
                if ($scope.addNew) {
                    $scope.addNew = false;
                    $scope.salesmen.push($scope.selectedSalesman);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedSalesman = null;
            };

            $scope.updateService = function () {
                $scope.closeSidebar();
                var s = $scope.selectedService;
                s.brutto = s.nettoValue + s.nettoValue * s.vatPercentage / 100;
                if ($scope.addNew) {
                    $scope.addNew = false;
                    $scope.services.push($scope.selectedService);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedService = null;
            };

            // adding
            $scope.addNewContractor = function () {
                $scope.addNew = true;
                $scope.selectedContractor = null;
                $mdSidenav('right').open();
            };

            $scope.addNewSalesman = function () {
                $scope.addNew = true;
                $scope.selectedSalesman = null;
                $mdSidenav('right').open();
            };

            $scope.addNewService = function () {
                $scope.addNew = true;
                $scope.selectedService = null;
                $mdSidenav('right').open();
            };

            // deleting
            $scope.deleteService = function () {
                console.info("deleting: " + $scope.selectedService)
                if (!$scope.addNew) {
                    var index = $scope.services.indexOf($scope.selectedService);
                    if (index !== -1) {
                        $scope.services.splice(index, 1);
                    }
                }
                $scope.closeSidebar();
            };
            $scope.deleteSalesman = function () {
                console.info("deleting: " + $scope.selectedSalesman)
                if (!$scope.addNew) {
                    var index = $scope.salesmen.indexOf($scope.selectedSalesman);
                    if (index !== -1) {
                        $scope.salesmen.splice(index, 1);
                    }
                }
                $scope.closeSidebar();
            };
            $scope.deleteContractor = function () {
                console.info("deleting: " + $scope.selectedContractor)
                if (!$scope.addNew) {
                    var index = $scope.contractors.indexOf($scope.selectedContractor);
                    if (index !== -1) {
                        $scope.contractors.splice(index, 1);
                    }
                }
                $scope.closeSidebar();
            };


            $scope.closeSidebar = function () {
                $mdSidenav('right').close();
                // todo add watcher for this variable
            };

            $scope.downloadImage = function () {
                window.open("/data/get-image", '_blank');
            };

            $scope.uploadFile = function (files) {
                console.info("upload starterd...")
                var fd = new FormData();
                //Take the first selected file
                fd.append("file", files[0]);

                $http.post("/data/uplad/img", fd, {
                    withCredentials: true,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) {
                    console.info("upload complete");
                    $state.go('logged-in');
                    $mdToast.show($mdToast.simple().textContent(data.text));
                }).error(function (data) {
                    console.info("upload error");
                    $mdToast.show($mdToast.simple().textContent(data.text));
                });
            };

        })
})();