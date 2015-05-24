angular
    .module('sfinapp.config.state', [
        'ui.router'
    ])
    .config(stateConfig)
    .run(stateRun);


function stateConfig($stateProvider,
                     $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');
}

function stateRun($log,
                  $rootScope,
                  toastr) {

    // Display errors on state changes (e.g. in resolve...)
    $rootScope.$on('$stateChangeError', (event, toState, toParams, fromState, fromParams, error) => {
        $log.error('$stateChangeError (' + fromState.name + ' -> ' + toState.name + '): ', error);

        toastr.error(getErrorMessage(error));
    });

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