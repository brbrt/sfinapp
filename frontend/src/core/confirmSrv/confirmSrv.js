(function () {
    'use strict';

    angular
        .module('sfinapp.core.confirmSrv', [

        ])
        .factory('confirmSrv', confirmSrv);


    function confirmSrv($window) {

        var factory = {
            confirm: confirm
        };

        return factory;

        ////////////

        function confirm(message, action) {
            if (!message || !angular.isFunction(action)) {
                return;
            }

            if (!$window.confirm(message)) {
                return;
            }

            action();
        }

    }

})();

