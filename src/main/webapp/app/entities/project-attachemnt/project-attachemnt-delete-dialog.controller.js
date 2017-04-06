(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectAttachemntDeleteController',ProjectAttachemntDeleteController);

    ProjectAttachemntDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProjectAttachemnt'];

    function ProjectAttachemntDeleteController($uibModalInstance, entity, ProjectAttachemnt) {
        var vm = this;

        vm.projectAttachemnt = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProjectAttachemnt.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
