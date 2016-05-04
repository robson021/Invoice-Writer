(function () {
    "use strict";
    angular.module("ngApp")
        .controller("register-login-ctr", function ($scope, $http, $mdSidenav, $mdToast) {
            $scope.hideRegisterLogin = false;
            $scope.hideMainApp = true;
            $scope.myData = null;
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

            $scope.isLoggedIn = false;

            $scope.buttonRegisterTest = {
                text: "register"
            };
            var u = $scope.user;
            var rButton = $scope.buttonRegisterTest;
            var isLoggedIn = $scope.isLoggedIn;

            /*$scope.rButtonFun = function () { // todo: variables don't not change their values
             console.info("isLoggedIn: " + isLoggedIn)
             if (isLoggedIn == true) {
             console.info("logout function");
             var ajax = $http.post('/login/logout', u);
             ajax.success(function (data) {
             console.info(data);
             if (data.result) {        
             console.info("successful logout")
             }
             rButton.text = "register";
             $scope.message.text = data.text;
             $scope.openToast();
             })
             isLoggedIn = false;
             } else {
             console.info("rbutton: opening sidebar")
             $scope.openSidebar();
             }
             }*/


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
            }

            $scope.loginFunction = function () {
                console.info("login button clicked");
                console.info(u);

                var ajax = $http.post('/login/loguser', u);
                ajax.success(function (data) {
                    $scope.message.text = data.text;
                    if (data.result) {
                        u.password = "";
                        u.repassword = "";
                        $scope.hideRegisterLogin = true; // todo: why does it auto swap to false?
                        $scope.myData = data;
                        console.info("Data form the server:");
                        console.info($scope.myData);
                        $scope.openToast();
                        rButton.text = "logout";
                        console.info("ok!")
                        console.info("hide register/login: " + $scope.hideRegisterLogin);
                        isLoggedIn = true;
                    } else {
                        console.error("failed to login")
                        $scope.openToast();
                    }
                });
            }

            $scope.openToast = function ($event) {
                $mdToast.show($mdToast.simple().textContent($scope.message.text));
                //$mdToast.showSimple(message.text);
            };

            $scope.openSidebar = function () {
                $mdSidenav('left').open();
            }
            $scope.closeSidebar = function () {
                $mdSidenav('left').close();
            }
        });
})();