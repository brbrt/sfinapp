(function () {
    'use strict';

    angular
        .module('sfinapp.tag', [
            'ui.router',
            'mm.foundation',
            'smart-table',

            'sfinapp.tag.tagDetail',
            'sfinapp.tag.tagSrv'
        ])
        .config(tagConfig)
        .controller('tagCtrl', tagCtrl);


    function tagConfig($stateProvider) {
        $stateProvider.state('tag', {
            url: '/tags',
            controller: 'tagCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/tag/tag.tpl.html',
            resolve: {
                tags: function getTags(tagSrv) {
                    return tagSrv.getAll();
                }
            }
        });
    }

    function tagCtrl($log,
                     $state,
                     $modal,
                     tagSrv,
                     tags) {

        var vm = this;

        vm.tags = tags.data;
        vm.openAddModal = openAddModal;
        vm.openEditModal = openEditModal;
        vm.deleteItem = deleteItem;

        init();

        ////////////

        function init() {

        }

        function openAddModal() {
            var modalInstance = $modal.open({
                templateUrl: 'src/tag/tagDetail/tagDetail.tpl.html',
                controller: 'tagDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Add tag',
                            tag: tagSrv.skeleton()
                        };
                    }
                }
            });

            modalInstance.result.then(createTag, angular.noop);
        }

        function openEditModal(tag) {
            var modalInstance = $modal.open({
                templateUrl: 'src/tag/tagDetail/tagDetail.tpl.html',
                controller: 'tagDetailCtrl as vm',
                resolve: {
                    modalData: function getModalData() {
                        return {
                            title: 'Edit tag',
                            tag: tag
                        };
                    }
                }
            });

            modalInstance.result.then(updateTag);
        }

        function createTag(newTag) {
            return tagSrv.create(newTag).then(serverSuccess, serverError);
        }

        function updateTag(editedTag) {
            return tagSrv.update(editedTag).then(serverSuccess, serverError);
        }

        function deleteItem(tag) {
            return tagSrv.delete(tag).then(serverSuccess, serverError);
        }

        function serverSuccess(resp) {
            $state.reload();
        }

        function serverError(err) {
            $log.error('Tag error: ', err);
        }
    }

})();

