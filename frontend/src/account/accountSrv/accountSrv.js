(function () {
    'use strict';

    angular
        .module('sfinapp.account.accountSrv', [

        ])
        .factory('accountSrv', accountSrv);


    function accountSrv($http) {
        var url = 'api/accounts/';

        var factory = {
            getAll: getAll,
            get: get,
            skeleton: skeleton,
            create: create,
            update: update,
            delete: delete_
        };

        return factory;

        ////////////

        function getAll() {
            return $http.get(url).then(getResponseData);
        }

        function get(id) {
            return $http.get(url + id).then(getResponseData);
        }

        function skeleton() {
            return {
                name: '',
                description: ''
            };
        }

        function create(account) {
            return $http.post(url, account);
        }

        function update(account) {
            return $http.put(url + account.id, account);
        }

        function delete_(account) {
            return $http.delete(url + account.id);
        }

        function getResponseData(resp) {
            return resp.data;
        }
    }

})();

