(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ServiceFormFieldDeleteController',ServiceFormFieldDeleteController);

    ServiceFormFieldDeleteController.$inject = ['$uibModalInstance', 'entity', 'ServiceFormField'];

    function ServiceFormFieldDeleteController($uibModalInstance, entity, ServiceFormField) {
        var vm = this;

        vm.serviceFormField = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            ServiceFormField.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
