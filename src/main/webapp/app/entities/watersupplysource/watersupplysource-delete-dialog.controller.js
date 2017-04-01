(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WatersupplysourceDeleteController',WatersupplysourceDeleteController);

    WatersupplysourceDeleteController.$inject = ['$uibModalInstance', 'entity', 'Watersupplysource'];

    function WatersupplysourceDeleteController($uibModalInstance, entity, Watersupplysource) {
        var vm = this;

        vm.watersupplysource = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Watersupplysource.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
