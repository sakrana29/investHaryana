(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectproductDeleteController',ProjectproductDeleteController);

    ProjectproductDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectproduct'];

    function ProjectproductDeleteController($uibModalInstance, entity, Projectproduct) {
        var vm = this;

        vm.projectproduct = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectproduct.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
