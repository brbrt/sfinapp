angular
    .module('sfinapp.config.http', [
        'sfinapp.core.dateUtilSrv'
    ])
    .config(httpConfig);


function httpConfig($httpProvider, dateUtilSrvProvider) {
    $httpProvider.defaults.transformResponse.push(parseResponseDates);

    function parseResponseDates(responseData) {
        var dateUtilSrv = dateUtilSrvProvider.$get();
        dateUtilSrv.parseDates(responseData);
        return responseData;
    }
}
