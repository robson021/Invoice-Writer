app.service('storageService', function () {
    var key = "csrftoken";
    this.setToken = function (token) {
        sessionStorage.setItem(key, token);
    };

    this.getToken = function () {
        return sessionStorage.getItem(key);
    }
});