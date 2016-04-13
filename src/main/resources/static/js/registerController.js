(function () {
    "use strict";
    angular.module("ngApp")
        .controller("registerCtrl", function ($scope, $http) {
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
                    $scope.message.text = 'Failed to register new account.';
                    var ajax = $http.post('/register/newuser', $scope.user);
                    ajax.success(function (data) {
                        $scope.message.text = 'You are registered now!';
                    });
                } else {
                    console.error("passwords do not match");
                    $scope.message.text = 'Passwords do not match!';
                }
            }
        });
})();