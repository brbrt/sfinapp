import angular from 'angular';

import './transactionItem/transactionItem.js';

import tpl from './transactionBatch.tpl.html';
import './transactionBatch.less';

angular
    .module('sfinapp.transaction.transactionBatch', [
        'ui.router',
        'toastr',

        'sfinapp.core',
        'sfinapp.account.accountSrv',
        'sfinapp.tag.tagSrv',
        'sfinapp.transaction.transactionSrv',

        'sfinapp.transaction.transactionBatch.transactionItem'
    ])
    .config(transactionBatchConfig)
    .controller('transactionBatchCtrl', transactionBatchCtrl);


function transactionBatchConfig($stateProvider) {
    $stateProvider.state('app.transaction.batch', {
        url: '/batch',
        controller: 'transactionBatchCtrl',
        controllerAs: 'vm',
        template: tpl,
        resolve: {
            accounts: (accountSrv) => { return accountSrv.getAll(); },
            descriptions: (transactionSrv) => { return transactionSrv.getAllDescriptions(); },
            tags: (tagSrv) => { return tagSrv.getAll();},
            transactionSkeleton: (transactionSrv) => { return transactionSrv.skeleton(); }
        }
    });
}

function transactionBatchCtrl($log,
                              toastr,
                              locationSrv,
                              transactionSrv,
                              accounts,
                              descriptions,
                              tags,
                              transactionSkeleton) {
    var vm = this;

    vm.accounts = accounts;
    vm.descriptions = descriptions;
    vm.tags = tags;
    vm.transactions = [];

    vm.extendTransactionList = extendTransactionList;
    vm.save = save;

    init();

    ////////////

    function init() {
        extendTransactionList(4);
    }

    function extendTransactionList(count) {
        for (var i = 0; i < count; i++) {
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
        return angular.copy(orig);
    }

    function saveSuccess() {
        toastr.success('Transactions are saved.');
        locationSrv.goToUrl('transactions');
    }

}
