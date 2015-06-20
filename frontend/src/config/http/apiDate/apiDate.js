angular
    .module('sfinapp.config.http.apiDate', [
        'sfinapp.core.dateUtilSrv'
    ])
    .factory('apiDateInterceptor', apiDateInterceptor);


function apiDateInterceptor(dateUtilSrv) {
    return {
        request: (request) => {
            dateUtilSrv.formatDates(request);
            return request;
        },
        response: (response) => {
            dateUtilSrv.parseDates(response);
            return response;
        }
    };
}
