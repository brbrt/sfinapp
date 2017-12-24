import angular from 'angular';

import tpl from './transactionDetail.tpl.html';

angular
    .module('sfinapp.transaction.transactionDetail', [
        'ui.router',
        'toastr',

        'sfinapp.core',
        'sfinapp.account.accountSrv',
        'sfinapp.tag.tagSrv',
        'sfinapp.transaction.transactionSrv'
    ])
    .config(transactionDetailConfig)
    .controller('transactionDetailCtrl', transactionDetailCtrl);


function transactionDetailConfig($stateProvider) {
    $stateProvider.state('app.transaction.detail', {
        url: '/:id',
        controller: 'transactionDetailCtrl',
        controllerAs: 'vm',
        template: tpl,
        resolve: {
            accounts: (accountSrv) => { return accountSrv.getAll(); },
            descriptions: (transactionSrv) => { return transactionSrv.getAllDescriptions(); },
            tags: (tagSrv) => { return tagSrv.getAll();},
            transactionId: ($stateParams) => { return $stateParams.id; },
            isNew: (transactionId) => { return transactionId === 'new'; },
            transaction: (transactionId, isNew, transactionSrv) => {
                return isNew ? transactionSrv.skeleton() : transactionSrv.get(transactionId);
            }
        }
    });
}

function transactionDetailCtrl($log,
                               toastr,
                               confirmSrv,
                               locationSrv,
                               transactionSrv,
                               suggestionSrv,
                               accounts,
                               descriptions,
                               isNew,
                               tags,
                               transaction) {
    var vm = this;

    vm.accounts = accounts;
    vm.tags = tags;
    vm.isNew = isNew;
    vm.transaction = transaction;

    vm.save = save;
    vm.remove = remove;

    vm.suggestDescription = (searchTerm) => { return suggestionSrv.suggest(descriptions, searchTerm); };

    vm.suggestTag = (searchTerm) => { return suggestionSrv.suggest(tags, searchTerm, t => t.name); };
    vm.selectedTag = null;

    init();

    ////////////

    function init() {
        if (isNew) {
            return;
        }

        // Populate tag autocomplete with the existing tag object.
        vm.selectedTag = tags.filter(t => t.id === transaction.tagId)[0];
    }

    function save() {
        var method = isNew ? transactionSrv.create : transactionSrv.update;

        vm.transaction.tagId = vm.selectedTag.id;

        method(vm.transaction).then(saveSuccess, toastr.apiError);
    }

    function saveSuccess() {
        toastr.success('Transaction is saved.');
        locationSrv.goToUrl('transactions');
    }

    function remove() {
        confirmSrv.confirm('Are you sure you want to delete this transaction?', callRemove);
    }

    function callRemove() {
        transactionSrv.remove(vm.transaction).then(removeSuccess, toastr.apiError);
    }

    function removeSuccess() {
        toastr.success('Transaction is deleted.');
        locationSrv.goToUrl('transactions');
    }

}
