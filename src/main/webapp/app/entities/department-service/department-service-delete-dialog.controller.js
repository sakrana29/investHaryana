(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceDeleteController',DepartmentServiceDeleteController);

    DepartmentServiceDeleteController.$inject = ['$uibModalInstance', 'entity', 'DepartmentService'];

    function DepartmentServiceDeleteController($uibModalInstance, entity, DepartmentService) {
        var vm = this;

        vm.departmentService = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DepartmentService.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
