(function () {
    "use strict";

    angular
        .module("ngApp")
        .factory("logoutFactory", function ($http, $mdToast) {
            function logoutUser() {
                var ajax = $http.get('login/logout');
                ajax.success(function (data) {
                    console.info("logout called: " + data.text);
                    if (data.result) {
                        console.info("logged out");
                        $mdToast.show($mdToast.simple().textContent(data.text));
                    } else {
                        console.info("session had expired before logout was clicked");
                        $mdToast.show($mdToast.simple().textContent(data.text));
                    }
                })
            }

            return {
                logoutUser: logoutUser
            }
        })
        .factory("logInOrLogOutFactory", function () {
            var isLoggedIn = false;

            function setLogged(tof) {
                isLoggedIn = tof;
            }

            function isLoggedIn() {
                if (isLoggedIn) return true;
                else return false;
            }

            return {
                setLogged: setLogged,
                isLoggedIn: isLoggedIn
            }
        })
        .factory("mainInfoFactory", function ($http) {
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
                })
            }

            function getAuthorInfo(toDisplay) {
                var ajax = $http.get('data/about/author');
                ajax.success(function (data) {
                    console.info("about author: " + data.title + "\n" + data.text);
                    toDisplay.title = data.title;
                    toDisplay.text = data.text;
                })
            }

            return {
                getAppInfo: getAppInfo,
                getAuthorInfo: getAuthorInfo
            }

        }); // factory end
})();