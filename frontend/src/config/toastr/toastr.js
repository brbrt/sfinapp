angular
    .module('sfinapp.config.toastr', [
        'toastr'
    ])
    .config(toastrConfig);


function toastrConfig($provide) {
    $provide.decorator('toastr', decorateToastr);
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
