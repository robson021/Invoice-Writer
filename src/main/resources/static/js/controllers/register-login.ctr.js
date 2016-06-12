(function () {
    "use strict";
    angular.module("ngApp")
        .controller("register-login-ctr", function ($rootScope, $scope, $http, $mdSidenav, $mdToast, $state) {


            $rootScope.hideRegisterLogin = false;
            $scope.hideMainApp = true;
            //$scope.myData = null;
            
            var r = $rootScope;

            $scope.user = {
                firstname: "",
                surname: "",
                email: "",
                password: "",
                repassword: ""
            };

            $scope.message = {
                text: "Failed"
            };

            $scope.buttonRegisterTest = {
                text: "register"
            };
            var u = $scope.user;
            var rButton = $scope.buttonRegisterTest;


            $scope.registerFunction = function () {
                console.info("register button clicked");
                console.info($scope.user);
                if ($scope.user.password === $scope.user.repassword) {
                    console.info("passwords ok")
                    var ajax = $http.post('/register/newuser', $scope.user);
                    ajax.success(function (data) {
                        $scope.message.text = data.text;
                        if (data.result) {
                            $mdSidenav('left').close();
                            $scope.openToast();
                        } else {
                            $scope.openToast();
                        }
                    });
                } else {
                    console.error("passwords do not match");
                    $scope.message.text = 'Passwords do not match!';
                    $scope.openToast();
                }
            };

            $scope.loginFunction = function () {
                console.info("login button clicked");
                console.info(u);

                var ajax = $http.post('/login/loguser', u);
                ajax.success(function (data) {
                    $scope.message.text = data.text;
                    if (data.result) {
                        u.password = "";
                        u.repassword = "";
                        $rootScope.hideRegisterLogin = true;

                        $rootScope.dbData = data;

                        console.info("Data form the server:");
                        console.info($rootScope.dbData);
                        $scope.openToast();
                        rButton.text = "logout";
                        console.info("ok!");
                        console.info("hide register/login: " + $rootScope.hideRegisterLogin);

                        r.token = data.token; // csrf token

                        r.isLoggedIn = true;
                        r.loginButtonEnabled = false;

                        $state.go('logged-in');
                    } else {
                        console.error("failed to login");
                        $scope.openToast();
                        r.isLoggedIn = false;
                    }
                });
            };

            $scope.openToast = function ($event) {
                $mdToast.show($mdToast.simple().textContent($scope.message.text));
                //$mdToast.showSimple(message.text);
            };

            $scope.openSidebar = function () {
                $mdSidenav('left').open();
            };
            $scope.closeSidebar = function () {
                $mdSidenav('left').close();
            };
        });
})();