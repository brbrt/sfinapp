angular
    .module('sfinapp', [
        // AngularJS modules.
        'ngAnimate',
        'ngSanitize',

        // External modules.
        'ui.router',
        'isteven-multi-select',
        'MassAutoComplete',
        'mm.foundation',
        'smart-table',
        'toastr',

        // Internal modules.
        'sfinapp.config',
        'sfinapp.core',
        'sfinapp.layout',
        'sfinapp.templates', // Created during build.

        'sfinapp.account',
        'sfinapp.tag',
        'sfinapp.transaction'
    ])
    .controller('mainCtrl', mainCtrl);


function mainCtrl($rootScope) {
    var vm = this;

    vm.isLoading = false;

    init();

    ////////////

    function init() {
        $rootScope.$on('http.start', () => { vm.isLoading = true; });
        $rootScope.$on('http.stop', () => { vm.isLoading = false; });
    }

}
