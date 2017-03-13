(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_naturetypeDetailController', Waste_water_naturetypeDetailController);

    Waste_water_naturetypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Waste_water_naturetype'];

    function Waste_water_naturetypeDetailController($scope, $rootScope, $stateParams, previousState, entity, Waste_water_naturetype) {
        var vm = this;

        vm.waste_water_naturetype = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:waste_water_naturetypeUpdate', function(event, result) {
            vm.waste_water_naturetype = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
