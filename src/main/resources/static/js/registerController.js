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
                text: "Failed"
            };


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
                var u = $scope.user;
                console.info("login button clicked");
                console.info(u);

                var ajax = $http.post('/login/loguser', u);
                ajax.success(function (data) {
                    $scope.message.text = data.text;
                    if (data.result) {
                        u.password = "";
                        u.repassword = "";
                        console.info("ok!")
                        $scope.myData = data;
                        console.info("Data form the server:");
                        console.info($scope.myData);
                        $scope.openToast();
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