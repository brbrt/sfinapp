angular.module( 'sfinapp-web.services.account', [ ])

.service('accountService', function($http) {
    var url = 'http://localhost:8008/sfinapp-server/api/account/';
    
    this.getAll = function() {
        return $http.get(url);
    };
});