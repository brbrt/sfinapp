angular.module( 'sfinapp-web.accounts', [
  'ui.router.state',
  'ui.bootstrap'
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

.controller( 'AccountCtrl', function AccountCtrl( $scope ) {

})

;
