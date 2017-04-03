(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectprocessflowstepsDeleteController',ProjectprocessflowstepsDeleteController);

    ProjectprocessflowstepsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectprocessflowsteps'];

    function ProjectprocessflowstepsDeleteController($uibModalInstance, entity, Projectprocessflowsteps) {
        var vm = this;

        vm.projectprocessflowsteps = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectprocessflowsteps.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
