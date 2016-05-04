(function () {
    "use strict";
    angular.module("ngApp")
        .controller("toolbar-ctr", function ($scope, $state, logoutFactory, logInOrLogOutFactory, mainInfoFactory /*,toastFactory*/) {

            $scope.basicInfo = {
                appInfo: {
                    title: null, text: null
                },
                authorInfo: {
                    title: null, text: null
                }
            };

            var info = $scope.basicInfo;
            
            $scope.logout = function () { //todo: watcher - enabled/disabled button
                console.info("logout button clicked");
                logoutFactory.logoutUser();
                logInOrLogOutFactory.setLogged(false);
                $scope.hideRegisterLogin = false; // todo
            }
            
            $scope.aboutAppFun = function () {
                mainInfoFactory.getAppInfo(info.appInfo);
                console.info("about app (controller): " + info.appInfo.title + "\n" + info.appInfo.text);
                $state.go('about-app');
            }

            $scope.aboutAuthorFun = function () {
                mainInfoFactory.getAuthorInfo(info.authorInfo);
                console.info("about author (controller): " + info.authorInfo.title + "\n" + info.authorInfo.text);
                $state.go('about-author');
            }

            $scope.mainView = function () {
                $state.go('default');
            }

        }); // end of ctrl
})();