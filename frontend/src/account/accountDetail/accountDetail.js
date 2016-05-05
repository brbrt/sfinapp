angular
    .module('sfinapp.account.accountDetail', [
        'ui.router',
        'smart-table',
        'toastr',

        'sfinapp.core',
        'sfinapp.account.accountSrv'
    ])
    .config(accountDetailConfig)
    .controller('accountDetailCtrl', accountDetailCtrl);


function accountDetailConfig($stateProvider) {
    $stateProvider.state('app.account.detail', {
        url: '/:id',
        controller: 'accountDetailCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/account/accountDetail/accountDetail.tpl.html',
        resolve: {
            accountId: ($stateParams) => { return $stateParams.id; },
            isNew: (accountId) => { return accountId === 'new';},
            account: (accountId, isNew, accountSrv) => {
                return isNew ? accountSrv.skeleton() : accountSrv.get(accountId);
            }
        }
    });
}

function accountDetailCtrl($log,
                           toastr,
                           confirmSrv,
                           locationSrv,
                           accountSrv,
                           isNew,
                           account) {
    var vm = this;

    vm.isNew = isNew;
    vm.account = account;

    vm.save = save;
    vm.remove = remove;

    ////////////

    function save() {
        var method = isNew ? accountSrv.create : accountSrv.update;
        method(vm.account).then(saveSuccess, toastr.apiError);
    }

    function saveSuccess() {
        toastr.success('Account is saved.');
        locationSrv.goToUrl('accounts');
    }

    function remove() {
        confirmSrv.confirm('Are you sure you want to delete this account?', callDelete);
    }

    function callDelete() {
        accountSrv.delete(vm.account).then(deleteSuccess, toastr.apiError);
    }

    function deleteSuccess() {
        toastr.success('Account is deleted.');
        locationSrv.goToUrl('accounts');
    }
}
