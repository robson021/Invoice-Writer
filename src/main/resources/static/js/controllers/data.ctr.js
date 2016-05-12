(function () {
    "use strict";
    angular.module("ngApp")
        .controller('data-ctr', function ($rootScope, $scope) {

            var data = $rootScope.dbData;
            //var json = JSON.parse(data);


            $scope.test1 = function () {
                console.info("test clicked");
                //console.info(json);
                //console.info("data:\n" + data.contractors[0].surname);
            }

        })
})();