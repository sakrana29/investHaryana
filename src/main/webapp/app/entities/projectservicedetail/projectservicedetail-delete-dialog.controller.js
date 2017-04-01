(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectservicedetailDeleteController',ProjectservicedetailDeleteController);

    ProjectservicedetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Projectservicedetail'];

    function ProjectservicedetailDeleteController($uibModalInstance, entity, Projectservicedetail) {
        var vm = this;

        vm.projectservicedetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Projectservicedetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
