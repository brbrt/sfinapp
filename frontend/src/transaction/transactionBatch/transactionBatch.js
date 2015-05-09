(function () {
    'use strict';

    angular
        .module('sfinapp.transaction.transactionBatch', [
            'ui.router',
            'toastr',

            'sfinapp.core',
            'sfinapp.account.accountSrv',
            'sfinapp.tag.tagSrv',
            'sfinapp.transaction.transactionSrv'
        ])
        .config(transactionBatchConfig)
        .controller('transactionBatchCtrl', transactionBatchCtrl);


    function transactionBatchConfig($stateProvider) {
        $stateProvider.state('transaction-batch', {
            url: '/transactions/batch',
            controller: 'transactionBatchCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/transaction/transactionBatch/transactionBatch.tpl.html',
            resolve: {
                accounts: function getAccounts(accountSrv) {
                    return accountSrv.getAll();
                },
                tags: function getTags(tagSrv) {
                    return tagSrv.getAll();
                },
                transactionSkeleton: function getTransactionSkeleton(transactionSrv) {
                    return transactionSrv.skeleton();
                }
            }
        });
    }

    function transactionBatchCtrl($log,
                                  $state,
                                  toastr,
                                  transactionSrv,
                                  accounts,
                                  tags,
                                  transactionSkeleton) {
        var vm = this;

        vm.accounts = accounts;
        vm.tags = tags;
        vm.transactions = [];

        vm.extendTransactionList = extendTransactionList;
        vm.save = save;

        init();

        ////////////

        function init() {
            extendTransactionList(10);
        }

        function extendTransactionList(count) {
            for (var i = 0; i < count; i++) {
                vm.transactions.push(angular.copy(transactionSkeleton));
            }
        }

        function save() {
            var filtered = vm.transactions.filter(providedInput);
            if (filtered.length === 0) {
                return;
            }

            transactionSrv.createBatch(filtered).then(saveSuccess, toastr.apiError);
        }

        function providedInput(tr) {
            return tr.description && tr.description.length > 0;
        }

        function saveSuccess() {
            $state.go('transaction');
            toastr.success('Transactions are saved.');
        }

    }

})();

