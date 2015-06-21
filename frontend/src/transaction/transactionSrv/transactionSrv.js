angular
    .module('sfinapp.transaction.transactionSrv', [

    ])
    .factory('transactionSrv', transactionSrv);


function transactionSrv($http) {
    var url = 'api/transactions/';

    var factory = {
        getAll: getAll,
        getAllDescriptions: getAllDescriptions,
        get: get,
        skeleton: skeleton,
        create: create,
        createBatch: createBatch,
        update: update,
        delete: delete_,
        suggestDescription: suggestDescription
    };

    return factory;

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

    function delete_(item) {
        return $http.delete(url + item.id);
    }

    function getResponseData(resp) {
        return resp.data;
    }

    function suggestDescription(descriptions, term) {
        var q = term.toLowerCase().trim();
        var results = [];

        // Find first 10 states that start with `term`.
        for (var i = 0; i < descriptions.length && results.length < 5; i++) {
            var description = descriptions[i];
            if (description.toLowerCase().indexOf(q) === 0) {
                results.push({label: description, value: description});
            }
        }

        return results;
    }

}
