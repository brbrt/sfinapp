angular
    .module('sfinapp.core.locationSrv', [

    ])
    .factory('locationSrv', locationSrv);


function locationSrv($location,
                     $state) {

    var factory = {
        reload: reload,
        goToUrl: goToUrl
    };

    return factory;

    ////////////

    function reload() {
        return $state.reload();
    }

    function goToUrl(url) {
        url = url.replace('#/', '');
        $location.url(url);
    }

}
