(function () {
    'use strict';

    angular
        .module('sfinapp.tag.tagDetail', [
            'ui.router',
            'smart-table',

            'sfinapp.core',
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
                           confirmSrv,
                           tagSrv,
                           isNew,
                           tag) {
        var vm = this;

        vm.isNew = isNew;
        vm.tag = tag;

        vm.save = save;
        vm.delete = delete_;

        ////////////

        function save() {
            var method = isNew ? tagSrv.create : tagSrv.update;
            method(vm.tag).then(saveSuccess, toastr.apiError);
        }

        function saveSuccess() {
            if ((isNew && vm.createAnother) || !isNew) {
                $state.reload();
            } else {
                $state.go('tag');
            }

            toastr.success('Tag is saved.');
        }

        function delete_() {
            confirmSrv.confirm('Are you sure you want to delete this tag?', callDelete);
        }

        function callDelete() {
            tagSrv.delete(vm.tag).then(deleteSuccess, toastr.apiError);
        }

        function deleteSuccess() {
            toastr.success('Tag is deleted.');
            $state.go('tag');
        }
    }

})();

