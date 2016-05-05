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
    $stateProvider.state('app.tag.detail', {
        url: '/:id',
        controller: 'tagDetailCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/tag/tagDetail/tagDetail.tpl.html',
        resolve: {
            tagId: ($stateParams) => { return $stateParams.id; },
            isNew: (tagId) => { return tagId === 'new'; },
            tag: (tagId, isNew, tagSrv) => {
                return isNew ? tagSrv.skeleton() : tagSrv.get(tagId);
            }
        }
    });
}

function tagDetailCtrl($log,
                       toastr,
                       confirmSrv,
                       locationSrv,
                       tagSrv,
                       isNew,
                       tag) {
    var vm = this;

    vm.isNew = isNew;
    vm.tag = tag;

    vm.save = save;
    vm.remove = remove;

    ////////////

    function save() {
        var method = isNew ? tagSrv.create : tagSrv.update;
        method(vm.tag).then(saveSuccess, toastr.apiError);
    }

    function saveSuccess() {
        toastr.success('Tag is saved.');
        locationSrv.goToUrl('tags');
    }

    function remove() {
        confirmSrv.confirm('Are you sure you want to delete this tag?', callDelete);
    }

    function callDelete() {
        tagSrv.delete(vm.tag).then(deleteSuccess, toastr.apiError);
    }

    function deleteSuccess() {
        toastr.success('Tag is deleted.');
        locationSrv.goToUrl('tags');
    }
}
