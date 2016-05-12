(function () {
    "use strict";
    angular.module("ngApp")
        .controller('data-ctr', function ($rootScope, $scope) {

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

        })
})();