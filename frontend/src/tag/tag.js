import angular from 'angular';

import './tagDetail/tagDetail.js';
import './tagSrv/tagSrv.js';

import tpl from './tag.tpl.html';

angular
    .module('sfinapp.tag', [
        'ui.router',
        'smart-table',

        'sfinapp.tag.tagDetail',
        'sfinapp.tag.tagSrv'
    ])
    .config(tagConfig)
    .controller('tagCtrl', tagCtrl);


function tagConfig($stateProvider) {
    $stateProvider
        .state('app.tag', {
            abstract: true,
            template: '<ui-view/>',
            url: '/tags'
        })
        .state('app.tag.list', {
            url: '',
            controller: 'tagCtrl',
            controllerAs: 'vm',
            template: tpl,
            resolve: {
                tags: (tagSrv) => { return tagSrv.getAll(); }
            }
        });
}

function tagCtrl(tags) {
    var vm = this;

    vm.tags = tags;

    init();

    ////////////

    function init() {

    }

}
