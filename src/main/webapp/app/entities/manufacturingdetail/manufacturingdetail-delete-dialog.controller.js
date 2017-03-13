(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailDeleteController',ManufacturingdetailDeleteController);

    ManufacturingdetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Manufacturingdetail'];

    function ManufacturingdetailDeleteController($uibModalInstance, entity, Manufacturingdetail) {
        var vm = this;

        vm.manufacturingdetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Manufacturingdetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
