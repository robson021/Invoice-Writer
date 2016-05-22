(function () {
    "use strict";
    angular.module("ngApp")
        .controller('data-ctr', function ($rootScope, $scope, $mdToast, $state, $http, $mdSidenav) {

            var data = $rootScope.dbData;
            //var json = JSON.parse(data);

            var addNew = false;

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


            $scope.test1 = function () {
                console.info("test clicked");
                //console.info(json);
                //console.info("data:\n" + data.contractors[0].surname);
            };

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
                if (addNew) {
                    addNew = false;
                    $scope.contractors.push($scope.selectedContractor);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedContractor = null;
            };

            $scope.updateSalesman = function () {
                $scope.closeSidebar();
                if (addNew) {
                    addNew = false;
                    $scope.salesmen.push($scope.selectedSalesman);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedSalesman = null;
            };

            $scope.updateService = function () {
                $scope.closeSidebar();
                if (addNew) {
                    addNew = false;
                    $scope.services.push($scope.selectedService);
                    console.info("added new");
                } else {
                    console.info("updated old");
                }
                $scope.selectedService = null;
            };

            $scope.addNewContractor = function () {
                addNew = true;
                $scope.selectedContractor = null;
                $mdSidenav('right').open();
            };

            $scope.addNewSalesman = function () {
                addNew = true;
                $scope.selectedSalesman = null;
                $mdSidenav('right').open();
            };

            $scope.addNewService = function () {
                addNew = true;
                $scope.selectedService = null;
                $mdSidenav('right').open();
            };


            $scope.closeSidebar = function () {
                $mdSidenav('right').close();
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