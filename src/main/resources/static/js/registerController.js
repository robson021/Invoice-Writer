(function () {
    "use strict";
    angular.module("ngApp")
        .controller("registerCtrl", function ($scope, $http, $mdSidenav, $mdToast) {
            $scope.myData = null;
            $scope.user = {
                firstname: "",
                surname: "",
                email: "",
                password: "",
                repassword: ""
            };

            $scope.message = {
                text: "You are not registered yet."
            };


            $scope.registerFunction = function () {
                console.info("register button clicked");
                console.info($scope.user);
                if ($scope.user.password === $scope.user.repassword) {
                    console.info("passwords ok")
                    //$scope.message.text = 'Failed to register new account.';
                    var ajax = $http.post('/register/newuser', $scope.user);
                    ajax.success(function (data) {
                        if (data.result) {
                            $scope.message.text = 'You are registered. ' + data.text;
                            $mdSidenav('left').close();
                        } else {
                            $scope.message.text = 'Registration failed. ' + data.text;
                        }
                    });
                } else {
                    console.error("passwords do not match");
                    $scope.message.text = 'Passwords do not match!';
                }
                $scope.openToast();
            }

            $scope.loginFunction = function () {
                var u = $scope.user;
                console.info("login button clicked");
                console.info(u);

                var ajax = $http.post('/login/loguser', u);
                ajax.success(function (data) {
                    console.info(data)
                    if (data.result) {
                        console.info("ok!")
                        $scope.myData = data;
                        console.info("Data form the server:");
                        console.info($scope.myData);
                    } else {
                        console.error("failed to login")
                    }
                    $scope.message.text = data.text;
                });
                $scope.openToast();
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