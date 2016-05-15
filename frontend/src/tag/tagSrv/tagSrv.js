angular
    .module('sfinapp.tag.tagSrv', [

    ])
    .factory('tagSrv', tagSrv);


function tagSrv($http) {
    var url = 'api/tags/';

    return {
        getAll: getAll,
        get: get,
        skeleton: skeleton,
        create: create,
        update: update,
        remove: remove
    };

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

    function create(newTag) {
        return $http.post(url, newTag);
    }

    function update(editedTag) {
        return $http.put(url + editedTag.id, editedTag);
    }

    function remove(tag) {
        return $http.delete(url + tag.id);
    }

    function getResponseData(resp) {
        return resp.data;
    }

}
