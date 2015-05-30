angular
    .module('sfinapp.config.http.responseDateParser', [
        'sfinapp.core.dateUtilSrv'
    ])
    .factory('responseDateParserInterceptor', responseDateParserInterceptor);


function responseDateParserInterceptor($q, dateUtilSrv) {
    return {
        response: (response) => {
            dateUtilSrv.parseDates(response);
            return response;
        }
    };
}
