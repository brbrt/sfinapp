(function () {
    'use strict';

    angular
        .module('sfinapp.core.filterWatch', [

        ])
        .directive('filterWatch', filterWatch);


    function filterWatch() {

        var directive = {
            link: link,
            restrict: 'EA',
            scope: {
                src: '=',
                dest: '=',
                filter: '=',
                fn: '='
            }
        };

        return directive;

        function link(scope, element, attrs) {

            init();

            ////////////////////////

            function init() {
                scope.$watchCollection('src', update);
                scope.$watch('filter', update, true);
            }

            function update() {
                if (!scope.filter || !angular.isFunction(scope.filter.fn)) {
                    return scope.src;
                }
                if (!angular.isArray(scope.src)) {
                    return [];
                }

                var filterFn = scope.filter.fn;
                scope.dest = scope.src.filter(filterFn);
            }
        }
    }

})();

