(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserroleDeleteController',UserroleDeleteController);

    UserroleDeleteController.$inject = ['$uibModalInstance', 'entity', 'Userrole'];

    function UserroleDeleteController($uibModalInstance, entity, Userrole) {
        var vm = this;

        vm.userrole = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Userrole.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
