(function () {
    "use strict";
    angular.module("ngApp")
        .controller("toolbar-ctr", function ($rootScope, $scope, $state, logoutFactory) {


            $scope.logout = function () {                
                logoutFactory.logoutUser();
            };

            $scope.aboutAppFun = function () {
                //mainInfoFactory.getAppInfo(info.appInfo);
                //console.info("about app (controller): " + info.appInfo.title + "\n" + info.appInfo.text);
                $state.go('about-app');
            };

            $scope.aboutAuthorFun = function () {
                //mainInfoFactory.getAuthorInfo(info.authorInfo);
                //console.info("about author (controller): " + info.authorInfo.title + "\n" + info.authorInfo.text);
                $state.go('about-author');
            };

            $scope.beforeUseFun = function () {
                $state.go('before-use');
            };

            $scope.mainView = function () {
                console.info($rootScope.isLoggedIn);
                if ($rootScope.isLoggedIn) {
                    $state.go('logged-in');
                } else $state.go('default');
            }

        }); // end of ctrl
})();