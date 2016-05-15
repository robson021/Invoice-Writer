(function () {
    "use strict";
    angular.module('ngApp')
        .service('fileToUpload', ['$http', 'ajaxService', function ($http, ajaxService) {
            var data = {}; //file object

            var fd = new FormData();
            fd.append('file', data.file);

            $http.post("/uplad/img", fd)
                .success(function (response, status, headers, config) {
                    console.log(response);

                    if (status == 200 || status == 202) //do whatever in success
                    {

                    }
                    else // handle error in  else if needed
                    {

                    }
                })
                .error(function (error, status, headers, config) {
                    console.log(error);

                    // handle else calls
                });
        }]);
})();