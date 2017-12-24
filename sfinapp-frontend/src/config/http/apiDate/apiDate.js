import angular from 'angular';

angular
    .module('sfinapp.config.http.apiDate', [
        'sfinapp.core.dateUtilSrv'
    ])
    .factory('apiDateInterceptor', apiDateInterceptor);


function apiDateInterceptor(dateUtilSrv) {
    return {
        request: (request) => {
            var copy = angular.copy(request);
            dateUtilSrv.formatDates(copy);
            return copy;
        },
        response: (response) => {
            dateUtilSrv.parseDates(response);
            return response;
        }
    };
}
