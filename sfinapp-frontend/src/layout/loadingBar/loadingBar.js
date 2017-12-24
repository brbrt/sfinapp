import angular from 'angular';

import tpl from './loadingBar.tpl.html';
import style from './loadingBar.less';

angular
    .module('sfinapp.layout.loadingBar', [

    ])
    .directive('loadingBar', loadingBar);


function loadingBar() {
    return {
        restrict: 'E',
        template: tpl
    };
}
