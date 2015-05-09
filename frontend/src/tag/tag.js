(function () {
    'use strict';

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
        $stateProvider.state('tag', {
            url: '/tags',
            controller: 'tagCtrl',
            controllerAs: 'vm',
            templateUrl: 'src/tag/tag.tpl.html',
            resolve: {
                tags: function getTags(tagSrv) {
                    return tagSrv.getAll();
                }
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

})();

