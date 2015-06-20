angular
    .module('sfinapp.transaction', [
        'ui.router',
        'smart-table',

        'sfinapp.core',
        'sfinapp.transaction.transactionBatch',
        'sfinapp.transaction.transactionDetail',
        'sfinapp.transaction.transactionSrv'
    ])
    .config(transactionConfig)
    .controller('transactionCtrl', transactionCtrl);


function transactionConfig($stateProvider) {
    $stateProvider.state('transaction', {
        url: '/transactions',
        controller: 'transactionCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/transaction/transaction.tpl.html'
    });
}

function transactionCtrl(toastr,
                         transactionSrv) {
    var vm = this;

    vm.filter = {};
    vm.transactions = [];
    vm.getTransactions = getTransactions;

    init();

    ////////////

    function init() {
        vm.filter = {
            from: moment().subtract(15, 'days').toDate(),
            to: moment().toDate(),
            description: ''
        };

        getTransactions();
    }

    function getTransactions() {
        transactionSrv.getAll(vm.filter).then(
            function success(data) {
                vm.transactions = data;
            },
            toastr.apiError
        );
    }

}
