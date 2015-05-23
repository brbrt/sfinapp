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
    $stateProvider.state('transaction-detail', {
        url: '/transactions/:id',
        controller: 'transactionDetailCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/transaction/transactionDetail/transactionDetail.tpl.html',
        resolve: {
            accounts: function getAccounts(accountSrv) {
                return accountSrv.getAll();
            },
            tags: function getTags(tagSrv) {
                return tagSrv.getAll();
            },
            transactionId: function getTransactionId($stateParams) {
                return $stateParams.id;
            },
            isNew: function isNew(transactionId) {
                return transactionId === 'new';
            },
            transaction: function getTransaction(transactionId, isNew, transactionSrv) {
                return isNew ? transactionSrv.skeleton() : transactionSrv.get(transactionId);
            }
        }
    });
}

function transactionDetailCtrl($log,
                               $state,
                               toastr,
                               confirmSrv,
                               transactionSrv,
                               accounts,
                               isNew,
                               tags,
                               transaction) {
    var vm = this;

    vm.accounts = accounts;
    vm.tags = tags;
    vm.isNew = isNew;
    vm.transaction = transaction;

    vm.save = save;
    vm.delete = delete_;

    ////////////

    function save() {
        var method = isNew ? transactionSrv.create : transactionSrv.update;
        method(vm.transaction).then(saveSuccess, toastr.apiError);
    }

    function saveSuccess() {
        if ((isNew && vm.createAnother) || !isNew) {
            $state.reload();
        } else {
            $state.go('transaction');
        }

        toastr.success('Transaction is saved.');
    }

    function delete_() {
        confirmSrv.confirm('Are you sure you want to delete this transaction?', callDelete);
    }

    function callDelete() {
        transactionSrv.delete(vm.transaction).then(deleteSuccess, toastr.apiError);
    }

    function deleteSuccess() {
        toastr.success('Transaction is deleted.');
        $state.go('transaction');
    }
}
