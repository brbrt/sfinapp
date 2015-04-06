(function () {
    'use strict';

    angular
        .module('sfinapp.core.filterSrv', [

        ])
        .factory('filterSrv', filterSrv);


    function filterSrv() {

        var factory = {
            dateInterval: dateInterval,
            substring: substring
        };

        return factory;

        ////////////

        function dateInterval(expected, actual) {
            if (!expected) {
                return true;
            }

            if (!actual) {
                return false;
            }

            var expStartDate = expected.from;
            var expEndDate = expected.to;
            var t = actual.getTime();

            return (
                (!expStartDate || t >= expStartDate.getTime()) &&
                (!expEndDate || t <= expEndDate.getTime())
            );
        }

        function substring(expected, actual) {
            return !expected || (!!actual && actual.toLowerCase().indexOf(expected.toLowerCase()) > -1);
        }

    }

})();

