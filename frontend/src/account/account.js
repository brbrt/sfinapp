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
    $stateProvider.state('account', {
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

function accountCtrl(accounts) {
    var vm = this;

    vm.accounts = accounts;

    init();

    ////////////

    function init() {

    }
}
