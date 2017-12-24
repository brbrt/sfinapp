import angular from 'angular';

import './accountDetail/accountDetail.js';
import './accountSrv/accountSrv.js';

import tpl from './account.tpl.html';

angular
    .module('sfinapp.account', [
        'ui.router',
        'smart-table',

        'sfinapp.account.accountDetail',
        'sfinapp.account.accountSrv'
    ])
    .config(accountConfig)
    .controller('accountCtrl', accountCtrl);


function accountConfig($stateProvider) {
    $stateProvider
        .state('app.account', {
            abstract: true,
            template: '<ui-view/>',
            url: '/accounts'
        })
        .state('app.account.list', {
            url: '',
            controller: 'accountCtrl',
            controllerAs: 'vm',
            template: tpl,
            resolve: {
                accounts: (accountSrv) => { return accountSrv.getAll(); }
            }
        });
}

function accountCtrl(accounts) {
    var vm = this;

    vm.accounts = accounts;

    init();

    ////////////

    function init() {

    }
}
