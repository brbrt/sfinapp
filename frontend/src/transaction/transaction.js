(function () {
    'use strict';

    angular
        .module('sfinapp.transaction', [
            'ui.router',
            'smart-table',

            'sfinapp.transaction.transactionDetail',
            'sfinapp.transaction.transactionSrv'
        ])
        .config(transactionConfig)
        .controller('transactionCtrl', transactionCtrl);


    function transactionConfig($stateProvider) {
        $stateProvider.state('transaction', {
            url: '/transactions',
            controller: 'transactionCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/transaction/transaction.tpl.html',
            resolve: {
                transactions: function getTransactions(transactionSrv) {
                    return transactionSrv.getAll();
                }
            }
        });
    }

    function transactionCtrl(transactions) {
        var vm = this;

        vm.transactions = transactions.data;

        init();

        ////////////

        function init() {

        }

    }

})();

