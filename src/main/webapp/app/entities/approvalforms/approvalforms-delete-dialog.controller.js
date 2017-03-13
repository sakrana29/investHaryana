(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ApprovalformsDeleteController',ApprovalformsDeleteController);

    ApprovalformsDeleteController.$inject = ['$uibModalInstance', 'entity', 'Approvalforms'];

    function ApprovalformsDeleteController($uibModalInstance, entity, Approvalforms) {
        var vm = this;

        vm.approvalforms = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Approvalforms.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
