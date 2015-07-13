angular
    .module('sfinapp.home', [
        'ui.router',

        'sfinapp.core',

        'sfinapp.home.apiCheckerSrv'
    ])
    .config(homeConfig)
    .controller('homeCtrl', homeCtrl);


function homeConfig($stateProvider) {
    $stateProvider.state('app.home', {
        url: '/home',
        controller: 'homeCtrl',
        controllerAs: 'vm'
    });
}

function homeCtrl(toastr,
                  locationSrv,
                  apiCheckerSrv) {
    var vm = this;

    init();

    ////////////

    function init() {
        apiCheckerSrv.check().then(
            function success() {
                locationSrv.goToUrl('transactions');
            },
            toastr.apiError
        );
    }
}
