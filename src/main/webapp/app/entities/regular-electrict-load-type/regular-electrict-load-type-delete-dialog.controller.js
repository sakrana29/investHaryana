(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Regular_electrict_load_typeDeleteController',Regular_electrict_load_typeDeleteController);

    Regular_electrict_load_typeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Regular_electrict_load_type'];

    function Regular_electrict_load_typeDeleteController($uibModalInstance, entity, Regular_electrict_load_type) {
        var vm = this;

        vm.regular_electrict_load_type = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Regular_electrict_load_type.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
