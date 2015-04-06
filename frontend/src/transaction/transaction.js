(function () {
    'use strict';

    angular
        .module('sfinapp.transaction', [
            'ui.router',
            'smart-table',

            'sfinapp.core',
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

    function transactionCtrl(filterSrv,
                             transactions) {
        var vm = this;

        vm.transactions = transactions.data;
        vm.filter = {};

        init();

        ////////////

        function init() {
            var from = new Date();
            from.setDate(from.getDate() - 30);

            vm.filter = {
                date: {
                    from: from,
                    to: new Date(),
                    description: ''
                },
                fn: filterFn
            };
        }

        function filterFn(item) {
            return (
                filterSrv.dateInterval(vm.filter.date, item.date) &&
                filterSrv.substring(vm.filter.description, item.description)
            );
        }

    }

})();

