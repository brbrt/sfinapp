(function () {
    'use strict';

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
        $stateProvider.state('accounts', {
            url: '/accounts',
            controller: 'accountCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/account/account.tpl.html',
            resolve: {
                accounts: function getAccounts(accountSrv) {
                    return accountSrv.getAll();
                }
            }
        });
    }

    function accountCtrl($state,
                         $log,
                         $modal,
                         accounts,
                         accountSrv) {
        var vm = this;
        vm.accounts = accounts.data;
        vm.openAddModal = openAddModal;
        vm.openEditModal = openEditModal;
        vm.deleteItem = deleteItem;

        init();

        ////////////

        function init() {

        }

        function openAddModal() {
            var modalInstance = $modal.open({
                templateUrl: 'src/account/accountDetail/accountDetail.tpl.html',
                controller: 'accountDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Add account',
                            account: accountSrv.skeleton()
                        };
                    }
                }
            });

            modalInstance.result.then(createAccount, angular.noop);
        }

        function openEditModal(account) {
            var modalInstance = $modal.open({
                templateUrl: 'src/account/accountDetail/accountDetail.tpl.html',
                controller: 'accountDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Edit account',
                            account: account
                        };
                    }
                }
            });

            modalInstance.result.then(updateAccount);
        }

        function createAccount(account) {
            return accountSrv.create(account).then(serverSuccess, serverError);
        }

        function updateAccount(account) {
            return accountSrv.update(account).then(serverSuccess, serverError);
        }

        function deleteItem(account) {
            return accountSrv.delete(account).then(serverSuccess, serverError);
        }

        function serverSuccess(resp) {
            $state.reload();
        }

        function serverError(err) {
            $log.error('Account error: ', err);
        }

    }

})();

