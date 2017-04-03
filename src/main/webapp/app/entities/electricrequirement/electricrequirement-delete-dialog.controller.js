(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementDeleteController',ElectricrequirementDeleteController);

    ElectricrequirementDeleteController.$inject = ['$uibModalInstance', 'entity', 'Electricrequirement'];

    function ElectricrequirementDeleteController($uibModalInstance, entity, Electricrequirement) {
        var vm = this;

        vm.electricrequirement = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Electricrequirement.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
