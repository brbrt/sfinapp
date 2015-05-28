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

        'sfinapp.account',
        'sfinapp.tag',
        'sfinapp.transaction'
    ])
    .controller('mainCtrl', mainCtrl);


function mainCtrl() {

}
