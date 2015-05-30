angular
    .module('sfinapp.layout.loadingBar', [

    ])
    .directive('loadingBar', loadingBar);


function loadingBar() {
    return {
        restrict: 'E',
        templateUrl: 'src/layout/loadingBar/loadingBar.tpl.html'
    };
}
