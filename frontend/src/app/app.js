angular.module( 'sfinapp-web', [
  'templates-app',
  'templates-common',
  'sfinapp-web.home',
  'sfinapp-web.accounts',
  'sfinapp-web.services.account',
  'ui.router.state',
  'ui.router'
])

.constant('constants', {
  apiUrl: 'http://localhost:8008/sfinapp-server/api/'
})

.config( function myAppConfig ( $stateProvider, $urlRouterProvider ) {
  $urlRouterProvider.otherwise( '/home' );
})

.run( function run () {
})

.controller( 'AppCtrl', function AppCtrl ( $scope, $location ) {
  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    if ( angular.isDefined( toState.data.pageTitle ) ) {
      $scope.pageTitle = toState.data.pageTitle + ' | ngBoilerplate' ;
    }
  });
})

;

