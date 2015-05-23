angular
    .module('sfinapp.config.http', [

    ])
    .config(httpConfig);


function httpConfig($httpProvider) {
    $httpProvider.defaults.transformResponse.push(parseResponseDates);
}



function parseResponseDates(responseData) {
    convertDateStringsToDates(responseData);
    return responseData;
}


var dateRegex = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})/;

function convertDateStringsToDates(input) {
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
            var milliseconds = Date.parse(match[0]);
            if (!isNaN(milliseconds)) {
                input[key] = new Date(milliseconds);
            }
        } else if (typeof value === 'object') {
            // Recurse into object
            convertDateStringsToDates(value);
        }
    }
}
