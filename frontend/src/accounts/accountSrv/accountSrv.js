(function () {
    'use strict';

    angular
        .module('sfinapp.accounts.accountSrv', [

        ])
        .factory('accountSrv', accountSrv);


    function accountSrv($http) {
        var factory = {
            getAll: getAll
        };

        return factory;

        ////////////

        function getAll() {
            return $http.get('/api/accounts');
        }
    }


})();

