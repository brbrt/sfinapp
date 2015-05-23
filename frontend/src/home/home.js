angular
    .module('sfinapp.home', [
        'ui.router'
    ])
    .config(homeConfig)
    .controller('homeCtrl', homeCtrl);


function homeConfig($stateProvider) {
    $stateProvider.state('home', {
        url: '/home',
        controller: 'homeCtrl',
        controllerAs: 'vm',
        templateUrl: 'src/home/home.tpl.html'
    });
}

function homeCtrl() {
    var vm = this;

    vm.title = 'Sfinapp';

}
