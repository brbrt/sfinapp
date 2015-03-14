(function () {
    'use strict';

    angular
        .module('sfinapp.tag.tagSrv', [

        ])
        .factory('tagSrv', tagSrv);


    function tagSrv($http) {
        var factory = {
            getAll: getAll
        };

        return factory;

        ////////////

        function getAll() {
            return $http.get('/api/tags');
        }
    }


})();

