import angular from 'angular';

import http from './http/http.js'
import state from './state/state.js'
import toastr from './toastr/toastr.js'

angular
    .module('sfinapp.config', [
        'sfinapp.config.http',
        'sfinapp.config.state',
        'sfinapp.config.toastr'
    ]);
