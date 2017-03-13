(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailDeleteController',Environment_impactdetailDeleteController);

    Environment_impactdetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Environment_impactdetail'];

    function Environment_impactdetailDeleteController($uibModalInstance, entity, Environment_impactdetail) {
        var vm = this;

        vm.environment_impactdetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Environment_impactdetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
