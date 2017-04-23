import angular from 'angular';

angular
    .module('sfinapp.transaction.transactionSrv', [

    ])
    .factory('transactionSrv', transactionSrv);


function transactionSrv($http) {
    var url = 'api/transaction/';

    return {
        getAll: getAll,
        getAllDescriptions: getAllDescriptions,
        get: get,
        skeleton: skeleton,
        create: create,
        createBatch: createBatch,
        update: update,
        remove: remove
    };

    ////////////

    function getAll(params) {
        return $http.get(url, {params: params}).then(getResponseData);
    }

    function getAllDescriptions() {
        return $http.get(url + 'descriptions').then(getResponseData);
    }

    function get(id) {
        return $http.get(url + id).then(getResponseData);
    }

    function skeleton() {
        return $http.get(url + 'skeleton').then(getResponseData);
    }

    function create(item) {
        return $http.post(url, item);
    }

    function createBatch(items) {
        return $http.post(url + 'batch', items);
    }

    function update(item) {
        return $http.put(url + item.id, item);
    }

    function remove(item) {
        return $http.delete(url + item.id);
    }

    function getResponseData(resp) {
        return resp.data;
    }

}
