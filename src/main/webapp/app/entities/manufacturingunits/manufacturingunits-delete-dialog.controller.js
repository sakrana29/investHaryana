(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingunitsDeleteController',ManufacturingunitsDeleteController);

    ManufacturingunitsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Manufacturingunits'];

    function ManufacturingunitsDeleteController($uibModalInstance, entity, Manufacturingunits) {
        var vm = this;

        vm.manufacturingunits = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Manufacturingunits.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
