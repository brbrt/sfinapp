angular
    .module('sfinapp.config.toastr', [
        'toastr'
    ])
    .config(configureToastr);


function configureToastr($provide, toastrConfig) {
    $provide.decorator('toastr', decorateToastr);

    toastrConfig.positionClass = 'toast-bottom-right';
}

function decorateToastr($delegate, $log) {
    var toastr = $delegate;
    toastr.apiError = apiError;
    return toastr;

    function apiError(err) {
        var msg = err.errorMessage;
        $log.info('Api error: ' + msg);
        toastr.error(msg);
    }
}
