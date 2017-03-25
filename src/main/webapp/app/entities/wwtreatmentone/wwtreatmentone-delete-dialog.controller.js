(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentoneDeleteController',WwtreatmentoneDeleteController);

    WwtreatmentoneDeleteController.$inject = ['$uibModalInstance', 'entity', 'Wwtreatmentone'];

    function WwtreatmentoneDeleteController($uibModalInstance, entity, Wwtreatmentone) {
        var vm = this;

        vm.wwtreatmentone = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Wwtreatmentone.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
