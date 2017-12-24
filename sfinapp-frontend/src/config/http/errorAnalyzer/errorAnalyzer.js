import angular from 'angular';

angular
    .module('sfinapp.config.http.errorAnalyzer', [

    ])
    .factory('errorAnalyzerInterceptor', errorAnalyzerInterceptor);


function errorAnalyzerInterceptor($q) {

    return {
        responseError: (error) => {
            error.errorMessage = getErrorMessage(error);
            return $q.reject(error);
        }
    };


    function getErrorMessage(error) {
        if (error.status === 502) {
            return 'Service unavailable.';
        }

        if (error.data && error.data.message) {
            return error.data.message;
        }

        return 'Unknown error.';
    }

}
