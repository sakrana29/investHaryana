(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WastewaterdetailDeleteController',WastewaterdetailDeleteController);

    WastewaterdetailDeleteController.$inject = ['$uibModalInstance', 'entity', 'Wastewaterdetail'];

    function WastewaterdetailDeleteController($uibModalInstance, entity, Wastewaterdetail) {
        var vm = this;

        vm.wastewaterdetail = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Wastewaterdetail.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
