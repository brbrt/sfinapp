angular
    .module('sfinapp.transaction.transactionBatch', [
        'ngSanitize',

        'ui.router',
        'isteven-multi-select',
        'MassAutoComplete',
        'toastr',

        'sfinapp.core',
        'sfinapp.account.accountSrv',
        'sfinapp.tag.tagSrv',
        'sfinapp.transaction.transactionSrv'
    ])
    .config(transactionBatchConfig)
    .controller('transactionBatchCtrl', transactionBatchCtrl);


function transactionBatchConfig($stateProvider) {
    $stateProvider.state('transaction-batch', {
        url: '/transactions/batch',
        controller: 'transactionBatchCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/transaction/transactionBatch/transactionBatch.tpl.html',
        resolve: {
            accounts: (accountSrv) => { return accountSrv.getAll(); },
            descriptions: (transactionSrv) => { return transactionSrv.getAllDescriptions(); },
            tags: (tagSrv) => { return tagSrv.getAll();},
            transactionSkeleton: (transactionSrv) => { return transactionSrv.skeleton(); }
        }
    });
}

function transactionBatchCtrl($log,
                              $state,
                              toastr,
                              transactionSrv,
                              accounts,
                              descriptions,
                              tags,
                              transactionSkeleton) {
    var vm = this;

    vm.accounts = accounts;
    vm.tagOptions = [];
    vm.transactions = [];

    vm.extendTransactionList = extendTransactionList;
    vm.save = save;
    vm.suggestDescription = (term) => { return transactionSrv.suggestDescription(descriptions, term); };

    init();

    ////////////

    function init() {
        extendTransactionList(10);
    }

    function extendTransactionList(count) {
        for (var i = 0; i < count; i++) {
            vm.tagOptions.push(angular.copy(tags));
            vm.transactions.push(angular.copy(transactionSkeleton));
        }
    }

    function save() {
        var data = vm.transactions.map(mapTags);

        var filtered = data.filter(providedInput);
        if (filtered.length === 0) {
            toastr.info('Nothing to save.');
            return;
        }

        transactionSrv.createBatch(filtered).then(saveSuccess, toastr.apiError);
    }

    function providedInput(tr) {
        return !angular.equals(tr, transactionSkeleton);
    }

    function mapTags(orig) {
        var tr = angular.copy(orig);
        tr.tagIds = orig.tags.map(function(tag) {
            return tag.id;
        });
        delete tr.tags;
        return tr;
    }

    function saveSuccess() {
        $state.go('transaction');
        toastr.success('Transactions are saved.');
    }

}
