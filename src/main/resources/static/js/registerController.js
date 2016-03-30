function MasterController($scope) {
    $scope.data = {message: 'not registerd yet'};
}

function registerUser($scope, $http, user) {
    var ajax = $http.post('/', user);
    ajax.succes(function (data) {
        if (data.result) {
            $scope.data.message = 'user registered!';
        } else {
            alert('Register attempt fail.');
        }
    })
}