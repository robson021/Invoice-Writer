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
                $scope.selectedContractor = item;
                console.info("item clicked: " + $scope.selectedSalesman.surname);
                $mdSidenav('right').open();
            };
            $scope.setValueService = function (item) {
                $scope.selectedContractor = item;
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

            $scope.uploadFile = function (files) {
                console.info("upload starterd...")
                var fd = new FormData();
                //Take the first selected file
                fd.append("file", files[0]);

                $http.post("/data/uplad/img", fd, {
                    withCredentials: true,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) { // todo: fix
                    console.info("upload complete");
                    $mdToast.show($mdToast.simple().textContent(data.text));
                    $state.go('default');
                }).error(function (data) {
                    console.info("upload error");
                    $mdToast.show($mdToast.simple().textContent(data.text));
                });
            };

        })
})();