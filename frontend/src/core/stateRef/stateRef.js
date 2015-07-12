angular
    .module('sfinapp.core.stateRef', [
        'ui.router'
    ])
    .directive('stateRef', stateRef);


function stateRef($log,
                  $timeout,
                  $state,
                  locationSrv) {

    var directive = {
        link: link,
        restrict: 'A',
        require: ['?^uiSrefActive', '?^uiSrefActiveEq'],
    };

    return directive;

    function link(scope, element, attrs, uiSrefActive) {

        var ref;

        init();

        ////////////////////////

        function init() {
            if (element.prop('tagName').toUpperCase() !== 'A') {
                throw new Error('stateRef directive should be used only on "a" (anchor) elements.');
            }

            ref = parseStateRef(attrs.stateRef, $state.current.name);

            if (ref.paramExpr) {
                scope.$watch(ref.paramExpr, update, true);
            }

            update();

            element.bind('click', activateState);
        }

        function parseStateRef(ref, current) {
            var preparsed = ref.match(/^\s*({[^}]*})\s*$/), parsed;
            if (preparsed) {
                ref = current + '(' + preparsed[1] + ')';
            }
            parsed = ref.replace(/\n/g, ' ').match(/^([^(]+?)\s*(\((.*)\))?$/);
            if (!parsed || parsed.length !== 4) {
                throw new Error('Invalid state ref "' + ref + '"');
            }
            return { state: parsed[1], paramExpr: parsed[3] || null };
        }

        function update(params) {
            var stateName = ref.state;

            var newHref = $state.href(stateName, angular.copy(params), { inherit: true });
            attrs.$set('href', newHref);

            var activeDirective = uiSrefActive[1] || uiSrefActive[0];
            if (activeDirective) {
                activeDirective.$$addStateInfo(ref.state, params);
            }
        }

        function activateState(e) {
            var button = e.which || e.button;
            if (button > 1 || e.ctrlKey || e.metaKey || e.shiftKey || element.attr('target')) {
                return;
            }

            e.preventDefault();

            // If we have parameters, need to use the $timeout service.
            $timeout(function() {
                var newPath = attrs.href;
                $log.debug('Changing location to: ' + newPath);
                locationSrv.goToUrl(newPath);
            });
        }
    }
}
