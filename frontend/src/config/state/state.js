import angular from 'angular';

angular
    .module('sfinapp.config.state', [
        'ui.router'
    ])
    .config(stateConfig)
    .run(stateRun);


function stateConfig($stateProvider,
                     $urlRouterProvider) {

    $stateProvider.state('app', {
        abstract: true,
        template: '<ui-view/>'
    });

    $urlRouterProvider.otherwise('/home');
}

function stateRun($log,
                  $rootScope,
                  toastr) {

    $rootScope.$on('$stateChangeStart', (event, toState, toParams, fromState, fromParams) => {
        $log.debug('$stateChangeStart (' + fromState.name + ' -> ' + toState.name + ')');
    });

    // Display errors on state changes (e.g. in resolve...)
    $rootScope.$on('$stateChangeError', (event, toState, toParams, fromState, fromParams, error) => {
        $log.error('$stateChangeError (' + fromState.name + ' -> ' + toState.name + '): ', error);

        toastr.apiError(error);
    });

}