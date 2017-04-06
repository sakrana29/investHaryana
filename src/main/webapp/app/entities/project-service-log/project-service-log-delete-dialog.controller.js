(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceLogDeleteController',ProjectServiceLogDeleteController);

    ProjectServiceLogDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProjectServiceLog'];

    function ProjectServiceLogDeleteController($uibModalInstance, entity, ProjectServiceLog) {
        var vm = this;

        vm.projectServiceLog = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProjectServiceLog.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
