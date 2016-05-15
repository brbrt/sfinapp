angular
    .module('sfinapp.transaction.transactionBatch.transactionItem', [
        'ngSanitize',

        'isteven-multi-select',
        'MassAutoComplete',

        'sfinapp.core.suggestionSrv'
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
                                 suggestionSrv) {
        var itemVm = this;

        itemVm.suggestDescription = (searchTerm) => { return suggestionSrv.suggest($scope.descriptions, searchTerm); };

        itemVm.suggestTag = (searchTerm) => { return suggestionSrv.suggest($scope.tags, searchTerm, t => t.name); };
        itemVm.selectedTag = null;

        itemVm.setTagId = (selectedTag) => { $scope.transaction.tagIds = [selectedTag.id]; }

    }

}
