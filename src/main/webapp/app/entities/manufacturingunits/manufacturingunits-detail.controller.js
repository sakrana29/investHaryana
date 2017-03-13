(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingunitsDetailController', ManufacturingunitsDetailController);

    ManufacturingunitsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manufacturingunits'];

    function ManufacturingunitsDetailController($scope, $rootScope, $stateParams, previousState, entity, Manufacturingunits) {
        var vm = this;

        vm.manufacturingunits = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:manufacturingunitsUpdate', function(event, result) {
            vm.manufacturingunits = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
