(function () {
    "use strict";
    angular.module("ngApp")
        .controller('other-views-ctr', function ($scope) {
            $scope.openInNewTab = function () {
                //console.info("open github link in new tab");
                var url = "https://github.com/robson021/Invoice-Writer.git";
                var win = window.open(url, '_blank');
                win.focus();
            };
        });
})();