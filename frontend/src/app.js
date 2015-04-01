(function () {
    'use strict';

    angular
        .module('sfinapp', [
            // AngularJS modules.
            'ngAnimate',

            // External modules.
            'ui.router',
            'mm.foundation',
            'smart-table',
            'toastr',

            // Internal modules.
            'sfinapp.config',

            'sfinapp.account',
            'sfinapp.home',
            'sfinapp.tag',
            'sfinapp.transaction'
        ])
        .controller('mainCtrl', mainCtrl);


    function mainCtrl($rootScope,
                      $log,
                      $state,
                      toastr) {

        $log.debug('Sfinapp states', $state.get());

        // Display errors on state changes (e.g. in resolve...)
        $rootScope.$on('$stateChangeError', function scError(event, toState, toParams, fromState, fromParams, error) {
            $log.error('$stateChangeError (' + fromState.name + ' -> ' + toState.name + '): ', error);

            toastr.error('$stateChangeError');
        });
    }

})();