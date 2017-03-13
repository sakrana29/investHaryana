(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandusezoneclassificationDeleteController',LandusezoneclassificationDeleteController);

    LandusezoneclassificationDeleteController.$inject = ['$uibModalInstance', 'entity', 'Landusezoneclassification'];

    function LandusezoneclassificationDeleteController($uibModalInstance, entity, Landusezoneclassification) {
        var vm = this;

        vm.landusezoneclassification = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Landusezoneclassification.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
