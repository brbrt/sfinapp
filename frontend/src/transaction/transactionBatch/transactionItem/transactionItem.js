angular
    .module('sfinapp.transaction.transactionBatch.transactionItem', [
        'ngSanitize',

        'isteven-multi-select',
        'MassAutoComplete',

        'sfinapp.transaction.transactionSrv'
    ])
    .directive('transactionItem', transactionItem);


function transactionItem() {
    return {
        restrict: 'A',
        controller: transactionItemCtrl,
        controllerAs: 'itemVm',
        templateUrl: 'src/transaction/transactionBatch/transactionItem/transactionItem.tpl.html',
        scope: {
            transaction: '=transactionItem',
            descriptions: '=',
            accounts: '=',
            tags: '='
        }
    };

    function transactionItemCtrl($scope,
                                 transactionSrv) {
        var vm = this;

        vm.suggestDescription = (term) => { return transactionSrv.suggestDescription($scope.descriptions, term); };
    }

}
