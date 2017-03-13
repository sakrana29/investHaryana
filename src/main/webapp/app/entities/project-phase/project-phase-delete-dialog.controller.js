(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_phaseDeleteController',Project_phaseDeleteController);

    Project_phaseDeleteController.$inject = ['$uibModalInstance', 'entity', 'Project_phase'];

    function Project_phaseDeleteController($uibModalInstance, entity, Project_phase) {
        var vm = this;

        vm.project_phase = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Project_phase.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
