(function () {
    'use strict';

    angular
        .module('sfinapp.transaction.transactionDetail', [
            'ui.router',
            'toastr',

            'sfinapp.account.accountSrv',
            'sfinapp.tag.tagSrv',
            'sfinapp.transaction.transactionSrv'
        ])
        .config(transactionDetailConfig)
        .controller('transactionDetailCtrl', transactionDetailCtrl);


    function transactionDetailConfig($stateProvider) {
        $stateProvider.state('transaction-detail', {
            url: '/transactions/:id',
            controller: 'transactionDetailCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/transaction/transactionDetail/transactionDetail.tpl.html',
            resolve: {
                accounts: function getAccounts(accountSrv) {
                    return accountSrv.getAll();
                },
                tags: function getTags(tagSrv) {
                    return tagSrv.getAll();
                },
                transactionId: function getTransactionId($stateParams) {
                    return $stateParams.id;
                },
                isNew: function isNew(transactionId) {
                    return transactionId === 'new';
                },
                transaction: function getTransactions(transactionId, isNew, transactionSrv) {
                    return isNew ? transactionSrv.skeleton() : transactionSrv.get(transactionId);
                }
            }
        });
    }

    function transactionDetailCtrl($log,
                                   $state,
                                   toastr,
                                   transactionSrv,
                                   accounts,
                                   isNew,
                                   tags,
                                   transaction) {

        var vm = this;

        vm.accounts = accounts.data;
        vm.tags = tags.data;
        vm.isNew = isNew;
        vm.transaction = transaction.data;

        vm.save = save;
        vm.delete = delete_;

        ////////////

        function save() {
            var method = isNew ? transactionSrv.create : transactionSrv.update;
            method(vm.transaction).then(serverSuccess, serverError);
        }

        function delete_() {
            transactionSrv.delete(vm.transaction).then(
                function success() {
                    toastr.success('Transaction is deleted.');
                    $state.go('transaction');
                },
                serverError
            );
        }

        function serverSuccess() {
            if ((isNew && vm.createAnother) || !isNew) {
                $state.reload();
            } else {
                $state.go('transaction');
            }

            toastr.success('Transaction is saved.');
        }

        function serverError(err) {
            $log.error('Transaction save error: ', err);
            toastr.error('Server error.');
        }

    }

})();

