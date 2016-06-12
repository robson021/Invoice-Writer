(function () {
    "use strict";

    angular
        .module("ngApp")
        .factory("logoutFactory", function ($http, $mdToast, $rootScope, $state) {
            function logoutUser() {
                //todo update db before logout
                var ajax = $http.get('login/logout');
                ajax.success(function (data) {
                    console.info("logout called: " + data.text);
                    if (data.result) {
                        console.info("logged out");
                        $mdToast.show($mdToast.simple().textContent(data.text));
                        $rootScope.isLoggedIn = false;
                        $rootScope.hideRegisterLogin = false;
                        $rootScope.loginButtonEnabled = true;
                    } else {
                        console.info("session had expired before logout was clicked");
                        $mdToast.show($mdToast.simple().textContent(data.text));
                    }
                    $state.go('default');
                })
            }

            return {
                logoutUser: logoutUser
            }
        })
        .factory("tokenFactory", function ($http, $rootScope) {
            function getNewToken() {
                var ajax = $http.get('/data/new-token');
                // todo
            }

            return {
                getNewToken: getNewToken
            }
        })
        .factory("mainInfoFactory", function ($http, $rootScope) {
            /*return {
             get:  function(){
             $http.get('datafiles/text.json'); // this will return a promise to controller
             }
             }*/

            function getAppInfo(toDisplay) {
                var ajax = $http.get('data/about/app');
                ajax.success(function (data) {
                    console.info("about app: " + data.title + "\n" + data.text);
                    toDisplay.title = data.title;
                    toDisplay.text = data.text;
                    $rootScope.basicInfo.appInfo.title = data.title;
                    $rootScope.basicInfo.appInfo.text = data.text;
                })
            }

            function getAuthorInfo(toDisplay) {
                var ajax = $http.get('data/about/author');
                ajax.success(function (data) {
                    console.info("about author: " + data.title + "\n" + data.text);
                    toDisplay.title = data.title;
                    toDisplay.text = data.text;
                    $rootScope.basicInfo.authorInfo.title = data.title;
                    $rootScope.basicInfo.authorInfo.text = data.text;
                })
            }

            return {
                getAppInfo: getAppInfo,
                getAuthorInfo: getAuthorInfo
            }

        }); // factory end
})();