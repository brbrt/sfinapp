(function () {
    'use strict';

    angular
        .module('sfinapp.account.accountDetail', [
            'ui.router',
            'smart-table',

            'sfinapp.tag.tagSrv'
        ])
        .controller('accountDetailCtrl', accountDetailCtrl);


    function accountDetailCtrl($modalInstance,
                               modalData) {
        var vm = this;

        vm.title = modalData.title;
        vm.account = modalData.account;
        vm.ok = ok;
        vm.cancel = cancel;

        ////////////

        function ok() {
            $modalInstance.close(vm.account);
        }

        function cancel() {
            $modalInstance.dismiss();
        }
    }

})();

