import angular from 'angular';

import confirmSrv from './confirmSrv/confirmSrv.js';
import dateUtilSrv from './dateUtilSrv/dateUtilSrv.js';
import locationSrv from './locationSrv/locationSrv.js';
import stateRef from './stateRef/stateRef.js';
import suggestionSrv from './suggestionSrv/suggestionSrv.js';

angular
    .module('sfinapp.core', [
        'sfinapp.core.confirmSrv',
        'sfinapp.core.dateUtilSrv',
        'sfinapp.core.locationSrv',
        'sfinapp.core.stateRef',
        'sfinapp.core.suggestionSrv'
    ]);
