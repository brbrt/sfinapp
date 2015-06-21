angular
    .module('sfinapp.config.http', [
        'sfinapp.config.http.apiDate',
        'sfinapp.config.http.errorAnalyzer',
        'sfinapp.config.http.infoDispatcher'
    ])
    .config(httpConfig);


function httpConfig($httpProvider) {
    $httpProvider.useApplyAsync(true);

    $httpProvider.interceptors.push('apiDateInterceptor');
    $httpProvider.interceptors.push('errorAnalyzerInterceptor');
    $httpProvider.interceptors.push('infoDispatcherInterceptor');
}
