(function () {
    'use strict';

    angular
        .module('sfinapp.accounts', [
            'ui.router',

            'sfinapp.accounts.accountSrv'
        ])
        .config(accountConfig)
        .controller('accountCtrl', accountCtrl);


    function accountConfig($stateProvider) {
        $stateProvider.state('accounts', {
            url: '/accounts',
            controller: 'accountCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/accounts/accounts.tpl.html',
            resolve: {
                accounts: function getAccounts(accountSrv) {
                    return accountSrv.getAll();
                }
            }
        });
    }

    function accountCtrl(accounts) {
        var vm = this;
        vm.accounts = accounts.data;

        init();

        ////////////

        function init() {

        }

    }

})();

