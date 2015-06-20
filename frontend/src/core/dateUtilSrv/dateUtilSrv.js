angular
    .module('sfinapp.core.dateUtilSrv', [

    ])
    .provider('dateUtilSrv', dateUtilSrvProvider);


function dateUtilSrvProvider() {
    var dateRegex = /^(\d{4})-(\d{2})-(\d{2})/;
    var dateFormat = 'YYYY-MM-DD';

    this.$get = () => { return new DateUtilSrv(); };


    function DateUtilSrv() {
        return {
            formatDate: formatDate,
            formatDates: formatDates,
            parseDate: parseDate,
            parseDates: parseDates
        };

        function formatDate(date) {
            return moment(date).format(dateFormat);
        }

        function formatDates(input) {
            // Ignore things that aren't objects.
            if (typeof input !== 'object') {
                return;
            }

            for (var key in input) {
                if (!input.hasOwnProperty(key)) {
                    continue;
                }

                var value = input[key];

                if (angular.isDate(value)) {
                    input[key] = formatDate(value);
                } else if (typeof value === 'object') {
                    formatDates(value);
                }
            }

            return input;
        }

        function parseDate(str) {
            return moment(str, dateFormat).toDate();
        }

        function parseDates(input) {
            // Ignore things that aren't objects.
            if (typeof input !== 'object') {
                return;
            }

            for (var key in input) {
                if (!input.hasOwnProperty(key)) {
                    continue;
                }

                var value = input[key];
                var match;

                // Check for string properties which look like dates.
                if (typeof value === 'string' && (match = value.match(dateRegex))) {
                    var parsed = parseDate(match[0]);
                    if (parsed) {
                        input[key] = parsed;
                    }
                } else if (typeof value === 'object') {
                    parseDates(value);
                }
            }

            return input;
        }
    }
}
