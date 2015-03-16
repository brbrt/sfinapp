(function () {
    'use strict';

    angular
        .module('sfinapp.tag.tagDetail', [
            'ui.router',
            'smart-table',

            'sfinapp.tag.tagSrv'
        ])
        .controller('tagDetailCtrl', tagDetailCtrl);


    function tagDetailCtrl($modalInstance,
                           modalData) {
        var vm = this;

        vm.title = modalData.title;
        vm.tag = modalData.tag;
        vm.ok = ok;
        vm.cancel = cancel;

        ////////////

        function ok() {
            $modalInstance.close(vm.tag);
        }

        function cancel() {
            $modalInstance.dismiss();
        }
    }

})();

