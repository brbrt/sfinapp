angular
    .module('sfinapp.core.dateUtilSrv', [

    ])
    .provider('dateUtilSrv', dateUtilSrvProvider);


function dateUtilSrvProvider() {
    var dateRegex = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})(\.\d{3})?/;

    this.$get = () => { return new DateUtilSrv(); };


    function DateUtilSrv() {
        return {
            parseDate: parseDate,
            parseDates: parseDates
        };

        function parseDate(str) {
            var milliseconds = Date.parse(str);
            if (isNaN(milliseconds)) {
                return null;
            }
            return new Date(milliseconds);
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
                    // Recurse into object
                    parseDates(value);
                }
            }

            return input;
        }
    }
}
