(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentserviceDeleteController',DepartmentserviceDeleteController);

    DepartmentserviceDeleteController.$inject = ['$uibModalInstance', 'entity', 'Departmentservice'];

    function DepartmentserviceDeleteController($uibModalInstance, entity, Departmentservice) {
        var vm = this;

        vm.departmentservice = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Departmentservice.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
