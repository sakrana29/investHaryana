(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectypeDeleteController',ProjectypeDeleteController);

    ProjectypeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectype'];

    function ProjectypeDeleteController($uibModalInstance, entity, Projectype) {
        var vm = this;

        vm.projectype = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectype.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
