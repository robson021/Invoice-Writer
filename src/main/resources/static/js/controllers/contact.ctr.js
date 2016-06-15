(function () {
    "use strict";
    angular.module("ngApp")
        .controller("contact-ctr", function ($scope, $mdToast, $http, storageService, $state) {

            $scope.messageBody = '';

            $scope.sendMessage = function () {
                if ($scope.messageBody.length > 10) {
                    var msg = {
                        'token': storageService.getToken(),
                        'text': $scope.messageBody
                    };
                    var ajax = $http.post('/data/email', msg);
                    ajax.success(function (data) {
                        if (data.result) {
                            $state.go('logged-in');
                        } else {
                            $mdToast.show($mdToast.simple().textContent("Could not send the message")
                                .hideDelay(3000));
                        }
                        storageService.setToken(data.token);
                    });
                } else {
                    $mdToast.show($mdToast.simple().textContent("Message is too short")
                        .hideDelay(3000));
                }
            };

        }); // end of ctrl
})();