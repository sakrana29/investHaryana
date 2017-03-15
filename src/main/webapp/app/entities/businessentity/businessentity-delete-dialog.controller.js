(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentityDeleteController',BusinessentityDeleteController);

    BusinessentityDeleteController.$inject = ['$uibModalInstance', 'entity', 'Businessentity'];

    function BusinessentityDeleteController($uibModalInstance, entity, Businessentity) {
        var vm = this;

        vm.businessentity = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Businessentity.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
