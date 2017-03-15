(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_naturetypeDeleteController',Waste_water_naturetypeDeleteController);

    Waste_water_naturetypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Waste_water_naturetype'];

    function Waste_water_naturetypeDeleteController($uibModalInstance, entity, Waste_water_naturetype) {
        var vm = this;

        vm.waste_water_naturetype = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Waste_water_naturetype.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
