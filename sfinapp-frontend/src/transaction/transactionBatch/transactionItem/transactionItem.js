import angular from 'angular';

import tpl from './transactionItem.tpl.html';

angular
    .module('sfinapp.transaction.transactionBatch.transactionItem', [
        'ngSanitize',

        'sfinapp.core.suggestionSrv'
    ])
    .directive('transactionItem', transactionItem);


function transactionItem() {
    return {
        restrict: 'A',
        controller: transactionItemCtrl,
        controllerAs: 'itemVm',
        template: tpl,
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

        itemVm.setTagId = function setTagId(selectedTag) {
            if (!selectedTag) {
                $scope.transaction.tagIds = [];
                return;
            }
            $scope.transaction.tagIds = [selectedTag.id];
        }

    }

}
