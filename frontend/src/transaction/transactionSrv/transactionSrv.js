(function () {
    'use strict';

    angular
        .module('sfinapp.transaction.transactionSrv', [

        ])
        .factory('transactionSrv', transactionSrv);


    function transactionSrv($http) {
        var url = '/api/transactions/';

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
            return $http.get(url);
        }

        function get(id) {
            return $http.get(url + id);
        }

        function skeleton() {
            var skeleton = {
                date: new Date(),
                description: '',
                tags: []
            };

            return { data: skeleton };
        }

        function create(item) {
            return $http.post(url, item);
        }

        function update(item) {
            return $http.put(url + item.id, item);
        }

        function delete_(item) {
            return $http.delete(url + item.id);
        }

    }


})();

