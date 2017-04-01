(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WatersupplysourceDetailController', WatersupplysourceDetailController);

    WatersupplysourceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Watersupplysource'];

    function WatersupplysourceDetailController($scope, $rootScope, $stateParams, previousState, entity, Watersupplysource) {
        var vm = this;

        vm.watersupplysource = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:watersupplysourceUpdate', function(event, result) {
            vm.watersupplysource = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
