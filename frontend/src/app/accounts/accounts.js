angular.module( 'sfinapp-web.accounts', [
  'ui.router.state',
  'ui.bootstrap',
  'sfinapp-web.services.account'
])

.config(function config( $stateProvider ) {
  $stateProvider.state( 'accounts', {
    url: '/accounts',
    views: {
      "main": {
        controller: 'AccountCtrl',
        templateUrl: 'accounts/accounts.tpl.html'
      }
    },
    data:{ pageTitle: 'Accounts' }
  });
})

.controller( 'AccountCtrl', function AccountCtrl( $scope, accountService ) {
    accountService.getAll().success(function(data) {
        $scope.allAccounts = data;
        console.log(data);
    });
})

;
