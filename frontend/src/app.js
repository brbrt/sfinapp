import angular from 'angular';
import moment from 'moment';

import 'angular-animate';
import 'angular-sanitize';
import 'angular-material';
import 'angular-material/angular-material.css';
import 'angular-ui-router';
import 'angular-smart-table';
import 'angular-toastr';
import 'angular-toastr/dist/angular-toastr.css';
import 'ng-focus-if';

import config from './config/config.js'
import core from './core/core.js'
import home from './home/home.js'
import layout from './layout/layout.js'

import account from './account/account.js'
import tag from './tag/tag.js'
import transaction from './transaction/transaction.js'

angular
    .module('sfinapp', [
        // AngularJS modules.
        'ngAnimate',
        'ngSanitize',

        // External modules.
        'ngMaterial',
        'ui.router',
        'smart-table',
        'toastr',
        'focus-if',

        // Internal modules.
        'sfinapp.config',
        'sfinapp.core',
        'sfinapp.home',
        'sfinapp.layout',

        'sfinapp.account',
        'sfinapp.tag',
        'sfinapp.transaction'
    ])
    .controller('mainCtrl', mainCtrl);


function mainCtrl($rootScope) {
    var vm = this;

    vm.isLoading = false;

    init();

    ////////////

    function init() {
        $rootScope.$on('http.start', () => { vm.isLoading = true; });
        $rootScope.$on('http.stop', () => { vm.isLoading = false; });
    }

}
