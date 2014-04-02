angular.module( 'sfinapp-web.services.account', [ ])

.service('accountService', function($http, constants) {
    var url = constants.apiUrl + 'account';
    
    this.getAll = function() {
        return $http.get(url);
    };
});