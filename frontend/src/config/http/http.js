angular
    .module('sfinapp.config.http', [
        'sfinapp.config.http.infoDispatcher',
        'sfinapp.config.http.responseDateParser'
    ])
    .config(httpConfig);


function httpConfig($httpProvider) {
    $httpProvider.useApplyAsync(true);

    $httpProvider.interceptors.push('responseDateParserInterceptor');
    $httpProvider.interceptors.push('infoDispatcherInterceptor');
}
