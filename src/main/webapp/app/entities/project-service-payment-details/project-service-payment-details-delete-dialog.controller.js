(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServicePaymentDetailsDeleteController',ProjectServicePaymentDetailsDeleteController);

    ProjectServicePaymentDetailsDeleteController.$inject = ['$uibModalInstance', 'entity', 'ProjectServicePaymentDetails'];

    function ProjectServicePaymentDetailsDeleteController($uibModalInstance, entity, ProjectServicePaymentDetails) {
        var vm = this;

        vm.projectServicePaymentDetails = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ProjectServicePaymentDetails.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
