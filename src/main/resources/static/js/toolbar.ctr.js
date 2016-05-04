(function () {
    "use strict";
    angular.module("ngApp")
        .controller("toolbar-ctr", function ($scope, logoutFactory, logInOrLogOutFactory /*,toastFactory*/) {


            $scope.logout = function () { //todo: watcher - enabled/disabled button
                console.info("logout button clicked");
                logoutFactory.logoutUser();
                logInOrLogOutFactory.setLogged(false);
                $scope.hideRegisterLogin = false; // todo
            }
            
            $scope.aboutAppFun = function () {

            }

            $scope.aboutAuthorFun = function () {

            }

        }); // end of ctrl
})();