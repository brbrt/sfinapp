(function () {
    'use strict';

    angular
        .module('sfinapp.account.accountDetail', [
            'ui.router',
            'smart-table',
            'toastr',

            'sfinapp.account.accountSrv'
        ])
        .config(accountDetailConfig)
        .controller('accountDetailCtrl', accountDetailCtrl);


    function accountDetailConfig($stateProvider) {
        $stateProvider.state('account-detail', {
            url: '/accounts/:id',
            controller: 'accountDetailCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/account/accountDetail/accountDetail.tpl.html',
            resolve: {
                accountId: function getAccountId($stateParams) {
                    return $stateParams.id;
                },
                isNew: function isNew(accountId) {
                    return accountId === 'new';
                },
                account: function getAccount(accountId, isNew, accountSrv) {
                    return isNew ? accountSrv.skeleton() : accountSrv.get(accountId);
                }
            }
        });
    }


    function accountDetailCtrl($state,
                               $log,
                               toastr,
                               accountSrv,
                               isNew,
                               account) {

        var vm = this;

        vm.isNew = isNew;
        vm.account = account.data;

        vm.save = save;
        vm.delete = delete_;

        ////////////

        function save() {
            var method = isNew ? accountSrv.create : accountSrv.update;
            method(vm.account).then(serverSuccess, serverError);
        }

        function delete_() {
            accountSrv.delete(vm.account).then(
                function success() {
                    toastr.success('Account is deleted.');
                    $state.go('account');
                },
                serverError
            );
        }

        function serverSuccess() {
            if ((isNew && vm.createAnother) || !isNew) {
                $state.reload();
            } else {
                $state.go('account');
            }

            toastr.success('Account is saved.');
        }

        function serverError(err) {
            $log.error('Account save error: ', err);
            toastr.error('Server error.');
        }
    }

})();

