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
                return false;
            }

            return {
                setLogged: setLogged,
                isLoggedIn: isLoggedIn
            }
        });
})();