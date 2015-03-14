(function () {
    'use strict';

    angular
        .module('sfinapp.config.state', [
            'ui.router'
        ])
        .config(stateConfig);


    function stateConfig($stateProvider,
                         $urlRouterProvider) {

        $urlRouterProvider.otherwise('/home');
    }


})();

