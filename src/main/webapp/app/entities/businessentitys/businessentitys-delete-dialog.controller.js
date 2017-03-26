(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentitysDeleteController',BusinessentitysDeleteController);

    BusinessentitysDeleteController.$inject = ['$uibModalInstance', 'entity', 'Businessentitys'];

    function BusinessentitysDeleteController($uibModalInstance, entity, Businessentitys) {
        var vm = this;

        vm.businessentitys = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Businessentitys.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
