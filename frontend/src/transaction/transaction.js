angular
    .module('sfinapp.transaction', [
        'ui.router',
        'smart-table',

        'sfinapp.core',
        'sfinapp.tag.tagSrv',
        'sfinapp.transaction.transactionBatch',
        'sfinapp.transaction.transactionDetail',
        'sfinapp.transaction.transactionSrv'
    ])
    .config(transactionConfig)
    .controller('transactionCtrl', transactionCtrl);


function transactionConfig($stateProvider) {
    $stateProvider.state('app.transaction', {
        url: '/transactions',
        controller: 'transactionCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/transaction/transaction.tpl.html',
        resolve: {
            tags: (tagSrv) => { return tagSrv.getAll(); }
        }
    });
}

function transactionCtrl(tags,
                         toastr,
                         transactionSrv) {
    var vm = this;

    vm.tags = [].concat({name: ''}, tags);
    vm.filter = {};
    vm.transactions = [];
    vm.getTransactions = getTransactions;

    init();

    ////////////

    function init() {
        vm.filter = {
            from: moment().subtract(15, 'days').toDate(),
            to: moment().toDate(),
            description: '',
            tag: ''
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
