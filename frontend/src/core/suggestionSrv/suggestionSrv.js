import angular from 'angular';

angular
    .module('sfinapp.core.suggestionSrv', [
    ])
    .factory('suggestionSrv', suggestionSrv);


function suggestionSrv() {

    return {
        suggest: suggest
    };

    ////////////

    function suggest(options, term, labelFn) {
        term = term || '';
        labelFn = labelFn || identityFn;

        var q = term.toLowerCase().trim();
        var result = [];

        // Find first 5 options that start with `term`.
        for (var i = 0; i < options.length && result.length < 5; i++) {
            var label = labelFn(options[i]);
            if (label.toLowerCase().indexOf(q) > -1) {
                result.push(options[i]);
            }
        }

        return result;
    }

    function identityFn(l) {
        return l;
    }

}
