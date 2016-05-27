import angular from 'angular';

import './loadingBar/loadingBar.js';

import './main.less';
import './table.less';

angular
    .module('sfinapp.layout', [
        'sfinapp.layout.loadingBar'
    ]);
