(function () {
    'use strict';

    angular
        .module('sfinapp.tag.tagDetail', [
            'ui.router',
            'smart-table',

            'sfinapp.tag.tagSrv'
        ])
        .config(tagDetailConfig)
        .controller('tagDetailCtrl', tagDetailCtrl);


    function tagDetailConfig($stateProvider) {
        $stateProvider.state('tag-detail', {
            url: '/tags/:id',
            controller: 'tagDetailCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/tag/tagDetail/tagDetail.tpl.html',
            resolve: {
                tagId: function getTagId($stateParams) {
                    return $stateParams.id;
                },
                isNew: function isNew(tagId) {
                    return tagId === 'new';
                },
                tag: function getTag(tagId, isNew, tagSrv) {
                    return isNew ? tagSrv.skeleton() : tagSrv.get(tagId);
                }
            }
        });
    }

    function tagDetailCtrl($state,
                           $log,
                           toastr,
                           tagSrv,
                           isNew,
                           tag) {
        var vm = this;

        vm.isNew = isNew;
        vm.tag = tag.data;

        vm.save = save;
        vm.delete = delete_;

        ////////////

        function save() {
            var method = isNew ? tagSrv.create : tagSrv.update;
            method(vm.tag).then(serverSuccess, serverError);
        }

        function delete_() {
            tagSrv.delete(vm.tag).then(
                function success() {
                    toastr.success('Tag is deleted.');
                    $state.go('tag');
                },
                serverError
            );
        }

        function serverSuccess() {
            if ((isNew && vm.createAnother) || !isNew) {
                $state.reload();
            } else {
                $state.go('tag');
            }

            toastr.success('Tag is saved.');
        }

        function serverError(err) {
            $log.error('Tag save error: ', err);
            toastr.error('Server error.');
        }
    }

})();

