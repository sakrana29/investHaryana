(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ParticularDeleteController',ParticularDeleteController);

    ParticularDeleteController.$inject = ['$uibModalInstance', 'entity', 'Particular'];

    function ParticularDeleteController($uibModalInstance, entity, Particular) {
        var vm = this;

        vm.particular = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Particular.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
