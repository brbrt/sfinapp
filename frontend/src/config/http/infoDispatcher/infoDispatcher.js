import angular from 'angular';

angular
    .module('sfinapp.config.http.infoDispatcher', [

    ])
    .factory('infoDispatcherInterceptor', infoDispatcherInterceptor);


function infoDispatcherInterceptor($q, $rootScope) {

    var inProgress = 0;

    return {
        request: (config) => {
            requestStarted();
            return config;
        },

        response: (response) => {
            responseReceived();
            return response;
        },

        responseError: (error) => {
            responseReceived();
            return $q.reject(error);
        }
    };

    function requestStarted() {
        if (inProgress === 0) {
            $rootScope.$emit('http.start');
        }

        inProgress++;
    }

    function responseReceived() {
        inProgress--;

        if (inProgress === 0) {
            $rootScope.$emit('http.stop');
        }
    }

}
