(function () {
    "use strict";
    angular.module("ngApp")
        .controller('data-ctr', function ($rootScope, $scope, $mdToast, $state, $http) {

            var data = $rootScope.dbData;
            //var json = JSON.parse(data);

            $scope.contractors = data.contractors;
            $scope.salesmen = data.salesmen;
            $scope.services = data.services;


            $scope.test1 = function () {
                console.info("test clicked");
                //console.info(json);
                //console.info("data:\n" + data.contractors[0].surname);
            }

            $scope.setValue = function (item) {
                console.info("item clicked: " + item);
            }

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
                })
            };

        })
})();