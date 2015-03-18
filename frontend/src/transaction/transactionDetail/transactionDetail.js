(function () {
    'use strict';

    angular
        .module('sfinapp.transaction.transactionDetail', [
            'ui.router',
            'smart-table',

            'sfinapp.transaction.transactionSrv'
        ])
        .controller('transactionDetailCtrl', transactionDetailCtrl);


    function transactionDetailCtrl($modalInstance,
                                   modalData) {
        var vm = this;

        vm.title = modalData.title;
        vm.transaction = modalData.transaction;
        vm.ok = ok;
        vm.cancel = cancel;

        ////////////

        function ok() {
            $modalInstance.close(vm.transaction);
        }

        function cancel() {
            $modalInstance.dismiss();
        }
    }

})();

