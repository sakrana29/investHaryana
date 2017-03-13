(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_fuel_typeDetailController', Emmision_fuel_typeDetailController);

    Emmision_fuel_typeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emmision_fuel_type'];

    function Emmision_fuel_typeDetailController($scope, $rootScope, $stateParams, previousState, entity, Emmision_fuel_type) {
        var vm = this;

        vm.emmision_fuel_type = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emmision_fuel_typeUpdate', function(event, result) {
            vm.emmision_fuel_type = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
