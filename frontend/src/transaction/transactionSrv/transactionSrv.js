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
            skeleton: skeleton,
            create: create,
            update: update,
            delete: delete_
        };

        return factory;

        ////////////

        function getAll() {
            return $http.get(url).then(postProcess);
        }

        function skeleton() {
            return {
                date: new Date(),
                description: ''
            };
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

        function postProcess(resp) {
            resp.data.forEach(function parseDate(item) {
                item.date = new Date(item.date);
            });

            return resp;
        }
    }


})();

