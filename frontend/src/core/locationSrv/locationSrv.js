angular
    .module('sfinapp.core.locationSrv', [
        'ui.router'
    ])
    .factory('locationSrv', locationSrv);


function locationSrv($location,
                     $state) {

    return {
        reload: reload,
        goToUrl: goToUrl
    };

    ////////////

    function reload() {
        return $state.reload();
    }

    function goToUrl(url) {
        url = url.replace('#/', '');
        $location.url(url);
    }

}
