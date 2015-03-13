(function () {
    'use strict';

    angular
        .module('sfinapp', [
            // AngularJS modules.

            // External modules.
            'ui.router',

            // Internal modules.
            'sfinapp.accounts',
            'sfinapp.home'
        ])
        .controller('mainCtrl', mainCtrl);


    function mainCtrl($rootScope,
                      $log,
                      $state) {

        $log.debug('Sfinapp states', $state.get());

        // Display errors on state changes (e.g. in resolve...)
        $rootScope.$on('$stateChangeError', function scError(event, toState, toParams, fromState, fromParams, error) {
            $log.error('$stateChangeError (' + fromState.name + ' -> ' + toState.name + '): ', error);
        });
    }

})();