(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FiletestingDeleteController',FiletestingDeleteController);

    FiletestingDeleteController.$inject = ['$uibModalInstance', 'entity', 'Filetesting'];

    function FiletestingDeleteController($uibModalInstance, entity, Filetesting) {
        var vm = this;

        vm.filetesting = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Filetesting.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
