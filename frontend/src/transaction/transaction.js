import angular from 'angular';
import moment from 'moment';

import './transactionBatch/transactionBatch.js';
import './transactionDetail/transactionDetail.js';
import './transactionSrv/transactionSrv.js';

import tpl from './transaction.tpl.html';
import './transaction.less';

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
    $stateProvider
        .state('app.transaction', {
            abstract: true,
            template: '<ui-view/>',
            url: '/transactions'
        })
        .state('app.transaction.list', {
            url: '',
            controller: 'transactionCtrl',
            controllerAs: 'vm',
            template: tpl,
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
