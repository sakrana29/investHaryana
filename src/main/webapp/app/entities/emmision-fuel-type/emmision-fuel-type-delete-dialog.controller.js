(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_fuel_typeDeleteController',Emmision_fuel_typeDeleteController);

    Emmision_fuel_typeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Emmision_fuel_type'];

    function Emmision_fuel_typeDeleteController($uibModalInstance, entity, Emmision_fuel_type) {
        var vm = this;

        vm.emmision_fuel_type = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Emmision_fuel_type.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
