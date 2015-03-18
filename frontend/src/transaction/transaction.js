(function () {
    'use strict';

    angular
        .module('sfinapp.transaction', [
            'ui.router',
            'mm.foundation',
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

    function transactionCtrl($log,
                             $state,
                             $modal,
                             transactionSrv,
                             transactions) {

        var vm = this;

        vm.transactions = transactions.data;
        vm.openAddModal = openAddModal;
        vm.openEditModal = openEditModal;
        vm.deleteItem = deleteItem;

        init();

        ////////////

        function init() {

        }

        function openAddModal() {
            var modalInstance = $modal.open({
                templateUrl: 'src/transaction/transactionDetail/transactionDetail.tpl.html',
                controller: 'transactionDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Add transaction',
                            transaction: transactionSrv.skeleton()
                        };
                    }
                }
            });

            modalInstance.result.then(createItem, angular.noop);
        }

        function openEditModal(item) {
            var modalInstance = $modal.open({
                templateUrl: 'src/transaction/transactionDetail/transactionDetail.tpl.html',
                controller: 'transactionDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Edit transaction',
                            transaction: item
                        };
                    }
                }
            });

            modalInstance.result.then(updateItem);
        }

        function createItem(item) {
            return transactionSrv.create(item).then(serverSuccess, serverError);
        }

        function updateItem(item) {
            return transactionSrv.update(item).then(serverSuccess, serverError);
        }

        function deleteItem(item) {
            return transactionSrv.delete(item).then(serverSuccess, serverError);
        }

        function serverSuccess(resp) {
            $state.reload();
        }

        function serverError(err) {
            $log.error('Transaction error: ', err);
        }
    }

})();

