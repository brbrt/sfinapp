describe('mainCtrl', function() {
    var mainCtrl, $scope;

    beforeEach(module('sfinapp'));

    beforeEach(inject(function($controller, $rootScope) {
        $scope = $rootScope.$new();
        mainCtrl = $controller('mainCtrl', { $scope: $scope });
    }));

    it('exists', inject( function() {
        expect(mainCtrl).toBeTruthy();
    }));
});
