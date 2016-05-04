(function () {
    "use strict";
    angular.module("ngApp")
        .controller("toolbar-ctr", function ($scope, logoutFactory /*,toastFactory*/) {


            $scope.logout = function () {
                console.info("logout button clicked");
                logoutFactory.logoutUser();
            }

        });
})();