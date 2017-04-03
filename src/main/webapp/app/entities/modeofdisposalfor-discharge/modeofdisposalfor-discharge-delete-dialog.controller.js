(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Modeofdisposalfor_dischargeDeleteController',Modeofdisposalfor_dischargeDeleteController);

    Modeofdisposalfor_dischargeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Modeofdisposalfor_discharge'];

    function Modeofdisposalfor_dischargeDeleteController($uibModalInstance, entity, Modeofdisposalfor_discharge) {
        var vm = this;

        vm.modeofdisposalfor_discharge = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Modeofdisposalfor_discharge.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
