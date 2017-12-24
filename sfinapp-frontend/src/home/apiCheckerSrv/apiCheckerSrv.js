import angular from 'angular';

angular
    .module('sfinapp.home.apiCheckerSrv', [

    ])
    .factory('apiCheckerSrv', apiCheckerSrv);


function apiCheckerSrv($http) {

    var factory = {
        check: check
    };

    return factory;

    ////////////

    function check() {
        return $http.get('api/healthcheck');
    }

}
